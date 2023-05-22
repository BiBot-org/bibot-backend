package com.coderecipe.receiptservice.v1.receipt.service;

import com.coderecipe.receiptservice.v1.clovalocr.dto.vo.OcrReq;

public interface ReceiptService {
    Boolean createReceipt(String kafkaMessage);

    String getOcrData(OcrReq req);
}
