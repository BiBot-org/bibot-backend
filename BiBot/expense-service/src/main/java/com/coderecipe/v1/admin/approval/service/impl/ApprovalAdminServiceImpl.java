package com.coderecipe.v1.admin.approval.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminReq;
import com.coderecipe.v1.admin.approval.dto.vo.ApprovalAdminRes;
import com.coderecipe.v1.admin.approval.service.ApprovalAdminService;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalRes;
import com.coderecipe.v1.user.approval.model.Approval;
import com.coderecipe.v1.user.approval.model.repository.ApprovalSpecification;
import com.coderecipe.v1.user.approval.model.repository.IApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApprovalAdminServiceImpl implements ApprovalAdminService {

    private final IApprovalRepository approvalRepository;

    @Override
    public List<ApprovalRes.ApprovalInfo> getApprovalThumbnail() {
        return approvalRepository.findTop5ByOrderByIdDesc()
                .stream().map(ApprovalRes.ApprovalInfo::of).toList();
    }

    @Override
    public ApprovalAdminRes.SearchAdminApprovalRes searchApprovalInfo(ApprovalReq.SearchApprovalInfoReq req, Pageable pageable) {
        Specification<Approval> spec = (root, query, cb) -> cb.isTrue(cb.literal((true)));

        if (req.getEndDate() != null && req.getStartDate() != null) {
            spec = spec.and(ApprovalSpecification.betweenDate(req.getStartDate(), req.getEndDate().plusDays(1)));
        }

        if (req.getCategoryId() != null && req.getCategoryId() != 0L) {
            spec = spec.and(ApprovalSpecification.equalCategoryId(req.getCategoryId()));
        }

        if (req.getStatus() != null) {
            spec = spec.and(ApprovalSpecification.equalStatus(req.getStatus()));
        }

        Page<ApprovalAdminRes.SearchAdminApproval> result = ApprovalAdminRes.SearchAdminApproval.of(approvalRepository.findAll(spec, pageable));
        return ApprovalAdminRes.SearchAdminApprovalRes.of(result);
    }

    @Override
    public String approveExpense(ApprovalAdminReq.RequestApproval req, UUID userId) {
        Approval approval = approvalRepository.findById(req.getApprovalId())
                .orElseThrow(() -> new CustomException(ResCode.BAD_REQUEST));
        approval.approvalExpense(req.getStatus(), req.getComment());
        approval.updateManagerId(userId);
        approvalRepository.save(approval);
        return approval.getId();

    }

}
