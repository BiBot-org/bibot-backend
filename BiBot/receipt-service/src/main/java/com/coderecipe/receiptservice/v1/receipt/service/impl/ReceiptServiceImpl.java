package com.coderecipe.receiptservice.v1.receipt.service.impl;

import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.receiptsform.worker.SelectForm;
import com.coderecipe.receiptservice.v1.receipt.service.ReceiptService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
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
public class ReceiptServiceImpl implements ReceiptService {

    @Value("${ocr-api-secret}")
    private String apiSecret;
    @Value("${ocr-api-url}")
    private String apiUrl;

    @KafkaListener(topics = "payment_success", groupId = "group-bibot")
    public Boolean createReceipt(String kafkaMessage) {
        log.info("kafka message = {}", kafkaMessage);

        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<Object, Object> map = mapper.readValue(kafkaMessage, new TypeReference<>() {
            });
            ReceiptReq.CreateMockReceiptReq req = mapper.convertValue(map, ReceiptReq.CreateMockReceiptReq.class);
            log.info(req.toString());
            SelectForm selectForm = new SelectForm();
            selectForm.createReceiptImage(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public StringBuffer getOcrData(OcrReq req) {

        StringBuffer response = null;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setRequestProperty("X-OCR-SECRET", apiSecret);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");

            //image should be public, otherwise, should use data
//            FileInputStream inputStream = new FileInputStream("/kakaru.png");
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            inputStream.close();
            URL url2 = new URL(
                "https://www.shutterstock.com/image-photo/background-white-paper-texture-box-260nw-1599254647.jpg");
            InputStream inputStream = url2.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();

            byte[] base64Bytes = Base64.getEncoder().encode(imageBytes);

            image.put("data", new String(base64Bytes));
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
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
            response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            String jsonText = response.toString();
            String data = new JSONObject(jsonText)
                .getJSONArray("images")
                .getJSONObject(0)
                .getJSONObject("receipt")
                .getJSONObject("result")
                .getJSONObject("storeInfo")
                .getJSONArray("addresses")
                .getJSONObject(0)
                .getString("text");

            log.info("data : " + data);

        } catch (Exception e) {
            System.out.println(e);
        }

        return response;
    }

}
