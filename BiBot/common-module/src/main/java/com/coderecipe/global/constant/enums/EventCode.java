package com.coderecipe.global.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventCode {
    PAYMENT_SUCCESS("EVENT-001", "Mock 결제 성공");

    private final String codeName;
    private final String eventName;
}
