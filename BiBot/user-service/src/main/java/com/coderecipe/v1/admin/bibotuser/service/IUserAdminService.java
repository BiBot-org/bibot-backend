package com.coderecipe.v1.admin.bibotuser.service;

import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.ChangeUserRole;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.UpdateUserReq;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.CreateUserReq;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.GetAdminInfo;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.CreateUserRes;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserAdminService {
    CreateUserRes createUser(CreateUserReq req);
    BibotUserDTO getUser(UUID userId);
    List<GetAdminInfo> getAdminInfoList();
    UUID updateUserInfo(UpdateUserReq req);
    UUID changeUserRole(ChangeUserRole req);
    UUID deleteUser(UUID userId);

}
