package com.coderecipe.v1.payment.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    @GetMapping
    public ResponseEntity<BaseRes<PaymentHistoryDTO>> getPaymentHistory(@RequestParam(name = "historyId", defaultValue = "") Long historyId) {
        return ResponseEntity.ok().body(BaseRes.success(new PaymentHistoryDTO(
                1L,
                2L,
                "{paymentDestination}",
                12000
        )));
    }
}
