package com.coderecipe.v1.admin.notice.dto.vo;

import com.coderecipe.v1.admin.notice.enums.NoticeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class NoticeRes {
    private NoticeRes() {
        throw new IllegalStateException("VO class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeInfo {
        private Long id;
        private String title;
        private String content;
        private NoticeType type;
        private String createdBy;
        private String modifiedBy;
        private LocalDateTime regTime;
        private LocalDateTime updateTime;
        private boolean isDeleted;
    }
}
