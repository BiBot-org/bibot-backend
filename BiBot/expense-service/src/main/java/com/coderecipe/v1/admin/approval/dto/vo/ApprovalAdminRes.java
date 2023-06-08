package com.coderecipe.v1.admin.approval.dto.vo;

import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.model.Approval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public class ApprovalAdminRes {
    private ApprovalAdminRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchAdminApproval {
        private String id;
        private UUID managerId;
        private UUID requesterId;
        private ApprovalStatus status;
        private String comment;
        private boolean isAutomated;
        private String regTime;
        private Long categoryId;

        public static SearchAdminApproval of(Approval entity) {
            return SearchAdminApproval.builder()
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

        public static Page<SearchAdminApproval> of(Page<Approval> entities) {
            return entities.map(SearchAdminApproval::of);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchAdminApprovalRes {
        private List<SearchAdminApproval> content;
        private Integer pageNo;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;

        public static SearchAdminApprovalRes of(Page<SearchAdminApproval> page) {
            return new SearchAdminApprovalRes(page.getContent(), page.getNumber(), page.isLast(), page.getTotalPages(), page.getTotalElements());
        }
    }
}
