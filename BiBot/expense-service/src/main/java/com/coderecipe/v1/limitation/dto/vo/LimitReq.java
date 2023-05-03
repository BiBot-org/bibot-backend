package com.coderecipe.v1.limitation.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

public class LimitReq {

    private LimitReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    public static class RequestLimitReq {

        private int id;
        private int categoryId;
        private int rankId;
        private int automatedCost;
    }
}
