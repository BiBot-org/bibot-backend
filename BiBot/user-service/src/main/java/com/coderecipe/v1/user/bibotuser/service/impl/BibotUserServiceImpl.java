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
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "user")
public class BibotUserServiceImpl implements BibotUserService {

    private final Storage storage;
    private final BibotUserRepository bibotUserRepository;

    @Override
    @Cacheable(key = "#userId")
    public BibotUserDTO getUser(UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        return BibotUserDTO.of(user);
    }

    @Override
    @Transactional
    @Cacheable(key = "#userId + 'info'")
    public BibotUserInfo getUserInfo(UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Team team = user.getTeam();
        Department department = team.getDepartment();

        return BibotUserInfo.of(user, department, team);
    }

    @Override
    @CacheEvict(key = "#userId + 'info'")
    public String addProfile(UUID userId, MultipartFile file) {

        BibotUser bibotUser = bibotUserRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ResCode.NOT_FOUND));
        if (bibotUser.isDeleted()) {
            throw new CustomException(ResCode.NOT_FOUND);
        }

        MultipartFile imageFile = null;

        try {
            imageFile = new MockMultipartFile(userId.toString(), userId + ".png", "image/png",
                file.getBytes());
        } catch (Exception e) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        String imagePath = String.format("USER_PROFILE/%s/%s", StringUtils.generateDateString(),
            imageFile.getName());
        BlobId blobId = BlobId.of("bibot_user", imagePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imageFile.getContentType())
            .build();

        Blob blob = null;
        try {
            blob = storage.create(blobInfo, imageFile.getBytes());
        } catch (Exception e) {
            throw new CustomException(ResCode.NOT_FOUND);
        }

        bibotUserRepository.save(bibotUser.addProfile(imagePath));
        log.info(blob.toString());
        log.info(StringUtils.generateCloudStorageUrl("bibot_user", imagePath));

        return StringUtils.generateCloudStorageUrl("bibot_user", imagePath);
    }

    @Override
    @CacheEvict(key = "#userId + 'info'")
    public String updateProfile(UUID userId, MultipartFile file) {

        BibotUser bibotUser = bibotUserRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ResCode.NOT_FOUND));

        if (bibotUser.isDeleted()) {
            throw new CustomException(ResCode.NOT_FOUND);
        }

        MultipartFile imageFile = null;

        try {
            imageFile = new MockMultipartFile(userId.toString(), userId + ".png", "image/png",
                file.getBytes());
        } catch (Exception e) {
            log.info("유효하지 않은 파일 형식입니다.");
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        if (bibotUser.getProfileUrl() == null) {
            log.info("profileURL 없음");
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        String profilePath = bibotUser.getProfileUrl();

        BlobId blobId = BlobId.of("bibot_user", profilePath);
        Blob blob = storage.get(blobId);

        if (blob == null) {
            log.info("blob 객체 없음");
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        storage.delete(blobId);
        bibotUserRepository.save(bibotUser.delete(profilePath));

        String imagePath = String.format("USER_PROFILE/%s/%s", StringUtils.generateDateString(),
            imageFile.getName());
        blobId = BlobId.of("bibot_user", imagePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imageFile.getContentType())
            .build();

        blob = null;
        try {
            blob = storage.create(blobInfo, imageFile.getBytes());
        } catch (Exception e) {
            throw new CustomException(ResCode.NOT_FOUND);
        }

        bibotUserRepository.save(bibotUser.updateProfile(imagePath));
        return StringUtils.generateCloudStorageUrl("bibot_user", imagePath);
    }

    @Override
    @CacheEvict(key = "#userId + 'info'")
    public String deleteProfile(UUID userId) {

        BibotUser bibotUser = bibotUserRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ResCode.NOT_FOUND));

        if (bibotUser.isDeleted()) {
            throw new CustomException(ResCode.NOT_FOUND);
        }

        if (bibotUser.getProfileUrl() == null) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        String profilePath = bibotUser.getProfileUrl();

        BlobId blobId = BlobId.of("bibot_user", profilePath);
        Blob blob = storage.get(blobId);

        if (blob == null) {
            throw new CustomException(ResCode.BAD_REQUEST);
        }

        storage.delete(blobId);
        bibotUserRepository.save(bibotUser.delete(profilePath));

        return StringUtils.generateCloudStorageUrl("bibot_user", profilePath);
    }

}
