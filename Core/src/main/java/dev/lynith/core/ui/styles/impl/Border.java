package dev.lynith.core.ui.styles.impl;

import dev.lynith.core.ui.styles.Style;

public class Border extends Style<Border, Style.TwoValue<Integer, Color>> {

    private int thickness;
    private Color color;

    public static Border EMPTY = new Border(0);

    public Border(int thickness) {
        this(thickness, new Color(0));
    }

    public Border(int thickness, Color color) {
        this.thickness = thickness;
        this.color = color;
    }

    @Override
    public String getName() {
        return "border";
    }

    @Override
    public String getValueSerialized() {
        return thickness + " " + color.getValueSerialized();
    }

    @Override
    public TwoValue<Integer, Color> getValue() {
        return new TwoValue<>(thickness, color);
    }

    @Override
    public void setValue(TwoValue<Integer, Color> value) {
        this.thickness = value.getFirst();
        this.color = value.getSecond();
    }

}
