package dev.lynith.core.utils;

import lombok.Getter;
import lombok.Setter;

public class MutableTuple<K, V> {

    @Getter
    private final K key;

    @Getter @Setter
    private V value;

    public MutableTuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> MutableTuple<K, V> of(K key, V value) {
        return new MutableTuple<>(key, value);
    }

}
