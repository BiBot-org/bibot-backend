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
import com.coderecipe.v1.payment.service.IPaymentHistoryService;
import com.coderecipe.v1.receipt.worker.ReceiptWorker;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class PaymentHistoryImpl implements IPaymentHistoryService {

    private final IPaymentHistoryRepository iPaymentHistoryRepository;
    private final ICardRepository iCardRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ReceiptWorker receiptWorker;

    @Override
    public PaymentHistoryDTO getPaymentHistory(String id) {
        PaymentHistory result = iPaymentHistoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        return PaymentHistoryDTO.of(result);
    }

    @Override
    public SearchPaymentHistoryRes searchPaymentHistory(SearchPaymentHistoryReq req) {
        Page<PaymentHistoryDTO> result = PaymentHistoryDTO.of(
                iPaymentHistoryRepository.findPaymentHistoriesByCardIdAndRegTimeBetween(
                        req.getCardId(), req.getStartDate().atStartOfDay(), req.getEndDate().atTime(LocalTime.MAX), req.getPageable()));

        return SearchPaymentHistoryRes.of(result);
    }


    @Override
    public PaymentHistoryDTO addPayment(MockPaymentReq req) {
        Card card = iCardRepository.findById(req.getCardId())
                .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        PaymentHistory paymentHistory = PaymentHistory.of(req, card);
        paymentHistory.setId(StringUtils.generateDateTimeCode(StringUtils.CODE_PAYMENT));
        ObjectMapper mapper = new ObjectMapper();
        String message = "";
//        try {
//            message = mapper.writeValueAsString(CreateMockReceiptReq.of(paymentHistory.getId(), card.getCardCompany(), req));
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch(Exception e) {
//            throw new RuntimeException(e);
//        }
//        kafkaTemplate.send("payment_success", message);
        iPaymentHistoryRepository.save(paymentHistory);

        if (Boolean.TRUE.equals(receiptWorker.createReceiptImage(
                CreateMockReceiptReq.of(paymentHistory.getId(), card.getCardCompany(), req)))) {
            return PaymentHistoryDTO.of(paymentHistory);
        } else {
            throw new CustomException(ResCode.INTERNAL_SERVER_ERROR);
        }
    }

}
