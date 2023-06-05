package com.coderecipe.v1.user.bibotuser.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.*;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import com.coderecipe.v1.user.bibotuser.service.BibotUserService;
import com.coderecipe.v1.user.department.model.Department;
import com.coderecipe.v1.user.team.model.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BibotUserServiceImpl implements BibotUserService {

    private final BibotUserRepository bibotUserRepository;
    private final Keycloak keycloak;

    @Override
    public BibotUserDTO getUser(UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        return BibotUserDTO.of(user);
    }

    @Override
    @Transactional
    public BibotUserInfo getUserInfo(UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Team team = user.getTeam();
        Department department = team.getDepartment();

        return BibotUserInfo.of(user, department, team);
    }

    @Override
    public boolean changePassword(String newPassword) {
        return false;
    }
}
