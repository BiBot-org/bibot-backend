package com.coderecipe.v1.admin.team.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TeamAdminReq {
    private TeamAdminReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateTeamReq {
        private String name;
        private Long departmentId;
    }
}
