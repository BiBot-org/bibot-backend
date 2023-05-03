package com.coderecipe.v1.biz_trip.service.impl;

import com.coderecipe.v1.biz_trip.dto.BizTripDTO;
import com.coderecipe.v1.biz_trip.model.BizTrip;
import com.coderecipe.v1.biz_trip.model.repository.IBizTripRepository;
import com.coderecipe.v1.biz_trip.service.IBizTripService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class BizTripServiceImpl implements IBizTripService {

    private final IBizTripRepository iBizTripRepository;

    @Override
    public List<Integer> addBizTrip(List<BizTripDTO> req) {
        return req.stream().map(e -> {
            BizTrip bizTrip = BizTrip.of(e);
            iBizTripRepository.save(bizTrip);
            return bizTrip.getId();
        }).toList();
    }
}
