package com.coderecipe.v1.user.bibotuser.service.impl;

import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import com.coderecipe.v1.user.bibotuser.service.BibotUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BibotUserServiceImpl implements BibotUserService {

    private final BibotUserRepository bibotUserRepository;

    @Override
    public Boolean isInit() {
        return bibotUserRepository.findAllByUserRoleInOrderByUserRole(List.of(UserRole.BIBOT_SUPER_ADMIN)).isEmpty();
    }
}
