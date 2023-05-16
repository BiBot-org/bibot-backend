package com.coderecipe.global.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateTimeFormat {
    DATE_TIME_FORMAT_DEFAULT("yyyy-MM-dd-HH-mm-ss"),
    DATE_TIME_FORMAT_WITHOUT_SECOND("yyyy-MM-dd-HH-mm"),
    DATE_FORMAT("yyyy-MM-dd");

    private final String formatStr;
}
