package com.coderecipe.v1.biz_trip.service;

import com.coderecipe.v1.biz_trip.dto.BizTripDTO;

import java.util.List;

public interface IBizTripService {

    List<Integer> addBizTrip(List<BizTripDTO> req);
}
