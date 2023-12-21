package dev.lynith.core.ui.styles.impl;

import dev.lynith.core.ui.styles.Style;
import dev.lynith.core.utils.nvg.Font;

public class FontStyles extends Style<FontStyles, Style.ThreeValue<String, Integer, Font.FontWeight>> {

    private int size;
    private Font.FontWeight weight;
    private String font;

    public FontStyles() {
        this("Roboto-Regular", 16);
    }

    public FontStyles(String font) {
        this(font, 16);
    }

    public FontStyles(String font, int size) {
        this(font, size, Font.FontWeight.REGULAR);
    }

    public FontStyles(String font, int size, Font.FontWeight weight) {
        this.font = font;
        this.size = size;
        this.weight = weight;
    }

    @Override
    public Style.ThreeValue<String, Integer, Font.FontWeight> getValue() {
        return new ThreeValue<>(font, size, weight);
    }

    @Override
    public void setValue(Style.ThreeValue<String, Integer, Font.FontWeight> value) {
        this.font = value.getFirst();
        this.size = value.getSecond();
        this.weight = value.getThird();
    }

    @Override
    public String getName() {
        return "font";
    }

    @Override
    public String getValueSerialized() {
        return "font(" + size + ", " + weight + ", " + font + ")";
    }

}
