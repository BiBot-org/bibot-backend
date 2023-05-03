package com.coderecipe.v1.category.controller;

import com.coderecipe.global.constant.BaseRes;
import com.coderecipe.v1.category.dto.CategoryDTO;
import com.coderecipe.v1.category.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense/v1/category")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    private final ICategoryService iCategoryService;

    @PostMapping("/add")
    public ResponseEntity<BaseRes<List<Integer>>> addCategory(
        @RequestBody List<CategoryDTO> categoryData) {
        List<Integer> result = iCategoryService.addCategory(categoryData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
