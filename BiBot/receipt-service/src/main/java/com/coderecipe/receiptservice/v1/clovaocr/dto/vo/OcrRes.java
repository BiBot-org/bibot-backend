package com.coderecipe.receiptservice.v1.clovaocr.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class OcrRes {
    private OcrRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OcrEndResponse {
        private OCRResponse ocrResponse;
        private String receiptId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OCRResponse {
        private OcrResult.StoreInfoRes storeInfo;
        private OcrResult.PaymentInfoRes paymentInfo;
        private List<OcrResult.PurchasedItem> items;
        private String totalPrice;

        public static OCRResponse of(OCRtoJSONRes.Result result) {
            return new OcrRes.OCRResponse(
                    OcrResult.StoreInfoRes.of(result.getStoreInfo()),
                    OcrResult.PaymentInfoRes.of(result.getPaymentInfo()),
                    OcrResult.PurchasedItem.of(result.getSubResults()),
                    result.getTotalPrice().getPrice().getText().replaceAll(",", "")
            );
        }
    }
}
