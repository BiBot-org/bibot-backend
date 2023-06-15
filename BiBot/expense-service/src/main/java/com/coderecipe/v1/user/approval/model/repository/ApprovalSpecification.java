package com.coderecipe.v1.user.approval.model.repository;

import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.model.Approval;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;

public class ApprovalSpecification {

    private ApprovalSpecification() {
        throw new IllegalStateException("JPA Specification class");
    }

    public static Specification<Approval> betweenDate(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriabuilder) -> criteriabuilder.between(root.get("regTime"), startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }

    public static Specification<Approval> equalStatus(ApprovalStatus status) {
        return (root, query, criteriabuilder) -> criteriabuilder.equal(root.get("status"), status);
    }

    public static Specification<Approval> equalCategoryId(Long categoryId) {
        return (root, query, criteriabuilder) -> criteriabuilder.equal(root.get("category").get("id"), categoryId);
    }


}
