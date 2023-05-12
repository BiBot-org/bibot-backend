package com.coderecipe.v1.auth.service.impl;

import com.coderecipe.v1.auth.dto.vo.AuthReq.SignInReq;
import com.coderecipe.v1.auth.dto.vo.AuthRes.TokenRes;
import com.coderecipe.v1.auth.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class IAuthServiceImpl implements IAuthService {

    @Value("$keycloak.auth-server-url")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private final Keycloak keycloak;

    @Override
    public TokenRes requestSignIn(SignInReq req) {
        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "password");

        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);
        AccessTokenResponse accessTokenResponse = authzClient.obtainAccessToken(req.getUserEmail(), req.getPassword());
        return TokenRes.of(accessTokenResponse);

    }

    public boolean existsByUsername(String userName) {

        List<UserRepresentation> search = keycloak.realm(realm).users()
                .search(userName);
        if (!search.isEmpty()) {
            log.debug("search : {}", search.get(0).getUsername());
            return true;
        }
        return false;
    }
}
