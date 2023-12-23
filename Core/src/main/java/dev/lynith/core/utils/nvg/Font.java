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

    public String formatted() {
        return name + "-" + weight.formatted();
    }

    @Override
    public String toString() {
        return "Font{" +
            "name='" + name + '\'' +
            ", path='" + path + '\'' +
            ", weight=" + weight +
            ", offset=" + offset +
            '}';
    }

    public enum FontWeight {
        LIGHT("Light"),
        REGULAR("Regular"),
        MEDIUM("Medium"),
        BOLD("Bold");

        private final String name;

        public String formatted() {
            return name;
        }

        FontWeight(String name) {
            this.name = name;
        }
    }

    public enum FontAlign {
        LEFT,
        CENTER,
        RIGHT,
    }

}
