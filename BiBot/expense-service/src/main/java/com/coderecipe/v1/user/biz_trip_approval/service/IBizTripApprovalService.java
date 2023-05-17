package com.coderecipe.v1.user.biz_trip_approval.service;

import com.coderecipe.v1.user.biz_trip_approval.dto.BizTripApprovalDTO;

import java.util.List;

public interface IBizTripApprovalService {

    List<Integer> addBizTripApproval(List<BizTripApprovalDTO> req);
}
