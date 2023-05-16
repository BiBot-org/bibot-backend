package com.coderecipe.v1.admin.department.service;

import com.coderecipe.v1.department.dto.DepartmentDTO;
import com.coderecipe.v1.department.dto.vo.DepartmentReq.AddDepartmentReq;
import com.coderecipe.v1.team.dto.TeamDTO;
import java.util.List;

public interface IDepartmentAdminService {

    Long addDepartment(AddDepartmentReq req);
    DepartmentDTO getDepartment(Long departmentId);
    Long updateDepartment(DepartmentDTO req);
    Long deleteDepartment(Long departmentId);
    List<TeamDTO> getTeamList(Long departmentId);
}
