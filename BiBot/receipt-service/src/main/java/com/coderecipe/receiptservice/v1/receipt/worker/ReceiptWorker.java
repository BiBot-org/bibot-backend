package com.coderecipe.receiptservice.v1.receipt.worker;

import com.coderecipe.receiptservice.v1.receipt.dto.vo.PaymentReq.CreateMockReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.receiptsform.worker.SelectForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptWorker {

    private final SelectForm selectForm;
    public Boolean createReceiptImage(CreateMockReceiptReq req) {

        try {
            selectForm.createReceiptImage(req);
        } catch (Exception e) {
            log.error("create receipt image failed!");
            return false;
        }
        return true;
    }
}