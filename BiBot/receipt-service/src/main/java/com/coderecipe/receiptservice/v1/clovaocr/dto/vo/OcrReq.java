package com.coderecipe.receiptservice.v1.clovaocr.dto.vo;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OcrStartReq {
        private Long cardId;
        private Long categoryId;
        private String paymentId;
        private UUID userId;
        private String imageUrl;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestAutoApproval {
        private int totalPrice;
        private Long categoryId;
        private String receiptId;
        private Long cardId;
        private UUID userId;
    }

}
