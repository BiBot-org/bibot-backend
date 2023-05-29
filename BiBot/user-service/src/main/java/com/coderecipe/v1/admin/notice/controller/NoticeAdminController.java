package com.coderecipe.v1.admin.notice.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq.CreateNoticeReq;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq.UpdateNoticeReq;
import com.coderecipe.v1.admin.notice.service.NoticeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/notice")
public class NoticeAdminController {

    private final NoticeAdminService noticeAdminService;


    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @PostMapping
    public ResponseEntity<BaseRes<Long>> createNotice(@RequestBody CreateNoticeReq req, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        Long result = noticeAdminService.createNotice(req, userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PutMapping
    public ResponseEntity<BaseRes<Long>> updateNotice(@RequestBody UpdateNoticeReq req, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        Long result = noticeAdminService.updateNotice(req, userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteNotice(@RequestParam(name = "id", defaultValue = "") Long id) {
        Long result = noticeAdminService.deleteNotice(id);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
