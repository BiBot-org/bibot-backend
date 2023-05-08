package com.coderecipe.v1.approval.model;

import com.coderecipe.v1.approval.dto.ApprovalDTO;
import com.coderecipe.v1.approval.enums.ApprovalStatus;
import com.coderecipe.v1.category.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "approval")
public class Approval {

    @Id
    @Column(name = "approval_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "requester_id")
    private String requesterId;

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