package com.coderecipe.v1.payment.service;

import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.*;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.*;
public interface IPaymentHistoryService {
    PaymentHistoryDTO getPaymentHistory(String id);

    SearchPaymentHistoryRes searchPaymentHistory(SearchPaymentHistoryReq req);

    String addPayment(MockPaymentReq req);
}
