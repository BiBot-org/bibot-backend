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

    @Scheduled(cron = "0/30 * * * * *")
    @SchedulerLock(name = "category_recycle", lockAtLeastFor = "5S", lockAtMostFor = "29S")
    public void recycle() {
        // 카테고리 id를 전체 순회한다.
        // 기본 값으로 제일 앞에 있는 category를 선택
        // 카테고리가 없으면 로직 실행 안됨.

        int num = iCategoryRepository.findAll().size();

//        for (int i = 0; i < num; i++) {

            Category recycle = iCategoryRepository.findById(1L).get();
            // 1. end_date와 현재 날짜가 같으면! 반복문 사용할 때는 1L이 변수로 변경될 것임
            if (iCategoryRepository.findById(1L) != null
                && recycle.getEndDate().compareTo(LocalDate.now()) <= 0) {

                // 1-1). will_be_updated  == true,
                // star_date => 현재 날짜 => 아래에 같은 로직 있어서 지워도 될 듯
                // end_date 값 => 현재날짜 + next_cycle 만큼 적용
                // reset_cycle 값 => next_cycle 값 적용
                // imitation 값 => next_limitation 값 적용
                // automated_cost => next_automated_cost 값 적용
                if (recycle.isWillBeUpdated()) {
                    Category category = Category.builder()
                        .id(recycle.getId())
                        .automatedCost(recycle.getNextAutomatedCost())
                        .nextAutomatedCost(recycle.getNextAutomatedCost())
                        .categoryName(recycle.getCategoryName())
                        .endDate(recycle.getEndDate().plusDays(
                            ResetCycle.getResetCycleDay(recycle.getNextCycle()))) // 여기서 중복된다
                        .startDate(LocalDate.now())
                        .willBeUpdated(false)
                        .nextCycle(recycle.getNextCycle())
                        .resetCycle(recycle.getNextCycle())
                        .limitation(recycle.getNextLimitation())
                        .nextLimitation(recycle.getNextLimitation())
                        .build();
                    iCategoryRepository.save(category);
                } else {
                    // 1-2) will_be_updated = false
                    // start_date 값 => 현재 날짜 적용
                    // end_date 값 => 현재날짜 + reset_cycle 만큼 적용
                    Category category = Category.builder()
                        .id(recycle.getId())
                        .automatedCost(recycle.getAutomatedCost())
                        .nextAutomatedCost(recycle.getNextAutomatedCost())
                        .categoryName(recycle.getCategoryName())
                        .endDate(recycle.getEndDate())
                        .startDate(LocalDate.now())
                        .willBeUpdated(false)
                        .nextCycle(recycle.getNextCycle())
                        .resetCycle(recycle.getResetCycle())
                        .limitation(recycle.getLimitation())
                        .nextLimitation(recycle.getNextLimitation())
                        .build();
                    iCategoryRepository.save(category);
                }
            }
            // 2.category Id를 기반으로 해당 카테고리의 이번 주기 당 유저의 경비 처리 총합을 계산.
        }
//    }
}