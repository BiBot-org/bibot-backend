package com.coderecipe.v1.user.category.enums;


public enum ResetCycle {
    DAILY, WEEKLY, MONTHLY;

    public static Long getResetCycleDay(ResetCycle resetCycle) {
        return switch (resetCycle) {
            case DAILY -> 1L;
            case WEEKLY -> 7L;
            case MONTHLY -> 30L;
            default -> null;
        };
    }

    public static Long getResetCycleDay(String name) {
        return switch (name) {
            case "DAILY" -> 1L;
            case "WEEKLY" -> 7L;
            case "MONTHLY" -> 30L;
            default -> null;
        };
    }
}
