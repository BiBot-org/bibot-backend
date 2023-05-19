package com.coderecipe.v1.user.approval.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.service.IApprovalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/approval")
@RequiredArgsConstructor
@Slf4j
public class ApprovalController {

    private final IApprovalService iApprovalService;

    @PostMapping
    public ResponseEntity<BaseRes<List<Long>>> addApproval(
            @RequestBody List<ApprovalDTO> approvalData) {
        List<Long> result = iApprovalService.addApproval(approvalData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping
    public ResponseEntity<BaseRes<ApprovalDTO>> getApproval() {
        return ResponseEntity.ok().body(BaseRes.success(new ApprovalDTO(
                1L,
                "{manager-uuid}",
                "{requester-uuid}",
                ApprovalStatus.APPROVED,
                "자동 승인",
                true
        )));
    }
}