package dev.lynith.core.utils;

import java.util.HashMap;

public class ExtendedHashMap<V> extends HashMap<String, V> {

    public Object get(String key, Object defaultValue) {
        return get(key) != null ? get(key) : defaultValue;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return get(key) != null ? (boolean) get(key) : defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        return get(key) != null ? (int) get(key) : defaultValue;
    }

    public float getFloat(String key, float defaultValue) {
        return get(key) != null ? (float) get(key) : defaultValue;
    }

    public double getDouble(String key, double defaultValue) {
        return get(key) != null ? (double) get(key) : defaultValue;
    }

    public String getString(String key, String defaultValue) {
        return get(key) != null ? (String) get(key) : defaultValue;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    public String getString(String key) {
        return getString(key, "");
    }

}
