package com.coderecipe.v1.admin.category.init.service;

import com.coderecipe.v1.admin.category.init.vo.CategoryInitReq;

public interface CategoryInitAdminService {
    Boolean initCategory(CategoryInitReq.AddCategoryInit req);
}
