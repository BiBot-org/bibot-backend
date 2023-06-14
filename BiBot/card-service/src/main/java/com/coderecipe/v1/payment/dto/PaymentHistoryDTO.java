package com.coderecipe.v1.payment.dto;

import com.coderecipe.v1.payment.model.PaymentHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentHistoryDTO {

    private String id;
    private Long cardId;
    private String paymentDestination;
    private Integer amount;
    private String approvalId;
    private boolean isRequested;

    public static PaymentHistoryDTO of(PaymentHistory entity) {
        return PaymentHistoryDTO.builder()
                .id(entity.getId())
                .cardId(entity.getCard().getId())
                .paymentDestination(entity.getPaymentDestination())
                .amount(entity.getAmount())
                .approvalId(entity.getApprovalId())
                .isRequested(entity.isRequested())
                .build();
    }

    public static Page<PaymentHistoryDTO> of(Page<PaymentHistory> entities) {
        return entities.map(PaymentHistoryDTO::of);
    }

}
