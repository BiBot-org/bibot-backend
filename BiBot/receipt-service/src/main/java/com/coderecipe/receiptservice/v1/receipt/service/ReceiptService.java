package com.coderecipe.receiptservice.v1.receipt.service;

import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;

public interface ReceiptService {
    Boolean createReceipt(String kafkaMessage);

    StringBuffer getOcrData(OcrReq req);
}
