package com.coderecipe.v1.card.dto;

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
}
