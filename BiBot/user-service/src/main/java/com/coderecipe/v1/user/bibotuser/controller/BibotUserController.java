package com.coderecipe.v1.user.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.*;
import com.coderecipe.v1.user.bibotuser.service.BibotUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class BibotUserController {

    private final BibotUserService bibotUserService;

    @GetMapping
    public ResponseEntity<BaseRes<BibotUserDTO>> getUser(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        System.out.println("getUser 메서드 실행");
        long StartTime = System.currentTimeMillis();
        BibotUserDTO res = bibotUserService.getUser(userId);
        long estimatedTime = System.currentTimeMillis() -StartTime;
        System.out.println("getUser 메서드 종료, 소요시간 : " + estimatedTime);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/info")
    public ResponseEntity<BaseRes<BibotUserInfo>> getUserInfo(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserInfo res = bibotUserService.getUserInfo(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/profile")
    public ResponseEntity<BaseRes<String>> addProfile(@RequestParam(name = "profile_url")
        MultipartFile file, @RequestParam UUID userId){
        String res = "";
        try {
             res = bibotUserService.addProfile(userId, file);
        } catch (Exception e) {
            throw new CustomException(ResCode.NOT_FOUND);
        }
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @DeleteMapping("/profile")
    public ResponseEntity<BaseRes<String>> deleteProfile(@RequestParam UUID userId){
        String res = bibotUserService.deleteProfile(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PutMapping("/profile")
    public ResponseEntity<BaseRes<String>> updateProfile(@RequestParam(name = "profile_url")
    MultipartFile file, @RequestParam UUID userId){
        String res = "";
        try {
            res = bibotUserService.updateProfile(userId, file);
        } catch (Exception e){
            throw new CustomException(ResCode.NOT_FOUND);
        }
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}
