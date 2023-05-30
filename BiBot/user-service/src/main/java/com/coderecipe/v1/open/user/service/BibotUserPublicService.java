package com.coderecipe.v1.open.user.service;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.global.utils.InternalDataUtils;
import com.coderecipe.global.utils.RedisUtils;
import com.coderecipe.v1.open.user.vo.UserReq.VerifyEmailReq;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BibotUserPublicService {
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String client;
    private final Keycloak keycloak;
    private final BibotUserRepository bibotUserRepository;
    private final EmailService emailService;
    private final RedisUtils redisUtils;
    private static final String KEY_FORMAT = "check:%s";
    private static final String KEY_VERIFY = "verify:%s";

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

    public String sendUserVerification(String email) {
        if (Boolean.FALSE.equals(bibotUserRepository.existsByEmail(email))) {
            throw new CustomException(ResCode.BAD_REQUEST);
        } else {
            try {
                int randNum = InternalDataUtils.makeRandNum();
                emailService.verifyEmail(email, randNum);
                redisUtils.setData(String.format(KEY_VERIFY, email), Integer.toString(randNum));
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

    public Boolean verifyUserEmail(VerifyEmailReq req) {
        int verificationNum = Integer.parseInt(
                redisUtils.getData(String.format(KEY_VERIFY, req.getEmail())));
        if (verificationNum == req.getVerifyCode()) {
            redisUtils.deleteData(String.format(KEY_VERIFY, req.getEmail()));
            return true;
        } else {
            return false;
        }
    }

    public UUID resetPasswordByEmail(VerifyEmailReq req) throws Exception {
        int verificationNum = Integer.parseInt(
                redisUtils.getData(String.format(KEY_VERIFY, req.getEmail())));
        if (verificationNum == req.getVerifyCode()) {

            redisUtils.deleteData(String.format(KEY_VERIFY, req.getEmail()));
            BibotUser user = bibotUserRepository.findBibotUserByEmail(req.getEmail())
                    .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

            String newPassword = String.valueOf(InternalDataUtils.makeRandNum());

            RealmResource realmResource = keycloak.realm(realm);
            UserResource userResource = realmResource.users().get(user.getId().toString());
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(newPassword);
            credential.setTemporary(false);
            userResource.resetPassword(credential);

            emailService.resetPasswordEmail(req.getEmail(), newPassword);
            return user.getId();
        } else {
            throw new CustomException(ResCode.BAD_REQUEST);
        }
    }

}
