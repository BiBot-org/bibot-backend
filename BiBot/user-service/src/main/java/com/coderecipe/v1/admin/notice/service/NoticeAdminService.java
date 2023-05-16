package com.coderecipe.v1.admin.notice.service;

import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq.SearchNoticeReq;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq.CreateNoticeReq;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq.UpdateNoticeReq;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeRes.SearchNoticeRes;


public interface NoticeAdminService {
    Long createNotice(CreateNoticeReq req);
    Long updateNotice(UpdateNoticeReq req);
    Long deleteNotice(Long id);
    SearchNoticeRes searchNotice(SearchNoticeReq req);


}
