package com.coderecipe.config.security;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final BibotUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BibotUser user = userRepository.findById(UUID.fromString(username))
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        return User.builder()
                .username(user.getEmail())
                .roles(user.getUserRole().toString())
                .build();

    }
}
