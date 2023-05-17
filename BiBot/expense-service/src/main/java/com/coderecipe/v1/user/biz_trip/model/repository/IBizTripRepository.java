package com.coderecipe.v1.user.biz_trip.model.repository;

import com.coderecipe.v1.user.biz_trip.model.BizTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBizTripRepository extends JpaRepository<BizTrip, Integer> {

}
