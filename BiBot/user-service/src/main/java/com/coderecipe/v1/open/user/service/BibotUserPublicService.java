package com.coderecipe.v1.open.user.service;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.global.utils.InternalDataUtils;
import com.coderecipe.global.utils.RedisUtils;
import com.coderecipe.v1.open.user.vo.UserReq.*;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibotUserPublicService {

    private final BibotUserRepository bibotUserRepository;
    private final EmailService emailService;
    private final RedisUtils redisUtils;
    private static final String KEY_FORMAT = "check:%s";

    public Boolean isInit() {
        return bibotUserRepository.findAllByUserRoleInOrderByUserRole(List.of(UserRole.SUPER_ADMIN)).isEmpty();
    }

    public String sendVerificationEmail(String email) {
        if (Boolean.TRUE.equals(bibotUserRepository.existsByEmail(email))) {
            throw new CustomException(ResCode.DUPLICATE_USER_EMAIL);
        } else {
            try {
                int randNum = InternalDataUtils.makeRandNum();
                emailService.joinEmail(email, randNum);
                redisUtils.setData(String.format(KEY_FORMAT, email), Integer.toString(randNum));
                return email;
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException(ResCode.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public Boolean verifyEmail(VerifyEmailReq req) {
        int verificationNum = Integer.parseInt(
                redisUtils.getData(String.format(KEY_FORMAT, req.getEmail())));
        if (verificationNum == req.getVerifyCode()) {
            redisUtils.deleteData(String.format(KEY_FORMAT, req.getEmail()));
            return true;
        } else {
            return false;
        }
    }

}
