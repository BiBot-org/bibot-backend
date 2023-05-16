package com.coderecipe.v1.admin.department.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.department.service.IDepartmentAdminService;
import com.coderecipe.v1.department.dto.DepartmentDTO;
import com.coderecipe.v1.department.dto.vo.DepartmentReq.AddDepartmentReq;
import com.coderecipe.v1.department.model.Department;
import com.coderecipe.v1.department.model.repository.DepartmentRepository;
import com.coderecipe.v1.team.dto.TeamDTO;
import com.coderecipe.v1.team.model.Team;
import com.coderecipe.v1.team.model.repository.TeamRepository;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class DepartAdminServiceImpl implements IDepartmentAdminService {

    private final DepartmentRepository departmentRepository;
    private final TeamRepository teamRepository;
    @Override
    public Long addDepartment(AddDepartmentReq req) {
        if (departmentRepository.existsByName(req.getName())) {
            throw new CustomException(ResCode.DUPLICATE_DEPARTMENT_NAME);
        } else {
            Department department = Department.of(req.getName());
            departmentRepository.save(department);
            return department.getId();
        }
    }

    @Override
    public DepartmentDTO getDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new CustomException(ResCode.DEPARTMENT_NOT_FOUND));
        return DepartmentDTO.of(department);
    }

    @Override
    public Long updateDepartment(DepartmentDTO req) {
        if(departmentRepository.existsByName(req.getName())) {
            throw new CustomException(ResCode.DUPLICATE_DEPARTMENT_NAME);
        } else {
            Department department = departmentRepository.findById(req.getId())
                    .orElseThrow(() -> new CustomException(ResCode.DEPARTMENT_NOT_FOUND));
            department.updateDepartmentInfo(req);
            departmentRepository.save(department);
            return department.getId();
        }
    }

    @Override
    public Long deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
        return departmentId;
    }

    @Override
    public List<TeamDTO> getTeamList(Long departmentId) {
        List<Team> teams = teamRepository.findByDepartmentId(departmentId);
        return teams.stream().map(TeamDTO::of).toList();
    }

}
