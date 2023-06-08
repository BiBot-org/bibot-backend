package com.coderecipe.receiptservice.v1.receipt.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.receiptservice.v1.receipt.model.BibotReceipt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BibotReceiptDTO {

    private String receiptId;
    private UUID userId;
    private Long cardId;
    private String approveId;
    private String paymentId;
    private String imageUrl;
    private Map<String, Object> ocrResult;

    public static BibotReceiptDTO of(BibotReceipt receipt) {
        return ModelMapperUtils.getModelMapper().map(receipt, BibotReceiptDTO.class);
    }

}
