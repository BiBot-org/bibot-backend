package com.coderecipe.v1.user.approval.dto.vo;

import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class ApprovalReq {

    private ApprovalReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    public static class RequestApproval {

        private long id;
        private String managerId;
        private String requesterId;
        private ApprovalStatus status;
        private String comment;
        private boolean isAutomated;
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

//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class

}
