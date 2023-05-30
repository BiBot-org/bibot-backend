package com.coderecipe.v1.payment.dto.vo;

import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.model.PaymentHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchPaymentHistoryRes {
        private List<PaymentHistoryDTO> content;
        private Integer pageNo;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;

        public static SearchPaymentHistoryRes of(Page<PaymentHistoryDTO> page) {
            return new SearchPaymentHistoryRes(page.getContent(), page.getNumber(), page.isLast(), page.getTotalPages(), page.getTotalElements());
        }
    }
}
