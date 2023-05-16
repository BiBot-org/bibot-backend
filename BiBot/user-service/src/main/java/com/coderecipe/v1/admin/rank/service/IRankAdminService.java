package com.coderecipe.v1.admin.rank.service;

import com.coderecipe.v1.user.rank.dto.RankDTO;

public interface IRankAdminService {

    Long addRank(RankDTO req);
    RankDTO getRank(Long rankId);
    Long updateRank(RankDTO req);
    Long deleteRank(Long rankId);
}
