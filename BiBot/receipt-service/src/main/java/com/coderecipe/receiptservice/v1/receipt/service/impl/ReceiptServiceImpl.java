package com.coderecipe.receiptservice.v1.receipt.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.Receipt;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrRes;
import com.coderecipe.receiptservice.v1.receipt.dto.BibotReceiptDTO;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.model.BibotReceipt;
import com.coderecipe.receiptservice.v1.receipt.model.repository.BibotReceiptRepository;
import com.coderecipe.receiptservice.v1.receipt.producer.ReceiptProducer;
import com.coderecipe.receiptservice.v1.receipt.receiptsform.worker.SelectForm;
import com.coderecipe.receiptservice.v1.receipt.service.IReceiptService;
import com.coderecipe.receiptservice.v1.receipt.utils.ReceiptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptServiceImpl implements IReceiptService {

    private final SelectForm selectForm;
    private final ReceiptUtils receiptUtils;
    private final ReceiptProducer receiptProducer;
    private final BibotReceiptRepository bibotReceiptRepository;
    private final ObjectMapper mapper;
    private final Storage storage;
    @Value("${ocr-api-secret}")
    private String apiSecret;
    @Value("${ocr-api-url}")
    private String apiUrl;

    public String createReceiptImage(ReceiptReq.CreateMockReceiptReq req) throws IOException {
        return selectForm.createReceiptImage(req);
    }

    @Override
    public String requestApprovalStart(ReceiptReq.ApprovalStartReq req, MultipartFile file) throws IOException {
        String originamFileName = file.getOriginalFilename().replace(" ", "");
        String imagePath = String.format("OCR_REQUEST/%s/%s", StringUtils.generateDateString(), originamFileName);
        BlobId blobId = BlobId.of("bibot_receipt", imagePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType()).build();
        log.info("image uploaded : " + storage.create(blobInfo, file.getBytes()).toString());
        String storageUrl = StringUtils.generateCloudStorageUrl("bibot_receipt", imagePath);
        receiptProducer.sendMessageOcrStart(OcrReq.OcrStartReq.of(req, storageUrl));
        return storageUrl;
    }

    @Override
    public String requestApprovalEnd(ReceiptReq.ApprovalEndReq req) {
        BibotReceipt receipt = bibotReceiptRepository.findById(req.getReceiptId())
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        receipt.setApproveId(req.getApprovalId());
        bibotReceiptRepository.save(receipt);
        receiptProducer.sendMessageApproveEndPayment(
                new ReceiptReq.ApprovalEndPaymentReq(receipt.getApproveId(), receipt.getPaymentId()));
        return receipt.getReceiptId();
    }

    @Override
    public BibotReceiptDTO getReceipt(String receiptId) {
        BibotReceipt receipt = bibotReceiptRepository.findById(receiptId)
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        return BibotReceiptDTO.of(receipt);
    }

    @Override
    public BibotReceiptDTO getReceiptByApproveId(String approveId) {
        BibotReceipt receipt = bibotReceiptRepository.findByApproveId(approveId)
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        return BibotReceiptDTO.of(receipt);
    }

    @Override
    public boolean ocrStart(OcrReq.OcrStartReq req) {
        BibotReceipt receipt = bibotReceiptRepository.findBibotReceiptByPaymentId(req.getPaymentId())
                .orElse(BibotReceipt.of(req));
        receipt.setImageUrl(req.getImageUrl());
        try {
            OcrRes.OCRResponse ocrResponse = getOcrData(req.getImageUrl());
            String ocrResDateTime = LocalDateTime.parse(
                            ocrResponse.getPaymentInfo().getDate() + ocrResponse.getPaymentInfo().getTime().replace(" ", ""))
                    .format(DateTimeFormatter.ofPattern(StringUtils.DATE_TIME_FORMAT));
            String regTimeStr = req.getRegTime().format(DateTimeFormatter.ofPattern(StringUtils.DATE_TIME_FORMAT));
            if (regTimeStr.equals(ocrResDateTime)) {
                Map res = mapper.convertValue(ocrResponse, Map.class);
                receipt.setOcrResult(res);
                log.info("OCR Result : " + res.toString());
                bibotReceiptRepository.save(receipt);
                OcrReq.RequestAutoApproval request = OcrReq.RequestAutoApproval.of(Integer.parseInt(ocrResponse.getTotalPrice()), receipt.getReceiptId(), req);
                receiptProducer.sendMessageOcrEnd(request);
                return true;
            } else {
                OcrReq.RequestApprovalFail request = OcrReq.RequestApprovalFail.of(req, "영수증의 날짜가 맞지 않습니다.", receipt.getReceiptId());
                bibotReceiptRepository.save(receipt);
                receiptProducer.sendMessageOcrFail(request);
                return false;
            }

        } catch (Exception e) {
            String errorMsg = "OCR 인식에 실패하였습니다. 이미지를 확인 해 주세요.";
            OcrReq.RequestApprovalFail request = OcrReq.RequestApprovalFail.of(req, errorMsg, receipt.getReceiptId());
            bibotReceiptRepository.save(receipt);
            receiptProducer.sendMessageOcrFail(request);
            return false;
        }

    }


    @Override
    public OcrRes.OCRResponse getOcrData(String imageUrl) {

        StringBuffer response = new StringBuffer();
        String jsonText = "";
        try {

            URL requestURL = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setRequestProperty("X-OCR-SECRET", apiSecret);

            URL url = new URL(imageUrl);
            String postParams = receiptUtils.getPostParams(new String(receiptUtils.transformInputStreamToByteArray(url.openStream())));

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            jsonText = response.toString();

        } catch (IOException | JSONException e) {
            log.error(e.toString());
            throw new CustomException(ResCode.OCR_FAIL);
        }

        ObjectMapper mapper = new ObjectMapper();
        if (JsonPath.read(jsonText, "$.images[0].receipt") != null) {
            Receipt receipt = mapper.convertValue(JsonPath.read(jsonText, "$.images[0].receipt"),
                    Receipt.class);
            return OcrRes.OCRResponse.of(receipt.getResult());
        } else {
            throw new CustomException(ResCode.OCR_FAIL);
        }
    }

}