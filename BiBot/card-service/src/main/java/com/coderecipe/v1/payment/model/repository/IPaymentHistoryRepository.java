package com.coderecipe.v1.payment.model.repository;

import com.coderecipe.v1.payment.dto.vo.PaymentRes.PaymentInfo;
import com.coderecipe.v1.payment.model.PaymentHistory;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    List<PaymentHistory> findAllByCardId(Long cardId);

    List<PaymentHistory> findAllByRegTimeBetweenAndCardId(LocalDateTime startDateTime, LocalDateTime endDateTime, Long cardId);

    List<PaymentHistory> findAllByCardIdAndAmount(Long cardId, Integer amount);
}
