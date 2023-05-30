package com.coderecipe.receiptservice.v1.receipt.service;

import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.utils.ExtractJson;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.PaymentReq.MockPaymentReq;

public interface IReceiptService {
//    ReceiptDTO createReceipt(String kafkaMessage);
    String createReceipt(MockPaymentReq req);

    ExtractJson getOcrData(OcrReq req);
}
