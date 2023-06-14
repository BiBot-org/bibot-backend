package com.coderecipe.v1.admin.category.vo;

import com.coderecipe.v1.user.category.enums.ResetCycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryAdminReq {
    private CategoryAdminReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddCategory {
        private String categoryName;
        private int limitation;
        private int automatedCost;
        private ResetCycle resetCycle;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateCategory {
        private Long id;
        private String categoryName;
        private int nextLimitation;
        private int nextAutomatedCost;
        private ResetCycle nextCycle;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteCategory {
        private Long id;
    }

}
