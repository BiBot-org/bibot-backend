package com.coderecipe.v1.card.dto.vo;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.card.model.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CardRes {

    private CardRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardInfoRes {
        private Long id;
        private UUID userId;
        private String cardNo;
        private String cardCompany;
        private String cardValid;

        public static CardInfoRes of(Card entity) {
            return ModelMapperUtils.getModelMapper().map(entity, CardInfoRes.class);
        }
    }

}
