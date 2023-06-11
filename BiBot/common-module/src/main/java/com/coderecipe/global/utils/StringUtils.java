package com.coderecipe.global.utils;

import com.coderecipe.global.constant.enums.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class StringUtils {

    public static final String DATE_TIME_FORMAT_FOR_PRIMARY_CODE = "yyyyMMddHHmmssSSSS";
    public static final String DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss";
    public static final String CODE_APPROVE = "APR";
    public static final String CODE_RECEIPT = "CRP";
    public static final String CODE_PAYMENT = "PAY";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateDateTimeCode(String params) {
        return params + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_FOR_PRIMARY_CODE));
    }

    public static String generateDateString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                DateTimeFormat.DATE_FORMAT.getFormatStr()));
    }

    public static String generateCloudStorageUrl(String bucketName, String filePath) {
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, filePath);
    }

    public static String generateDateStringRandom() {
        Random rand = new Random();
        int randNum = rand.nextInt(1, 3);
        if (randNum % 3 == 0) {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                    DateTimeFormat.DATE_TIME_FORMAT_DEFAULT.getFormatStr()));
        } else if (randNum % 3 == 1) {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                    DateTimeFormat.DATE_TIME_FORMAT_WITHOUT_SECOND.getFormatStr()));
        } else {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                    DateTimeFormat.DATE_FORMAT.getFormatStr()));
        }
    }
}
