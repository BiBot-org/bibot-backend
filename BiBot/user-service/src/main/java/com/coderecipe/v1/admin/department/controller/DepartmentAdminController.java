package com.coderecipe.v1.admin.department.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.department.dto.vo.DepartmentAdminRes.DepartmentInfo;
import com.coderecipe.v1.admin.department.service.IDepartmentAdminService;
import com.coderecipe.v1.user.department.dto.DepartmentDTO;
import com.coderecipe.v1.user.department.dto.vo.DepartmentReq.AddDepartmentReq;
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

@Tag(name = "부서 Admin API", description = "부서 Admin API 문서 입니다.")
@RestController()
@RequestMapping("api/admin/v1/department")
@RequiredArgsConstructor
@Slf4j
public class DepartmentAdminController {

    private final IDepartmentAdminService iDepartmentAdminService;

    @Operation(summary = "부서 추가", description = "새로운 부서 추가 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @PostMapping
    public ResponseEntity<BaseRes<Long>> addDepartment(@RequestBody AddDepartmentReq departmentData) {
        Long result = iDepartmentAdminService.addDepartment(departmentData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "부서 전체 조회", description = "등록 된 모든 부서 정보 조회 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @GetMapping("/all")
    public ResponseEntity<BaseRes<List<DepartmentDTO>>> getAllDepartments() {
        List<DepartmentDTO> result = iDepartmentAdminService.getAllDepartment();
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "부서 상세 정보 전체 조회", description = "등록 된 모든 부서 상세 정보 조회 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @GetMapping("/info/all")
    public ResponseEntity<BaseRes<List<DepartmentInfo>>> getAllDepartmentsInfo() {
        List<DepartmentInfo> result = iDepartmentAdminService.getAllDepartmentInfo();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "부서 정보 단건 조회", description = "부서 정보 단건 조회 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @GetMapping
    public ResponseEntity<BaseRes<DepartmentDTO>> getDepartment(@RequestParam(name = "departmentId", defaultValue = "") Long departmentId) {
        DepartmentDTO result = iDepartmentAdminService.getDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "부서 정보 업데이트", description = "부서 정보 업데이트 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @PutMapping
    public ResponseEntity<BaseRes<Long>> updateDepartment(@RequestBody DepartmentDTO departmentData) {
        Long result = iDepartmentAdminService.updateDepartment(departmentData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "부서 삭제", description = "부서 삭제 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteDepartment(@RequestParam(name = "departmentId", defaultValue = "") Long departmentId) {
        Long result = iDepartmentAdminService.deleteDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "부서 하위 팀 조회", description = "부서 하위 팀 전체 조회 API 입니다.")
    @RolesAllowed({"SUPER_ADMIN", "ADMIN"})
    @GetMapping("/team")
    public ResponseEntity<BaseRes<List<TeamDTO>>> getTeamList(@RequestParam(name = "departmentId", defaultValue = "") Long departmentId) {
        List<TeamDTO> result = iDepartmentAdminService.getTeamList(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
