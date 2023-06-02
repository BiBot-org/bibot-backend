package com.coderecipe.v1.admin.category.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.category.service.CategoryAdminService;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.*;
import com.coderecipe.v1.user.category.dto.CategoryDTO;
import com.coderecipe.v1.user.category.model.Category;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryAdminServiceImpl implements CategoryAdminService {

    private final ICategoryRepository categoryRepository;

    @Override
    public Long addCategory(AddCategory req) {
        if (categoryRepository.existsByCategoryName(req.getCategoryName())) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }
        Category category = Category.of(req);
        categoryRepository.save(category);
        return category.getId();
    }

    @Override
    public CategoryDTO updateCategory(UpdateCategory req) {
        Category category = Category.of(categoryRepository.findById(req.getId())
            .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST)), req);
        if (categoryRepository.findById(req.getId()).get().isDeleted()) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }
        log.info(category.categoryName);
        return ModelMapperUtils.getModelMapper()
            .map(categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(DeleteCategory req) {
        if (!categoryRepository.findById(req.getId()).isPresent() || categoryRepository.findById(req.getId()).get().isDeleted() ) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }
        Category category = categoryRepository.findById(req.getId()).get();
        category.setDeleted(true);
        return ModelMapperUtils.getModelMapper()
            .map(categoryRepository.save(category), CategoryDTO.class);

    }
}
