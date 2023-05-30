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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/approval")
@RequiredArgsConstructor
@Slf4j
public class ApprovalController {

    private final IApprovalService iApprovalService;

    @PostMapping
    @Deprecated(since = "개발 초기 테스트용")
    public ResponseEntity<BaseRes<List<String>>> addApproval(
            @RequestBody List<ApprovalDTO> approvalData) {
        List<String> result = iApprovalService.addApproval(approvalData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping
    @Deprecated(since = "개발 초기 테스트용")
    public ResponseEntity<BaseRes<ApprovalDTO>> getApproval() {
        return ResponseEntity.ok().body(BaseRes.success(new ApprovalDTO(
                "test",
                UUID.randomUUID(),
                UUID.randomUUID(),
                ApprovalStatus.APPROVED,
                "자동 승인",
                true
        )));
    }
}
