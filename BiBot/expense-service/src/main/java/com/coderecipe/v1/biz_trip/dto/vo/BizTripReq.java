package com.coderecipe.v1.biz_trip.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

public class BizTripReq {

    private BizTripReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    public static class RequestBizTripReq {

        private int id;
        private UUID userId;
        private Date date;
        private int amount;
    }
}
