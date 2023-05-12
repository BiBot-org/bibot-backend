package com.coderecipe.v1.admin.user.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.dto.BibotUserDTO;
import com.coderecipe.v1.user.enums.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/user")
public class UserAdminController {
    @GetMapping
    public ResponseEntity<BaseRes<BibotUserDTO>> getUserMock() {
        return ResponseEntity.ok().body(BaseRes.success(BibotUserDTO.builder()
                .id(UUID.randomUUID())
                .userRole(UserRole.BIBOT_USER)
                .name("test")
                .email("test@test.com")
                .duty("개발자")
                .profileUrl("test")
                .build()));
    }
}
