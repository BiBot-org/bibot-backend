package com.coderecipe.v1.admin.notice.model.repository;

import com.coderecipe.v1.admin.notice.enums.NoticeType;
import com.coderecipe.v1.admin.notice.model.Notice;
import org.springframework.data.jpa.domain.Specification;

public class NoticeSpecification {
    private NoticeSpecification() {
        throw new IllegalStateException("JPA Specification class");
    }

    public static Specification<Notice> likeNoticeName(String noticeName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("title"), "%" + noticeName + "%"));
    }

    public static Specification<Notice> equalType(NoticeType type) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("type"), type));
    }
}
