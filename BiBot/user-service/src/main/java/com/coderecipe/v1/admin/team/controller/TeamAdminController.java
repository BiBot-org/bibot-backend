package com.coderecipe.v1.admin.team.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.team.service.ITeamAdminService;
import com.coderecipe.v1.admin.team.vo.TeamAdminReq.CreateTeamReq;
import com.coderecipe.v1.user.team.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/v1/team")
@RequiredArgsConstructor
@Slf4j
public class TeamAdminController {

    private final ITeamAdminService iTeamAdminService;

    @PostMapping
    public ResponseEntity<BaseRes<Long>> addTeam(@RequestBody CreateTeamReq teamData) {
        Long result = iTeamAdminService.addTeam(teamData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping
    public ResponseEntity<BaseRes<TeamDTO>> getTeam(
            @RequestParam(name = "teamId", defaultValue = "") Long teamId) {
        TeamDTO result = iTeamAdminService.getTeam(teamId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping("/department")
    public ResponseEntity<BaseRes<List<TeamDTO>>> getTeamByDepartment(
            @RequestParam(name = "deptId", defaultValue = "") Long deptId) {
        List<TeamDTO> result = iTeamAdminService.getTeamInfoByDepartmentId(deptId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PutMapping
    public ResponseEntity<BaseRes<Long>> updateTeam(@RequestBody TeamDTO teamDTO) {
        Long result = iTeamAdminService.updateTeam(teamDTO);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteTeam(
            @RequestParam(name = "teamId", defaultValue = "") Long teamId) {
        Long result = iTeamAdminService.deleteTeam(teamId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
