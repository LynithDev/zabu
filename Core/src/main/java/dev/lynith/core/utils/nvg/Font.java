package dev.lynith.core.utils.nvg;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Setter @Getter
public class Font {

    public final String name;
    public final String path;
    public final FontWeight weight;
    public ByteBuffer buffer;
    public final float offset;

    public Font(String name, String path, FontWeight weight, float offset) {
        this.name = name;
        this.path = path;
        this.weight = weight;
        this.offset = offset;
    }

    public Font(String name, String path, FontWeight weight) {
        this(name, path, weight, 0);
    }

    public int getHeightInPixels(int size) {
        return size;
    }

    public int getWidthInPixels(int size, String text) {
        return text.length() * size;
    }

    public enum FontWeight {
        LIGHT,
        REGULAR,
        MEDIUM,
        BOLD,
    }

    public enum FontAlign {
        LEFT,
        CENTER,
        RIGHT,
    }

}
