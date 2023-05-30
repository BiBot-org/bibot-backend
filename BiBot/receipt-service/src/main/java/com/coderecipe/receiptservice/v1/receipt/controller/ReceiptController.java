package com.coderecipe.receiptservice.v1.receipt.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.utils.ExtractJson;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.receiptservice.v1.receipt.service.IReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/receipt")
@RequiredArgsConstructor
@Slf4j
public class ReceiptController {

    private final IReceiptService ireceiptService;

    @PostMapping
    public ResponseEntity<BaseRes<ExtractJson>> getOcrData(@RequestBody OcrReq req) {
        ExtractJson result = ireceiptService.getOcrData(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/creating")
    public ResponseEntity<BaseRes<String>> createReceipt(@RequestBody MockPaymentReq req) {
        String result = ireceiptService.createReceipt(req);
        return ResponseEntity.ok().body(BaseRes.success((result)));
    }
}
