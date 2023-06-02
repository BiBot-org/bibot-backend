package com.coderecipe.global.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventCode {
    PAYMENT_SUCCESS("EVENT-001", "Mock 결제 성공"),
    OCR_START("EVENT-002", "OCR Start"),
    OCR_END("EVENT-003", "OCR End");

    private final String codeName;
    private final String eventName;
}
