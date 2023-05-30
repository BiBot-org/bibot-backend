package com.coderecipe.v1.admin.department.dto.vo;

import com.coderecipe.v1.user.department.dto.DepartmentDTO;
import com.coderecipe.v1.user.department.model.Department;
import com.coderecipe.v1.user.team.dto.TeamDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class DepartmentAdminRes {
    private DepartmentAdminRes() {
        throw new IllegalStateException("VO class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepartmentInfo {
        private DepartmentDTO department;
        private List<TeamDTO> teams;

        public static DepartmentInfo of(Department department) {
            return new DepartmentInfo(DepartmentDTO.of(department), TeamDTO.of(department.getTeams()));
        }

        public static List<DepartmentInfo> of(List<Department> entities) {
            return entities.stream().map(DepartmentInfo::of).toList();
        }
    }
}
