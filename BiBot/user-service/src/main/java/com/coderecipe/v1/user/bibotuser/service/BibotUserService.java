package com.coderecipe.v1.user.bibotuser.service;

import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.*;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface BibotUserService {

    BibotUserDTO getUser(UUID userId);

    BibotUserInfo getUserInfo(UUID userId);

    String addProfile(UUID userId, MultipartFile file);

    String deleteProfile(UUID userId);

    String updateProfile(UUID userId, MultipartFile file);
}
