package com.coderecipe.v1.admin.category.init.service.impl;

import com.coderecipe.v1.admin.category.init.service.CategoryInitAdminService;
import com.coderecipe.v1.admin.category.init.vo.CategoryInitReq;
import com.coderecipe.v1.user.category.model.Category;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryInitAdminServiceImpl implements CategoryInitAdminService {

    private final ICategoryRepository categoryRepository;

    @Override
    @Transactional
    public Boolean initCategory(CategoryInitReq.AddCategoryInit req) {
        req.getCategoryList().forEach(categoryInfo -> {
            Category category = Category.of(categoryInfo);
            categoryRepository.save(category);
        });
        return true;
    }
}
