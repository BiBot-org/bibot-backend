package com.coderecipe.v1.biz_trip_approval.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.biz_trip_approval.dto.BizTripApprovalDTO;
import com.coderecipe.v1.biz_trip_approval.service.IBizTripApprovalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense/v1/biz_trip_approval")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BizTripApprovalController {

    private final IBizTripApprovalService iBizTripApprovalService;

    @PostMapping("/add")
    public ResponseEntity<BaseRes<List<Integer>>> addBizTripApproval(
            @RequestBody List<BizTripApprovalDTO> req) {
        List<Integer> result = iBizTripApprovalService.addBizTripApproval(req);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
