package dev.lynith.core.utils;

import lombok.Getter;
import lombok.Setter;

public class ZabuColor {

    @Getter @Setter
    private int red, green, blue, alpha;

    public ZabuColor(int r, int g, int b, int a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }

    public ZabuColor(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public ZabuColor(int rgb) {
        this((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }

    public ZabuColor() {
        this(0, 0, 0);
    }

    public ZabuColor(ZabuColor color) {
        this(color.red, color.green, color.blue, color.alpha);
    }

    public int toHex() {
        int RGB = (alpha << 24);
        RGB = RGB | (red << 16);
        RGB = RGB | (green << 8);
        RGB = RGB | (blue);
        return RGB;
    }

    public static ZabuColor fromRGB(int rgb) {
        return new ZabuColor(rgb);
    }

    public static ZabuColor from(int shared) { return new ZabuColor(shared, shared, shared); }

    public static ZabuColor from(int r, int g, int b) {
        return new ZabuColor(r, g, b);
    }

    public static ZabuColor from(int r, int g, int b, int a) {
        return new ZabuColor(r, g, b, a);
    }

    public static ZabuColor from(ZabuColor color) {
        return new ZabuColor(color.red, color.green, color.blue, color.alpha);
    }

    @Override
    public String toString() {
        return "rgba(" + red + ", " + green + ", " + blue + ", " + alpha + ")";
    }
}
