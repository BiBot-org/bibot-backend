package com.coderecipe.v1.admin.team.service;

import com.coderecipe.v1.team.dto.TeamDTO;

public interface ITeamAdminService {

    Long addTeam(TeamDTO req);
    TeamDTO getTeam(Long teamId);
    Long updateTeam(TeamDTO req);
    Long deleteTeam(Long teamId);
}
