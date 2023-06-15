package com.coderecipe.v1.user.approval.service;

import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IApprovalService {


    ApprovalStatus autoApproval(ApprovalReq.RequestAutoApproval req);

    ApprovalStatus autoApprovalFail(ApprovalReq.RequestApprovalFail req);

    ApprovalRes.SearchApprovalInfoRes searchApprovalInfo(ApprovalReq.SearchApprovalInfoReq req, Pageable pageable);

    ApprovalDTO getApprovalInfo(String id);

    ApprovalRes.GetExpenseProcessingStatusByCategoryRes getExpenseProcessingstatusByCategory(UUID userId, Long categoryId);

    ApprovalRes.GetExpenseProcessingStatusByCategoryRes getAllExpenseProcessingStatus(UUID userId);

}
