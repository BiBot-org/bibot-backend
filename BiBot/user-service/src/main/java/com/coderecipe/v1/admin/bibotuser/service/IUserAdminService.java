package com.coderecipe.v1.admin.bibotuser.service;

import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.CreateUserReq;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.CreateUserRes;

public interface IUserAdminService {
    CreateUserRes createUser(CreateUserReq req);
}
