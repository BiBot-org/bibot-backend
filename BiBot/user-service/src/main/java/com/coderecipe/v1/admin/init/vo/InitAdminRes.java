package com.coderecipe.v1.admin.init.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class InitAdminRes {

    private InitAdminRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateInitDepartmentAndTeamsRes {
        private Long departmentId;
        private List<Long> teamId;
    }

}
