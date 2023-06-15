package com.coderecipe.v1.admin.category.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.category.service.CategoryAdminService;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.AddCategory;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.DeleteCategory;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.UpdateCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "경비 카테고리 Admin API", description = "경비 카테고리 Admin API 문서 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/category")
public class CategoryAdminController {

    private final CategoryAdminService categoryAdminService;

    @Operation(summary = "경비 카테고리 추가", description = "경비 카테고리 추가 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @PostMapping
    public ResponseEntity<BaseRes<Long>> addCategory(@RequestBody AddCategory req) {
        Long res = categoryAdminService.addCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "경비 카테고리 업데이트", description = "경비 카테고리 업데이트 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @PutMapping
    public ResponseEntity<BaseRes<Long>> updateCategory(@RequestBody UpdateCategory req) {
        Long rep = categoryAdminService.updateCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(rep));
    }

    @Operation(summary = "경비 카테고리 삭제", description = "경비 카테고리 식 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteCategory(@RequestBody DeleteCategory req) {
        Long rep = categoryAdminService.deleteCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(rep));
    }

}
