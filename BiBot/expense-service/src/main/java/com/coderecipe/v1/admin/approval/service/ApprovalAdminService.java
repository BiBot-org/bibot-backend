package com.coderecipe.v1.admin.approval.service;

import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminReq;
import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminRes;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ApprovalAdminService {
    List<ApprovalRes.ApprovalInfo> getApprovalThumbnail();

    ApprovalAdminRes.SearchAdminApprovalRes searchApprovalInfo(ApprovalReq.SearchApprovalInfoReq req, Pageable pageable);

    String approveExpense(ApprovalAdminReq.RequestApproval req, UUID userId);
}
