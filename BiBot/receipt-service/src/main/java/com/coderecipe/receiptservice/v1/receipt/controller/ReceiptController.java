package com.coderecipe.receiptservice.v1.receipt.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.receiptservice.v1.receipt.dto.BibotReceiptDTO;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.service.IReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/receipt")
@RequiredArgsConstructor
@Slf4j
public class ReceiptController {

    private final IReceiptService ireceiptService;
    private final SseEmitter emitter = new SseEmitter();

    @GetMapping("/sse")
    public SseEmitter getReceiptInfoEndPoint() {
        return emitter;
    }

    @GetMapping
    public ResponseEntity<BaseRes<BibotReceiptDTO>> getReceiptInfo(@RequestParam String id) {
        BibotReceiptDTO res = ireceiptService.getReceipt(id);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/approval")
    public ResponseEntity<BaseRes<BibotReceiptDTO>> getReceiptInfoByApprovalId(@RequestParam String id) {
        BibotReceiptDTO res = ireceiptService.getReceiptByApproveId(id);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/image")
    public ResponseEntity<BaseRes<String>> uploadReceiptImage(@RequestParam(name = "file") MultipartFile file,
                                                              @RequestParam(name = "cardId") Long cardId,
                                                              @RequestParam(name = "categoryId") Long categoryId,
                                                              @RequestParam(name = "paymentId") String paymentId,
                                                              @RequestParam(name = "userId") UUID userId) throws IOException {
        String res = ireceiptService.requestApprovalStart(new ReceiptReq.ApprovalStartReq(cardId, categoryId, paymentId, userId), file);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/test")
    public ResponseEntity<BaseRes<String>> testApprovalStart(@RequestBody ReceiptReq.MockApprovalStartReq req) {
        String res = ireceiptService.requestMockApprovalStart(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}
