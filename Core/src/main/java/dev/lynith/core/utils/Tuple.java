package dev.lynith.core.utils;

import lombok.Getter;
import lombok.Setter;

public class Tuple<K, V> {

    @Getter @Setter
    private K key;

    @Getter @Setter
    private V value;

    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Tuple<K, V> of(K key, V value) {
        return new Tuple<>(key, value);
    }

}
