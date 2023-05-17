package com.coderecipe.v1.user.biz_trip_approval.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BizTripApprovalDTO {

    private int id;
    private int tripId;
    private int approvalId;
}
