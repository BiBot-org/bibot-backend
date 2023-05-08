package com.coderecipe.global.constant.error;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.global.constant.enums.ResCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<BaseRes<String>> handleCustomException(CustomException e) {
        log.error(e.errorCode.getMessage());
        return ResponseEntity.status(e.errorCode.getHttpStatus())
                .body(BaseRes.fail(e.errorCode));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseRes<String>> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseRes.fail(ResCode.INTERNAL_SERVER_ERROR));
    }
}
