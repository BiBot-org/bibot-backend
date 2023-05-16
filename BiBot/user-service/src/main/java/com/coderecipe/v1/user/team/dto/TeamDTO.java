package com.coderecipe.v1.user.team.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.user.team.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;
    private Long departmentId;

    public static TeamDTO of(Team entity) {
        return ModelMapperUtils.getModelMapper().map(entity, TeamDTO.class);
    }
}
