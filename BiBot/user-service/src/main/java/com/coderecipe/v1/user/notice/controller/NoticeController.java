package com.coderecipe.v1.user.notice.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.notice.dto.NoticeDTO;
import com.coderecipe.v1.admin.notice.enums.NoticeType;
import com.coderecipe.v1.user.notice.dto.vo.NoticeRes.SearchNoticeRes;
import com.coderecipe.v1.user.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Tag(name = "공지사항 Service API", description = "공지사항 Service API 문서 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 조회", description = "공지사항 단건 조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<NoticeDTO>> getNotice(@RequestParam(name = "id", defaultValue = "") Long id) {
        NoticeDTO result = noticeService.getNotice(id);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }


    @Operation(summary = "공지사항 검색", description = "공지사항 검색 API 입니다. 검색 조건으로는 제목, 타입, 페이지 번호가 있습니다.")
    @GetMapping("/search")
    public ResponseEntity<BaseRes<SearchNoticeRes>> searchNotice(
            @RequestParam(name = "title", defaultValue = "") String title,
            @RequestParam(name = "type", defaultValue = "") NoticeType type,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchNoticeRes result = noticeService.searchNotice(title, type, pageable);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "공지사항 썸네일 출력", description = "메인 화면 공지사항 썸네일 출력 API 입니다.")
    @GetMapping("/main")
    public ResponseEntity<BaseRes<List<NoticeDTO>>> getNoticeMain(Principal principal) {
        List<NoticeDTO> result = noticeService.getNoticeMain();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
