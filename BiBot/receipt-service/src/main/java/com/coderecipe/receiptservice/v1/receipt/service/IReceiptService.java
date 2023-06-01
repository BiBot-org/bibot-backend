package com.coderecipe.receiptservice.v1.receipt.service;

import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrResult;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;

public interface IReceiptService {
    String createReceipt(ReceiptReq.CreateMockReceiptReq req) throws Exception;

    OcrResult.OcrResultInfo getOcrData(OcrReq req);
}
