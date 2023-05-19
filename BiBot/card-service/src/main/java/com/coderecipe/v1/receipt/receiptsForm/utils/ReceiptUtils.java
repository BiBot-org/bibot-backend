package com.coderecipe.v1.receipt.receiptsForm.utils;

import com.coderecipe.v1.receipt.dto.ReceiptDTO;

import java.util.Random;

public class ReceiptUtils {

    private ReceiptUtils() {
        throw new IllegalStateException("Utility Class");
    }

    public static ReceiptDTO getReceiptData() {
        Random rand = new Random();
        ReceiptDTO result = new ReceiptDTO();
        int randInt = rand.nextInt(1, 100);
        if (randInt % 3 == 0) {
            result.setAmountName("총합");
        } else if (randInt % 3 == 1) {
            result.setAmountName("총가격");
        } else {
            result.setAmountName("총계");
        }

        if (randInt % 3 == 0) {
            result.setCountName("갯수");
        } else if (randInt % 3 == 1) {
            result.setCountName("수량");
        } else {
            result.setCountName("총량");
        }

        if (randInt % 3 == 0) {
            result.setPriceName("가격");
        } else if (randInt % 3 == 1) {
            result.setPriceName("금액");
        } else {
            result.setPriceName("단가");
        }
        return result;
    }

}
