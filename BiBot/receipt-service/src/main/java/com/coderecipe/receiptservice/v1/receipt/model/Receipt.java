package com.coderecipe.receiptservice.v1.receipt.model;

import com.coderecipe.global.constant.entity.BaseImmutableEntity;
import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.receiptservice.v1.receipt.dto.ReceiptDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "receipt")
public class Receipt extends BaseImmutableEntity {
    @Id
    @Column(name = "id")
    private String receiptId = StringUtils.generateReceiptCode();

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "approve_id")
    private String approveId;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "image_url")
    private String imageUrl;

    public static Receipt of(ReceiptDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, Receipt.class);
    }

}
