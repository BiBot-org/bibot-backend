package com.coderecipe.v1.payment.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.card.model.repository.ICardRepository;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.*;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.*;
import com.coderecipe.v1.payment.model.PaymentHistory;
import com.coderecipe.v1.payment.model.repository.IPaymentHistoryRepository;
import com.coderecipe.v1.payment.producer.PaymentProducer;
import com.coderecipe.v1.payment.service.IPaymentHistoryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class PaymentHistoryServiceImpl implements IPaymentHistoryService {

    private final IPaymentHistoryRepository iPaymentHistoryRepository;
    private final ICardRepository iCardRepository;
    private final PaymentProducer paymentProducer;

    @Override
    public PaymentHistoryDTO getPaymentHistory(String id) {
        PaymentHistory result = iPaymentHistoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        return PaymentHistoryDTO.of(result);
    }

    @Override
    public SearchPaymentHistoryInfoRes getAllPaymentHistoryByIsRequested(boolean isRequested, Pageable pageable) {
        Page<PaymentHistoryInfo> result = PaymentHistoryInfo.of(iPaymentHistoryRepository.findAllByIsRequested(isRequested, pageable));
        return SearchPaymentHistoryInfoRes.of(result);
    }

    @Override
    public SearchPaymentHistoryRes searchPaymentHistory(SearchPaymentHistoryReq req) {
        Page<PaymentHistoryInfo> result = PaymentHistoryInfo.of(
                iPaymentHistoryRepository.findPaymentHistoriesByCardIdAndRegTimeBetween(
                        req.getCardId(), req.getStartDate().atStartOfDay(), req.getEndDate().atTime(LocalTime.MAX), req.getPageable()));

        return SearchPaymentHistoryRes.of(result);
    }


    @Override
    public String addPayment(MockPaymentReq req) {
        Card card = iCardRepository.findById(req.getCardId())
                .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        PaymentHistory paymentHistory = PaymentHistory.of(req, card);
        paymentHistory.updatePaymentDate(req.getPaymentDate());
        paymentHistory.setId(StringUtils.generateDateTimeCode(StringUtils.CODE_PAYMENT));
        iPaymentHistoryRepository.save(paymentHistory);
        paymentProducer.sendMessage(CreateMockReceiptReq.of(paymentHistory.getId(), card.getCardCompany(), req));
        return paymentHistory.getId();
    }

}
