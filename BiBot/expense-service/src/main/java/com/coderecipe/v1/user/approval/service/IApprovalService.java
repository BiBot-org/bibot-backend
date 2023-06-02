package com.coderecipe.v1.user.approval.service;

import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;

public interface IApprovalService {
    ApprovalStatus autoApproval(ApprovalReq.RequestAutoApproval req);

    String approvalExpense(String approvalId, ApprovalStatus status, String comment);


}
