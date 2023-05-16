package com.coderecipe.v1.admin.rank.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.admin.rank.service.IRankAdminService;
import com.coderecipe.v1.rank.dto.RankDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/rank")
@RequiredArgsConstructor
@Slf4j
public class RankAdminController {

    private final IRankAdminService iRankAdminService;

    @PostMapping
    public ResponseEntity<BaseRes<Long>> addRank(@RequestBody RankDTO rankData) {
        Long result = iRankAdminService.addRank(rankData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping
    public ResponseEntity<BaseRes<RankDTO>> getRank(
        @RequestParam(name = "rankId", defaultValue = "") Long rankId) {
        RankDTO result = iRankAdminService.getRank(rankId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @PutMapping
    public ResponseEntity<BaseRes<Long>> updateRank(@RequestBody RankDTO rankData) {
        Long result = iRankAdminService.updateRank(rankData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteRank(
        @RequestParam(name = "rankId", defaultValue = "") Long rankId) {
        Long result = iRankAdminService.deleteRank(rankId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));

    }
}
