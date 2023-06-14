package com.coderecipe.v1.admin.approval.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminReq;
import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminRes;
import com.coderecipe.v1.admin.approval.service.ApprovalAdminService;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Tag(name = "경비 결재 Admin API", description = "경비 결재 Admin API 문서 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/approval")
public class ApprovalAdminController {

    private final ApprovalAdminService approvalAdminService;

    @Operation(summary = "경비 요청 내역 썸네일", description = "어드민 페이지에서 경비 요청내역 썸네일 페이지 출력에 사용되는 API 입니다.")
    @GetMapping("/thumbnail")
    public ResponseEntity<BaseRes<List<ApprovalRes.ApprovalInfo>>> getApprovalThumbnailList() {
        List<ApprovalRes.ApprovalInfo> result = approvalAdminService.getApprovalThumbnail();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "경비 요청 내역 검색", description = "경비 요청 내역 검색 API 입니다. 검색 조건으로는 날짜, 상태, 경비 카테고리, 페이지 번호가 있습니다.")
    @GetMapping("/search")
    public ResponseEntity<BaseRes<ApprovalAdminRes.SearchAdminApprovalRes >> searchApproval(
            @RequestParam(name = "startDate", defaultValue = "") LocalDate startDate,
            @RequestParam(name = "endDate", defaultValue = "") LocalDate endDate,
            @RequestParam(name = "status", defaultValue = "") ApprovalStatus status,
            @RequestParam(name = "categoryId", defaultValue = "") Long categoryId,
            @PageableDefault(page = 6, sort = "regTime", direction = Sort.Direction.DESC) Pageable pageable) {
        ApprovalAdminRes.SearchAdminApprovalRes res = approvalAdminService.searchApprovalInfo(new ApprovalReq.SearchApprovalInfoReq(startDate, endDate, status, categoryId), pageable);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "경비 요청 처리", description = "자동 결재 처리가 되지 않은 (자동 결재 한도 초과) 처리 요청을 처리해주는 API 입니다.")
    @PutMapping
    public ResponseEntity<BaseRes<String>> approveExpense(@RequestBody ApprovalAdminReq.RequestApproval req, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        String res = approvalAdminService.approveExpense(req, userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
