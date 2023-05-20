package com.coderecipe.v1.payment.service;

import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;

public interface IPaymentHistoryService {

    String addPayment(MockPaymentReq req);
}
