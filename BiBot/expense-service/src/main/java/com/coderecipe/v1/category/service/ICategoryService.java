package com.coderecipe.v1.category.service;

import com.coderecipe.v1.category.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    List<Integer> addCategory(List<CategoryDTO> req);
}
