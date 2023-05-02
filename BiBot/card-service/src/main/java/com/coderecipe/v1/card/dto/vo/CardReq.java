package com.coderecipe.v1.card.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CardReq {

    private CardReq () {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    public static class RequestCard {
        private Long id;
        private String cardNo;
        private String cardCompany;
        private String cardValid;
    }

}
