package com.coderecipe.v1.user.approval.model.repository;

import com.coderecipe.v1.user.approval.model.Approval;
import com.coderecipe.v1.user.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IApprovalRepository extends JpaRepository<Approval, String>,
        JpaSpecificationExecutor<Approval> {
    List<Approval> findApprovalsByRegTimeBetweenAndRequesterIdAndCategory(LocalDateTime startDate, LocalDateTime endDate, UUID userId, Category category);
}
