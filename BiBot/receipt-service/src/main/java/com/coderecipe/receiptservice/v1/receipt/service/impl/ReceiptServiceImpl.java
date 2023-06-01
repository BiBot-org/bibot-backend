package com.coderecipe.receiptservice.v1.receipt.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.Receipt;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrResult;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.receiptsform.worker.SelectForm;
import com.coderecipe.receiptservice.v1.receipt.service.IReceiptService;
import com.coderecipe.receiptservice.v1.receipt.utils.ReceiptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class ReceiptServiceImpl implements IReceiptService {

    private final SelectForm selectForm;
    @Value("${ocr.api.secret}")
    private String apiSecret;
    @Value("${ocr.api.url}")
    private String apiUrl;
    private final ReceiptUtils receiptUtils;

    public String createReceipt(ReceiptReq.CreateMockReceiptReq req) throws Exception {
        return selectForm.createReceiptImage(req);
    }

    @Override
    public OcrResult.OcrResultInfo getOcrData(OcrReq req) {

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

            URL url = new URL(
                    "https://img.etnews.com/photonews/1707/971120_20170705143932_354_0001.jpg");
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

            log.info("data : " + jsonText);

        } catch (IOException e) {
            log.error(e.toString());
        } catch (JSONException e) {
            log.error(e.toString());
        }

        ObjectMapper mapper = new ObjectMapper();
        if (JsonPath.read(jsonText, "$.images[0].receipt") != null) {
            Receipt receipt = mapper.convertValue(JsonPath.read(jsonText, "$.images[0].receipt"),
                    Receipt.class);
            return OcrResult.OcrResultInfo.of(receipt.getResult());
        } else {
            throw new CustomException(ResCode.BAD_REQUEST);
        }
    }

}