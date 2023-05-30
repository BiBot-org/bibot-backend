package com.coderecipe.v1.admin.category.init.vo;

import com.coderecipe.v1.admin.category.vo.CategoryAdminReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class CategoryInitReq {
    private CategoryInitReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddCategoryInit {
        List<CategoryAdminReq.AddCategory> categoryList;
    }
}
