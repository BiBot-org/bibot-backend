package com.coderecipe.receiptservice.v1.receipt.service.impl;

import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.receiptsform.worker.SelectForm;
import com.coderecipe.receiptservice.v1.receipt.service.ReceiptService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

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
}
