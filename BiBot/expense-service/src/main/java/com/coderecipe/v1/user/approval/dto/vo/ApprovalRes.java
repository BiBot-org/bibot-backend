package com.coderecipe.v1.user.approval.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApprovalRes {
    private ApprovalRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AutoApprovalRes {
        private String approvalId;
        private String receiptId;
    }
}
