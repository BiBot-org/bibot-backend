package com.coderecipe.v1.user.bibotuser.service;

import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.*;

import java.util.UUID;

public interface BibotUserService {

    BibotUserDTO getUser(UUID userId);

    BibotUserInfo getUserInfo(UUID userId);
}
