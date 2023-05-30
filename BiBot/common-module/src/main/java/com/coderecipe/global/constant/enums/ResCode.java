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
    DUPLICATE_DEPARTMENT_NAME(HttpStatus.BAD_REQUEST, "해당 부서 이름이 존재합니다.", "ERROR-BR-003"),
    DUPLICATE_TEAM_NAME(HttpStatus.BAD_REQUEST, "해당 팀 이름이 존재합니다.", "ERROR-BR-004"),
    DUPLICATE_RANK_NAME(HttpStatus.BAD_REQUEST, "해당 직급 이름이 존재합니다.", "ERROR-BR-005"),
    DUPLICATE_USER_EMAIL(HttpStatus.BAD_REQUEST, "해당 유저 이메일이 존재합니다.", "ERROR-BR-006"),

    //UNAUTHORIZED
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다.", "ERROR-UA-000"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Token Expired", "ERROR-UA-001"),
    TOKEN_ILLEGAL_ARGUMENT(HttpStatus.UNAUTHORIZED, "잘못 된 토큰 요청입니다.", "ERROR-UA-002"),
    TOKEN_UNSUPPORTED(HttpStatus.UNAUTHORIZED, "지원하지 않는 토큰 양식입니다.", "ERROR-UA-003"),
    TOKEN_MALFORMED(HttpStatus.UNAUTHORIZED, "잘못 생성 된 토큰 입니다.", "ERROR-UA-004"),
    TOKEN_INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, "시그니쳐 키가 잘못 되었습니다.", "ERROR-UA-005"),

    //NOT_FOUND
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 요청 정보를 찾을 수 없습니다.", "ERROR-NF-000"),
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 카드를 찾을 수 없습니다.", "ERROR-NF-001"),
    DEPARTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 부서 정보를 찾을 수 없습니다.", "ERROR-NF-002"),
    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "팀 정보를 찾을 수 없습니다.", "ERROR-NF-003"),
    RANK_NOT_FOUND(HttpStatus.NOT_FOUND, "직급 정보를 찾을 수 없습니다.", "ERROR-NF-004"),
    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 공지를 찾을 수 없습니다.", "ERROR-NF-005"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.", "ERROR-NF-006"),

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
