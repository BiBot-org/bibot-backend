package com.coderecipe.global.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class InternalDataUtils {

    private InternalDataUtils() {
        throw new IllegalStateException("Utility Class");
    }

    public static int makeRandNum() throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        return rand.nextInt(888888) + 111111;

    }
}
