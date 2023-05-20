package com.coderecipe.receiptservice.v1.receipt.service;

public interface ReceiptService {
    Boolean createReceipt(String kafkaMessage);
}
