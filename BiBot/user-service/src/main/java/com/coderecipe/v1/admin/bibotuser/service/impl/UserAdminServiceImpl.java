package com.coderecipe.v1.admin.bibotuser.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.global.utils.InternalDataUtils;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.ChangeUserRole;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.CreateUserReq;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.SearchUserReq;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.UpdateUserReq;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.CreateUserRes;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.GetAdminInfo;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.SearchUserRes;
import com.coderecipe.v1.admin.bibotuser.service.IUserAdminService;
import com.coderecipe.v1.open.user.service.EmailService;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserSpecification;
import com.coderecipe.v1.user.team.model.Team;
import com.coderecipe.v1.user.team.model.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserAdminServiceImpl implements IUserAdminService {

    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String client;
    private final BibotUserRepository bibotUserRepository;
    private final TeamRepository teamRepository;
    private final Keycloak keycloak;
    private final EmailService emailService;

    @Override
    @CachePut(key = "#req.email")
    public CreateUserRes createUser(CreateUserReq req) throws CustomException, NoSuchAlgorithmException {

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));


        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(req.getEmail());
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());

        String userPassword = String.valueOf(InternalDataUtils.makeRandNum());
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(userPassword);
        credential.setTemporary(false);

        RealmResource realmResource = keycloak.realm(realm);
        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId(client).get(0);
        UsersResource usersResource = realmResource.users();
        Response response = usersResource.create(user);

        String userId = CreatedResponseUtil.getCreatedId(response);

        UserResource userResource = usersResource.get(userId);
        userResource.resetPassword(credential);

        RoleRepresentation roleRepresentation = realmResource.clients().get(clientRepresentation.getId())
                .roles().get(UserRole.USER.toString()).toRepresentation();
        userResource.roles().clientLevel(clientRepresentation.getId()).add(Collections.singletonList(roleRepresentation));

        BibotUser bibotUser = BibotUser.of(req, UUID.fromString(userId));
        bibotUser.setTeam(team);
        bibotUserRepository.save(bibotUser);
        emailService.userPasswordEmail(req.getEmail(), userPassword);

        return new CreateUserRes(bibotUser.getId());
    }

    @Override
    public SearchUserRes searchUser(SearchUserReq req, Pageable pageable) {
        Specification<BibotUser> spec = (root, query, cb) -> cb.isTrue(cb.literal((true)));

        if (req.getDepartmentId() != null && req.getDepartmentId() != 0) {
            spec = spec.and(BibotUserSpecification.equalDepartmentId(
                    req.getDepartmentId()));
        }

        if (req.getTeamId() != null && req.getTeamId() != 0) {
            spec = spec.and(BibotUserSpecification.equalTeamId(req.getTeamId()));
        }

        if (req.getName() != null) {
            spec = spec.and(BibotUserSpecification.likeUsername(req.getName()));
        }

        return SearchUserRes.of(BibotUserReq.BibotUserInfo.of(bibotUserRepository.findAll(spec, pageable)));
    }

    @Override
    @Cacheable(key = "#userId")
    public BibotUserDTO getUser(UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        return BibotUserDTO.of(user);
    }

    @Override
    @Cacheable(key = "'admin'" )
    public List<GetAdminInfo> getAdminInfoList() {
        return bibotUserRepository.findAllByUserRoleInOrderByUserRole(List.of(UserRole.ADMIN, UserRole.SUPER_ADMIN))
                .stream().map(GetAdminInfo::of).toList();
    }

    @Override
    @CachePut(key = "#req.userId + 'info'")
    public UUID updateUserInfo(UpdateUserReq req) {

        BibotUser user = bibotUserRepository.findById(req.getId())
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        if (req.getTeamId() != null) {
            Team team = teamRepository.findById(req.getTeamId())
                    .orElseThrow(() -> new CustomException(ResCode.TEAM_NOT_FOUND));
            user.setTeam(team);
        }

        RealmResource realmResource = keycloak.realm(realm);
        UserResource userResource = realmResource.users().get(req.getId().toString());
        UserRepresentation userRepresentation = userResource.toRepresentation();

        userRepresentation.setEmail(req.getEmail());
        userRepresentation.setUsername(req.getEmail());
        userRepresentation.setFirstName(req.getFirstName());
        userRepresentation.setLastName(req.getLastName());
        userResource.update(userRepresentation);

        user.updateEntityColumns(req);


        bibotUserRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    @CachePut(key = "#req.userId + 'role'")
    @CacheEvict(key = "'admin'")
    public UUID changeUserRole(ChangeUserRole req) {

        RealmResource realmResource = keycloak.realm(realm);
        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId(client).get(0);

        RoleRepresentation prevRoleRep = realmResource.clients().get(clientRepresentation.getId())
                .roles().get(req.getPrevRole().toString()).toRepresentation();
        RoleRepresentation nextRoleRep = realmResource.clients().get(clientRepresentation.getId())
                .roles().get(req.getNextRole().toString()).toRepresentation();

        UserRepresentation userRep = realmResource.users().get(req.getUserId().toString()).toRepresentation();
        UserResource userResource = realmResource.users().get(userRep.getId());
        userResource.roles().clientLevel(clientRepresentation.getClientId()).remove(Collections.singletonList(prevRoleRep));
        userResource.roles().clientLevel(clientRepresentation.getClientId()).add(Collections.singletonList(nextRoleRep));

        keycloak.realm(realm).users().get(req.getUserId().toString()).update(userRep);

        BibotUser user = bibotUserRepository.findById(req.getUserId())
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        user.setUserRole(req.getNextRole());
        bibotUserRepository.save(user);
        return user.getId();

    }

    @Override
    @Caching(evict = {
        @CacheEvict(key = "#req.userId + 'role'"),
        @CacheEvict(key = "#req.userId + 'info'"),
        @CacheEvict(key = "#userId"),
        @CacheEvict(key = "'admin'")
    })
    public UUID deleteUser(UUID userId) {

        RealmResource realmResource = keycloak.realm(realm);

        UserRepresentation userRep = realmResource.users().get(userId.toString()).toRepresentation();
        UserResource userResource = realmResource.users().get(userRep.getId());
        userResource.remove();

        bibotUserRepository.deleteById(userId);
        return userId;
    }
}
