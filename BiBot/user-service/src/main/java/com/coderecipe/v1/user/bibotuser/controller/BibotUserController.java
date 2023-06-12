package com.coderecipe.v1.user.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.*;
import com.coderecipe.v1.user.bibotuser.service.BibotUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
public class BibotUserController {

    private final BibotUserService bibotUserService;

    @GetMapping
    public ResponseEntity<BaseRes<BibotUserDTO>> getUser(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserDTO res = bibotUserService.getUser(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/myInfo")
    public ResponseEntity<BaseRes<BibotUserInfo>> getMyUserInfo(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        BibotUserInfo res = bibotUserService.getUserInfo(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/info")
    public ResponseEntity<BaseRes<BibotUserInfo>> getUserInfo(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserInfo res = bibotUserService.getUserInfo(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/profile")
    public ResponseEntity<BaseRes<String>> addProfile(@RequestParam(name = "profile_url")
        MultipartFile file, Principal principal) throws IOException {
        UUID userId = UUID.fromString(principal.getName());
        String res = bibotUserService.addProfile(userId, file);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @DeleteMapping("/profile")
    public ResponseEntity<BaseRes<String>> deleteProfile(@RequestParam UUID userId){
        String res = bibotUserService.deleteProfile(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PutMapping("/profile")
    public ResponseEntity<BaseRes<String>> updateProfile(@RequestParam(name = "profile_url")
    MultipartFile file, Principal principal) throws IOException {
        UUID userId = UUID.fromString(principal.getName());
        String res = bibotUserService.updateProfile(userId, file);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PutMapping("/password/reset")
    public ResponseEntity<BaseRes<Boolean>> resetPassword(@RequestBody ChangeUserPassword req, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        boolean res = bibotUserService.changePassword(userId, req.getNewPassword());
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}
