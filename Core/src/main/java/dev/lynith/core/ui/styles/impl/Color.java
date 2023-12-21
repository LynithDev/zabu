package dev.lynith.core.ui.styles.impl;

import dev.lynith.core.ui.styles.Style;
import lombok.*;

@Getter
@Setter
@Builder
public class Color extends Style<Color, Style.FourValue<Integer, Integer, Integer, Integer>> {

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int alpha = 255;

    public Color() {
        this(0, 0, 0, 255);
    }

    public Color(int rgb) {
        this(rgb, 255);
    }

    public Color(int rgb, int alpha) {
        this(rgb, rgb, rgb, alpha);
    }

    public Color(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public Color(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color(float rgb) {
        this(rgb, 1);
    }

    public Color(float rgb, float alpha) {
        this(rgb, rgb, rgb, alpha);
    }

    public Color(float red, float green, float blue) {
        this(red, green, blue, 1);
    }

    public Color(float red, float green, float blue, float alpha) {
        this.red = (int) (red * 255);
        this.green = (int) (green * 255);
        this.blue = (int) (blue * 255);
        this.alpha = (int) (alpha * 255);
    }

    @Override
    public String getName() {
        return "color";
    }

    @Override
    public String getValueSerialized() {
        return red + " " + green + " " + blue + " " + alpha;
    }

    @Override
    public FourValue<Integer, Integer, Integer, Integer> getValue() {
        return new FourValue<>(red, green, blue, alpha);
    }

    @Override
    public void setValue(FourValue<Integer, Integer, Integer, Integer> value) {
        super.setValue(value);
    }

}
