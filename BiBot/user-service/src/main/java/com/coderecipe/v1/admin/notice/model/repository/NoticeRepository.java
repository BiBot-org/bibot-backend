package com.coderecipe.v1.admin.notice.model.repository;

import com.coderecipe.v1.admin.notice.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findTop5ByOrderByIdDesc();
}
