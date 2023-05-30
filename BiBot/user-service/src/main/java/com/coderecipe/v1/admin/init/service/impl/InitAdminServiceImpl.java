package com.coderecipe.v1.admin.init.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.init.service.InitAdminService;
import com.coderecipe.v1.admin.init.vo.InitAdminReq.InitRootUserSetupReq;
import com.coderecipe.v1.admin.team.vo.TeamAdminReq;
import com.coderecipe.v1.user.bibotuser.enums.UserRole;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import com.coderecipe.v1.user.department.model.Department;
import com.coderecipe.v1.user.department.model.repository.DepartmentRepository;
import com.coderecipe.v1.user.team.model.Team;
import com.coderecipe.v1.user.team.model.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitAdminServiceImpl implements InitAdminService {

    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String client;
    private final BibotUserRepository bibotUserRepository;
    private final Keycloak keycloak;
    private final DepartmentRepository departmentRepository;
    private final TeamRepository teamRepository;

    @PostConstruct
    public void initialize() {
        if (Boolean.FALSE.equals(bibotUserRepository.existsBibotUserByUserRole(UserRole.SUPER_ADMIN))) {
            RealmResource realmResource = keycloak.realm(realm);
            ClientRepresentation clientRepresentation = realmResource.clients().findByClientId(client).get(0);
            ClientResource clientResource = realmResource.clients().get(clientRepresentation.getId());
            List<UserRepresentation> userList = clientResource.roles().get(UserRole.SUPER_ADMIN.toString()).getUserMembers();
            if (userList.size() == 1) {
                log.info("========================================================");
                log.info("Root 계정 초기 셋업 정보입니다.");
                log.info("ID : root@root.com PW : root");
                log.info("========================================================");
            } else {
                log.error("========================================================");
                log.error("시스템 초기 셋업에 문제가 생겼습니다.");
                log.error("기술 지원팀에 문의하세요.");
                log.error("========================================================");
            }

        }
    }

    @Override
    @Transactional
    public boolean setInitRootUser(InitRootUserSetupReq req) {
        req.getDepartmentList().forEach(dept -> {
            Department department = Department.of(dept.getName());
            departmentRepository.save(department);
            Long departmentId = department.getId();
            dept.getTeams().forEach(teamName -> {
                Team team = Team.of(new TeamAdminReq.CreateTeamReq(teamName, departmentId));
                teamRepository.save(team);
            });
        });

        Team team = teamRepository.findTeamByName(req.getRootTeamName())
                .orElseThrow(() -> new CustomException(ResCode.TEAM_NOT_FOUND));

        RealmResource realmResource = keycloak.realm(realm);
        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId(client).get(0);
        ClientResource clientResource = realmResource.clients().get(clientRepresentation.getId());
        UserRepresentation userRepresentation = clientResource.roles().get(UserRole.SUPER_ADMIN.toString()).getUserMembers().get(0);


        userRepresentation.setFirstName(req.getFirstName());
        userRepresentation.setLastName(req.getLastName());
        userRepresentation.setEmail(req.getEmail());

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(req.getPassword());
        credential.setTemporary(false);

        UserResource userResource = realmResource.users().get(userRepresentation.getId());
        userResource.resetPassword(credential);
        userResource.update(userRepresentation);


        BibotUser initUser = BibotUser.of(
                UUID.fromString(userRepresentation.getId()),
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                team);

        bibotUserRepository.save(initUser);

        return true;

    }

}
