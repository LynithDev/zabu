package dev.lynith.Core.versions.renderer;

import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.utils.ZabuColor;

public interface IRenderer {

    /**
     * @return The current screen being rendered
     */
    GuiScreens getCurrentScreen();

    /**
     * Sets the current screen to be rendered
     * @param screen The screen to render
     */
    void setCurrentScreen(Screen screen);
    
    /**
     * Sets the current Minecraft screen to be rendered. This is a cross-version compatibility method.
     * @param screen The screen type to render
     */
    void setCurrentScreen(GuiScreens screen, Object... args);

    /**
     * Draws a rectangle on the screen
     * @param x The x position of the rectangle
     * @param y The y position of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param color The color of the rectangle
     */
    void rect(int x, int y, int width, int height, ZabuColor color);

    /**
     * Draws a rectangle with an outline and no fill on the screen
     * @param x The x position of the rectangle
     * @param y The y position of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param thickness The thickness of the outline
     * @param color The color of the rectangle
     */
    default void rectOutline(int x, int y, int width, int height, int thickness, ZabuColor color) {
        rect(x, y, width, thickness, color); // Top
        rect(x, y, thickness, height, color); // Left
        rect(x + width - thickness, y, thickness, height, color); // Right
        rect(x, y + height - thickness, width, thickness, color); // Bottom
    }
    
    /**
     * Draws a circle on the screen
     * @param x The x position of the circle
     * @param y The y position of the circle
     * @param radius The radius of the circle
     * @param color The color of the circle
     */
    void circle(int x, int y, int radius, ZabuColor color);
    
    /**
     * Draws a line on the screen
     * @param x1 The x position of the first point
     * @param y1 The y position of the first point
     * @param x2 The x position of the second point
     * @param y2 The y position of the second point
     * @param color The color of the line
     *              
     * @apiNote Not yet implemented
     */
    void line(int x1, int y1, int x2, int y2, ZabuColor color);

    /**
     * Draws text on the screen
     * @param text The text to draw
     * @param x The x position of the text
     * @param y The y position of the text
     * @param color The color of the text
     * @param shadow Whether to draw a shadow
     */
    void text(String text, int x, int y, ZabuColor color, boolean shadow);

    /**
     * Draws text without a shadow
     * @see #text(String, int, int, ZabuColor, boolean)
     */
    default void text(String text, int x, int y, ZabuColor color) { text(text, x, y, color, false); }
    
    /**
     * Draws text without a shadow, with a default color of white
     * @see #text(String, int, int, ZabuColor, boolean)
     */
    default void text(String text, int x, int y) { text(text, x, y, ZabuColor.from(255)); }

    /**
     * Gets the width of a string from the current font renderer
     * @param text
     * @return The width of the string
     */
    int getTextWidth(String text);
    
    /**
     * Gets the height of a string from the current font renderer
     * @return The height of the string
     */
    int getTextHeight();

    
    void image(String path, int x, int y, int width, int height);

    /**
     * Gets the width of the display
     * @return The width of the display
     */
    int getWindowWidth();
    
    /**
     * Gets the height of the display
     * @return The height of the display
     */
    int getWindowHeight();

    /**
     * Gets the users game GUI scale
     * @return The GUI scale
     */
    int getScaleFactor();
}
