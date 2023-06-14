package com.coderecipe.v1.admin.team.service;

import com.coderecipe.v1.admin.team.vo.TeamAdminReq.CreateTeamReq;
import com.coderecipe.v1.user.team.dto.TeamDTO;

import java.util.List;

public interface ITeamAdminService {

    Long addTeam(CreateTeamReq req);

    TeamDTO getTeam(Long teamId);

    List<TeamDTO> getTeamInfoByDepartmentId(Long departmentId);

    Long updateTeam(TeamDTO req);

    Long deleteTeam(Long teamId);
}
