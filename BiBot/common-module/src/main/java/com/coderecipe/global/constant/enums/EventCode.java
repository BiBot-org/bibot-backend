package com.coderecipe.global.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventCode {
    PAYMENT_SUCCESS("EVENT-PAY-001", "Mock 결제 성공", "payment_success"),
    PAYMENT_END("EVENT-PAY-002", "영수증 발급 성공", "paymend_end"),

    OCR_START("EVENT-OCR-001", "OCR Start", "ocr_start"),
    OCR_END("EVENT-OCR-002", "OCR End", "ocr_end"),
    OCR_FAIL("EVENT-OCR-003", "OCR Fail", "ocr_fail"),

    AUTO_APPROVAL_END("EVENT-APR-001", "Approval End", "approval_end_rcp"),
    AUTO_APPROVAL_END_PAYMENT("EVENT-APR-002", "Approval End / Payment", "approval_end_pay");

    private final String codeName;
    private final String eventName;
    private final String topic;
}
