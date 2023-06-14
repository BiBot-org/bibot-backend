package com.coderecipe.global.constant.error;

import com.coderecipe.global.constant.enums.ResCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    final ResCode errorCode;

    public CustomException(ResCode resCode) {
        super(resCode.getMessage());
        this.errorCode = resCode;
    }
}
