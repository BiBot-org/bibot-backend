package com.coderecipe.v1.admin.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.*;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.*;
import com.coderecipe.v1.admin.bibotuser.service.IUserAdminService;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Tag(name = "유저 Admin API", description = "유저 Admin API 문서 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/user")
public class UserAdminController {

    private final IUserAdminService userAdminService;

    @Operation(summary = "유저 조회", description = "UUID 기반 유저 단건조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<BibotUserDTO>> getUser(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserDTO res = userAdminService.getUser(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 검색", description = "유저 검색 API 입니다. 검색 조건으로는 부서번호, 팀 번호, 이름이 있습니다.")
    @GetMapping("/search")
    public ResponseEntity<BaseRes<SearchUserRes>> getUsers(
            @RequestParam(value = "department", required = false, defaultValue = "") Long departmentId,
            @RequestParam(value = "team", required = false, defaultValue = "") Long teamId,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        SearchUserRes res = userAdminService.searchUser(new SearchUserReq(departmentId, teamId, name), pageable);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 추가", description = "새로운 유저 생성 API 입니다. 유저는 회원가입이 따로 없고 해당 API로 어드민이 생성 해 줍니다.")
    @PostMapping
    public ResponseEntity<BaseRes<CreateUserRes>> addUser(@RequestBody CreateUserReq req) throws NoSuchAlgorithmException {
        CreateUserRes res = userAdminService.createUser(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "관리자 유저 조회", description = "관리자 권한을 가진 유저를 조회하는 API 입니다.")
    @GetMapping("/admin/all")
    public ResponseEntity<BaseRes<List<GetAdminInfo>>> getAdminInfoList() {
        List<GetAdminInfo> res = userAdminService.getAdminInfoList();
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 업데이트", description = "유저 정보 업데이트 API 입니다.")
    @PutMapping
    public ResponseEntity<BaseRes<UUID>> updateUserInfo(@RequestBody UpdateUserReq req) {
        UUID res = userAdminService.updateUserInfo(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 롤 변경", description = "유저 롤 정보 변경 API 입니다.")
    @PutMapping("/update/role")
    public ResponseEntity<BaseRes<UUID>> changeUserRole(@RequestBody ChangeUserRole req) {
        UUID res = userAdminService.changeUserRole(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 삭제", description = "유저 삭제 API 입니다.")
    @DeleteMapping
    public ResponseEntity<BaseRes<UUID>> deleteUser(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        UUID res = userAdminService.deleteUser(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}
