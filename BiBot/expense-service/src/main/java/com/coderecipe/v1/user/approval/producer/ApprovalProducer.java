package com.coderecipe.v1.user.approval.producer;

import com.coderecipe.global.constant.dto.KafkaPayload;
import com.coderecipe.global.constant.enums.EventCode;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovalProducer {

    private static final String TOPIC_APPROVE_END = "approval_end";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageApproveEnd(ApprovalRes.AutoApprovalRes res) {
        Message<KafkaPayload> message = MessageBuilder
                .withPayload(KafkaPayload.of(EventCode.AUTO_APPROVAL_END, res))
                .setHeader(KafkaHeaders.TOPIC, TOPIC_APPROVE_END)
                .build();
        kafkaTemplate.send(message);
    }
}
