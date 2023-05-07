package com.coderecipe.v1.payment.model.repository;

import com.coderecipe.v1.payment.model.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
}
