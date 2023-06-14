package com.coderecipe.v1.user.category.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.category.dto.CategoryDTO;
import com.coderecipe.v1.user.category.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "경비 카테고리 Service API", description = "경비 카테고리 Service API 문서 입니다.")
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final ICategoryService iCategoryService;

    @Operation(summary = "경비 카테고리 전체 조회", description = "경비 카테고리 전체 조회 API 입니다.")
    @GetMapping("/all")
    public ResponseEntity<BaseRes<List<CategoryDTO>>> getCategoryList() {
        List<CategoryDTO> result = iCategoryService.getAllCategoryList();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "경비 카테고리 단건 조회", description = "경비 카테고리 단건 조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<CategoryDTO>> getCategory(@RequestParam(name = "id", defaultValue = "") Long id) {
        CategoryDTO result = iCategoryService.getCategory(id);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
