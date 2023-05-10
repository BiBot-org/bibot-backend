package com.coderecipe.v1.payment.model.repository;

import com.coderecipe.v1.payment.model.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

}
