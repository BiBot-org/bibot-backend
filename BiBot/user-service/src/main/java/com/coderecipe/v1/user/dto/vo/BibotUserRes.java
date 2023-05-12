package com.coderecipe.v1.user.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class BibotUserRes {
    private BibotUserRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserRes {
        private UUID userId;
    }
}
