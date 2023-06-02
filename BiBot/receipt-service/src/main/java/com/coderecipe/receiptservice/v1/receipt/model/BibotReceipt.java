package com.coderecipe.receiptservice.v1.receipt.model;

import com.coderecipe.global.constant.entity.BaseImmutableEntity;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OcrReq;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "receipt")
public class BibotReceipt extends BaseImmutableEntity {
    @Id
    @Column(name = "id")
    private String receiptId = StringUtils.generateDateTimeCode(StringUtils.CODE_RECEIPT);

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "approve_id")
    private String approveId;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "ocr_result")
    private String ocrResult;

    public static BibotReceipt of(OcrReq.OcrStartReq req) {
        return BibotReceipt.builder()
                .receiptId(StringUtils.generateDateTimeCode(StringUtils.CODE_RECEIPT))
                .userId(req.getUserId())
                .cardId(req.getCardId())
                .paymentId(req.getPaymentId())
                .imageUrl(req.getImageUrl())
                .build();
    }

}
