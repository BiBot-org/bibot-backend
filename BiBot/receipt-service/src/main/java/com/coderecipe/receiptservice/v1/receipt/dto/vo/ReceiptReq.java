package com.coderecipe.receiptservice.v1.receipt.dto.vo;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.global.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

public class ReceiptReq {

    private ReceiptReq() {
        throw new IllegalStateException("VO Class");
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MockPaymentReq {

        private String paymentDestination;
        private String businessLicense;
        private String representationName;
        private String address;
        private String destinationNum;
        private Long cardId;
        private List<ProductOrderList> productOrderList;

        public Integer getTotalPrice() {
            return productOrderList.stream().mapToInt(ProductOrderList::getAmount).sum();
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateMockReceiptReq extends MockPaymentReq {

        private String cardName;
        private String paymentCode;
        private String paymentDateStr;
        private String paymentCardCompany;
        private Integer totalPrice;

        public static CreateMockReceiptReq of(String paymentCode, String paymentCardCompany,
                                              MockPaymentReq req) {
            CreateMockReceiptReq result = ModelMapperUtils.getModelMapper()
                    .map(req, CreateMockReceiptReq.class);
            result.setPaymentCode(paymentCode);
            result.setPaymentDateStr(StringUtils.generateDateStringRandom());
            result.setPaymentCardCompany(paymentCardCompany);
            result.setTotalPrice(req.getTotalPrice());
            return result;
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductOrderList {

        private String productName;
        private Integer productCost;
        private Integer count;

        public Integer getAmount() {
            return productCost * count;
        }
    }
}
