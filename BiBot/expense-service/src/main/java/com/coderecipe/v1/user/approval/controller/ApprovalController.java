package com.coderecipe.v1.user.approval.controller;

import com.coderecipe.v1.user.approval.service.IApprovalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/approval")
@RequiredArgsConstructor
@Slf4j
public class ApprovalController {

    private final IApprovalService iApprovalService;


}
