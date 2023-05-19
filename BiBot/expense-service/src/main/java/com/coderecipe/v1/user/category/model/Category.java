package com.coderecipe.v1.user.category.model;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.*;
import com.coderecipe.v1.user.category.dto.CategoryDTO;
import com.coderecipe.v1.user.category.enums.ResetCycle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "category_name", columnDefinition = "VARCHAR(20) NOT NULL")
    public String categoryName;

    @Column(name = "limitaion", columnDefinition = "INT4")
    public Integer limitation;

    @Column(name = "automated_cost", columnDefinition = "INT4")
    private Integer automatedCost;

    @Column(name = "reset_cycle")
    @Enumerated(EnumType.STRING)
    public ResetCycle resetCycle;

    public static Category of(CategoryDTO dto) {
        return Category.builder()
                .id(dto.getId())
                .categoryName(dto.getCategoryName())
                .resetCycle(dto.getResetCycle())
                .build();
    }

    public static Category of(AddCategory req) {
        return ModelMapperUtils.getModelMapper().map(req, Category.class);
    }

}