package com.coderecipe.v1.user.bibotuser.dto.vo;

import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.department.dto.DepartmentDTO;
import com.coderecipe.v1.user.department.model.Department;
import com.coderecipe.v1.user.team.dto.TeamDTO;
import com.coderecipe.v1.user.team.model.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

public class BibotUserReq {
    private BibotUserReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BibotUserInfo {
        private BibotUserDTO bibotUser;
        private DepartmentDTO department;
        private TeamDTO team;

        public static BibotUserInfo of(BibotUser user, Department department, Team team) {
            return BibotUserInfo.builder()
                    .bibotUser(BibotUserDTO.of(user))
                    .department(DepartmentDTO.of(department))
                    .team(TeamDTO.of(team))
                    .build();
        }

        public static BibotUserInfo of(BibotUser user) {
            return BibotUserInfo.builder()
                    .bibotUser(BibotUserDTO.of(user))
                    .team(TeamDTO.of(user.getTeam()))
                    .department(DepartmentDTO.of(user.getTeam().getDepartment()))
                    .build();
        }

        public static Page<BibotUserInfo> of(Page<BibotUser> entities) {
            return entities.map(BibotUserInfo::of);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChangeUserPassword {
        private String password;
        private String newPassword;
    }
}
