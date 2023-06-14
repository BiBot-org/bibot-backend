package com.coderecipe.receiptservice.v1.receipt.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.receiptservice.v1.receipt.dto.BibotReceiptDTO;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.service.IReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

@Tag(name = "영수증 Service API", description = "영수증 Service API 문서 입니다.")
@RestController
@RequestMapping("/api/v1/receipt")
@RequiredArgsConstructor
@Slf4j
public class ReceiptController {

    private final IReceiptService ireceiptService;

    @Operation(summary = "영수증 정보 단건 조회", description = "영수증 정보 단건 조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<BibotReceiptDTO>> getReceiptInfo(@RequestParam String id) {
        BibotReceiptDTO res = ireceiptService.getReceipt(id);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "결재번호 기반 영수증 조회", description = "결재 번호 기반 영수증 정보 단건 조회 API 입니다.")
    @GetMapping("/approval")
    public ResponseEntity<BaseRes<BibotReceiptDTO>> getReceiptInfoByApprovalId(@RequestParam String id) {
        BibotReceiptDTO res = ireceiptService.getReceiptByApproveId(id);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "영수증 등록 및 결재 요청", description = "영수증 등록 및 결재 요청 API 입니다. 자동결재의 시작 포인트입니다.")
    @PostMapping("/image")
    public ResponseEntity<BaseRes<String>> uploadReceiptImage(@RequestParam(name = "file") MultipartFile file,
                                                              @RequestParam(name = "cardId") Long cardId,
                                                              @RequestParam(name = "categoryId") Long categoryId,
                                                              @RequestParam(name = "paymentId") String paymentId,
                                                              @RequestParam(name = "regTime") LocalDateTime regTime,
                                                              Principal principal) throws IOException {
        UUID userId = UUID.fromString(principal.getName());
        String res = ireceiptService.requestApprovalStart(new ReceiptReq.ApprovalStartReq(cardId, categoryId, paymentId, regTime, userId), file);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}
