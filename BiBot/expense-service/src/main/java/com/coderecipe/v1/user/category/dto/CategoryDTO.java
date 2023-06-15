package com.coderecipe.v1.user.category.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.user.category.enums.ResetCycle;
import com.coderecipe.v1.user.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String categoryName;
    private Integer limitation;
    private Integer nextLimitation;
    private Integer automatedCost;
    private Integer nextAutomatedCost;
    private ResetCycle resetCycle;
    private String startDate;
    private String endDate;
    private boolean willBeUpdated;
    private ResetCycle nextCycle;

    public static CategoryDTO of(Category entity) {
        return ModelMapperUtils.getModelMapper().map(entity, CategoryDTO.class);
    }
}
