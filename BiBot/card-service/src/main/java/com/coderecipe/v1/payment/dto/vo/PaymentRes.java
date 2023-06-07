package com.coderecipe.v1.payment.dto.vo;

import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.model.PaymentHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

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
        private String destinationNum;
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

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentHistoryInfo {
        private String id;
        private Long cardId;
        private String cardCompany;
        private String cardNo;
        private String paymentDestination;
        private Integer amount;
        private String approvalId;
        private String regTime;
        private boolean isRequested;

        public static PaymentHistoryInfo of(PaymentHistory entity) {
            return PaymentHistoryInfo.builder()
                    .id(entity.getId())
                    .cardId(entity.getCard().getId())
                    .cardCompany(entity.getCard().getCardCompany())
                    .cardNo(entity.getCard().getCardNo())
                    .paymentDestination(entity.getPaymentDestination())
                    .amount(entity.getAmount())
                    .approvalId(entity.getApprovalId())
                    .regTime(entity.getRegTime().toString())
                    .isRequested(entity.isRequested())
                    .build();
        }

        public static Page<PaymentHistoryInfo> of(Page<PaymentHistory> entities) {
            return entities.map(PaymentHistoryInfo::of);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchPaymentHistoryInfoRes {
        private List<PaymentHistoryInfo> content;
        private Integer pageNo;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;

        public static SearchPaymentHistoryInfoRes of(Page<PaymentHistoryInfo> page) {
            return new SearchPaymentHistoryInfoRes(page.getContent(), page.getNumber(), page.isLast(), page.getTotalPages(), page.getTotalElements());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentHistoryAndUserId {
        private PaymentHistoryInfo paymentHistory;
        private UUID userId;

        public static PaymentHistoryAndUserId of(PaymentHistory entity) {
            return new PaymentHistoryAndUserId(PaymentHistoryInfo.of(entity), entity.getCard().getUserId());
        }
    }
}
