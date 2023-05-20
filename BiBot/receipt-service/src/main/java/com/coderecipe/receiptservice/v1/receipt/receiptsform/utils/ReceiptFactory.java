package com.coderecipe.receiptservice.v1.receipt.receiptsform.utils;

import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.model.IReceipt;
import com.coderecipe.receiptservice.v1.receipt.model.impl.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class ReceiptFactory {

    private final Random rand = SecureRandom.getInstanceStrong();

    public ReceiptFactory() throws NoSuchAlgorithmException {
    }


    public IReceipt createReceipt(ReceiptReq.CreateMockReceiptReq req) {
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
