package com.coderecipe.v1.user.approval.consumer;

import com.coderecipe.global.constant.dto.KafkaPayload;
import com.coderecipe.v1.user.approval.dto.vo.ApprovalReq;
import com.coderecipe.v1.user.approval.enums.ApprovalStatus;
import com.coderecipe.v1.user.approval.service.IApprovalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApprovalConsumer {
    private final ObjectMapper mapper;
    private final IApprovalService approvalService;

    @KafkaListener(topics = "ocr_end", groupId = "group-bibot", containerFactory = "concurrentListener")
    public void autoApprovalExecute(KafkaPayload message) {
        ApprovalReq.RequestAutoApproval request = mapper.convertValue(message.getBody(), ApprovalReq.RequestAutoApproval.class);
        log.info(request.toString());
        approvalService.autoApproval(request);
    }
}
