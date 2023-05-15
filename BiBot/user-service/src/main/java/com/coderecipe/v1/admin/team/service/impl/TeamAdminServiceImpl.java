package com.coderecipe.v1.admin.team.service.impl;

import com.coderecipe.v1.admin.team.service.ITeamAdminService;
import com.coderecipe.v1.team.dto.TeamDTO;
import com.coderecipe.v1.team.model.Team;
import com.coderecipe.v1.team.model.repository.TeamRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class TeamAdminServiceImpl implements ITeamAdminService {

    private final TeamRepository teamRepository;

    @Override
    public TeamDTO addTeam(TeamDTO req) {
        return TeamDTO.of(teamRepository.save(Team.of(req)));
    }

    @Override
    public TeamDTO getTeam(Long teamId) {
        return TeamDTO.of(teamRepository.getReferenceById(teamId));
    }

    @Override
    public TeamDTO updateTeam(TeamDTO req) {
        return TeamDTO.of(teamRepository.save(Team.of(req)));
    }

    @Override
    public TeamDTO deleteTeam(Long teamId) {
        TeamDTO teamDTO = TeamDTO.of(teamRepository.getReferenceById(teamId));
        teamRepository.deleteById(teamId);
        return teamDTO;
    }
}
