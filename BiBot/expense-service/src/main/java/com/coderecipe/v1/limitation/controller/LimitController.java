package com.coderecipe.v1.limitation.controller;

import com.coderecipe.global.constant.BaseRes;
import com.coderecipe.v1.limitation.dto.LimitDTO;
import com.coderecipe.v1.limitation.service.ILimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense/v1/limit")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class LimitController {

    private final ILimitService iLimitService;

    @PostMapping("/add")
    public ResponseEntity<BaseRes<List<Integer>>> addLimit(@RequestBody List<LimitDTO> limitData) {
        List<Integer> result = iLimitService.addLimit(limitData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));

    }
}
