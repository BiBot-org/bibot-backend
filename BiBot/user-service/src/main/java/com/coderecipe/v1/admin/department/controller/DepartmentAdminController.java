package com.coderecipe.v1.admin.department.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.department.service.IDepartmentAdminService;
import com.coderecipe.v1.department.dto.DepartmentDTO;
import com.coderecipe.v1.department.dto.vo.DepartmentReq.AddDepartmentReq;
import com.coderecipe.v1.team.dto.TeamDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/admin/department")
@RequiredArgsConstructor
@Slf4j
public class DepartmentAdminController {

    private final IDepartmentAdminService iDepartmentAdminService;

    @PostMapping
    public ResponseEntity<BaseRes<Long>> addDepartment(@RequestBody AddDepartmentReq departmentData) {
        Long result = iDepartmentAdminService.addDepartment(departmentData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping
    public ResponseEntity<BaseRes<DepartmentDTO>> gerDepartment(@RequestParam(name = "departmentId", defaultValue = "") Long departmentId) {
        DepartmentDTO result = iDepartmentAdminService.getDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @PutMapping
    public ResponseEntity<BaseRes<Long>> updateDepartment(@RequestBody DepartmentDTO departmentData) {
        Long result = iDepartmentAdminService.updateDepartment(departmentData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteDepartment(@RequestParam(name = "departmentId", defaultValue = "") Long departmentId) {
        Long result = iDepartmentAdminService.deleteDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping("/team")
    public ResponseEntity<BaseRes<List<TeamDTO>>> getTeamList(@RequestParam(name = "departmentId", defaultValue = "") Long departmentId) {
        List<TeamDTO> result = iDepartmentAdminService.getTeamList(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
