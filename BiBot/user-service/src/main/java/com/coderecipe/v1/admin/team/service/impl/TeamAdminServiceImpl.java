package com.coderecipe.v1.admin.team.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.team.service.ITeamAdminService;
import com.coderecipe.v1.admin.team.vo.TeamAdminReq.*;
import com.coderecipe.v1.user.team.dto.TeamDTO;
import com.coderecipe.v1.user.team.model.Team;
import com.coderecipe.v1.user.team.model.repository.TeamRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class TeamAdminServiceImpl implements ITeamAdminService {

    private final TeamRepository teamRepository;

    @Override
    public Long addTeam(CreateTeamReq req) {
        if (teamRepository.existsByName(req.getName())) {
            throw new CustomException(ResCode.DUPLICATE_TEAM_NAME);
        } else {
            Team team = Team.of(req);
            teamRepository.save(team);
            return team.getId();
        }
    }

    @Override
    public TeamDTO getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(ResCode.TEAM_NOT_FOUND));

        return TeamDTO.of(team);
    }

    @Override
    public List<TeamDTO> getTeamInfoByDepartmentId(Long departmentId) {
        return teamRepository.findByDepartmentId(departmentId)
                .stream().map(TeamDTO::of).toList();
    }

    @Override
    public Long updateTeam(TeamDTO req) {
        if (teamRepository.existsByName(req.getName())) {
            throw new CustomException(ResCode.DUPLICATE_TEAM_NAME);
        } else {
            Team team = teamRepository.findById(req.getId())
                    .orElseThrow(() -> new CustomException(ResCode.TEAM_NOT_FOUND));
            team.updateTeamName(req);
            teamRepository.save(team);
            return team.getId();
        }
    }

    @Override
    public Long deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
        return teamId;
    }
}
