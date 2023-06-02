package com.coderecipe.receiptservice.v1.receipt.model.repository;

import com.coderecipe.receiptservice.v1.receipt.model.BibotReceipt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BibotReceiptRepository extends JpaRepository<BibotReceipt, String> {
}
