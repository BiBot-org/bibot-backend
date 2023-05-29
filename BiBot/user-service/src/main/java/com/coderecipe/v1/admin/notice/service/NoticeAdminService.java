package com.coderecipe.v1.admin.notice.service;

import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq.CreateNoticeReq;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq.UpdateNoticeReq;

import java.util.UUID;


public interface NoticeAdminService {
    Long createNotice(CreateNoticeReq req, UUID userId);

    Long updateNotice(UpdateNoticeReq req, UUID userId);

    Long deleteNotice(Long id);


}
