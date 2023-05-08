package com.coderecipe.global.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ResCode {

    //BAD_REQUEST
    OK(HttpStatus.OK, "요청 성공", "STATUS-OK"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청이 올바르지 않습니다.", "ERROR-BR-000"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다.", "ERROR-BR-001"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "올바르지 않은 토큰 요청입니다.", "ERROR-BR-002"),

    //UNAUTHORIZED
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다.", "ERROR-UA-000"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Token Expired", "ERROR-UA-001"),
    TOKEN_ILLEGAL_ARGUMENT(HttpStatus.UNAUTHORIZED, "잘못 된 토큰 요청입니다.", "ERROR-UA-002"),
    TOKEN_UNSUPPORTED(HttpStatus.UNAUTHORIZED, "지원하지 않는 토큰 양식입니다.", "ERROR-UA-003"),
    TOKEN_MALFORMED(HttpStatus.UNAUTHORIZED, "잘못 생성 된 토큰 입니다.", "ERROR-UA-004"),
    TOKEN_INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, "시그니쳐 키가 잘못 되었습니다.", "ERROR-UA-005"),
    //NOT_FOUND
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 요청 정보를 찾을 수 없습니다.", "ERROR-NF-000"),

    //FORBIDDEN
    FORBIDDEN(HttpStatus.FORBIDDEN, "금지된 요청입니다.", "ERROR-FB-000"),
    REFRESH_TOKEN_EXPIRED(HttpStatus.FORBIDDEN, "리프레쉬 토큰이 만료되었습니다.", "ERROR-FB-001"),

    //CONFLICT
    CONFLICT(HttpStatus.CONFLICT, "해당 요청에 대해 충돌이 일어났습니다.", "ERROR-CF-000"),

    //INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다. 관리자에게 문의하세요",
            "ERROR-ISE-000");

    private final HttpStatus httpStatus;
    private final String message;
    private final String errorCode;
}
