package com.coderecipe.v1.approval.model.repository;

import com.coderecipe.v1.approval.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApprovalRepository extends JpaRepository<Approval, Long> {

}
