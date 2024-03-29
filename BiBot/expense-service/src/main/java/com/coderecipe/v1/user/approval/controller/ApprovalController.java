package com.coderecipe.v1.user.approval.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.service.IApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.util.UUID;

@Tag(name = "경비 내역 Service API", description = "경비 내역 Service API 문서 입니다.")
@RestController
@RequestMapping("/api/v1/approval")
@RequiredArgsConstructor
@Slf4j
public class ApprovalController {

    private final IApprovalService iApprovalService;

    @Operation(summary = "경비 요청 내역 단건 조회", description = "경비 요청 내역 단건 조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<ApprovalDTO>> getApprovalInfo(@RequestParam(name = "id", defaultValue = "") String id) {
        ApprovalDTO res = iApprovalService.getApprovalInfo(id);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "경비 요청 내역 검색", description = "경비 요청 내역 검색 API 입니다. 검색 조건으로는 날짜, 상태, 경비 카테고리, 페이지번호 입니다.")
    @GetMapping("/search")
    public ResponseEntity<BaseRes<ApprovalRes.SearchApprovalInfoRes>> searchApproval(
            @RequestParam(name = "startDate", defaultValue = "") LocalDate startDate,
            @RequestParam(name = "endDate", defaultValue = "") LocalDate endDate,
            @RequestParam(name = "status", defaultValue = "") ApprovalStatus status,
            @RequestParam(name = "categoryId", defaultValue = "") Long categoryId,
            @PageableDefault(page = 6, sort = "regTime", direction = Sort.Direction.DESC) Pageable pageable) {
        ApprovalRes.SearchApprovalInfoRes res = iApprovalService.searchApprovalInfo(new ApprovalReq.SearchApprovalInfoReq(startDate, endDate, status, categoryId), pageable);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "경비 요청 현황 조회", description = "카테고리 별 경비 요청 현황 조회 API 입니다.")
    @GetMapping("/status")
    public ResponseEntity<BaseRes<ApprovalRes.GetExpenseProcessingStatusByCategoryRes>> getExpenseProcessingStatusByCategory(
            @RequestParam(name = "categoryId", defaultValue = "") Long categoryId, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        ApprovalRes.GetExpenseProcessingStatusByCategoryRes res = iApprovalService.getExpenseProcessingstatusByCategory(userId, categoryId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "경비 요청 현황 조회")
    @GetMapping("/status/all")
    @Deprecated(since = "2023-06-09, 굳이 나눌 필요가 없어서 곧 삭제 예정")
    public ResponseEntity<BaseRes<ApprovalRes.GetExpenseProcessingStatusByCategoryRes>> getAllExpenseProcessingStatus(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        ApprovalRes.GetExpenseProcessingStatusByCategoryRes res = iApprovalService.getAllExpenseProcessingStatus(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
