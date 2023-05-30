package com.coderecipe.receiptservice.v1.receipt.dto.vo;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.global.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
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
        private Long cardId;
        private List<ProductOrderList> productOrderList;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchPaymentHistoryReq {
        private Long cardId;
        private LocalDate startDate;
        private LocalDate endDate;
        private Pageable pageable;
    }

}
