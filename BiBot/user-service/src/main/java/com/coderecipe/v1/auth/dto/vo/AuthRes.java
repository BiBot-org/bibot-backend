package com.coderecipe.v1.auth.dto.vo;

import com.coderecipe.v1.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;

import java.util.UUID;

public class AuthRes {
    private AuthRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpRes {
        private UUID userId;
        private String userEmail;
        private UserRole userRole;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TokenRes {
        private String accessToken;
        private String refreshToken;

        public static TokenRes of(AccessTokenResponse accessTokenResponse) {
            return new TokenRes(accessTokenResponse.getToken(), accessTokenResponse.getRefreshToken());
        }
    }
}
