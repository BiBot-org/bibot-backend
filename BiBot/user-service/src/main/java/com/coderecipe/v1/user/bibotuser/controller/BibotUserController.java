package com.coderecipe.v1.user.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.*;
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
    public ResponseEntity<BaseRes<BibotUserDTO>> getUser(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserDTO res = bibotUserService.getUser(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/info")
    public ResponseEntity<BaseRes<BibotUserInfo>> getUserInfo(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserInfo res = bibotUserService.getUserInfo(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
