package com.coderecipe.v1.user.bibotuser.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.v1.user.bibotuser.dto.BibotUserDTO;
import com.coderecipe.v1.user.bibotuser.dto.vo.BibotUserReq.BibotUserInfo;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import com.coderecipe.v1.user.bibotuser.service.BibotUserService;
import com.coderecipe.v1.user.department.model.Department;
import com.coderecipe.v1.user.team.model.Team;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "user")
public class BibotUserServiceImpl implements BibotUserService {
    private final Storage storage;
    private final BibotUserRepository bibotUserRepository;
    private final Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String client;
    @Value("${gcp.bucketName}")
    private String bucketName;

    @Override
    @Cacheable(key = "#userId", unless = "#result == null")
    public BibotUserDTO getUser(UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        return BibotUserDTO.of(user);
    }

    @Override
    @Transactional
    @Cacheable(key = "#userId + '::info'", unless = "#result == null")
    public BibotUserInfo getUserInfo(UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Team team = user.getTeam();
        Department department = team.getDepartment();
        return BibotUserInfo.of(user, department, team);
    }

    @Override
    @CachePut(key = "#userId + '::info'", unless = "#result == null")
    public String addProfile(UUID userId, MultipartFile file) throws IOException {

        BibotUser bibotUser = bibotUserRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(ResCode.NOT_FOUND));

        String imagePath = String.format("USER_PROFILE/%s/%s", StringUtils.generateDateString(),
                file.getOriginalFilename());
        BlobId blobId = BlobId.of(bucketName, imagePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());
        String storageUrl = StringUtils.generateCloudStorageUrl(bucketName, imagePath);
        bibotUser.addProfile(storageUrl);
        bibotUserRepository.save(bibotUser);

        return StringUtils.generateCloudStorageUrl(bucketName, imagePath);
    }

    @Override
    @CachePut(key = "#userId + '::info'", unless = "#result == null")
    public String updateProfile(UUID userId, MultipartFile file) throws IOException {

        BibotUser bibotUser = bibotUserRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(ResCode.NOT_FOUND));

        MultipartFile imageFile = new MockMultipartFile(userId.toString(), userId + ".png", "image/png",
                file.getBytes());

        if (bibotUser.getProfileUrl() == null || "".equals(bibotUser.getProfileUrl())) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        String profilePath = bibotUser.getProfileUrl();

        BlobId blobId = BlobId.of(bucketName, profilePath);

        if (storage.get(blobId) != null) {
            storage.delete(blobId);
        } else {
            throw new CustomException(ResCode.BAD_REQUEST);
        }
        bibotUser.deleteProfile();
        bibotUserRepository.save(bibotUser);

        String imagePath = String.format("USER_PROFILE/%s/%s", StringUtils.generateDateString(),
                imageFile.getName());
        blobId = BlobId.of(bucketName, imagePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imageFile.getContentType())
                .build();

        storage.create(blobInfo, imageFile.getBytes());
        bibotUser.updateProfile(imagePath);
        bibotUserRepository.save(bibotUser);
        return StringUtils.generateCloudStorageUrl(bucketName, imagePath);
    }

    @Override
    @CacheEvict(key = "#userId + '::info'")
    public String deleteProfile(UUID userId) {

        BibotUser bibotUser = bibotUserRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(ResCode.NOT_FOUND));

        if (bibotUser.getProfileUrl() == null) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        String profilePath = bibotUser.getProfileUrl();
        BlobId blobId = BlobId.of(bucketName, profilePath);

        if (storage.get(blobId) != null) {
            storage.delete(blobId);
        } else {
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        bibotUser.deleteProfile();
        bibotUserRepository.save(bibotUser);

        return StringUtils.generateCloudStorageUrl(bucketName, profilePath);
    }

    public boolean changePassword(UUID userId, String newPassword) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(newPassword);
        credential.setTemporary(false);

        RealmResource realmResource = keycloak.realm(realm);
        UserResource userResource = realmResource.users().get(userId.toString());
        userResource.resetPassword(credential);

        return true;
    }
}
