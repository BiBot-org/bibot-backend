package com.coderecipe.v1.approval.dto;

import com.coderecipe.v1.approval.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDTO {

    private Long id;
    private String managerId;
    private String requesterId;
    private ApprovalStatus status;
    private String comment;
    private boolean isAutomated;
}
