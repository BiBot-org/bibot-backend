package com.coderecipe.receiptservice.v1.receipt.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.receiptservice.v1.receipt.model.Receipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    String receiptId;
    UUID userId;
    String approveId;
    String paymentId;
    String imageUrl;

    public static ReceiptDTO of(Receipt entity) {
        return ModelMapperUtils.getModelMapper().map(entity, ReceiptDTO.class);
    }
}
