package com.coderecipe.v1.admin.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes;
import com.coderecipe.v1.admin.bibotuser.service.IUserAdminService;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/user")
public class UserAdminController {

    private final IUserAdminService userAdminService;

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

    @PostMapping()
    public ResponseEntity<BaseRes<UserAdminRes.CreateUserRes>> addUser(@RequestBody UserAdminReq.CreateUserReq req) {
        UserAdminRes.CreateUserRes res = userAdminService.createUser(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}
