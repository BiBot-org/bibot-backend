package com.coderecipe.v1.auth.dto.vo;

import com.coderecipe.v1.user.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthReq {
    private AuthReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpReq {
        private String userEmail;
        private String password;
        @NotNull
        private UserRole userRole;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignInReq {
        private String userEmail;
        private String password;
    }
}
