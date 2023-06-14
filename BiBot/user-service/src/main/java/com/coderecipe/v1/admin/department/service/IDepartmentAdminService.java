package com.coderecipe.v1.admin.department.service;

import com.coderecipe.v1.admin.department.dto.vo.DepartmentAdminRes.DepartmentInfo;
import com.coderecipe.v1.user.department.dto.DepartmentDTO;
import com.coderecipe.v1.user.department.dto.vo.DepartmentReq.AddDepartmentReq;
import com.coderecipe.v1.user.team.dto.TeamDTO;

import java.util.List;

public interface IDepartmentAdminService {

    Long addDepartment(AddDepartmentReq req);

    List<DepartmentDTO> getAllDepartment();

    List<DepartmentInfo> getAllDepartmentInfo();

    DepartmentDTO getDepartment(Long departmentId);

    Long updateDepartment(DepartmentDTO req);

    Long deleteDepartment(Long departmentId);

    List<TeamDTO> getTeamList(Long departmentId);
}
