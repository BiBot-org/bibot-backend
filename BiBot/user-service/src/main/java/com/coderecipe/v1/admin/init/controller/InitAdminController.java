package com.coderecipe.v1.admin.init.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.init.service.InitAdminService;
import com.coderecipe.v1.admin.init.vo.InitAdminReq.InitRootUserSetupReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@Tag(name = "초기 셋업 API", description = "초기 셋업 Admin API 문서 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/init")
public class InitAdminController {

    private final InitAdminService initAdminService;

    @Operation(summary = "서비스 초기 셋업", description = "서비스 초기 셋업 API 입니다. 슈퍼 어드민 Root 유저를 셋업합니다.")
    @RolesAllowed({"SUPER_ADMIN"})
    @PostMapping
    public ResponseEntity<BaseRes<Boolean>> initializeService(@RequestBody InitRootUserSetupReq req) {
        Boolean result = initAdminService.setInitRootUser(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
