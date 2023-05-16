package com.coderecipe.v1.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {

    private String amountName;
    private String countName;
    private String priceName;
}
