package com.coderecipe.v1.admin.notice.model;

import com.coderecipe.global.constant.entity.BaseEntity;
import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq;
import com.coderecipe.v1.admin.notice.dto.vo.NoticeReq.CreateNoticeReq;
import com.coderecipe.v1.admin.notice.enums.NoticeType;
import com.coderecipe.v1.user.bibotuser.model.BibotUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(50) NOT NULL")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT NOT NULL")
    private String content;

    @Column(name = "type", columnDefinition = "VARCHAR(15)")
    @Enumerated(EnumType.STRING)
    private NoticeType type;

    public static Notice of(CreateNoticeReq req, BibotUser user) {
        Notice notice = ModelMapperUtils.getModelMapper().map(req, Notice.class);
        notice.setCreatedBy(user.getId());
        return notice;
    }

    public void updateNotice(NoticeReq.UpdateNoticeReq req, UUID userId) {
        this.title = req.getTitle();
        this.content = req.getContent();
        this.type = req.getType();
        this.setModifiedBy(userId);
    }

}
