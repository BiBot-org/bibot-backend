package com.coderecipe.v1.biz_trip.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.biz_trip.dto.BizTripDTO;
import com.coderecipe.v1.biz_trip.service.IBizTripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense/v1/biz_trip")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BizTripController {

    private final IBizTripService iBizTripService;

    @PostMapping("/add")
    public ResponseEntity<BaseRes<List<Integer>>> addBizTrip(
            @RequestBody List<BizTripDTO> req) {
        List<Integer> result = iBizTripService.addBizTrip(req);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
