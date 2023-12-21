package dev.lynith.core.utils.nvg;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.styles.impl.Color;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

import static org.lwjgl.nanovg.NanoVG.*;

public class NanoVGHelper {

    public static void createFrame() {
        nvgBeginFrame(ctx(), ClientStartup.getInstance().getVersion().getRenderer().getWindowWidth(), ClientStartup.getInstance().getVersion().getRenderer().getWindowHeight(), 1);
        nvgSave(ctx());
    }

    public static void endFrame() {
        nvgRestore(ctx());
        nvgEndFrame(ctx());
    }

    public static NVGColor createColor(int r, int g, int b, int a) {
        NVGColor nvgColor = NVGColor.create();
        nvgRGBAf((float) r / 255, (float) g / 255, (float) b / 255, (float) a / 255, nvgColor);
        return nvgColor;
    }

    public static NVGColor createColor(Color color) {
        return createColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static void rectangle(float left, float top, float width, float height, Color color) {
        nvgBeginPath(ctx());
        NVGColor c = createColor(color);
        nvgFillColor(ctx(), c);
        nvgRect(ctx(), left, top, width, height);
        nvgFill(ctx());
        c.free();
    }

    public static void circle(float centerX, float centerY, float radius, Color color) {
        nvgBeginPath(ctx());
        NVGColor c = createColor(color);
        nvgFillColor(ctx(), c);
        nvgCircle(ctx(), centerX, centerY, radius);
        nvgFill(ctx());
        c.free();
    }

    public static void text(String text, float left, float top, int size, Color color, Font font) {
        nvgBeginPath(ctx());
        nvgFontFace(ctx(), font.getName());
        nvgFontSize(ctx(), size);
        nvgTextAlign(ctx(), NVG_ALIGN_LEFT | NVG_ALIGN_MIDDLE);
        NVGColor c = createColor(color);
        nvgFillColor(ctx(), c);
        nvgText(ctx(), left, top, text);
        c.free();
    }

    public static long ctx() {
        return ClientStartup.getInstance().getNvgContext();
    }

}
