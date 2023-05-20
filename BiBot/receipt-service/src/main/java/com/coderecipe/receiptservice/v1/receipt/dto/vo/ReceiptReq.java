package com.coderecipe.receiptservice.v1.receipt.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReceiptReq {

    private ReceiptReq() {
        throw new IllegalStateException("VO Class");
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateMockReceiptReq {

        private String paymentDestination;
        private String businessLicense;
        private String representationName;
        private String address;
        private Long cardId;
        private List<ProductOrderList> productOrderList;

        private String paymentCode;
        private String paymentDateStr;
        private String paymentCardCompany;

        public Integer getTotalPrice() {
            return this.productOrderList.stream().mapToInt(ProductOrderList::getAmount).sum();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductOrderList {

        private String productName;
        private String productCost;
        private Integer count;
        private Integer amount;
    }
}
