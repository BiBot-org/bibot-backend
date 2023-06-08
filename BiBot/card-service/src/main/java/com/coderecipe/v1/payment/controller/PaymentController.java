package com.coderecipe.v1.payment.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.v1.payment.dto.vo.PaymentRes;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.SearchPaymentHistoryRes;
import com.coderecipe.v1.payment.service.IPaymentHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final IPaymentHistoryService iPaymentHistoryService;

    @GetMapping
    public ResponseEntity<BaseRes<PaymentHistoryDTO>> getPaymentHistory(
            @RequestParam(name = "id", defaultValue = "") String historyId) {
        PaymentHistoryDTO result = iPaymentHistoryService.getPaymentHistory(historyId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/requested/not")
    public ResponseEntity<BaseRes<PaymentRes.SearchPaymentHistoryInfoRes>> getAllPaymentHistoryIsRequestedNot(@PageableDefault(page = 5, sort = "regTime", direction = Sort.Direction.DESC) Pageable pageable) {
        PaymentRes.SearchPaymentHistoryInfoRes res = iPaymentHistoryService.getAllPaymentHistoryByIsRequested(false, pageable);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping
    public ResponseEntity<BaseRes<String>> addPayment(@RequestBody MockPaymentReq req) {
        String result = iPaymentHistoryService.addPayment(req);
        return ResponseEntity.ok(BaseRes.success(result));
    }

    @GetMapping("/search")
    public ResponseEntity<BaseRes<SearchPaymentHistoryRes>> searchPaymentHistory(
            @RequestParam(name = "cardId", defaultValue = "", required = true) Long id,
            @RequestParam(name = "startDate", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            @RequestParam(name = "endDate", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate,
            @PageableDefault(size = 3, sort = "regTime", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchPaymentHistoryRes result = iPaymentHistoryService.searchPaymentHistory(
                new PaymentReq.SearchPaymentHistoryReq(id, startDate, endDate, pageable));
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
