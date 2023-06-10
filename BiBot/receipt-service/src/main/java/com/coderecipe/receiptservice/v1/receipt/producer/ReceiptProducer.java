package com.coderecipe.receiptservice.v1.receipt.producer;

import com.coderecipe.global.constant.dto.KafkaPayload;
import com.coderecipe.global.constant.enums.EventCode;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiptProducer {

    private static final String TOPIC_OCR_END = "ocr_end";
    private static final String TOPIC_OCR_START = "ocr_start";
    private static final String TOPIC_OCR_FAIL = "ocr_fail";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageOcrStart(OcrReq.OcrStartReq request) {
        Message<KafkaPayload> message = MessageBuilder
                .withPayload(KafkaPayload.of(EventCode.OCR_START, request))
                .setHeader(KafkaHeaders.TOPIC, TOPIC_OCR_START)
                .build();
        kafkaTemplate.send(message);
    }

    public void sendMessageOcrEnd(OcrReq.RequestAutoApproval request) {
        Message<KafkaPayload> message = MessageBuilder
                .withPayload(KafkaPayload.of(EventCode.OCR_END, request))
                .setHeader(KafkaHeaders.TOPIC, TOPIC_OCR_END)
                .build();
        kafkaTemplate.send(message);
    }

}
