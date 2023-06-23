package dev.lynith.core.ui.styles;

public class Spacing {
    public int top;
    public int right;
    public int bottom;
    public int left;

    public Spacing(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    public Spacing(int topBottom, int leftRight) {
        this(topBottom, leftRight, topBottom, leftRight);
    }

    public Spacing(int all) {
        this(all, all);
    }

    public Spacing() {
        this(0);
    }

    public static Spacing from(int top, int left, int bottom, int right) {
        return new Spacing(top, left, bottom, right);
    }

    public static Spacing from(int topBottom, int leftRight) {
        return new Spacing(topBottom, leftRight);
    }

    public static Spacing from(int all) {
        return new Spacing(all);
    }

    public static Spacing none() {
        return new Spacing();
    }

}
