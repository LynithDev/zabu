package dev.lynith.core.ui.styles;

import dev.lynith.core.utils.ZabuColor;

public class Border {

    public int width;
    public ZabuColor color;

    public Border(int width, ZabuColor color) {
        this.width = width;
        this.color = color;
    }

    public Border(int width) {
        this(width, ZabuColor.from(0));
    }

    public Border() {
        this(0);
    }

}
