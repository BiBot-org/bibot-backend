package com.coderecipe.v1.admin.department.service.impl;

import com.coderecipe.v1.admin.department.service.IDepartmentAdminService;
import com.coderecipe.v1.department.dto.DepartmentDTO;
import com.coderecipe.v1.department.model.Department;
import com.coderecipe.v1.department.model.repository.DepartmentRepository;
import com.coderecipe.v1.team.dto.TeamDTO;
import com.coderecipe.v1.team.model.Team;
import com.coderecipe.v1.team.model.repository.TeamRepository;
import java.util.List;
import java.util.stream.Collectors;
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
    public DepartmentDTO addDepartment(DepartmentDTO req) {
        return DepartmentDTO.of(departmentRepository.save(Department.of(req)));
    }

    @Override
    public DepartmentDTO getDepartment(Long departmentId) {
        return DepartmentDTO.of(departmentRepository.getReferenceById(departmentId));
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO req) {
        return DepartmentDTO.of(departmentRepository.save(Department.of(req)));
    }

    @Override
    public DepartmentDTO deleteDepartment(Long departmentId) {
        DepartmentDTO departmentDTO = DepartmentDTO.of(departmentRepository.getReferenceById(departmentId));
        departmentRepository.deleteById(departmentId);
        return departmentDTO;
    }

    @Override
    public List<TeamDTO> getTeamList(Long departmentId) {
        List<Team> teams = teamRepository.findByDepartmentId(departmentId);
        return teams.stream().map(TeamDTO::of).collect(Collectors.toList());
    }

}
