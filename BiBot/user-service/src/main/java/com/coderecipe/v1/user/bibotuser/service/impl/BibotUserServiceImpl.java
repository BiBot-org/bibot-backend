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

    /**
     * @deprecated
     * - 유저 API에서 어드민 목적 API는 모두 제거합니다.
     */
    @Override
    @Deprecated(since = "유저 서비스에 어드민 목적의 API는 모두 제거합니다.")
    public Boolean isInit() {
        return bibotUserRepository.findAllByUserRoleInOrderByUserRole(List.of(UserRole.SUPER_ADMIN)).isEmpty();
    }
}
