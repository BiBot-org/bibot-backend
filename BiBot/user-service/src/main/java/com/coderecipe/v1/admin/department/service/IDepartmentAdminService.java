package com.coderecipe.v1.admin.department.service;

import com.coderecipe.v1.department.dto.DepartmentDTO;
import com.coderecipe.v1.department.model.Department;
import com.coderecipe.v1.team.dto.TeamDTO;
import java.util.List;

public interface IDepartmentAdminService {

    DepartmentDTO addDepartment(DepartmentDTO req);
    DepartmentDTO getDepartment(Long departmentId);
    DepartmentDTO updateDepartment(DepartmentDTO req);
    DepartmentDTO deleteDepartment(Long departmentId);
    List<TeamDTO> getTeamList(Long departmentId);
}
