package com.coderecipe.v1.user.notice.dto.vo;

import com.coderecipe.v1.admin.notice.dto.NoticeDTO;
import com.coderecipe.v1.admin.notice.enums.NoticeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

        public static SearchNoticeRes of(Page<NoticeDTO> result) {
            return new SearchNoticeRes(result.getContent(), result.getNumber(), result.isLast(), result.getTotalPages(), result.getTotalElements());
        }
    }
}
