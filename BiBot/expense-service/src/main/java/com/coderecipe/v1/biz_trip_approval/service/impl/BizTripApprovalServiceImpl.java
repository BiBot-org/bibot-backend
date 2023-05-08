package com.coderecipe.v1.biz_trip_approval.service.impl;

import com.coderecipe.v1.biz_trip_approval.dto.BizTripApprovalDTO;
import com.coderecipe.v1.biz_trip_approval.model.BizTripApproval;
import com.coderecipe.v1.biz_trip_approval.model.repository.IBizTripApprovalRepository;
import com.coderecipe.v1.biz_trip_approval.service.IBizTripApprovalService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class BizTripApprovalServiceImpl implements IBizTripApprovalService {

    private final IBizTripApprovalRepository iBizTripApprovalRepository;

    @Override
    public List<Integer> addBizTripApproval(List<BizTripApprovalDTO> req) {
        return req.stream().map(e -> {
            BizTripApproval bizTripApproval = BizTripApproval.of(e);
            iBizTripApprovalRepository.save(bizTripApproval);
            return bizTripApproval.getId();
        }).toList();
    }
}
