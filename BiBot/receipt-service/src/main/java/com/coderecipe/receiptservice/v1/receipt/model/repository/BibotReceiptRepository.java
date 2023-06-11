package com.coderecipe.receiptservice.v1.receipt.model.repository;

import com.coderecipe.receiptservice.v1.receipt.model.BibotReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BibotReceiptRepository extends JpaRepository<BibotReceipt, String> {
    Optional<BibotReceipt> findByApproveId(String approveId);
    Optional<BibotReceipt> findBibotReceiptByPaymentId(String paymentId);
}
