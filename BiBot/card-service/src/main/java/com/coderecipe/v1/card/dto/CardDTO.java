package com.coderecipe.v1.card.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.card.model.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private Long id;
    private String cardNo;
    private String cardCompany;
    private String cardValid;

    public static CardDTO of(Card entity) {
        return ModelMapperUtils.getModelMapper().map(entity, CardDTO.class);
    }
}
