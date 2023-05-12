package com.coderecipe.v1.payment.service.impl;

import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.ProductOrderList;
import com.coderecipe.v1.payment.service.IPaymentHistoryService;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
@Slf4j
class PaymentHistoryImplTest {

    @Autowired
    IPaymentHistoryService paymentHistoryService;

    @Test
    @DisplayName("영수증 생성 테스트")
    public void createReceiptImageTest() {
        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();

        for (int i = 0; i < 2; i++) {
            PaymentHistoryDTO result = paymentHistoryService.addPayment(
                MockPaymentReq.builder()
                    .paymentDestination("스파로스 커피")
                    .businessLicense("1123-0035-11")
                    .representationName("노홍기")
                    .address("부산 해운대구 우동")
                    .cardId(1234L)
                    .productOrderList(
                        List.of(
                            ProductOrderList.builder()
                                .productName("아메리카노")
                                .count(1)
                                .productCost("2,000")
                                .amount(2000)
                                .build(),
                            ProductOrderList.builder()
                                .productName("카페라떼")
                                .count(1)
                                .productCost("2,000")
                                .amount(3000)
                                .build(),
                            ProductOrderList.builder()
                                .productName("흑당버블")
                                .count(1)
                                .productCost("4,000")
                                .amount(4000)
                                .build(),
                            ProductOrderList.builder()
                                .productName("카누")
                                .count(1)
                                .productCost("1,000")
                                .amount(1000)
                                .build()
                        )
                    )
                    .build()
            );
            log.info(result.toString());
        }
        stopWatch1.stop();
        System.out.println("Execution time: " + stopWatch1.getTotalTimeMillis() + "ms");
    }
}
