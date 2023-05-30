package com.coderecipe.v1.user.approval.dto;

import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDTO {

    private String id;
    private UUID managerId;
    private UUID requesterId;
    private ApprovalStatus status;
    private String comment;
    private boolean isAutomated;
}
