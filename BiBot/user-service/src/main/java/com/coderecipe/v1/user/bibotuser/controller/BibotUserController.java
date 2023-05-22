package com.coderecipe.v1.user.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.bibotuser.service.BibotUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class BibotUserController {

    private final BibotUserService bibotUserService;

    /**
     * @deprecated
     * - 유저 API에서 어드민 목적 API는 모두 제거합니다.
     */
    @Deprecated(since = "유저 서비스에 어드민 목적의 API는 모두 제거합니다.")
    @GetMapping("/isInit")
    public ResponseEntity<BaseRes<Boolean>> isInit() {
        Boolean res = bibotUserService.isInit();
        return ResponseEntity.ok().body(BaseRes.success(res));
    }


}
