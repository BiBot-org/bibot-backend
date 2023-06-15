package com.coderecipe.receiptservice.v1.clovaocr.dto.vo;

import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;


public class OcrReq {
    private OcrReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetOcrDataReq {
        private String version;
        private String requestId;
        private String timestamp;
        private Map<String, String> image;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OcrStartReq {
        private Long cardId;
        private Long categoryId;
        private String paymentId;
        private UUID userId;
        private LocalDateTime regTime;
        private String imageUrl;

        public static OcrStartReq of(ReceiptReq.ApprovalStartReq req, String imageUrl) {
            return OcrStartReq.builder()
                    .cardId(req.getCardId())
                    .categoryId(req.getCategoryId())
                    .paymentId(req.getPaymentId())
                    .userId(req.getUserId())
                    .regTime(req.getRegTime())
                    .imageUrl(imageUrl)
                    .build();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestAutoApproval {
        private int totalPrice;
        private Long categoryId;
        private String receiptId;
        private Long cardId;
        private UUID userId;

        public static RequestAutoApproval of(int totalPrice, String receiptId, OcrStartReq req) {
            return RequestAutoApproval.builder()
                    .totalPrice(totalPrice)
                    .categoryId(req.getCategoryId())
                    .receiptId(receiptId)
                    .cardId(req.getCardId())
                    .userId(req.getUserId())
                    .build();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestApprovalFail {
        private Long categoryId;
        private String receiptId;
        private Long cardId;
        private UUID userId;
        private String message;

        public static RequestApprovalFail of(OcrStartReq req, String message, String receiptId) {
            return RequestApprovalFail.builder()
                    .categoryId(req.getCategoryId())
                    .receiptId(receiptId)
                    .cardId(req.getCardId())
                    .userId(req.getUserId())
                    .message(message)
                    .build();
        }
    }

}
