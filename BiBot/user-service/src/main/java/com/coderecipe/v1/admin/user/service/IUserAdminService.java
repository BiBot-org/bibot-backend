package com.coderecipe.v1.admin.user.service;

import com.coderecipe.v1.admin.user.dto.vo.UserAdminReq.CreateUserReq;
import com.coderecipe.v1.admin.user.dto.vo.UserAdminRes.CreateUserRes;

public interface IUserAdminService {
    CreateUserRes createUser(CreateUserReq req);
}
