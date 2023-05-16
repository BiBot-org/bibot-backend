package com.coderecipe.v1.admin.rank.service;

import com.coderecipe.v1.rank.dto.RankDTO;

public interface IRankAdminService {

    RankDTO addRank(RankDTO req);
    RankDTO getRank(Long rankId);
    RankDTO updateRank(RankDTO req);
    RankDTO deleteRank(Long rankId);
}
