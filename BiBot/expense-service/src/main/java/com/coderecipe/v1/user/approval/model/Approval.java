package com.coderecipe.v1.user.approval.model;

import com.coderecipe.global.constant.entity.BaseTimeEntity;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.category.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "approval")
public class Approval extends BaseTimeEntity {

    @Id
    @Column(name = "id")
    private String id = StringUtils.generateDateTimeCode(StringUtils.CODE_APPROVE);

    @Column(name = "manager_id")
    private UUID managerId;

    @Column(name = "requester_id")
    private UUID requesterId;

    @Column(name = "receipt_id")
    private String receiptId;

    @Column(name = "amount")
    private Integer amount;

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

    public void updateApprovalStatus(ApprovalStatus status) {
        this.status = status;
    }

    public void updateManagerId(UUID userId) {
        this.managerId = userId;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }

    public void approvalExpense(ApprovalStatus status, String comment) {
        this.status = status;
        this.comment = comment;
    }

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

    public static Approval init(Category category, UUID userId, int amount, String receiptId) {
        return Approval.builder()
                .id(StringUtils.generateDateTimeCode(StringUtils.CODE_APPROVE))
                .requesterId(userId)
                .receiptId(receiptId)
                .amount(amount)
                .category(category)
                .build();
    }

    public static Approval init(Category category, UUID userId, String receiptId) {
        return Approval.builder()
                .id(StringUtils.generateDateTimeCode(StringUtils.CODE_APPROVE))
                .requesterId(userId)
                .receiptId(receiptId)
                .category(category)
                .build();
    }

}