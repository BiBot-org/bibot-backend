package com.coderecipe.v1.user.category.service.impl;

import com.coderecipe.v1.user.category.dto.CategoryDTO;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import com.coderecipe.v1.user.category.service.ICategoryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@Data
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository iCategoryRepository;

    @Override
    public List<CategoryDTO> getAllCategoryList() {
        return iCategoryRepository.findAll().stream().map(CategoryDTO::of).toList();
    }
}
