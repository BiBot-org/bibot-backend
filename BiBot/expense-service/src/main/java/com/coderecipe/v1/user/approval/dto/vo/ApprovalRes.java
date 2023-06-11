package com.coderecipe.v1.user.approval.dto.vo;

import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminRes;
import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.model.Approval;
import com.coderecipe.v1.user.category.dto.CategoryDTO;
import com.coderecipe.v1.user.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ApprovalRes {
    private ApprovalRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApprovalInfo {
        private ApprovalDTO approval;
        private CategoryDTO category;
        private String createAt;

        public static ApprovalInfo of(Approval approval, Category category) {
            return new ApprovalInfo(ApprovalDTO.of(approval), CategoryDTO.of(category), approval.getRegTime().toString());
        }

        public static ApprovalInfo of(Approval approval) {
            return new ApprovalInfo(ApprovalDTO.of(approval), CategoryDTO.of(approval.getCategory()), approval.getRegTime().toString());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AutoApprovalRes {
        private String approvalId;
        private String receiptId;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchApproval {
        private String id;
        private UUID managerId;
        private UUID requesterId;
        private ApprovalStatus status;
        private String comment;
        private boolean isAutomated;
        private String regTime;
        private Long categoryId;

        public static SearchApproval of(Approval entity) {
            return SearchApproval.builder()
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

        public static Page<SearchApproval> of(Page<Approval> entities) {
            return entities.map(SearchApproval::of);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchAdminApprovalRes {
        private List<ApprovalAdminRes.SearchAdminApproval> content;
        private Integer pageNo;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;

        public static ApprovalAdminRes.SearchAdminApprovalRes of(Page<ApprovalAdminRes.SearchAdminApproval> page) {
            return new ApprovalAdminRes.SearchAdminApprovalRes(page.getContent(), page.getNumber(), page.isLast(), page.getTotalPages(), page.getTotalElements());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchApprovalInfoRes {
        private List<SearchApproval> content;
        private Integer pageNo;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;

        public static SearchApprovalInfoRes of(Page<SearchApproval> page) {
            return new SearchApprovalInfoRes(page.getContent(), page.getNumber(), page.isLast(), page.getTotalPages(), page.getTotalElements());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetExpenseProcessingStatusByCategoryRes {
        private int limitation;
        private int expenseUsed;
    }
}
