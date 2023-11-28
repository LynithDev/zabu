package dev.lynith.core.ui.styles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Style<S extends Style<S, V>, V> {

    @Setter
    private V value;

    public abstract String getName();
    public abstract String getValueSerialized();

    @Override
    public String toString() {
        return getName() + ": " + getValueSerialized();
    }

    @Getter @Setter @AllArgsConstructor
    public static class TwoValue<A, B> {
        private A first;
        private B second;
    }

    @Getter @Setter @AllArgsConstructor
    public static class ThreeValue<A, B, C> {
        private A first;
        private B second;
        private C third;
    }

    @Getter @Setter @AllArgsConstructor
    public static class FourValue<A, B, C, D> {
        private A first;
        private B second;
        private C third;
        private D fourth;
    }

}
