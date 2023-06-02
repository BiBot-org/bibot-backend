package com.coderecipe.global.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomLogFormat {
    GENERATE_RECEIPT_ERROR("generate receipt error : %s"),
    GENERATE_RECEIPT_SUCCESS("generate receipt success : %s");

    private final String formatString;
}
