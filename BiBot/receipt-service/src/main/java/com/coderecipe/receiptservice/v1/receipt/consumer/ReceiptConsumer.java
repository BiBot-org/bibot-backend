package com.coderecipe.receiptservice.v1.receipt.consumer;

import com.coderecipe.global.constant.dto.KafkaPayload;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq.CreateMockReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.service.IReceiptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiptConsumer {

    private final IReceiptService receiptService;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "payment_success", groupId = "group-bibot", containerFactory = "concurrentListener")
    public String createMockReceipt(KafkaPayload message) throws Exception {
        CreateMockReceiptReq req = mapper.convertValue(message.getBody(), CreateMockReceiptReq.class);
        return receiptService.createReceiptImage(req);
    }

    @KafkaListener(topics = "ocr_start", groupId = "group-bibot", containerFactory = "concurrentListener")
    public String ocrRequest(KafkaPayload message) throws JsonProcessingException {
        OcrReq.OcrStartReq req = mapper.convertValue(message.getBody(), OcrReq.OcrStartReq.class);
        receiptService.ocrStart(req);
        return null;
    }

}
