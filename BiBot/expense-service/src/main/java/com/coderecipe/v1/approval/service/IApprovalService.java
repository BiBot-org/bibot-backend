package com.coderecipe.v1.approval.service;

import com.coderecipe.v1.approval.dto.ApprovalDTO;

import java.util.List;

public interface IApprovalService {

    List<Long> addApproval(List<ApprovalDTO> req);
}
