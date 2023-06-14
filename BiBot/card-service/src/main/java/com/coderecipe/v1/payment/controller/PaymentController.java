package com.coderecipe.v1.payment.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.v1.payment.dto.vo.PaymentRes;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.SearchPaymentHistoryRes;
import com.coderecipe.v1.payment.service.IPaymentHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "가상 결제 Service API", description = "가상 결제 Service API 문서 입니다.")
@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final IPaymentHistoryService iPaymentHistoryService;

    @Operation(summary = "가상 결제 내역 단건 조회", description = "가상 결제 내역 단건 조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<PaymentHistoryDTO>> getPaymentHistory(
            @RequestParam(name = "id", defaultValue = "") String historyId) {
        PaymentHistoryDTO result = iPaymentHistoryService.getPaymentHistory(historyId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "경비 처리 번호 기반 가상 결제 내역 상세 정보 단건 조회", description = "경비 처리 별 가상 결제 내역 단건 조회 API 입니다.")
    @GetMapping("/approval")
    public ResponseEntity<BaseRes<PaymentRes.PaymentHistoryInfo>> getPaymentHistoryByApprovalId(@RequestParam(name="id", defaultValue = "") String approvalId) {
        PaymentRes.PaymentHistoryInfo result = iPaymentHistoryService.getPaymentHistoryByApprovalId(approvalId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "경비 처리 되지 않은 가상 결제 내역 조회", description = "경비 처리가 아직 요청 되지 않은 가상 결제 내역 조회 API 입니다.")
    @GetMapping("/requested/not")
    public ResponseEntity<BaseRes<PaymentRes.SearchPaymentHistoryInfoRes>> getAllPaymentHistoryIsRequestedNot(@PageableDefault(page = 5, sort = "regTime", direction = Sort.Direction.DESC) Pageable pageable) {
        PaymentRes.SearchPaymentHistoryInfoRes res = iPaymentHistoryService.getAllPaymentHistoryByIsRequested(false, pageable);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "가상 결제 처리", description = "가상 결제 처리 API 입니다.")
    @PostMapping
    public ResponseEntity<BaseRes<String>> addPayment(@RequestBody MockPaymentReq req) {
        String result = iPaymentHistoryService.addPayment(req);
        return ResponseEntity.ok(BaseRes.success(result));
    }

    @Operation(summary = "가상 결제 내역 검색", description = "가상 결제 내역 검색 API 입니다.")
    @GetMapping("/search")
    public ResponseEntity<BaseRes<SearchPaymentHistoryRes>> searchPaymentHistory(
            @RequestParam(name = "cardId", defaultValue = "") Long id,
            @RequestParam(name = "startDate", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            @RequestParam(name = "endDate", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate,
            @PageableDefault(size = 10, sort = "regTime", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchPaymentHistoryRes result = iPaymentHistoryService.searchPaymentHistory(
                new PaymentReq.SearchPaymentHistoryReq(id, startDate, endDate, pageable));
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
