package com.coderecipe.v1.user.notice.dto.vo;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.notice.enums.NoticeType;
import com.coderecipe.v1.admin.notice.model.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class NoticeRes {
    private NoticeRes () {
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
        private String regTime;
        public static NoticeInfo of (Notice entity) {
            return ModelMapperUtils.getModelMapper().map(entity, NoticeInfo.class);
        }
    }

}
