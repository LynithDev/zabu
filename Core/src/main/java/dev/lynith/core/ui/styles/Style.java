package dev.lynith.core.ui.styles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
public abstract class Style<S extends Style<S, V>, V> {

    private V value;

    public abstract V getValue();
    public abstract void setValue(V value);

    public abstract String getName();
    public abstract String getValueSerialized();

    @Override
    public String toString() {
        return getName() + ": " + getValueSerialized();
    }

    @Accessors(chain = true)
    @Getter @Setter
    @AllArgsConstructor
    public static class TwoValue<A, B> {
        private A first;
        private B second;
    }

    @Accessors(chain = true)
    @Getter @Setter
    @AllArgsConstructor
    public static class ThreeValue<A, B, C> {
        private A first;
        private B second;
        private C third;
    }

    @Accessors(chain = true)
    @Getter @Setter
    @AllArgsConstructor
    public static class FourValue<A, B, C, D> {
        private A first;
        private B second;
        private C third;
        private D fourth;
    }

}
