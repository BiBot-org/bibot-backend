package com.coderecipe.receiptservice.v1.receipt.producer;

import com.coderecipe.global.constant.dto.KafkaPayload;
import com.coderecipe.global.constant.enums.EventCode;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiptProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageOcrStart(OcrReq.OcrStartReq request) {
        Message<KafkaPayload> message = MessageBuilder
                .withPayload(KafkaPayload.of(EventCode.OCR_START, request))
                .setHeader(KafkaHeaders.TOPIC, EventCode.OCR_START.getTopic())
                .build();
        kafkaTemplate.send(message);
    }

    public void sendMessageOcrEnd(OcrReq.RequestAutoApproval request) {
        Message<KafkaPayload> message = MessageBuilder
                .withPayload(KafkaPayload.of(EventCode.OCR_END, request))
                .setHeader(KafkaHeaders.TOPIC, EventCode.OCR_END.getTopic())
                .build();
        kafkaTemplate.send(message);
    }

    public void sendMessageOcrFail(OcrReq.RequestApprovalFail request) {
        Message<KafkaPayload> message = MessageBuilder
                .withPayload(KafkaPayload.of(EventCode.OCR_FAIL, request))
                .setHeader(KafkaHeaders.TOPIC, EventCode.OCR_FAIL.getTopic())
                .build();
        kafkaTemplate.send(message);
    }

    public void sendMessageApproveEndPayment(ReceiptReq.ApprovalEndPaymentReq request) {
        Message<KafkaPayload> message = MessageBuilder
                .withPayload(KafkaPayload.of(EventCode.AUTO_APPROVAL_END_PAYMENT, request))
                .setHeader(KafkaHeaders.TOPIC, EventCode.AUTO_APPROVAL_END_PAYMENT.getTopic())
                .build();
        kafkaTemplate.send(message);
    }
}
