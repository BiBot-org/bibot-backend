package com.coderecipe.v1.user.biz_trip_approval.model.repository;

import com.coderecipe.v1.user.biz_trip_approval.model.BizTripApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBizTripApprovalRepository extends JpaRepository<BizTripApproval, Integer> {

}
