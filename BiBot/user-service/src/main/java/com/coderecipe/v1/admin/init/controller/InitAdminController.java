package com.coderecipe.v1.admin.init.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.init.service.InitAdminService;
import com.coderecipe.v1.admin.init.vo.InitAdminReq.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/init")
public class InitAdminController {

    private final InitAdminService initAdminService;

    @PostMapping
    public ResponseEntity<BaseRes<Boolean>> initializeService(@RequestBody InitRootUserSetupReq req) {
        Boolean result = initAdminService.setInitRootUser(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
