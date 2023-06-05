package com.coderecipe.v1.admin.category.service;

import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.*;

public interface CategoryAdminService {
    Long addCategory(AddCategory req);

    Long updateCategory(UpdateCategory req);

    Long deleteCategory(DeleteCategory req);
}
