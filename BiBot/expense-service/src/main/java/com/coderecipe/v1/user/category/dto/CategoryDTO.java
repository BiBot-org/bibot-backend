package com.coderecipe.v1.user.category.dto;

import com.coderecipe.v1.user.category.enums.ResetCycle;
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
    private Integer automatedCost;
    private ResetCycle resetCycle;
}
