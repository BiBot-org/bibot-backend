package com.coderecipe.v1.payment.service.impl;

import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.card.model.repository.ICardRepository;
import com.coderecipe.v1.payment.dto.vo.PaymentReq;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.v1.payment.model.PaymentHistory;
import com.coderecipe.v1.payment.model.repository.IPaymentHistoryRepository;
import com.coderecipe.v1.payment.service.IPaymentHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class PaymentHistoryImpl implements IPaymentHistoryService {

    private final IPaymentHistoryRepository iPaymentHistoryRepository;
    private final ICardRepository iCardRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Deprecated(since = "영수증 서비스 분리 후 삭제 예정입니다.")
    public String addPayment(MockPaymentReq req) {
        Card card = Card.builder()
                .id(req.getCardId())
                .userId(UUID.randomUUID())
                .cardCompany("SSG 카드")
                .cardCvc("000")
                .cardNo("1234")
                .cardValid("123")
                .build();
        PaymentHistory paymentHistory = PaymentHistory.of(req, card);
        paymentHistory.setId(StringUtils.generateDateTimeCode(StringUtils.CODE_PAYMENT));
        ObjectMapper mapper = new ObjectMapper();
        String message = "";
        try {
            message = mapper.writeValueAsString(PaymentReq.CreateMockReceiptReq.of(paymentHistory.getId(), card.getCardCompany(), req));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send("payment_success", message);
        return paymentHistory.getId();
    }

}
