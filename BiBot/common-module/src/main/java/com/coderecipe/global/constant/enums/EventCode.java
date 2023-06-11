package com.coderecipe.global.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventCode {
    PAYMENT_SUCCESS("EVENT-PAY-001", "Mock 결제 성공"),

    OCR_START("EVENT-OCR-001", "OCR Start"),
    OCR_END("EVENT-OCR-002", "OCR End"),
    OCR_FAIL("EVENT-OCR-003", "OCR Fail"),

    AUTO_APPROVAL_END("EVENT-APR-001", "Approval End");

    private final String codeName;
    private final String eventName;
}
