package com.coderecipe.v1.admin.notice.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.notice.enums.NoticeType;
import com.coderecipe.v1.admin.notice.model.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

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
    private UUID createdBy;
    private UUID modifiedBy;
    private String regTime;
    private String updateTime;
    private boolean isDeleted;

    public static NoticeDTO of(Notice entity) {
        return ModelMapperUtils.getModelMapper().map(entity, NoticeDTO.class);
    }

    public static Page<NoticeDTO> of(Page<Notice> entities) {
        return entities.map(NoticeDTO::of);
    }

}
