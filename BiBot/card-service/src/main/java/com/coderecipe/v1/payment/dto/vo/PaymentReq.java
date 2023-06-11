package com.coderecipe.v1.payment.dto.vo;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.global.utils.StringUtils;

import java.time.LocalDate;
import java.util.List;

import com.coderecipe.v1.payment.model.PaymentHistory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class PaymentReq {

    private PaymentReq() {
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
        private String paymentDate;
        private String destinationNum;
        private Long cardId;
        private List<ProductOrderList> productOrderList;

        public Integer getTotalPrice() {
            return productOrderList.stream().mapToInt(ProductOrderList::getAmount).sum();
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
        private Integer totalPrice;
        private String regTime;

        public static CreateMockReceiptReq of(PaymentHistory paymentHistory, String paymentCardCompany,
                                              MockPaymentReq req) {
            CreateMockReceiptReq result = ModelMapperUtils.getModelMapper()
                    .map(req, CreateMockReceiptReq.class);
            result.setPaymentCode(paymentHistory.getId());
            result.setPaymentDateStr(req.getPaymentDate());
            result.setPaymentCardCompany(paymentCardCompany);
            result.setTotalPrice(req.getTotalPrice());
            result.setRegTime(paymentHistory.getRegTime().toString());
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchPaymentHistoryReq {
        private Long cardId;
        private LocalDate startDate;
        private LocalDate endDate;
        private Pageable pageable;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApprovalEndPaymentReq {
        private String approvalId;
        private String paymentId;
    }

}
