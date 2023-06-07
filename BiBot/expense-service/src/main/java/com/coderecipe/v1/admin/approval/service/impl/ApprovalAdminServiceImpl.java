package com.coderecipe.v1.admin.approval.service.impl;

import com.coderecipe.v1.admin.approval.service.ApprovalAdminService;
import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
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

        Page<ApprovalDTO> result = ApprovalDTO.of(approvalRepository.findAll(spec, pageable));
        return ApprovalRes.SearchApprovalInfoRes.of(result);
    }
}
