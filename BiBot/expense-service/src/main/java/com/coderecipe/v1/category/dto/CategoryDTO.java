package com.coderecipe.v1.category.dto;

import com.coderecipe.v1.category.enums.ResetCycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private int id;
    private String categoryName;
    private ResetCycle resetCycle;
}
