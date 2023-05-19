package com.coderecipe.v1.receipt.utils;

import com.coderecipe.v1.payment.dto.vo.PaymentReq.CreateMockReceiptReq;
import com.coderecipe.v1.receipt.model.IReceipt;
import com.coderecipe.v1.receipt.model.impl.Receipt1;
import com.coderecipe.v1.receipt.model.impl.Receipt2;
import com.coderecipe.v1.receipt.model.impl.Receipt3;
import com.coderecipe.v1.receipt.model.impl.Receipt4;
import com.coderecipe.v1.receipt.model.impl.Receipt5;
import com.coderecipe.v1.receipt.model.impl.Receipt6;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class ReceiptFactory {

    private final Random rand = SecureRandom.getInstanceStrong();

    public ReceiptFactory() throws NoSuchAlgorithmException {
    }


    public IReceipt createReceipt(CreateMockReceiptReq req) {
        int randNum = this.rand.nextInt(1, 7);
        if (randNum % 6 == 0) {
            return new Receipt1(req);
        } else if (randNum % 6 == 1) {
            return new Receipt2(req);
        } else if (randNum % 6 == 2) {
            return new Receipt3(req);
        } else if (randNum % 6 == 3) {
            return new Receipt4(req);
        } else if (randNum % 6 == 4) {
            return new Receipt5(req);
        } else {
            return new Receipt6(req);
        }
    }

}
