package com.coderecipe.v1.admin.team.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.team.service.ITeamAdminService;
import com.coderecipe.v1.admin.team.vo.TeamAdminReq.CreateTeamReq;
import com.coderecipe.v1.user.team.dto.TeamDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Tag(name = "팀 Admin API", description = "팀 Admin API 문서 입니다.")
@RestController
@RequestMapping("api/admin/v1/team")
@RequiredArgsConstructor
@Slf4j
public class TeamAdminController {

    private final ITeamAdminService iTeamAdminService;

    @Operation(summary = "팀 생성", description = "팀 생성 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @PostMapping
    public ResponseEntity<BaseRes<Long>> addTeam(@RequestBody CreateTeamReq teamData) {
        Long result = iTeamAdminService.addTeam(teamData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "팀 단건 조회", description = "팀 단건 조 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @GetMapping
    public ResponseEntity<BaseRes<TeamDTO>> getTeam(
            @RequestParam(name = "teamId", defaultValue = "") Long teamId) {
        TeamDTO result = iTeamAdminService.getTeam(teamId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "부서 별 팀 조회", description = "부서 별 팀 조회 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @GetMapping("/department")
    public ResponseEntity<BaseRes<List<TeamDTO>>> getTeamByDepartment(
            @RequestParam(name = "deptId", defaultValue = "") Long deptId) {
        List<TeamDTO> result = iTeamAdminService.getTeamInfoByDepartmentId(deptId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "팀 정보 업데이트", description = "팀 정보 업데이트 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @PutMapping
    public ResponseEntity<BaseRes<Long>> updateTeam(@RequestBody TeamDTO teamDTO) {
        Long result = iTeamAdminService.updateTeam(teamDTO);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "팀 삭제", description = "팀 삭제 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteTeam(
            @RequestParam(name = "teamId", defaultValue = "") Long teamId) {
        Long result = iTeamAdminService.deleteTeam(teamId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
