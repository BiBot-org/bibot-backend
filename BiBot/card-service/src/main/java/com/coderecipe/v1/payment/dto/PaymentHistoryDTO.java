package com.coderecipe.v1.payment.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.payment.model.PaymentHistory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryDTO {

    private Long id;
    private Long cardId;
    private String paymentDestination;
    private Integer amount;

    public static PaymentHistoryDTO of(PaymentHistory entity) {
        return ModelMapperUtils.getModelMapper().map(entity, PaymentHistoryDTO.class);
    }

}
