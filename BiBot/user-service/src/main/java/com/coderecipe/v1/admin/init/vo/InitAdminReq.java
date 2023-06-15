package com.coderecipe.v1.admin.init.vo;

import com.coderecipe.global.utils.InternalDataUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class InitAdminReq {
    private InitAdminReq() {
        throw new IllegalStateException("VO class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InitRootUserSetupReq {
        private String rootEmail;
        private String rootPassword;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private List<CreateInitDepartmentAndTeams> departmentList;
        private String rootTeamName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddCategory {
        private String categoryName;
        private int limitation;
        private int automatedCost;
        private String resetCycle;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SetRootUserReq {
        private String firstName;
        private String lastName;
        private String rootEmail;
        private String rootPassword;
        private String rootTeamName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateInitUserReq {
        private String email;
        private String password;

        public static CreateInitUserReq initRootUser() throws Exception {
            return new CreateInitUserReq("root@root.com", String.valueOf(InternalDataUtils.makeRandNum()));
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateInitDepartmentAndTeams {
        private String name;
        private List<String> teams;
    }
}
