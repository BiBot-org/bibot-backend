package com.coderecipe.v1.user.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.service.BibotUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class BibotUserController {

    private final BibotUserService bibotUserService;
    @GetMapping
    public ResponseEntity<BaseRes<BibotUserDTO>> getUserMock() {
        return ResponseEntity.ok().body(BaseRes.success(BibotUserDTO.builder()
                .id(UUID.randomUUID())
                .userRole(UserRole.BIBOT_USER)
                .firstName("test")
                .lastName("test")
                .email("test@test.com")
                .duty("개발자")
                .profileUrl("test")
                .build()));
    }

    @GetMapping
    public ResponseEntity<BaseRes<Boolean>> isInit() {
        Boolean res = bibotUserService.isInit();
        return ResponseEntity.ok().body(BaseRes.success(res));
    }


}
