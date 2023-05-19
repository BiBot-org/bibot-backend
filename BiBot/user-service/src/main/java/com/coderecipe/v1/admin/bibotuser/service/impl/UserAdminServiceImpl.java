package com.coderecipe.v1.admin.bibotuser.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.UpdateUserReq;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.ChangeUserRole;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.GetAdminInfo;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminRes.CreateUserRes;
import com.coderecipe.v1.admin.bibotuser.dto.vo.UserAdminReq.CreateUserReq;
import com.coderecipe.v1.admin.bibotuser.service.IUserAdminService;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.rank.model.Rank;
import com.coderecipe.v1.user.rank.model.repository.RankRepository;
import com.coderecipe.v1.user.team.model.Team;
import com.coderecipe.v1.user.team.model.repository.TeamRepository;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAdminServiceImpl implements IUserAdminService {

    @Value("${keycloak.realm}")
    private String realm;
    private final BibotUserRepository bibotUserRepository;
    private final RankRepository rankRepository;
    private final TeamRepository teamRepository;
    private final Keycloak keycloak;

    @Override
    public CreateUserRes createUser(CreateUserReq req) throws CustomException {

        Rank rank = rankRepository.findById(req.getRankId())
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(req.getEmail());
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(req.getPassword());
        credential.setTemporary(false);

        user.setRealmRoles(List.of("BIBOT_USER"));

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);

        UserResource userResource = usersResource.get(userId);
        userResource.resetPassword(credential);

        BibotUser bibotUser = BibotUser.of(req, UUID.fromString(userId));
        bibotUser.setTeam(team);
        bibotUser.setRank(rank);
        bibotUserRepository.save(bibotUser);

        return new CreateUserRes(bibotUser.getId());
    }

    @Override
    public BibotUserDTO getUser(UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        return BibotUserDTO.of(user);
    }

    @Override
    public List<GetAdminInfo> getAdminInfoList() {
        return bibotUserRepository.findAllByUserRoleInOrderByUserRole(List.of(UserRole.BIBOT_ADMIN, UserRole.BIBOT_SUPER_ADMIN))
                .stream().map(GetAdminInfo::of).toList();
    }

    @Override
    public UUID updateUserInfo(UpdateUserReq req) {
        BibotUser user = bibotUserRepository.findById(req.getUserId())
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        user.updateEntityColumns(req);

        if (req.getRankId() != null) {
            Rank rank = rankRepository.findById(req.getRankId())
                    .orElseThrow(() -> new CustomException(ResCode.RANK_NOT_FOUND));
            user.setRank(rank);
        }

        if (req.getTeamId() != null) {
            Team team = teamRepository.findById(req.getTeamId())
                    .orElseThrow(() -> new CustomException(ResCode.TEAM_NOT_FOUND));
            user.setTeam(team);
        }

        bibotUserRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public UUID changeUserRole(ChangeUserRole req) {

        RealmResource realmResource = keycloak.realm(realm);
        UserRepresentation userRep = realmResource.users().get(req.getUserId().toString()).toRepresentation();
        userRep.setRealmRoles(List.of(req.getUserRole().toString()));
        keycloak.realm(realm).users().get(req.getUserId().toString()).update(userRep);

        BibotUser user = bibotUserRepository.findById(req.getUserId())
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        user.setUserRole(req.getUserRole());
        bibotUserRepository.save(user);
        return user.getId();

    }

    @Override
    public UUID deleteUser(UUID userId) {
        bibotUserRepository.deleteById(userId);
        return userId;
    }
}
