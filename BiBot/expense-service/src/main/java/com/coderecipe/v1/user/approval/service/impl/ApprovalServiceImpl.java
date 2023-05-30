package com.coderecipe.v1.user.approval.service.impl;

import com.coderecipe.v1.user.approval.dto.ApprovalDTO;
import com.coderecipe.v1.user.approval.model.Approval;
import com.coderecipe.v1.user.approval.model.repository.IApprovalRepository;
import com.coderecipe.v1.user.approval.service.IApprovalService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class ApprovalServiceImpl implements IApprovalService {

    private final IApprovalRepository iApprovalRepository;

    @Override
    @Deprecated(since = "추후 삭제예정 (초기 테스트용 API)")
    public List<String> addApproval(List<ApprovalDTO> req) {
        return req.stream().map(e -> {
            Approval approval = Approval.of(e);
            iApprovalRepository.save(approval);
            return approval.getId();
        }).toList();
    }
}
