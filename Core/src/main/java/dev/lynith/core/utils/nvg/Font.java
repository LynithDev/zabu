package dev.lynith.core.utils.nvg;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Setter @Getter
public class Font {

    private final String name;
    private final String path;
    private final FontWeight weight;
    private ByteBuffer buffer;

    public Font(String name, String path, FontWeight weight) {
        this.name = name;
        this.path = path;
        this.weight = weight;
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

}
