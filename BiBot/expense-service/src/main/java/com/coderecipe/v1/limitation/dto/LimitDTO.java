package com.coderecipe.v1.limitation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LimitDTO {

    private int id;
    private int categoryId;
    private int rankId;
    private int automatedCost;
}
