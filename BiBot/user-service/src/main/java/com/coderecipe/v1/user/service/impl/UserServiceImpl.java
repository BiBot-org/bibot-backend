package com.coderecipe.v1.user.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.rank.model.Rank;
import com.coderecipe.v1.rank.model.repository.RankRepository;
import com.coderecipe.v1.team.model.Team;
import com.coderecipe.v1.team.model.repository.TeamRepository;
import com.coderecipe.v1.user.dto.vo.BibotUserReq.CreateUserReq;
import com.coderecipe.v1.user.dto.vo.BibotUserRes.CreateUserRes;
import com.coderecipe.v1.user.model.BibotUser;
import com.coderecipe.v1.user.model.repository.BibotUserRepository;
import com.coderecipe.v1.user.service.IUserService;
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
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    @Value("${keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;
    private final BibotUserRepository bibotUserRepository;
    private final RankRepository rankRepository;
    private final TeamRepository teamRepository;

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
}
