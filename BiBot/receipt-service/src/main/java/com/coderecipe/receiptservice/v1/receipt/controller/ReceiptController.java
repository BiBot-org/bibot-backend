package com.coderecipe.receiptservice.v1.receipt.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.receiptservice.v1.receipt.dto.ReceiptDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/receipt")
public class ReceiptController {

    @GetMapping()
    public ResponseEntity<BaseRes<ReceiptDTO>> getReceiptInfo() {
        return ResponseEntity.ok().body(BaseRes.success(new ReceiptDTO(
                StringUtils.generateReceiptCode(),
                UUID.randomUUID(),
                StringUtils.generateApproveCode(),
                StringUtils.generatePaymentCode(),
                "test"
        )));
    }
}
