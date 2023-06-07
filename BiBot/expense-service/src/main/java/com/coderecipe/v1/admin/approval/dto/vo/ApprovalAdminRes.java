package com.coderecipe.v1.admin.approval.dto.vo;

import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.model.Approval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.UUID;

public class ApprovalAdminRes {
    private ApprovalAdminRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchAdminApprovalRes {
        private String id;
        private UUID managerId;
        private UUID requesterId;
        private ApprovalStatus status;
        private String comment;
        private boolean isAutomated;
        private String regTime;
        private Long categoryId;

        public static SearchAdminApprovalRes of (Approval entity) {
            return SearchAdminApprovalRes.builder()
                    .id(entity.getId())
                    .managerId(entity.getManagerId())
                    .requesterId(entity.getRequesterId())
                    .status(entity.getStatus())
                    .comment(entity.getComment())
                    .isAutomated(entity.isAutomated())
                    .regTime(entity.getRegTime().toString())
                    .categoryId(entity.getCategory().getId())
                    .build();
        }

        public static Page<SearchAdminApprovalRes> of (Page<Approval> entities) {
            return entities.map(SearchAdminApprovalRes::of);
        }
    }
}
