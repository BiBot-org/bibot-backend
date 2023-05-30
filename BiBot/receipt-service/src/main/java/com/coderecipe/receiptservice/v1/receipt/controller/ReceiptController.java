package com.coderecipe.receiptservice.v1.receipt.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.utils.ExtractJson;
import com.coderecipe.receiptservice.v1.receipt.service.ReceiptService;
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

    private final ReceiptService receiptService;

    @PostMapping
    public ResponseEntity<BaseRes<ExtractJson>> getOcrData(@RequestBody OcrReq req) {
        ExtractJson result = receiptService.getOcrData(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
