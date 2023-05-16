package com.coderecipe.v1.admin.team.service;

import com.coderecipe.v1.team.dto.TeamDTO;

public interface ITeamAdminService {

    TeamDTO addTeam(TeamDTO req);
    TeamDTO getTeam(Long teamId);
    TeamDTO updateTeam(TeamDTO req);
    TeamDTO deleteTeam(Long teamId);
}
