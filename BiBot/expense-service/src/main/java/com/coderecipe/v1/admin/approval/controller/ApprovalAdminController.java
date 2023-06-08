package com.coderecipe.v1.admin.approval.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminReq;
import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminRes;
import com.coderecipe.v1.admin.approval.service.ApprovalAdminService;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/approval")
public class ApprovalAdminController {

    private final ApprovalAdminService approvalAdminService;

    @GetMapping("/thumbnail")
    public ResponseEntity<BaseRes<List<ApprovalRes.ApprovalInfo>>> getApprovalThumbnailList() {
        List<ApprovalRes.ApprovalInfo> result = approvalAdminService.getApprovalThumbnail();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

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

    @PutMapping
    public ResponseEntity<BaseRes<String>> approveExpense(@RequestBody ApprovalAdminReq.RequestApproval req, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        String res = approvalAdminService.approveExpense(req, userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
