package dev.lynith.core.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BoundingBox {

    @Setter
    private int x, y, width, height;

    public boolean contains(int x, int y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    public boolean intersects(BoundingBox other) {
        return x < other.x + other.width && x + width > other.x && y < other.y + other.height && y + height > other.y;
    }

    public void set(BoundingBox other) {
        x = other.x;
        y = other.y;
        width = other.width;
        height = other.height;
    }

    public void set(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

}
