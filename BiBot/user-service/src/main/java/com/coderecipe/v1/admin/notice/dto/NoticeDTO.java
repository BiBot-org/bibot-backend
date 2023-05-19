package com.coderecipe.v1.admin.notice.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.notice.enums.NoticeType;
import com.coderecipe.v1.admin.notice.model.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDTO {
    private Long id;
    private String title;
    private String content;
    private NoticeType type;
//    private String author;
    private UUID createdBy;
    private UUID modifiedBy;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
    private boolean isDeleted;

    public static NoticeDTO of (Notice entity) {
        return ModelMapperUtils.getModelMapper().map(entity, NoticeDTO.class);
    }

}
