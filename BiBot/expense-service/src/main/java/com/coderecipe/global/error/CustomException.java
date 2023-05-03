package com.coderecipe.global.error;

import com.coderecipe.global.enums.ResCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    final ResCode errorCode;
}
