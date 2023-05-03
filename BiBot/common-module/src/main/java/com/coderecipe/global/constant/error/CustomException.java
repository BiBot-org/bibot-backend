package com.coderecipe.global.constant.error;

import com.coderecipe.global.constant.enums.ResCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    final ResCode errorCode;
}
