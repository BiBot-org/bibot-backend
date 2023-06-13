package com.coderecipe.v1.user.notice.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.notice.dto.NoticeDTO;
import com.coderecipe.v1.admin.notice.enums.NoticeType;
import com.coderecipe.v1.admin.notice.model.Notice;
import com.coderecipe.v1.admin.notice.model.repository.NoticeRepository;
import com.coderecipe.v1.admin.notice.model.repository.NoticeSpecification;
import com.coderecipe.v1.user.notice.dto.vo.NoticeRes.*;
import com.coderecipe.v1.user.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "notice")
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    @Cacheable(key ="#id")
    public NoticeDTO getNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResCode.NOTICE_NOT_FOUND));
        return NoticeDTO.of(notice);
    }

    @Override
    public SearchNoticeRes searchNotice(String title, NoticeType type, Pageable pageable) {
        Specification<Notice> spec = (root, query, cb) -> cb.isTrue(cb.literal((true)));

        if (!Objects.equals(title, "") && title != null) {
            spec = spec.and(NoticeSpecification.likeNoticeName(title));
        }

        if (type != null) {
            spec = spec.and(NoticeSpecification.equalType(type));
        }

        Page<NoticeDTO> searchResult = NoticeDTO.of(noticeRepository.findAll(spec, pageable));
        return SearchNoticeRes.of(searchResult);
    }

    @Override
    @Cacheable(key ="'notionMain'")
    public List<NoticeDTO> getNoticeMain() {
        return noticeRepository.findTop3ByOrderByIdDesc()
                .stream().map(NoticeDTO::of).toList();
    }
}
