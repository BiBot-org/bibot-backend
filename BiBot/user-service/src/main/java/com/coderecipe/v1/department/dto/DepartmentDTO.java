package com.coderecipe.v1.department.dto;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.department.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long Id;
    private String name;

    public static DepartmentDTO of(Department entity) {
        return ModelMapperUtils.getModelMapper().map(entity, DepartmentDTO.class);
    }
}
