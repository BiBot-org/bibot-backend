package com.coderecipe.v1.payment.model;

import com.coderecipe.global.constant.entity.BaseImmutableTimeEntity;
import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "payment_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentHistory extends BaseImmutableTimeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "payment_destination", columnDefinition = "VARCHAR(20) NOT NULL")
    private String paymentDestination;

    @Column(name = "amount")
    private Integer amount;

    public static PaymentHistory of(PaymentHistoryDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, PaymentHistory.class);
    }
}
