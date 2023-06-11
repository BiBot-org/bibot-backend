package com.coderecipe.v1.open.user.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.open.user.service.BibotUserPublicService;
import com.coderecipe.v1.open.user.vo.UserReq.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/public/v1/user")
public class BibotUserPublicController {

    private final BibotUserPublicService bibotUserPublicService;
    private final Keycloak keycloak;

    @GetMapping("/isInit")
    public ResponseEntity<BaseRes<Boolean>> isInit() {
        Boolean res = bibotUserPublicService.isInit();
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/email")
    public ResponseEntity<BaseRes<String>> confirmEmail(
            @RequestParam(value = "email", defaultValue = "") String email
    ) {
        String res = bibotUserPublicService.sendVerificationEmail(email);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    //비밀번호 초기화용
    @GetMapping("/verify/email")
    public ResponseEntity<BaseRes<String>> verifyUserByEmail(@RequestParam(value = "email", defaultValue = "") String email) {
        String res = bibotUserPublicService.sendUserVerification(email);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/email")
    public ResponseEntity<BaseRes<Boolean>> verifyEmail(@RequestBody VerifyEmailReq req) {
        Boolean res = bibotUserPublicService.verifyEmail(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/verify/email")
    @Deprecated(since = "2023-06-09 이후로 삭제. 의도는 있었는데 결국 중복코드 그 이상 그이하도 아님.")
    public ResponseEntity<BaseRes<Boolean>> verifyUserEmail(@RequestBody VerifyEmailReq req) {
        Boolean res = bibotUserPublicService.verifyUserEmail(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/password/reset/email")
    public ResponseEntity<BaseRes<UUID>> resetPasswordByUserByEmail(@RequestBody VerifyEmailReq req) throws Exception {
        UUID res = bibotUserPublicService.resetPasswordByEmail(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
