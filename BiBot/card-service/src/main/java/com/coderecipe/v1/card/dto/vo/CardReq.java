package com.coderecipe.v1.card.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class CardReq {

    private CardReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestCard {

        private Long id;
        private UUID userId;
        private String cardNo;
        private String cardCompany;
        private String cardCvc;
        private String cardValid;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateCard {
        private String cardNo;
        private String cardCompany;
        private String cardCvc;
        private String cardValid;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardId {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestGetPayments {
        private Long cardId;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
    }

}
