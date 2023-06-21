package dev.lynith.core.utils;

import lombok.Getter;

public class Text {

    @Getter
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public String unformatted() {
        return text.replaceAll("§.", "");
    }

    public String formatted() {
        return text;
    }

    public static Text of(String text) {
        return new Text(text);
    }

}
