package com.coderecipe.v1.user.notice.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.notice.dto.NoticeDTO;
import com.coderecipe.v1.user.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<BaseRes<NoticeDTO>> getNotice(@RequestParam(name = "id", defaultValue = "") Long id) {
        NoticeDTO result = noticeService.getNotice(id);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/main")
    public ResponseEntity<BaseRes<List<NoticeDTO>>> getNoticeMain() {
        List<NoticeDTO> result = noticeService.getNoticeMain();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}