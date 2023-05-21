package com.coderecipe.v1.payment.dto.vo;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.global.utils.StringUtils;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;


public class PaymentReq {

    private PaymentReq() {
        throw new IllegalStateException("VO Class");
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MockPaymentReq {

        private String title;
        private Long cardId;
        private String paymentDateStr;
        private String approvalNo;
        private String cardCompany;
        private String paymentDestination;
        private String businessLicense;
        private String representationName;
        private String destinationNum;
        private String tel;
        private String destinationAddress;
        private List<ProductOrderList> productOrderList;
        private String vat;
        private String notice;


        public Integer getTotalPrice() {
            return this.productOrderList.stream().mapToInt(ProductOrderList::getAmount).sum();
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @JsonIgnoreProperties(ignoreUnknown =true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateMockReceiptReq extends MockPaymentReq {

        private String paymentCode;
        private String paymentDateStr;
        private String paymentCardCompany;

        public static CreateMockReceiptReq of(String paymentCode, String paymentCardCompany,
                                              MockPaymentReq req) {
            CreateMockReceiptReq result = ModelMapperUtils.getModelMapper()
                    .map(req, CreateMockReceiptReq.class);
            result.setPaymentCode(paymentCode);
            result.setPaymentDateStr(StringUtils.generateDateStringRandom());
            result.setPaymentCardCompany(paymentCardCompany);
            return result;
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
