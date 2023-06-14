package com.coderecipe.v1.user.notice.service;

import com.coderecipe.v1.admin.notice.dto.NoticeDTO;
import com.coderecipe.v1.admin.notice.enums.NoticeType;
import com.coderecipe.v1.user.notice.dto.vo.NoticeRes.SearchNoticeRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    NoticeDTO getNotice(Long id);

    SearchNoticeRes searchNotice(String title, NoticeType type, Pageable pageable);

    List<NoticeDTO> getNoticeMain();
}
