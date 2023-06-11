package com.coderecipe.v1.payment.service;

import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.SearchPaymentHistoryReq;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.SearchPaymentHistoryInfoRes;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.SearchPaymentHistoryRes;
import org.springframework.data.domain.Pageable;

public interface IPaymentHistoryService {
    PaymentHistoryDTO getPaymentHistory(String id);

    PaymentHistoryDTO getPaymentHistoryByApprovalId(String approvalId);

    SearchPaymentHistoryInfoRes getAllPaymentHistoryByIsRequested(boolean isRequested, Pageable pageable);

    SearchPaymentHistoryRes searchPaymentHistory(SearchPaymentHistoryReq req);

    String addPayment(MockPaymentReq req);
}
