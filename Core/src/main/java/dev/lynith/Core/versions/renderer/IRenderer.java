package dev.lynith.Core.versions.renderer;

import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.utils.ZabuColor;

public interface IRenderer {

    GuiScreens getCurrentScreen();
    void setCurrentScreen(Screen screen);
    void setCurrentScreen(GuiScreens screen);

    void rect(int x, int y, int width, int height, ZabuColor color);
    void circle(int x, int y, int radius, ZabuColor color);
    void line(int x1, int y1, int x2, int y2, ZabuColor color);

    void text(String text, int x, int y, ZabuColor color, boolean shadow);
    default void text(String text, int x, int y, ZabuColor color) { text(text, x, y, color, false); }
    default void text(String text, int x, int y) { text(text, x, y, ZabuColor.from(255)); }
    int getTextWidth(String text);
    int getTextHeight();


    void image(String path, int x, int y, int width, int height);

    int getDisplayWidth();
    int getDisplayHeight();

    int getScaleFactor();
}
