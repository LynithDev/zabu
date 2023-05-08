package dev.lynith.core.utils;

public class MathHelper {

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

}
