package com.coderecipe.v1.payment.model.repository;

import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.payment.model.PaymentHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {
    List<PaymentHistory> findAllByCardId(Long cardId);

    Page<PaymentHistory> findAllByIsRequested(boolean isRequested, Pageable pageable);

    Optional<PaymentHistory> findPaymentHistoryByApprovalId(String approvalId);

    Page<PaymentHistory> findPaymentHistoriesByCardIdAndRegTimeBetween(Long cardId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<PaymentHistory> findAllByRegTimeBetweenAndCardIdOrderByRegTimeDesc(LocalDateTime startDateTime, LocalDateTime endDateTime, Long cardId);

    @Query(value = "SELECT SUM(p.amount) FROM PaymentHistory p WHERE p.regTime BETWEEN :startDateTime AND :endDateTime AND p.card = :card")
    Integer findAndSumAllByRegTimeBetweenAndCardId(@Param("startDateTime") LocalDateTime startDateTime,
                                                   @Param("endDateTime") LocalDateTime endDateTime, @Param("card") Card card);

}
