package com.coderecipe.v1.user.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.BibotUserInfo;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.ChangeUserPassword;
import com.coderecipe.v1.user.bibotuser.service.BibotUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Tag(name = "유저 Service API", description = "유저 Service API 문서 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
public class BibotUserController {

    private final BibotUserService bibotUserService;

    @Operation(summary = "유저 정보 조회", description = "UUID 기반 유저 정보 조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<BibotUserDTO>> getUser(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserDTO res = bibotUserService.getUser(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "내 정보 조회", description = "요청자의 토큰 정보에 맞는 유저 정보를 리턴 해 주는 API 입니다.")
    @GetMapping("/myInfo")
    public ResponseEntity<BaseRes<BibotUserInfo>> getMyUserInfo(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        BibotUserInfo res = bibotUserService.getUserInfo(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 정보 상세 조회", description = "유저 정보, 팀 및 부서 정보까지 조회하는 API 입니다.")
    @GetMapping("/info")
    public ResponseEntity<BaseRes<BibotUserInfo>> getUserInfo(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserInfo res = bibotUserService.getUserInfo(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 프로필 업데이트 API", description = "유저 프로필 이미지를 업데이트 하는 API 입니다.")
    @PostMapping("/profile")
    public ResponseEntity<BaseRes<String>> addProfile(@RequestParam(name = "profile_url")
                                                      MultipartFile file, Principal principal) throws IOException {
        UUID userId = UUID.fromString(principal.getName());
        String res = bibotUserService.addProfile(userId, file);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 프로필 삭제 API", description = "유저 프로필 이미지 삭제 API 입니다.")
    @DeleteMapping("/profile")
    public ResponseEntity<BaseRes<String>> deleteProfile(@RequestParam UUID userId) {
        String res = bibotUserService.deleteProfile(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 프로필 변경 API")
    @Deprecated(since = "2023-06-14, POST API와 구별되는 특징이 없어서 삭제 될 예정")
    @PutMapping("/profile")
    public ResponseEntity<BaseRes<String>> updateProfile(@RequestParam(name = "profile_url")
                                                         MultipartFile file, Principal principal) throws IOException {
        UUID userId = UUID.fromString(principal.getName());
        String res = bibotUserService.updateProfile(userId, file);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "비밀번호 변경 API", description = "오픈 API와 차이점이 있다면, 로그인 이후 유저가 직접 비밀번호를 변경한다는 점.")
    @PutMapping("/password/reset")
    public ResponseEntity<BaseRes<Boolean>> resetPassword(@RequestBody ChangeUserPassword req, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        boolean res = bibotUserService.changePassword(userId, req.getNewPassword());
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}
