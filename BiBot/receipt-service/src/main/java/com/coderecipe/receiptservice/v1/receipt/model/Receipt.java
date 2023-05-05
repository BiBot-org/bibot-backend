package com.coderecipe.receiptservice.v1.receipt.model;

import com.coderecipe.global.constant.entity.BaseImmutableEntity;
import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.receiptservice.v1.receipt.dto.ReceiptDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receipt")
public class Receipt extends BaseImmutableEntity {
    @Id
    @Column(name = "id")
    String receiptId = StringUtils.generateReceiptCode();

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "approve_id")
    String approveId;

    @Column(name = "payment_id")
    String paymentId;

    @Column(name = "image_url")
    String imageUrl;

    public static Receipt of(ReceiptDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, Receipt.class);
    }

}
