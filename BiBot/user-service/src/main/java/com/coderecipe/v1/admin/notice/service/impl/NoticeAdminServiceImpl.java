package com.coderecipe.v1.admin.notice.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq;
import com.coderecipe.v1.admin.notice.model.Notice;
import com.coderecipe.v1.admin.notice.model.repository.NoticeRepository;
import com.coderecipe.v1.admin.notice.service.NoticeAdminService;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import com.coderecipe.v1.user.bibotuser.model.repository.BibotUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "notice")
public class NoticeAdminServiceImpl implements NoticeAdminService {

    private final NoticeRepository noticeRepository;
    private final BibotUserRepository bibotUserRepository;

    @Override
    @CacheEvict(key = "'noticeMain'")
    public Long createNotice(NoticeReq.CreateNoticeReq req, UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        Notice notice = Notice.of(req, user);
        noticeRepository.save(notice);
        return notice.getId();
    }

    @Override
    @CacheEvict(key = "'noticeMain'")
    public Long updateNotice(NoticeReq.UpdateNoticeReq req, UUID userId) {
        BibotUser user = bibotUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        Notice notice = noticeRepository.findById(req.getId())
                .orElseThrow(() -> new CustomException(ResCode.NOTICE_NOT_FOUND));
        notice.updateNotice(req, user.getId());
        noticeRepository.save(notice);
        return notice.getId();
    }

    @Override
    @CacheEvict(key = "'noticeMain'")
    public Long deleteNotice(Long id) {
        noticeRepository.deleteById(id);
        return id;
    }


}
