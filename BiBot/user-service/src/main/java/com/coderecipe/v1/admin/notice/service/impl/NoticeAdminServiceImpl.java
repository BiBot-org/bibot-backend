package com.coderecipe.v1.admin.notice.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeRes;
import com.coderecipe.v1.admin.notice.model.Notice;
import com.coderecipe.v1.admin.notice.model.repository.NoticeRepository;
import com.coderecipe.v1.admin.notice.service.NoticeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeAdminServiceImpl implements NoticeAdminService {

    private final NoticeRepository noticeRepository;

    @Override
    public Long createNotice(NoticeReq.CreateNoticeReq req) {
        Notice notice = Notice.of(req);
        noticeRepository.save(notice);
        return notice.getId();
    }

    @Override
    public Long updateNotice(NoticeReq.UpdateNoticeReq req) {
        Notice notice = noticeRepository.findById(req.getId())
                .orElseThrow(() -> new CustomException(ResCode.NOTICE_NOT_FOUND));
        notice.updateNotice(req);
        noticeRepository.save(notice);
        return notice.getId();
    }

    @Override
    public Long deleteNotice(Long id) {
        noticeRepository.deleteById(id);
        return id;
    }

    @Override
    public NoticeRes.SearchNoticeRes searchNotice(NoticeReq.SearchNoticeReq req) {
        return null;
    }
}
