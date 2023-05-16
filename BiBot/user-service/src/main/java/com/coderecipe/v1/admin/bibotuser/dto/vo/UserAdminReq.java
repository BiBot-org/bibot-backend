package com.coderecipe.v1.admin.bibotuser.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserAdminReq {

    private UserAdminReq () {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserReq {
        private String firstName;
        private String lastName;
        private String profileUrl;
        private String email;
        private String password;
        private String duty;
        private Long teamId;
        private Long rankId;
    }
}
