package com.coderecipe.receiptservice.v1.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReceiptDTO {

    private String id;
    private Long cardId;
    private String paymentDestination;
    private Integer amount;
    private String approvalId;
    private boolean isRequested;

}
