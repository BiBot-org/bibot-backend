package com.coderecipe.v1.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Deprecated(since = "영수증 서비스 분리 후 삭제 예정입니다.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {

    private String amountName;
    private String countName;
    private String priceName;
}
