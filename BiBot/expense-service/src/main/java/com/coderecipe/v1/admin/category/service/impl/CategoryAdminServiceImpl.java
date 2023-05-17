package com.coderecipe.v1.admin.category.service.impl;

import com.coderecipe.v1.admin.category.service.CategoryAdminService;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.*;
import com.coderecipe.v1.user.category.model.Category;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryAdminServiceImpl implements CategoryAdminService {

    private final ICategoryRepository categoryRepository;
    @Override
    public Long addCategory(AddCategory req) {
        Category category = Category.of(req);
        categoryRepository.save(category);
        return category.getId();
    }
}
