package dev.lynith.core.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@RequiredArgsConstructor
public class Key {

    @Getter
    private final int code;

    @Override
    public String toString() {
        return "Key{" +
                "code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return code == key.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
