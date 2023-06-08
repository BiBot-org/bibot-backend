package com.coderecipe.v1.admin.approval.dto.vo;

import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApprovalAdminReq {
    private ApprovalAdminReq () {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestApproval {

        private String approvalId;
        private ApprovalStatus status;
        private String comment;
    }

}
