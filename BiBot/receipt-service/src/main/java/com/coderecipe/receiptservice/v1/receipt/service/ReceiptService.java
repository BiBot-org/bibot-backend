package com.coderecipe.receiptservice.v1.receipt.service;

import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.utils.ExtractJson;

public interface ReceiptService {
    Boolean createReceipt(String kafkaMessage);

    ExtractJson getOcrData(OcrReq req);
}
