package com.coderecipe.v1.admin.team.service;

import com.coderecipe.v1.admin.team.vo.TeamAdminReq.*;
import com.coderecipe.v1.user.team.dto.TeamDTO;

public interface ITeamAdminService {

    Long addTeam(CreateTeamReq req);

    TeamDTO getTeam(Long teamId);

    Long updateTeam(TeamDTO req);

    Long deleteTeam(Long teamId);
}
