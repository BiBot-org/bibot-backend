package com.coderecipe.v1.user.category.model;

import com.coderecipe.global.constant.entity.BaseEntity;
import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.AddCategory;
import com.coderecipe.v1.admin.category.vo.CategoryAdminReq.UpdateCategory;
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
public class Category extends BaseEntity {

    @Column(name = "next_limitaion", columnDefinition = "INT4")
    public Integer nextLimitation;
    @Column(name = "reset_cycle")
    @Enumerated(EnumType.STRING)
    public ResetCycle resetCycle;
    @Column(name = "next_cycle")
    @Enumerated(EnumType.STRING)
    public ResetCycle nextCycle;
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

    @Column(name = "next_automated_cost", columnDefinition = "INT4")
    private Integer nextAutomatedCost;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "will_be_updated")
    private boolean willBeUpdated = false;

    public static Category of(CategoryDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, Category.class);
    }

    public static Category of(AddCategory req) {

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now()
                .plusDays(ResetCycle.getResetCycleDay(req.getResetCycle().toString()));

        return Category.builder()
                .categoryName(req.getCategoryName())
                .limitation(req.getLimitation())
                .nextLimitation(req.getLimitation())
                .automatedCost(req.getAutomatedCost())
                .nextAutomatedCost(req.getAutomatedCost())
                .resetCycle(req.getResetCycle())
                .nextCycle(req.getResetCycle())
                .startDate(startDate)
                .endDate(endDate)
                .willBeUpdated(false)
                .build();
    }

    public void updateCategory(UpdateCategory req) {
        boolean check = false;
        if (!this.categoryName.equals(req.getCategoryName())) {
            this.categoryName = req.getCategoryName();
            check = true;
        }
        if (this.nextLimitation == null || !this.nextLimitation.equals(req.getNextLimitation())) {
            this.nextLimitation = req.getNextLimitation();
            check = true;
        }
        if (this.nextAutomatedCost == null || !this.nextAutomatedCost.equals(req.getNextAutomatedCost())) {
            this.nextAutomatedCost = req.getNextAutomatedCost();
            check = true;
        }
        if (this.nextCycle == null || !this.nextCycle.equals(req.getNextCycle())) {
            this.nextCycle = req.getNextCycle();
            check = true;
        }
        if (check) {
            this.willBeUpdated = true;
        }
    }

    public void updateNextValue() {
        this.automatedCost = this.nextAutomatedCost;
        this.limitation = this.nextLimitation;
        this.resetCycle = this.nextCycle;
        this.willBeUpdated = false;
    }

    public void updateNextSequence() {
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusDays(ResetCycle.getResetCycleDay(this.resetCycle));
    }

    public void deleteCategory() {
        super.setDeleted(true);
    }

}
