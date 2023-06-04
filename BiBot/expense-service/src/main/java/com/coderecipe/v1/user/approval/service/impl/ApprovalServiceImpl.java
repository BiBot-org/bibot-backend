package com.coderecipe.v1.user.approval.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.model.Approval;
import com.coderecipe.v1.user.approval.model.repository.IApprovalRepository;
import com.coderecipe.v1.user.approval.producer.ApprovalProducer;
import com.coderecipe.v1.user.approval.service.IApprovalService;
import com.coderecipe.v1.user.category.model.Category;
import com.coderecipe.v1.user.category.model.repository.ICategoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
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

        Approval approval = Approval.init(category, req.getUserId(), req.getTotalPrice(), req.getReceiptId());

        int amounOfApprovals = iApprovalRepository.findApprovalsByRegTimeBetweenAndRequesterIdAndCategory(
                startDate.atStartOfDay(), endDate.atStartOfDay(), req.getUserId(), category
        ).stream().mapToInt(Approval::getAmount).sum();

        if (amounOfApprovals + req.getTotalPrice() >= category.getLimitation()) {
            approval.updateApprovalStatus(ApprovalStatus.REJECTED);
        } else {
            if (req.getTotalPrice() > category.getAutomatedCost()) {
                approval.updateApprovalStatus(ApprovalStatus.PENDING);
            } else {
                approval.updateApprovalStatus(ApprovalStatus.APPROVED);
            }
        }
        log.info(String.format("Auto Approval end : %s -> %s", approval.getId(), approval.getStatus()));

        iApprovalRepository.save(approval);
        approvalProducer.sendMessageApproveEnd(new ApprovalRes.AutoApprovalRes(approval.getId(), req.getReceiptId()));
        return approval.getStatus();
    }

    @Override
    public String approvalExpense(String approvalId, ApprovalStatus status, String comment) {
        Approval approval = iApprovalRepository.findById(approvalId)
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        approval.approvalExpense(status, comment);
        iApprovalRepository.save(approval);
        return approval.getId();
    }
}
