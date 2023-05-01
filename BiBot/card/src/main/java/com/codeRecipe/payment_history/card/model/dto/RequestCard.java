package com.codeRecipe.payment_history.card.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCard {
    private Long id;
    private String cardNo;
    private String cardCompany;
    private String cardValid;
}
