package com.coderecipe.v1.biz_trip_approval.model;

import com.coderecipe.v1.biz_trip_approval.dto.BizTripApprovalDTO;
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

    @Column(name = "trip_id")
    private int tripId;

    @Column(name = "approval_id")
    private int approvalId;

    public static BizTripApproval of(BizTripApprovalDTO dto) {
        return BizTripApproval.builder()
            .id(dto.getId())
            .tripId(dto.getTripId())
            .approvalId(dto.getApprovalId())
            .build();
    }
}
