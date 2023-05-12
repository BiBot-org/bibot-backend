package com.coderecipe.v1.user.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.dto.BibotUserDTO;
import com.coderecipe.v1.user.dto.vo.BibotUserReq.CreateUserReq;
import com.coderecipe.v1.user.dto.vo.BibotUserRes.CreateUserRes;
import com.coderecipe.v1.user.enums.UserRole;
import com.coderecipe.v1.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final IUserService userService;

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
    public ResponseEntity<BaseRes<CreateUserRes>> addUser(@RequestBody CreateUserReq req) {
        CreateUserRes res = userService.createUser(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}