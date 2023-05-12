package com.coderecipe.v1.auth.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.auth.dto.vo.AuthReq.SignInReq;
import com.coderecipe.v1.auth.dto.vo.AuthRes;
import com.coderecipe.v1.auth.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/auth")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<BaseRes<?>> login(@RequestBody SignInReq req) {
        AuthRes.TokenRes res = authService.requestSignIn(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
