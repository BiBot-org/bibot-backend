package com.coderecipe.v1.open.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserReq {
    private UserReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VerifyEmailReq {
        private String email;
        private Integer verifyCode;
    }
}
