package com.coderecipe.global.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {

    final static String DATE_FORMAT = "yyyyMMddHHmmssSSSS";

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateApproveCode() {
        return "APR" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String generateReceiptCode() {
        return "RCP" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String generatePaymentCode() {
        return "PAY" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
