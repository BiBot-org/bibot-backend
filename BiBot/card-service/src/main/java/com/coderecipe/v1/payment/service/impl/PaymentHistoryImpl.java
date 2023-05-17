package com.coderecipe.v1.payment.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.card.model.repository.ICardRepository;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.CreateMockReceiptReq;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.v1.payment.model.PaymentHistory;
import com.coderecipe.v1.payment.model.repository.IPaymentHistoryRepository;
import com.coderecipe.v1.payment.service.IPaymentHistoryService;
import com.coderecipe.v1.receipt.worker.ReceiptWorker;

import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class PaymentHistoryImpl implements IPaymentHistoryService {

    private final IPaymentHistoryRepository iPaymentHistoryRepository;
    private final ICardRepository iCardRepository;
    private final ReceiptWorker receiptWorker;

    @Override
    @Transactional
//    @Deprecated(since = "testcode 입니다. 실제로는 사용하시면 안됍니다.")
    public PaymentHistoryDTO addPayment(MockPaymentReq req) {
//        Card card = iCardRepository.findById(req.getCardId())
//            .orElseThrow(() -> new CustomException(ResCode.NOT_FOUND));
        Card card = Card.builder()
                .id(req.getCardId())
                .userId(UUID.randomUUID())
                .cardCvc("000")
                .cardNo("1234")
                .cardValid("123")
                .build();
        PaymentHistory paymentHistory = PaymentHistory.of(req, card);
        iPaymentHistoryRepository.save(paymentHistory);

        if (receiptWorker.createReceiptImage(
                CreateMockReceiptReq.of(paymentHistory.getId(), card.getCardCompany(), req))) {
            return PaymentHistoryDTO.of(paymentHistory);
        } else {
            throw new CustomException(ResCode.INTERNAL_SERVER_ERROR);
        }
    }

}
