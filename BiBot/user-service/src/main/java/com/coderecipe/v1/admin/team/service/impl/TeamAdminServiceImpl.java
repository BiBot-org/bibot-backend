package com.coderecipe.v1.admin.team.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
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
    public Long addTeam(TeamDTO req) {
        Team team = Team.of(req);
        teamRepository.save(team);
        return team.getId();
    }

    @Override
    public TeamDTO getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(ResCode.TEAM_NOT_FOUND));

        return TeamDTO.of(team);
    }

    @Override
    public Long updateTeam(TeamDTO req) {
        Team team = teamRepository.findById(req.getId())
                .orElseThrow(() -> new CustomException(ResCode.TEAM_NOT_FOUND));
        team.updateTeamName(req);
        teamRepository.save(team);
        return team.getId();
    }

    @Override
    public Long deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
        return teamId;
    }
}
