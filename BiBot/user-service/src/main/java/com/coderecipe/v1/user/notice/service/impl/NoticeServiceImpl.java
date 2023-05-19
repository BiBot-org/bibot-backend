package com.coderecipe.v1.user.notice.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.notice.dto.NoticeDTO;
import com.coderecipe.v1.admin.notice.model.Notice;
import com.coderecipe.v1.admin.notice.model.repository.NoticeRepository;
import com.coderecipe.v1.user.notice.service.NoticeService;
import com.coderecipe.v1.user.notice.dto.vo.NoticeRes.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public NoticeDTO getNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResCode.NOTICE_NOT_FOUND));
        return NoticeDTO.of(notice);
    }

    @Override
    public List<NoticeInfo> getNoticeMain() {
        return noticeRepository.findTop5ByOrderByIdDesc()
                .stream().map(NoticeInfo::of).toList();
    }
}
