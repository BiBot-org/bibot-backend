package com.coderecipe.v1.admin.notice.dto.vo;

import com.coderecipe.v1.admin.notice.dto.NoticeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class NoticeRes {
    private NoticeRes() {
        throw new IllegalStateException("VO class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchNoticeRes {
        private List<NoticeDTO> content;
        private Integer pageNo;
        private boolean isLast;
        private Integer totalPage;
        private Long totalElement;
    }
}
