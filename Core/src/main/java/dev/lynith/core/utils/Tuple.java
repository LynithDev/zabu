package dev.lynith.core.utils;

import lombok.Getter;

public class Tuple<K, V> {

    @Getter
    private final K key;

    @Getter
    private final V value;

    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Tuple<K, V> of(K key, V value) {
        return new Tuple<>(key, value);
    }

}
