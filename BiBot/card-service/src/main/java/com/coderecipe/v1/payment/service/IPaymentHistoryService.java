package com.coderecipe.v1.payment.service;

import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.*;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPaymentHistoryService {
    PaymentHistoryDTO getPaymentHistory(String id);
    SearchPaymentHistoryInfoRes getAllPaymentHistoryByIsRequested(boolean isRequested, Pageable pageable);
    SearchPaymentHistoryRes searchPaymentHistory(SearchPaymentHistoryReq req);
    String addPayment(MockPaymentReq req);
}
