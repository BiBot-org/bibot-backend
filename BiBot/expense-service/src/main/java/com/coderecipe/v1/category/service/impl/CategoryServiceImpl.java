package com.coderecipe.v1.category.service.impl;

import com.coderecipe.v1.category.dto.CategoryDTO;
import com.coderecipe.v1.category.model.Category;
import com.coderecipe.v1.category.model.repository.ICategoryRepository;
import com.coderecipe.v1.category.service.ICategoryService;
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
    public List<Integer> addCategory(List<CategoryDTO> req) {
        return req.stream().map(e -> {
            Category category = Category.of(e);
            iCategoryRepository.save(category);
            return category.getId();
        }).toList();
    }
}
