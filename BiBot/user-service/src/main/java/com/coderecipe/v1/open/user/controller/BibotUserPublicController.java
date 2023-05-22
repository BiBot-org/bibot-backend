package com.coderecipe.v1.open.user.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.open.user.service.BibotUserPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/v1/user")
public class BibotUserPublicController {

    private final BibotUserPublicService bibotUserPublicService;
    @GetMapping("/isInit")
    public ResponseEntity<BaseRes<Boolean>> isInit() {
        Boolean res = bibotUserPublicService.isInit();
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
