package com.coderecipe.v1.payment.service;

import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.MockPaymentReq;

public interface IPaymentHistoryService {

    PaymentHistoryDTO addPayment(MockPaymentReq req);
}
