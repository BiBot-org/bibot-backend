package com.coderecipe.v1.payment.dto.vo;

import com.coderecipe.v1.payment.model.PaymentHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PaymentRes {
    private PaymentRes() {
        throw new IllegalStateException("VO class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentInfo {
        private String id;
        private String paymentDestination;
        private Integer amount;

        public static PaymentInfo of(PaymentHistory entity) {
            return PaymentInfo.builder()
                    .id(entity.getId())
                    .paymentDestination(entity.getPaymentDestination())
                    .amount(entity.getAmount())
                    .build();
        }
    }
}
