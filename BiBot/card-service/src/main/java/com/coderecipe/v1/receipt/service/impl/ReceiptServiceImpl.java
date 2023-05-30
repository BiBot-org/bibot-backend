package com.coderecipe.v1.receipt.service.impl;

import com.coderecipe.v1.payment.dto.vo.PaymentReq;
import com.coderecipe.v1.receipt.receiptsForm.makeReciept.SelectForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Deprecated(since = "영수증 서비스 분리 후 삭제 예정입니다.")
@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl {
    private final SelectForm selectForm;
    public boolean createReceipt(String kafkaMessage) {
        log.info("kafka message = {}", kafkaMessage);


        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<Object, Object> map = mapper.readValue(kafkaMessage, new TypeReference<>() {});
            PaymentReq.CreateMockReceiptReq req = mapper.convertValue(map, PaymentReq.CreateMockReceiptReq.class);
            log.info(req.toString());
            selectForm.createReceiptImage(req);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
