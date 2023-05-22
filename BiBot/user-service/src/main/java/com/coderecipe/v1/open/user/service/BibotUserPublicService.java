package com.coderecipe.v1.open.user.service;

import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibotUserPublicService {

    private final BibotUserRepository bibotUserRepository;
    public Boolean isInit() {
        return bibotUserRepository.findAllByUserRoleInOrderByUserRole(List.of(UserRole.BIBOT_SUPER_ADMIN)).isEmpty();
    }

}
