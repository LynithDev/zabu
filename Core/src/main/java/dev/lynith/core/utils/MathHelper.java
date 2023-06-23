package dev.lynith.core.utils;

public class MathHelper {

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static boolean intersecting(int left, int top, int right, int bottom, int x, int y) {
        return x >= left && x <= right && y >= top && y <= bottom;
    }

    public static boolean intersecting(int left, int top, int right, int bottom, int left2, int top2, int right2, int bottom2) {
        return left <= right2 && right >= left2 && top <= bottom2 && bottom >= top2;
    }

}
