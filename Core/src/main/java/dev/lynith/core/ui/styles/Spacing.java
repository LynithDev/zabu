package dev.lynith.core.ui.styles;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter @Setter
public class Spacing {
    private int top;
    private int right;
    private int bottom;
    private int left;

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

    public Spacing left(int left) {
        this.left = left;
        return this;
    }

    public Spacing right(int right) {
        this.right = right;
        return this;
    }

    public Spacing top(int top) {
        this.top = top;
        return this;
    }

    public Spacing bottom(int bottom) {
        this.bottom = bottom;
        return this;
    }

    // --- Static factory methods ---

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

    public static Spacing fromLeft(int left) {
        return new Spacing(0, left, 0, 0);
    }

    public static Spacing fromRight(int right) {
        return new Spacing(0, 0, 0, right);
    }

    public static Spacing fromTop(int top) {
        return new Spacing(top, 0, 0, 0);
    }

    public static Spacing fromBottom(int bottom) {
        return new Spacing(0, 0, bottom, 0);
    }

}
