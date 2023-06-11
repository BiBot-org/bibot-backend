package com.coderecipe.v1.payment.consumer;

import com.coderecipe.global.constant.dto.KafkaPayload;
import com.coderecipe.v1.payment.dto.vo.PaymentReq;
import com.coderecipe.v1.payment.service.IPaymentHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentConsumer {

    private final IPaymentHistoryService paymentHistoryService;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "approval_end_pay", groupId = "group-bibot", containerFactory = "concurrentListener")
    public String autoApprovalEndPayment(KafkaPayload message) {
        PaymentReq.ApprovalEndPaymentReq req = mapper.convertValue(message.getBody(), PaymentReq.ApprovalEndPaymentReq.class);
        return paymentHistoryService.updatePaymentFromApprovalUpdate(req);
    }
}
