package com.coderecipe.v1.open.user.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.open.user.service.BibotUserPublicService;
import com.coderecipe.v1.open.user.vo.UserReq.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "유저 OpenAPI", description = "유저 서비스 OpenAPI 입니다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/public/v1/user")
public class BibotUserPublicController {

    private final BibotUserPublicService bibotUserPublicService;
    private final Keycloak keycloak;

    @Operation(summary = "초기 셋업 상태 조회", description = "어드민 서비스 실행 시, 초기 셋업이 진행 되었는 지 확인하는 API 입니다.")
    @GetMapping("/isInit")
    public ResponseEntity<BaseRes<Boolean>> isInit() {
        Boolean res = bibotUserPublicService.isInit();
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "이메일 인증", description = "초기 셋업, 비밀번호 변경 시 사용되는 API 입니다.")
    @GetMapping("/email")
    public ResponseEntity<BaseRes<String>> confirmEmail(
            @RequestParam(value = "email", defaultValue = "") String email
    ) {
        String res = bibotUserPublicService.sendVerificationEmail(email);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    //비밀번호 초기화용
    @Operation(summary = "이메일 인증 검증 API", description = "이메일 인증 검증 API 입니다.")
    @GetMapping("/verify/email")
    public ResponseEntity<BaseRes<String>> verifyUserByEmail(@RequestParam(value = "email", defaultValue = "") String email) {
        String res = bibotUserPublicService.sendUserVerification(email);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "비밀번호 변경 목적 이메일 검증 API")
    @Deprecated(since = "2023-06-14 이후로 삭제 예정.")
    @PostMapping("/email")
    public ResponseEntity<BaseRes<Boolean>> verifyEmail(@RequestBody VerifyEmailReq req) {
        Boolean res = bibotUserPublicService.verifyEmail(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
    @Operation(summary = "이메일 검증 API")
    @PostMapping("/verify/email")
    @Deprecated(since = "2023-06-14 이후로 삭제. 의도는 있었는데 결국 중복코드 그 이상 그이하도 아님.")
    public ResponseEntity<BaseRes<Boolean>> verifyUserEmail(@RequestBody VerifyEmailReq req) {
        Boolean res = bibotUserPublicService.verifyUserEmail(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "비밀번호 리셋 이메일 인증 코드", description = "비밀번호 초기화 시 사용되는 API 입니다.")
    @PostMapping("/password/reset/email")
    public ResponseEntity<BaseRes<UUID>> resetPasswordByUserByEmail(@RequestBody VerifyEmailReq req) throws Exception {
        UUID res = bibotUserPublicService.resetPasswordByEmail(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
