package com.coderecipe.v1.approval.dto.vo;

import com.coderecipe.v1.approval.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

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

}
