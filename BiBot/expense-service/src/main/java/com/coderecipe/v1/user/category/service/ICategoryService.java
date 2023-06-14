package com.coderecipe.v1.user.category.service;

import com.coderecipe.v1.user.category.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAllCategoryList();

    CategoryDTO getCategory(Long id);
}
