package com.coderecipe.v1.approval.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.approval.dto.ApprovalDTO;
import com.coderecipe.v1.approval.service.IApprovalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense/v1/approval")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ApprovalController {

    private final IApprovalService iApprovalService;

    @PostMapping("/add")
    public ResponseEntity<BaseRes<List<Long>>> addApproval(
            @RequestBody List<ApprovalDTO> approvalData) {
        List<Long> result = iApprovalService.addApproval(approvalData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
