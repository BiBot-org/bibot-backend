package com.coderecipe.v1.admin.rank.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.rank.service.IRankAdminService;
import com.coderecipe.v1.rank.dto.RankDTO;
import com.coderecipe.v1.rank.model.Rank;
import com.coderecipe.v1.rank.model.repository.RankRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class RankAdminServiceImpl implements IRankAdminService {

    private final RankRepository rankRepository;

    @Override
    public Long addRank(RankDTO req) {
        if(rankRepository.existsByName(req.getName())) {
            throw new CustomException(ResCode.DUPLICATE_RANK_NAME);
        } else {
            Rank rank = Rank.of(req);
            rankRepository.save(rank);
            return rank.getId();
        }
    }

    @Override
    public RankDTO getRank(Long rankId) {
        Rank rank = rankRepository.findById(rankId)
                .orElseThrow(() -> new CustomException(ResCode.RANK_NOT_FOUND));

        return RankDTO.of(rank);
    }

    @Override
    public Long updateRank(RankDTO req) {
        if(rankRepository.existsByName(req.getName())) {
            throw new CustomException(ResCode.DUPLICATE_RANK_NAME);
        } else {
            Rank rank = rankRepository.findById(req.getId())
                    .orElseThrow(() -> new CustomException(ResCode.RANK_NOT_FOUND));
            rank.updateRank(req);
            rankRepository.save(rank);
            return rank.getId();
        }
    }

    @Override
    public Long deleteRank(Long rankId) {
        rankRepository.deleteById(rankId);
        return rankId;
    }
}
