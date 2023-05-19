package com.coderecipe.v1.admin.category.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.category.service.CategoryAdminService;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/category")
public class CategoryAdminController {

    private final CategoryAdminService categoryAdminService;

    public ResponseEntity<BaseRes<Long>> addCategory(@RequestBody AddCategory req) {
        Long res = categoryAdminService.addCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
