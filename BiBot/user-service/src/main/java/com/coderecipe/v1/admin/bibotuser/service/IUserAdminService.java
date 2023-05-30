package com.coderecipe.v1.admin.bibotuser.service;

import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.*;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.*;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import org.springframework.data.domain.Pageable;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public interface IUserAdminService {
    CreateUserRes createUser(CreateUserReq req) throws NoSuchAlgorithmException;

    SearchUserRes searchUser(SearchUserReq req, Pageable pageable);

    BibotUserDTO getUser(UUID userId);

    List<GetAdminInfo> getAdminInfoList();

    UUID updateUserInfo(UpdateUserReq req);

    UUID changeUserRole(ChangeUserRole req);

    UUID deleteUser(UUID userId);

}
