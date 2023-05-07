package com.coderecipe.receiptservice.model;

import com.coderecipe.global.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ReceiptTest {
    @Test
    @DisplayName("영수증 코드 생성 함수 테스트")
    void testReceiptCodeFunction() {
        for (int i = 0; i < 10; i++) {
            log.info(StringUtils.generateReceiptCode());
        }
    }
}
