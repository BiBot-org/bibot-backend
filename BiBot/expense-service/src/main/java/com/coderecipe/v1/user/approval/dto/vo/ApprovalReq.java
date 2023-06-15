package com.coderecipe.v1.user.approval.dto.vo;

import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

public class ApprovalReq {

    private ApprovalReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestAutoApproval {
        private int totalPrice;
        private Long categoryId;
        private Long cardId;
        private String receiptId;
        private UUID userId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestApprovalFail {
        private Long categoryId;
        private String receiptId;
        private Long cardId;
        private UUID userId;
        private String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchApprovalInfoReq {
        private LocalDate startDate;
        private LocalDate endDate;
        private ApprovalStatus status;
        private Long categoryId;
    }

}
