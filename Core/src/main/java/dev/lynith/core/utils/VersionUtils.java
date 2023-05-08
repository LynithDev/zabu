package dev.lynith.core.utils;

public class VersionUtils {

    private static Integer parseIntSafe(String s) {
        try {
            return Integer.parseInt(s.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static Integer getMajorVersion(String version) {
        return parseIntSafe(version.split("\\.")[0]);
    }

    public static Integer getMinorVersion(String version) {
        return parseIntSafe(version.split("\\.")[1]);
    }

    public static Integer getPatchVersion(String version) {
        return parseIntSafe(version.split("\\.")[2]);
    }

    public static boolean isBetween(int from, int current, int to) {
        if (from > to) {
            return current >= to && current <= from;
        } else {
            return current >= from && current <= to;
        }
    }

    public static boolean isBetween(String from, String current, String to) {
        return isBetween(parseIntSafe(from), parseIntSafe(current), parseIntSafe(to));
    }

}
