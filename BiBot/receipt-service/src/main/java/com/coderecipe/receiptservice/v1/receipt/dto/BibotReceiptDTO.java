package com.coderecipe.receiptservice.v1.receipt.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.receiptservice.v1.receipt.model.BibotReceipt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BibotReceiptDTO {

    private String id;
    private Long cardId;
    private String paymentDestination;
    private Integer amount;
    private String approvalId;
    private boolean isRequested;

    public static BibotReceiptDTO of(BibotReceipt receipt) {
        return ModelMapperUtils.getModelMapper().map(receipt, BibotReceiptDTO.class);
    }

}
