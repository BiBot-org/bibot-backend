package com.coderecipe.v1.user.approval.service;

import com.coderecipe.v1.user.approval.dto.ApprovalDTO;

import java.util.List;

public interface IApprovalService {

    List<String> addApproval(List<ApprovalDTO> req);
}
