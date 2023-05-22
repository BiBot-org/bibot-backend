package com.coderecipe.v1.admin.bibotuser.dto.vo;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
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

        public static GetAdminInfo of(BibotUser entity) {
            return ModelMapperUtils.getModelMapper().map(entity, GetAdminInfo.class);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchUserRes {
        private List<BibotUserDTO> content;
        private Integer pageNo;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;

        public static SearchUserRes of(Page<BibotUserDTO> page) {
            return new SearchUserRes(page.getContent(), page.getNumber(), page.isLast(), page.getTotalPages(), page.getTotalElements());
        }
    }
}
