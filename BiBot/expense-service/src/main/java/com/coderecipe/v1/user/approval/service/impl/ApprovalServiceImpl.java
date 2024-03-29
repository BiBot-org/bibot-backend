package com.coderecipe.v1.user.approval.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.model.Approval;
import com.coderecipe.v1.user.approval.model.repository.ApprovalSpecification;
import com.coderecipe.v1.user.approval.model.repository.IApprovalRepository;
import com.coderecipe.v1.user.approval.producer.ApprovalProducer;
import com.coderecipe.v1.user.approval.service.IApprovalService;
import com.coderecipe.v1.user.category.model.Category;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "approval")
public class ApprovalServiceImpl implements IApprovalService {

    private final IApprovalRepository iApprovalRepository;
    private final ICategoryRepository iCategoryRepository;
    private final ApprovalProducer approvalProducer;

    @Override
    public ApprovalStatus autoApproval(ApprovalReq.RequestAutoApproval req) {
        Category category = iCategoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        LocalDate startDate = category.getStartDate();
        LocalDate endDate = category.getEndDate();
        Approval approval = iApprovalRepository.findApprovalByReceiptId(req.getReceiptId())
                .orElse(Approval.init(category, req.getUserId(), req.getTotalPrice(), req.getReceiptId()));
        approval.updateComment("");
        approval.updateAmount(req.getTotalPrice());

        int amountOfApprovals = iApprovalRepository.findApprovalsByRegTimeBetweenAndRequesterIdAndCategory(
                startDate.atStartOfDay(), endDate.atStartOfDay(), req.getUserId(), category
        ).stream().filter(e -> e.getStatus() == ApprovalStatus.APPROVED).mapToInt(Approval::getAmount).sum();

        if (amountOfApprovals + req.getTotalPrice() >= category.getLimitation()) {
            approval.updateApprovalStatus(ApprovalStatus.REJECTED);
        } else {
            if (req.getTotalPrice() > category.getAutomatedCost()) {
                approval.updateApprovalStatus(ApprovalStatus.PENDING);
            } else {
                approval.updateComment("자동 결재");
                approval.updateApprovalStatus(ApprovalStatus.APPROVED);
                approval.updateIsAutomated(true);
            }
        }
        log.info(String.format("Auto Approval end : %s -> %s", approval.getId(), approval.getStatus()));

        iApprovalRepository.save(approval);
        approvalProducer.sendMessageApproveEnd(new ApprovalRes.AutoApprovalRes(approval.getId(), req.getReceiptId()));
        return approval.getStatus();
    }

    @Override
    public ApprovalStatus autoApprovalFail(ApprovalReq.RequestApprovalFail req) {
        Category category = iCategoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));

        Approval approval = Approval.init(category, req.getUserId(), req.getReceiptId(), req.getMessage());
        approval.updateApprovalStatus(ApprovalStatus.REJECTED);
        iApprovalRepository.save(approval);
        approvalProducer.sendMessageApproveEnd(new ApprovalRes.AutoApprovalRes(approval.getId(), req.getReceiptId()));
        return ApprovalStatus.REJECTED;
    }

    @Override
    public ApprovalRes.SearchApprovalInfoRes searchApprovalInfo(ApprovalReq.SearchApprovalInfoReq req, Pageable pageable) {
        Specification<Approval> spec = (root, query, cb) -> cb.isTrue(cb.literal((true)));

        if (req.getEndDate() != null && req.getStartDate() != null) {
            spec = spec.and(ApprovalSpecification.betweenDate(req.getStartDate(), req.getEndDate()));
        }

        if (req.getCategoryId() != null) {
            spec = spec.and(ApprovalSpecification.equalCategoryId(req.getCategoryId()));
        }

        if (req.getStatus() != null) {
            spec = spec.and(ApprovalSpecification.equalStatus(req.getStatus()));
        }

        Page<ApprovalRes.SearchApproval> result = ApprovalRes.SearchApproval.of(iApprovalRepository.findAll(spec, pageable));
        return ApprovalRes.SearchApprovalInfoRes.of(result);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public ApprovalDTO getApprovalInfo(String id) {
        Approval approval = iApprovalRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        return ApprovalDTO.of(approval);
    }

    @Override
    public ApprovalRes.GetExpenseProcessingStatusByCategoryRes getExpenseProcessingstatusByCategory(UUID userId, Long categoryId) {

        if (categoryId != null) {
            Category category = iCategoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));

            List<Approval> result = iApprovalRepository.findApprovalsByRegTimeBetweenAndRequesterIdAndCategory(category.getStartDate().atStartOfDay(),
                    category.getEndDate().atStartOfDay(), userId, category);
            int amountOfApprovals = result.stream().filter(e -> e.getStatus() == ApprovalStatus.APPROVED).mapToInt(Approval::getAmount).sum();

            int limitation = category.getLimitation();
            return new ApprovalRes.GetExpenseProcessingStatusByCategoryRes(limitation, amountOfApprovals);
        } else {
            List<Category> categories = iCategoryRepository.findAll();
            int limitation = categories.stream().mapToInt(Category::getLimitation).sum();
            int amountOfApprovals = categories.stream()
                    .mapToInt(category -> iApprovalRepository.findApprovalsByRegTimeBetweenAndRequesterIdAndCategory(
                                    category.getStartDate().atStartOfDay(), category.getEndDate().atStartOfDay(), userId, category)
                            .stream().filter(approval -> approval.getStatus() == ApprovalStatus.APPROVED).mapToInt(Approval::getAmount).sum()
                    ).sum();
            return new ApprovalRes.GetExpenseProcessingStatusByCategoryRes(limitation, amountOfApprovals);
        }
    }

    @Override
    @Deprecated(since = "2023-06-09, 위에 친구와 리턴 타입이 같으므로 굳이 필요가 없음.")
    public ApprovalRes.GetExpenseProcessingStatusByCategoryRes getAllExpenseProcessingStatus(UUID userId) {
        List<Category> categories = iCategoryRepository.findAll();
        int limitation = categories.stream().mapToInt(Category::getLimitation).sum();
        int amountOfApprovals = categories.stream()
                .mapToInt(category -> iApprovalRepository.findApprovalsByRegTimeBetweenAndRequesterIdAndCategory(
                                category.getStartDate().atStartOfDay(), category.getEndDate().atStartOfDay(), userId, category)
                        .stream().filter(approval -> approval.getStatus() == ApprovalStatus.APPROVED).mapToInt(Approval::getAmount).sum()
                ).sum();
        return new ApprovalRes.GetExpenseProcessingStatusByCategoryRes(limitation, amountOfApprovals);
    }
}
