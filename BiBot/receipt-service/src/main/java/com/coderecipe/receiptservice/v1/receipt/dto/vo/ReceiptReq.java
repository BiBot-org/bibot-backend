package com.coderecipe.receiptservice.v1.receipt.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ReceiptReq {

    private ReceiptReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApprovalStartReq {
        private Long cardId;
        private Long categoryId;
        private String paymentId;
        private LocalDateTime regTime;
        private UUID userId;
        @Nullable
        private String approvalId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApprovalErrorReq {
        private Long cardId;
        private Long categoryId;
        private String paymentId;
        private LocalDateTime regTime;
        private UUID userId;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApprovalEndReq {
        private String approvalId;
        private String receiptId;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MockApprovalStartReq extends ApprovalStartReq {
        private String imageUrl;
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MockPaymentReq {

        private String paymentDestination;
        private String businessLicense;
        private String representationName;
        private String address;
        private String paymentDate;
        private String destinationNum;
        private Long cardId;
        private List<ProductOrderList> productOrderList;
    }

    @EqualsAndHashCode(callSuper = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateMockReceiptReq extends MockPaymentReq {

        private String cardName;
        private String paymentCode;
        private String paymentDateStr;
        private String paymentCardCompany;
        private Integer totalPrice;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductOrderList {

        private String productName;
        private Integer productCost;
        private Integer count;

    }
}
