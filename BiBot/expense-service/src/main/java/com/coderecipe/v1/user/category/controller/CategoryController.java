package com.coderecipe.v1.user.category.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.category.dto.CategoryDTO;
import com.coderecipe.v1.user.category.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final ICategoryService iCategoryService;

    @GetMapping("/all")
    public ResponseEntity<BaseRes<List<CategoryDTO>>> getCategoryList() {
        List<CategoryDTO> result = iCategoryService.getAllCategoryList();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
