package dev.lynith.Core.utils;

import lombok.Getter;
import lombok.Setter;

public class ImmutableTuple<K, V> {

    @Getter
    private final K key;

    @Getter @Setter
    private V value;

    public ImmutableTuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

}
