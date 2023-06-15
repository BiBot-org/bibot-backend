package com.coderecipe.v1.admin.category.init.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.category.init.service.CategoryInitAdminService;
import com.coderecipe.v1.admin.category.init.vo.CategoryInitReq;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/category/init")
public class CategoryInitAdminController {

    private final CategoryInitAdminService categoryInitAdminService;

    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @PostMapping
    public ResponseEntity<BaseRes<Boolean>> addCategoryInit(@RequestBody CategoryInitReq.AddCategoryInit req) {
        Boolean res = categoryInitAdminService.initCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
