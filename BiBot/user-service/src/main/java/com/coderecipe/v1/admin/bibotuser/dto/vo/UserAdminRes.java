package com.coderecipe.v1.admin.bibotuser.dto.vo;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class UserAdminRes {

    private UserAdminRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserRes {
        private UUID userId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetAdminInfo {
        private UUID userId;
        private String firstName;
        private String lastName;
        private UserRole userRole;

        public static GetAdminInfo of (BibotUser entity) {
            return ModelMapperUtils.getModelMapper().map(entity, GetAdminInfo.class);
        }
    }
}
