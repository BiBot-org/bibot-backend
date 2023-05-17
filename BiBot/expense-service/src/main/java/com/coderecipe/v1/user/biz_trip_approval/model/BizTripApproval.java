package com.coderecipe.v1.user.biz_trip_approval.model;

import com.coderecipe.v1.user.approval.model.Approval;
import com.coderecipe.v1.user.biz_trip.model.BizTrip;
import com.coderecipe.v1.user.biz_trip_approval.dto.BizTripApprovalDTO;
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
@Table(name = "biz_trip_approval")
public class BizTripApproval {

    @Id
    @Column(name = "biz_trip_approval_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biztrip_id", nullable = false)
    private BizTrip bizTrip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_id", nullable = false)
    private Approval approval;


    public static BizTripApproval of(BizTripApprovalDTO dto) {
        return BizTripApproval.builder()
                .id(dto.getId())
                .build();
    }
}
