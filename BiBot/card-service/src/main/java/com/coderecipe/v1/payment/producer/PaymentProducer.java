package com.coderecipe.v1.payment.producer;

import com.coderecipe.global.constant.dto.KafkaPayload;
import com.coderecipe.global.constant.enums.EventCode;
import com.coderecipe.v1.payment.dto.vo.PaymentReq;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private static final String TOPIC = "payment_success";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(PaymentReq.CreateMockReceiptReq data) {
        Message<KafkaPayload> message = MessageBuilder
                .withPayload(KafkaPayload.of(EventCode.PAYMENT_SUCCESS, data))
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        kafkaTemplate.send(message);
    }

}
