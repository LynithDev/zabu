package dev.lynith.core.bridge.gui;

public interface IRendererHelper {

    void rect(int x, int y, int width, int height, int color);

    void text(String text, int x, int y, int color);
    void text(String text, int x, int y, int color, boolean shadow);
    int textWidth(String text);
    int fontHeight();

}
