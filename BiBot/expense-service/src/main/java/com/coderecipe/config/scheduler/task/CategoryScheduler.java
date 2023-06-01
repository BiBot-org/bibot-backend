package com.coderecipe.config.scheduler.task;

import com.coderecipe.v1.user.category.enums.ResetCycle;
import com.coderecipe.v1.user.category.model.Category;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CategoryScheduler {

    private final ICategoryRepository iCategoryRepository;

    @Scheduled(cron = "0/10 * * * * *")
    @SchedulerLock(name = "category_recycle", lockAtLeastFor = "5S", lockAtMostFor = "29S")
    public void recycle() {

        for (Category category : iCategoryRepository.findAll()) {

            if (category.getId() != null
                && category.getEndDate().compareTo(LocalDate.now()) <= 0) {

                Category recycle;
                if (category.isWillBeUpdated()) {
                    recycle = Category.builder()
                        .id(category.getId())
                        .automatedCost(category.getNextAutomatedCost())
                        .nextAutomatedCost(category.getNextAutomatedCost())
                        .categoryName(category.getCategoryName())
                        .endDate(category.getEndDate().plusDays(
                            ResetCycle.getResetCycleDay(category.getNextCycle())))
                        .startDate(LocalDate.now())
                        .willBeUpdated(false)
                        .nextCycle(category.getNextCycle())
                        .resetCycle(category.getNextCycle())
                        .limitation(category.getNextLimitation())
                        .nextLimitation(category.getNextLimitation())
                        .build();
                } else {
                    recycle = Category.builder()
                        .id(category.getId())
                        .automatedCost(category.getAutomatedCost())
                        .nextAutomatedCost(category.getNextAutomatedCost())
                        .categoryName(category.getCategoryName())
                        .endDate(LocalDate.now()
                            .plusDays(ResetCycle.getResetCycleDay(category.getResetCycle())))
                        .startDate(LocalDate.now())
                        .willBeUpdated(false)
                        .nextCycle(category.getNextCycle())
                        .resetCycle(category.getResetCycle())
                        .limitation(category.getLimitation())
                        .nextLimitation(category.getNextLimitation())
                        .build();
                }
                iCategoryRepository.save(recycle);
            }
        }
    }
}