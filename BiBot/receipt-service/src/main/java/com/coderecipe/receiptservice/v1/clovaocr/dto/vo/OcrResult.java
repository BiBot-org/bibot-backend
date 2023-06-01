package com.coderecipe.receiptservice.v1.clovaocr.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class OcrResult {
    private OcrResult() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OcrResultInfo {
        private StoreInfoRes storeInfo;
        private PaymentInfoRes paymentInfo;
        private List<PurchasedItem> items;
        private String totalPrice;

        public static OcrResultInfo of(OCRtoJSONRes.Result result) {
            return new OcrResultInfo(
                    StoreInfoRes.of(result.getStoreInfo()),
                    PaymentInfoRes.of(result.getPaymentInfo()),
                    PurchasedItem.of(result.getSubResults()),
                    result.getTotalPrice().getPrice().getText()
            );
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreInfoRes {
        private String storeName;
        private String bizNum;
        private List<String> address;
        private List<String> tel;

        public static StoreInfoRes of(OCRtoJSONRes.StoreInfo storeInfo) {
            return new StoreInfoRes(storeInfo.getName().getText(),
                    storeInfo.getBizNum().getText(),
                    storeInfo.getAddresses().stream().map(OCRtoJSONRes.Address::getText).toList(),
                    storeInfo.getTel().stream().map(OCRtoJSONRes.Tel::getText).toList());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentInfoRes {
        private String date;
        private CardInfoRes cardInfo;
        private String confirmNum;

        public static PaymentInfoRes of(OCRtoJSONRes.PaymentInfo paymentInfo) {
            return new PaymentInfoRes(
                    paymentInfo.getDate().getText(),
                    CardInfoRes.of(paymentInfo.getCardInfo()),
                    paymentInfo.getConfirmNum().getText()
            );
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardInfoRes {
        private String cardCompany;
        private String number;

        public static CardInfoRes of(OCRtoJSONRes.CardInfo cardInfo) {
            return new CardInfoRes(
                    cardInfo.getCompany().getText(),
                    cardInfo.getNumber().getText()
            );
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PurchasedItem {
        private String name;
        private String count;
        private String price;

        public static PurchasedItem of(OCRtoJSONRes.Item item) {

            return new PurchasedItem(item.getName().getText() != null ? item.getName().getText() : "",
                    item.getCount() != null && item.getCount().getText() != null ? item.getCount().getText() : "",
                    item.getPrice().getPrice().getText() != null ? item.getPrice().getPrice().getText() : "");
        }

        public static List<PurchasedItem> of(List<OCRtoJSONRes.SubResult> subResults) {
            return subResults.stream().flatMap(s -> s.getItems().stream()).map(PurchasedItem::of).toList();
        }
    }
}
