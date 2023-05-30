package com.coderecipe.receiptservice.v1.receipt.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.Receipt;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.utils.ExtractJson;
import com.coderecipe.receiptservice.v1.receipt.dto.ReceiptDTO;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.PaymentReq.CreateMockReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.receiptsform.worker.SelectForm;
import com.coderecipe.receiptservice.v1.receipt.service.IReceiptService;
import com.coderecipe.receiptservice.v1.receipt.worker.ReceiptWorker;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
@Slf4j
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class ReceiptServiceImpl implements IReceiptService {

    private final ReceiptWorker receiptWorker;
    @Value("${ocr-api-secret}")
    private String apiSecret;
    @Value("${ocr-api-url}")
    private String apiUrl;

//    @KafkaListener(topics = "payment_success", groupId = "group-bibot")
//    public ReceiptDTO createReceipt(String kafkaMessage) {
    public String createReceipt(MockPaymentReq req) {
//        log.info("kafka message = {}", kafkaMessage);

//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Map<Object, Object> map = mapper.readValue(kafkaMessage, new TypeReference<>() {
//            });
//            ReceiptReq.CreateMockReceiptReq req = mapper.convertValue(map, ReceiptReq.CreateMockReceiptReq.class);
//            log.info(req.toString());
//            SelectForm selectForm = new SelectForm();
//            selectForm.createReceiptImage(req);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (receiptWorker.createReceiptImage(
            CreateMockReceiptReq.of("결제승인코드 및 이미지 이름", "결제한 카드 회사", req))) {
            return "성공";
        } else {
            throw new CustomException(ResCode.INTERNAL_SERVER_ERROR);
        }

//        return null;
    }

    @Override
    public ExtractJson getOcrData(OcrReq req) {

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
            InputStream inputStream = url.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] base64Bytes = Base64.getEncoder().encode(outputStream.toByteArray());
            inputStream.close(); outputStream.close();

            JSONObject json = new JSONObject();
            JSONArray images = new JSONArray();
            JSONObject image = new JSONObject();

            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            json.put("images", images);

            images.put(image);
            image.put("format", "jpg");
            image.put("data", new String(base64Bytes));
            image.put("name", "demo");

            String postParams = json.toString();

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

        } catch (Exception e) {
            System.out.println(e);
        }

     ObjectMapper mapper = new ObjectMapper();
        ExtractJson ex = new ExtractJson();

        if (JsonPath.read(jsonText, "$.images[0].receipt") != null) {
            Receipt receipt = mapper.convertValue(JsonPath.read(jsonText, "$.images[0].receipt"),
                Receipt.class);
            ex = ExtractJson.of(receipt);
        }

        System.out.println(ex);

        return ex;
    }

}

/*
     ObjectMapper mapper = new ObjectMapper();
        ExtractJson ex = new ExtractJson();

        if (JsonPath.read(response, "$.images[0].receipt") != null) {
            Receipt receipt = mapper.convertValue(JsonPath.read(response, "$.images[0].receipt"),
                Receipt.class);
            ex = ExtractJson.of(receipt);
        }
 */

//image should be public, otherwise, should use data
//            FileInputStream inputStream = new FileInputStream("/kakaru.png");
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            inputStream.close();