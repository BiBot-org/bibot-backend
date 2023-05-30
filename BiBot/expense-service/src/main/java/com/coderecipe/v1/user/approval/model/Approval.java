package com.coderecipe.v1.user.approval.model;

import com.coderecipe.global.constant.entity.BaseImmutableTimeEntity;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.category.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "approval")
public class Approval extends BaseImmutableTimeEntity {

    @Id
    @Column(name = "id")
    private String id = StringUtils.generateDateTimeCode(StringUtils.CODE_APPROVE);

    @Column(name = "manager_id")
    private UUID managerId;

    @Column(name = "requester_id")
    private UUID requesterId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_automated")
    private boolean isAutomated;

    public static Approval of(ApprovalDTO dto) {
        return Approval.builder()
                .id(dto.getId())
                .managerId(dto.getManagerId())
                .requesterId(dto.getRequesterId())
                .status(dto.getStatus())
                .comment(dto.getComment())
                .isAutomated(dto.isAutomated())
                .build();
    }

}