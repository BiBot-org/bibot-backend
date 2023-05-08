package com.coderecipe.v1.category.dto.vo;

import com.coderecipe.v1.category.enums.ResetCycle;
import lombok.AllArgsConstructor;
import lombok.Data;

public class CategoryReq {

    private CategoryReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    public static class RequestCategoryReq {

        private int id;
        private String categoryName;
        private ResetCycle resetCycle;
    }
}
