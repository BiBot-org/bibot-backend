package com.coderecipe.v1.user.approval.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.service.IApprovalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/approval")
@RequiredArgsConstructor
@Slf4j
public class ApprovalController {

    private final IApprovalService iApprovalService;

    @GetMapping
    public ResponseEntity<BaseRes<ApprovalDTO>> getApprovalInfo(@RequestParam(name = "id", defaultValue = "")String id){
        ApprovalDTO res = iApprovalService.getApprovalInfo(id);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

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

    @GetMapping("/status")
    public ResponseEntity<BaseRes<ApprovalRes.GetExpenseProcessingStatusByCategoryRes>> getExpenseProcessingStatusByCategory(
            @RequestParam(name = "categoryId", defaultValue = "") Long categoryId, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        ApprovalRes.GetExpenseProcessingStatusByCategoryRes res = iApprovalService.getExpenseProcessingstatusByCategory(userId, categoryId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/status/all")
    public ResponseEntity<BaseRes<ApprovalRes.GetExpenseProcessingStatusByCategoryRes>> getAllExpenseProcessingStatus(Principal principal){
        UUID userId = UUID.fromString(principal.getName());
        ApprovalRes.GetExpenseProcessingStatusByCategoryRes res = iApprovalService.getAllExpenseProcessingStatus(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
