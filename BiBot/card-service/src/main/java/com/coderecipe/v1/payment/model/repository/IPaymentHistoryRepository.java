package com.coderecipe.v1.payment.model.repository;

import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.payment.model.PaymentHistory;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {
    List<PaymentHistory> findAllByCardId(Long cardId);
    Page<PaymentHistory> findAllByIsRequested(boolean isRequested, Pageable pageable);

    Page<PaymentHistory> findPaymentHistoriesByCardIdAndRegTimeBetween(Long cardId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<PaymentHistory> findAllByRegTimeBetweenAndCardIdOrderByRegTimeDesc(LocalDateTime startDateTime, LocalDateTime endDateTime, Long cardId);

    @Query(value = "SELECT SUM(p.amount) FROM PaymentHistory p WHERE p.regTime BETWEEN :startDateTime AND :endDateTime AND p.card = :card")
    Integer findAndSumAllByRegTimeBetweenAndCardId(@Param("startDateTime") LocalDateTime startDateTime,
                                                   @Param("endDateTime") LocalDateTime endDateTime, @Param("card") Card card);

}
