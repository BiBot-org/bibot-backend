package com.coderecipe.v1.admin.bibotuser.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.*;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.*;
import com.coderecipe.v1.admin.bibotuser.service.IUserAdminService;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/user")
public class UserAdminController {

    private final IUserAdminService userAdminService;

    @GetMapping
    public ResponseEntity<BaseRes<BibotUserDTO>> getUser(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        BibotUserDTO res = userAdminService.getUser(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping()
    public ResponseEntity<BaseRes<CreateUserRes>> addUser(@RequestBody CreateUserReq req) {
        CreateUserRes res = userAdminService.createUser(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping()
    public ResponseEntity<BaseRes<List<GetAdminInfo>>> getAdminInfoList() {
        List<GetAdminInfo> res = userAdminService.getAdminInfoList();
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping()
    public ResponseEntity<BaseRes<UUID>> updateUserInfo(@RequestBody UpdateUserReq req) {
        UUID res = userAdminService.updateUserInfo(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PutMapping()
    public ResponseEntity<BaseRes<UUID>> changeUserInfo(@RequestBody ChangeUserRole req) {
        UUID res = userAdminService.changeUserRole(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<UUID>> deleteUser(@RequestParam(name = "id", defaultValue = "") UUID userId) {
        UUID res = userAdminService.deleteUser(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
}
