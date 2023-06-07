package com.coderecipe.v1.admin.category.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.category.service.CategoryAdminService;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.AddCategory;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.DeleteCategory;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.UpdateCategory;
import com.coderecipe.v1.user.category.model.Category;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "category")
public class CategoryAdminServiceImpl implements CategoryAdminService {

    private final ICategoryRepository categoryRepository;

    @Override
    @CacheEvict(key = "'all'")
    public Long addCategory(AddCategory req) {
        if (categoryRepository.existsByCategoryName(req.getCategoryName())) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }
        Category category = Category.of(req);
        categoryRepository.save(category);
        return category.getId();
    }

    @Override
    @CacheEvict(key = "'all'")
    public Long updateCategory(UpdateCategory req) {
        Category category = categoryRepository.findByIdAndIsDeletedNot(req.getId(), true)
            .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        category.updateCategory(req);
        categoryRepository.save(category);
        return category.getId();
    }

    @Override
    @CacheEvict(key = "'all'")
    public Long deleteCategory(DeleteCategory req) {
        Category category = categoryRepository.findByIdAndIsDeletedNot(req.getId(), true)
            .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        category.deleteCategory();
        categoryRepository.save(category);
        return category.getId();
    }
}
