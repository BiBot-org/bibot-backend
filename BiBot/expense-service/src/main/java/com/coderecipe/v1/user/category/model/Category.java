package com.coderecipe.v1.user.category.model;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.AddCategory;
import com.coderecipe.v1.user.category.dto.CategoryDTO;
import com.coderecipe.v1.user.category.enums.ResetCycle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private Long id;

    @Column(name = "category_name", columnDefinition = "VARCHAR(20) NOT NULL", unique = true)
    private String categoryName;

    @Column(name = "limitaion", columnDefinition = "INT4")
    private Integer limitation;

    @Column(name = "automated_cost", columnDefinition = "INT4")
    private Integer automatedCost;

    @Column(name = "reset_cycle")
    @Enumerated(EnumType.STRING)
    private ResetCycle resetCycle;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "will_be_updated")
    private boolean willBeUpdated = false;

    @Column(name = "next_cycle")
    @Enumerated(EnumType.STRING)
    private ResetCycle nextCycle;

    public static Category of(CategoryDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, Category.class);
    }

    public static Category of(AddCategory req) {

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now()
                .plusDays(ResetCycle.getResetCycleDay(req.getResetCycle().toString()));

        return Category.builder()
                .categoryName(req.getName())
                .limitation(req.getLimitation())
                .automatedCost(req.getAutomatedCost())
                .resetCycle(req.getResetCycle())
                .startDate(startDate)
                .endDate(endDate)
                .willBeUpdated(false)
                .build();
    }

}
