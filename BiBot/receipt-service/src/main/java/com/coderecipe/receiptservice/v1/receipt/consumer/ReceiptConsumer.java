package com.coderecipe.receiptservice.v1.receipt.consumer;

import com.coderecipe.global.constant.dto.KafkaPayload;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrRes;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq.CreateMockReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.service.IReceiptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptConsumer {

    private final IReceiptService receiptService;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "payment_success", groupId = "group-bibot", containerFactory = "concurrentListener")
    public String createMockReceipt(KafkaPayload message) throws Exception {
        CreateMockReceiptReq req = mapper.convertValue(message.getBody(), CreateMockReceiptReq.class);
        String result = receiptService.createReceiptImage(req);
        log.info(String.format("payment success : %s", result));
        return result;
    }

    @KafkaListener(topics = "ocr_start", groupId = "group-bibot", containerFactory = "concurrentListener")
    public boolean ocrRequest(KafkaPayload message) throws JsonProcessingException {
        OcrReq.OcrStartReq req = mapper.convertValue(message.getBody(), OcrReq.OcrStartReq.class);
        return receiptService.ocrStart(req);
    }
    @KafkaListener(topics = "approval_end_rcp", groupId = "group-bibot", containerFactory = "concurrentListener")
    public String autoApprovalEnd(KafkaPayload message) {
        ReceiptReq.ApprovalEndReq req = mapper.convertValue(message.getBody(), ReceiptReq.ApprovalEndReq.class);
        String result = receiptService.requestApprovalEnd(req);
        log.info(String.format("approval end : %s -> %s", result, req.getApprovalId()));
        return result;
    }

}
