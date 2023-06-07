package com.coderecipe.v1.user.approval.dto.vo;

import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.model.Approval;
import com.coderecipe.v1.user.category.dto.CategoryDTO;
import com.coderecipe.v1.user.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

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
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchApprovalInfoRes {
        private List<ApprovalDTO> content;
        private Integer pageNo;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;

        public static SearchApprovalInfoRes of(Page<ApprovalDTO> page) {
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
