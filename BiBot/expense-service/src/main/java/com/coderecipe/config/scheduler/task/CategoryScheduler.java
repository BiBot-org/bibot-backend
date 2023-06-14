package com.coderecipe.config.scheduler.task;

import com.coderecipe.v1.user.category.model.Category;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Component
public class CategoryScheduler {

    private final ICategoryRepository iCategoryRepository;

    @Scheduled(cron = "0 0 10 * * *")
    @SchedulerLock(name = "category_recycle", lockAtLeastFor = "5S", lockAtMostFor = "30S")
    public void recycle() {
        for (Category category : iCategoryRepository.findAllByIsDeletedNot(true)) {
            if (category.getEndDate().isBefore(LocalDate.now())) {
                if (category.isWillBeUpdated()) {
                    category.updateNextValue();
                }
                category.updateNextSequence();
                iCategoryRepository.save(category);
            }
            log.info(
                    String.format("%s category updated ! start : %s , end : %s", category.getCategoryName(),
                            category.getStartDate(), category.getEndDate()));
        }
    }
}