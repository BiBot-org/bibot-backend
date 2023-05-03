package com.coderecipe.global.constant;

import com.coderecipe.global.enums.ResCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseRes<T> {

    private final HttpStatus status;
    private final T data;
    private final String message;

    public static <T> BaseRes<T> success(T resultData) {
        return new BaseRes<>(HttpStatus.OK, resultData, "SUCCESS");
    }

    public static BaseRes<String> fail(ResCode err) {
        return new BaseRes<>(err.getHttpStatus(), err.getErrorCode(), err.getMessage());
    }
}
