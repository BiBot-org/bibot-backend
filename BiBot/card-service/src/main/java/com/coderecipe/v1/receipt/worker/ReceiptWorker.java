package com.coderecipe.v1.receipt.worker;

import com.coderecipe.v1.payment.dto.vo.PaymentReq.CreateMockReceiptReq;
import com.coderecipe.v1.receipt.receiptsForm.makeReciept.SelectForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiptWorker {

//    //로컬 테스트용
//    @Value("${imagePath}")
//    private String imagePath;

    public Boolean createReceiptImage(CreateMockReceiptReq req) {
        SelectForm selectForm = new SelectForm();
        try {
            selectForm.createReceiptImage(req);
        } catch (Exception e) {
            log.error("create receipt image failed!");
            return false;
        }
        return true;
    }
}
