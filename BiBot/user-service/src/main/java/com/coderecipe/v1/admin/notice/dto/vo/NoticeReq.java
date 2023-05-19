package com.coderecipe.v1.admin.notice.dto.vo;

import com.coderecipe.v1.admin.notice.enums.NoticeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class NoticeReq {
    private NoticeReq () {
        throw new IllegalStateException("VO class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateNoticeReq {
        private String title;
        private String content;
        private NoticeType type;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateNoticeReq extends CreateNoticeReq {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchNoticeReq {
        private String title;
        private NoticeType type;
        private LocalDate startDate;
        private LocalDate endDate;
    }

}
