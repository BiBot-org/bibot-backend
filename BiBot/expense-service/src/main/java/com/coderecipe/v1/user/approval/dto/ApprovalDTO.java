package com.coderecipe.v1.user.approval.dto;

import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.model.Approval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalDTO {

    private String id;
    private UUID managerId;
    private UUID requesterId;
    private ApprovalStatus status;
    private String comment;
    private boolean isAutomated;

    public static ApprovalDTO of (Approval entity) {
        return ApprovalDTO.builder()
                .id(entity.getId())
                .managerId(entity.getManagerId())
                .requesterId(entity.getRequesterId())
                .status(entity.getStatus())
                .comment(entity.getComment())
                .isAutomated(entity.isAutomated())
                .build();
    }

    public static Page<ApprovalDTO> of (Page<Approval> entities) {
        return entities.map(ApprovalDTO::of);
    }

}
