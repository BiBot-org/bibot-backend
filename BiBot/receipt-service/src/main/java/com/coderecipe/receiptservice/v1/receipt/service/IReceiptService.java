package com.coderecipe.receiptservice.v1.receipt.service;

import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrRes;
import com.coderecipe.receiptservice.v1.receipt.dto.BibotReceiptDTO;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IReceiptService {
    String createReceiptImage(ReceiptReq.CreateMockReceiptReq req) throws IOException;

    String requestApprovalStart(ReceiptReq.ApprovalStartReq req, MultipartFile file) throws IOException;

    String requestApprovalEnd(ReceiptReq.ApprovalEndReq req);

    String requestMockApprovalStart(ReceiptReq.MockApprovalStartReq req);

    BibotReceiptDTO getReceipt(String receiptId);
    BibotReceiptDTO getReceiptByApproveId(String approveId);

    OcrRes.OcrEndResponse ocrStart(OcrReq.OcrStartReq req) throws JsonProcessingException;

    OcrRes.OCRResponse getOcrData(String imageUrl);
}
