package com.coderecipe.v1.payment.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.card.model.repository.ICardRepository;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.v1.payment.model.PaymentHistory;
import com.coderecipe.v1.payment.model.repository.IPaymentHistoryRepository;
import com.coderecipe.v1.payment.service.IPaymentHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final IPaymentHistoryService iPaymentHistoryService;
    private final IPaymentHistoryRepository iPaymentHistoryRepository;
    private final ICardRepository iCardRepository;

    @GetMapping
    public ResponseEntity<BaseRes<PaymentHistoryDTO>> getPaymentHistory(
            @RequestParam(name = "historyId", defaultValue = "") Long historyId) {

        iPaymentHistoryRepository.save(
            PaymentHistory.builder().card(iCardRepository.findById(1L).get())
                .id("dsdsdsdsdsdf")
                .paymentDestination("gd")
                .amount(12312)
                .build());
        return ResponseEntity.ok().body(BaseRes.success(new PaymentHistoryDTO(
                "tras",
                2L,
                "{paymentDestination}",
                12000
        )));
    }

    @PostMapping
    public ResponseEntity<BaseRes<String>> addPayment(@RequestBody MockPaymentReq req) {
        String result = iPaymentHistoryService.addPayment(req);
        return ResponseEntity.ok().body(BaseRes.success((result)));
    }

}
