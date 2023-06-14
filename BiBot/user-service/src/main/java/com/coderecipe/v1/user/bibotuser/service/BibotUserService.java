package com.coderecipe.v1.user.bibotuser.service;

import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.BibotUserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface BibotUserService {

    BibotUserDTO getUser(UUID userId);

    BibotUserInfo getUserInfo(UUID userId);

    String addProfile(UUID userId, MultipartFile file) throws IOException;

    String deleteProfile(UUID userId);

    String updateProfile(UUID userId, MultipartFile file) throws IOException;

    boolean changePassword(UUID userId, String newPassword);
}
