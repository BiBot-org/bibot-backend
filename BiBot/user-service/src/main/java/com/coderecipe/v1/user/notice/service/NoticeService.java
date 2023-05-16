package com.coderecipe.v1.user.notice.service;

import com.coderecipe.v1.admin.notice.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {
    NoticeDTO getNotice(Long id);
    List<NoticeDTO> getNoticeMain();
}
