package com.coderecipe.v1.admin.rank.service.impl;

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

    private RankRepository rankRepository;

    @Override
    public RankDTO addRank(RankDTO req) {
        return RankDTO.of(rankRepository.save(Rank.of(req)));
    }

    @Override
    public RankDTO getRank(Long rankId) {
        return RankDTO.of(rankRepository.getReferenceById(rankId));
    }

    @Override
    public RankDTO updateRank(RankDTO req) {
        return RankDTO.of(rankRepository.save(Rank.of(req)));
    }

    @Override
    public RankDTO deleteRank(Long rankId) {
        RankDTO rankDTO = RankDTO.of(rankRepository.getReferenceById(rankId));
        rankRepository.deleteById(rankId);
        return rankDTO;
    }
}
