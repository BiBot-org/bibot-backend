package com.coderecipe.v1.admin.category.service;

import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.*;
import com.coderecipe.v1.user.category.dto.CategoryDTO;

public interface CategoryAdminService {
    Long addCategory(AddCategory req);

    CategoryDTO updateCategory(UpdateCategory req);

    CategoryDTO deleteCategory(DeleteCategory req);
}
