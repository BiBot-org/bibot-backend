package com.coderecipe.v1.user.service;

import com.coderecipe.v1.user.dto.vo.BibotUserReq.CreateUserReq;
import com.coderecipe.v1.user.dto.vo.BibotUserRes.CreateUserRes;

public interface IUserService {
    CreateUserRes createUser(CreateUserReq req);
}
