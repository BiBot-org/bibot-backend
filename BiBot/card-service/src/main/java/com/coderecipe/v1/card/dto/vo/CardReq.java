package com.coderecipe.v1.card.dto.vo;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

public class CardReq {

    private CardReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    public static class RequestCard {

        private Long id;
        private UUID userId;
        private String cardNo;
        private String cardCompany;
        private String cardCvc;
        private String cardValid;
    }

}
