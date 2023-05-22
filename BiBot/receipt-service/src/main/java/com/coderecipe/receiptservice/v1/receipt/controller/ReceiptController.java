package com.coderecipe.receiptservice.v1.receipt.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.receiptservice.v1.clovalocr.dto.vo.OcrReq;
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
    /*
      clobal ocr 영수증 api 구축
      1. 요청 바디를 받아온다
      2. 요청 바디를 통해서 ocr 데이터를 받아온다.
      3. 해당 데이터를 Json 타입으로 저장한다.
     */
    @PostMapping
    public ResponseEntity<BaseRes<String>> getOcrData(@RequestBody OcrReq req) {
        String result = receiptService.getOcrData(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
