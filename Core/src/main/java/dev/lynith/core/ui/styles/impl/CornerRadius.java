package dev.lynith.core.ui.styles.impl;

import dev.lynith.core.ui.styles.Style;

public class CornerRadius extends Style<CornerRadius, Integer> {

    private Integer value;

    public CornerRadius() {
        this(0);
    }

    public CornerRadius(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return "corner-radius";
    }

    @Override
    public String getValueSerialized() {
        return value.toString();
    }

}
