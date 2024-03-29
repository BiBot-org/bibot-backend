package com.coderecipe.v1.payment.model;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentHistory {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "payment_destination", columnDefinition = "VARCHAR(20) NOT NULL")
    private String paymentDestination;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "approval_id")
    private String approvalId;

    @Column(name = "is_requested")
    private boolean isRequested;

    @Column(name = "reg_time")
    private LocalDateTime regTime;

    public static PaymentHistory of(PaymentHistoryDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, PaymentHistory.class);
    }

    public static PaymentHistory of(MockPaymentReq req, Card card) {
        return PaymentHistory.builder()
                .id(StringUtils.generateDateTimeCode(StringUtils.CODE_PAYMENT))
                .card(card)
                .paymentDestination(req.getPaymentDestination())
                .amount(req.getTotalPrice())
                .isRequested(false)
                .build();
    }

    public void updatePaymentDate(String paymentDate) {
        if (paymentDate.isEmpty()) {
            this.setRegTime(LocalDateTime.now());
        } else {
            this.setRegTime(LocalDateTime.parse(paymentDate));
        }
        this.setRegTime(LocalDateTime.parse(paymentDate));
    }

    public void updateApprovalId(String id) {
        this.approvalId = id;
    }
}
