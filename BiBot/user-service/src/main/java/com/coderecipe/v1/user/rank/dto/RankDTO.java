package com.coderecipe.v1.user.rank.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.user.rank.model.Rank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankDTO {
    private Long id;
    private String name;
    public static RankDTO of(Rank entity) {
        return ModelMapperUtils.getModelMapper().map(entity, RankDTO.class);
    }
}
