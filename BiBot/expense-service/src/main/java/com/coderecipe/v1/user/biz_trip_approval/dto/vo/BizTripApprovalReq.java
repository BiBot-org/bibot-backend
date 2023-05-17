package com.coderecipe.v1.user.biz_trip_approval.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BizTripApprovalReq {

    private BizTripApprovalReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    public static class RequestBizTripApprovalReq {

        private int id;
        private int tripId;
        private int approvalId;
    }
}
