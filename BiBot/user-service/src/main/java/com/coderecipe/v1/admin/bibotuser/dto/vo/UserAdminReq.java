package com.coderecipe.v1.admin.bibotuser.dto.vo;

import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class UserAdminReq {

    private UserAdminReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserReq {
        private String firstName;
        private String lastName;
        private String profileUrl;
        private String email;
        private String duty;
        private Long teamId;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchUserReq {
        private Long departmentId;
        private Long teamId;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateUserReq {
        private UUID id;
        private String firstName;
        private String lastName;
        private String profileUrl;
        private String email;
        private String duty;
        @Nullable
        private Long teamId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChangeUserRole {
        private UUID userId;
        private UserRole prevRole;
        private UserRole nextRole;
    }
}
