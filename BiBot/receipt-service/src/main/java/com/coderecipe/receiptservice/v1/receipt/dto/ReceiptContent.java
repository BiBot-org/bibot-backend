package com.coderecipe.receiptservice.v1.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptContent {
    private String amountName;
    private String countName;
    private String priceName;

}
