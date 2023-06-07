package com.coderecipe.v1.admin.approval.service;

import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApprovalAdminService {
    List<ApprovalRes.ApprovalInfo> getApprovalThumbnail();
    ApprovalRes.SearchApprovalInfoRes searchApprovalInfo(ApprovalReq.SearchApprovalInfoReq req, Pageable pageable);
}
