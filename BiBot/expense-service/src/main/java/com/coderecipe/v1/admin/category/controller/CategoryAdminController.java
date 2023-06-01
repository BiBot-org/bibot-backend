package com.coderecipe.v1.admin.category.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.category.service.CategoryAdminService;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.*;
import com.coderecipe.v1.user.category.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/category")
public class CategoryAdminController {

    private final CategoryAdminService categoryAdminService;

    @PostMapping
    public ResponseEntity<BaseRes<Long>> addCategory(@RequestBody AddCategory req) {
        Long res = categoryAdminService.addCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PutMapping
    public ResponseEntity<BaseRes<CategoryDTO>> updateCategory(@RequestBody UpdateCategory req){
        CategoryDTO rep = categoryAdminService.updateCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(rep));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<CategoryDTO>> deleteCategory(@RequestBody DeleteCategory req) {
        CategoryDTO rep = categoryAdminService.deleteCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(rep));
    }

}
