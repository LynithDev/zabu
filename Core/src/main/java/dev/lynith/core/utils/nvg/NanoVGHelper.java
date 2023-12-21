package dev.lynith.core.utils.nvg;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.BoundingBox;
import dev.lynith.core.ui.styles.impl.Border;
import dev.lynith.core.ui.styles.impl.Color;
import dev.lynith.core.ui.styles.impl.CornerRadius;
import dev.lynith.core.ui.styles.impl.FontStyles;
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
        nvgFillColor(ctx(), createColor(color));
        nvgRect(ctx(), left, top, width, height);
        nvgFill(ctx());
    }

    public static void rectangle(BoundingBox bounds, Color color, Border border, CornerRadius cornerRadius) {
        int radius = 0;
        if (cornerRadius != null) {
            radius = cornerRadius.getValue();
        }

        // Background
        nvgBeginPath(ctx());
        nvgFillColor(ctx(), createColor(color));
        nvgRoundedRect(ctx(), bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), radius);
        nvgFill(ctx());

        // Border
        if (border != null && border.getValue().getFirst() > 0) {
            nvgBeginPath(ctx());
            nvgStrokeColor(ctx(), createColor(border.getValue().getSecond()));
            nvgStrokeWidth(ctx(), border.getValue().getFirst());
            nvgRoundedRect(ctx(), bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), radius);
            nvgStroke(ctx());
        }
    }

    public static void rectangle(BoundingBox bounds, Color color, Border border) {
        rectangle(bounds, color, border, null);
    }

    public static void rectangle(BoundingBox bounds, Color color) {
        rectangle(bounds, color, null, null);
    }

    public static void circle(float centerX, float centerY, float radius, Color color) {
        nvgBeginPath(ctx());
        nvgFillColor(ctx(), createColor(color));
        nvgCircle(ctx(), centerX, centerY, radius);
        nvgFill(ctx());
    }

    public static void text(String text, float left, float top, int size, Color color, Font font) {
        nvgBeginPath(ctx());
        nvgFontFace(ctx(), font.getName());
        nvgFontSize(ctx(), size);
        nvgTextAlign(ctx(), NVG_ALIGN_LEFT | NVG_ALIGN_MIDDLE);
        nvgFillColor(ctx(), createColor(color));
        nvgText(ctx(), left, top, text);
    }

    public static void text(String text, BoundingBox box, FontStyles styles, Color color) {
        nvgBeginPath(ctx());
        nvgFontFace(ctx(), styles.getValue().getFirst());
        nvgFontSize(ctx(), styles.getValue().getSecond());
        nvgTextAlign(ctx(), NVG_ALIGN_LEFT | NVG_ALIGN_MIDDLE);
        nvgFillColor(ctx(), createColor(color));
        nvgTextBox(ctx(), box.getX(), box.getY(), box.getWidth(), text);
    }

    public static float textWidth(String text, FontStyles styles) {
        nvgBeginPath(ctx());
        nvgFontFace(ctx(), styles.getValue().getFirst());
        nvgFontSize(ctx(), styles.getValue().getSecond());
        float[] bounds = new float[4];
        System.out.println("textWidth: " + text);
        nvgTextBounds(ctx(), 0, 0, text, bounds);
        return bounds[2] - bounds[0];
    }

    public static int textWidthInt(String text, FontStyles styles) {
        return (int) textWidth(text, styles);
    }

    public static float textHeight(String text, FontStyles styles) {
        nvgBeginPath(ctx());
        nvgFontFace(ctx(), styles.getValue().getFirst());
        nvgFontSize(ctx(), styles.getValue().getSecond());
        float[] bounds = new float[4];
        System.out.println("textHeight: " + text);
        nvgTextBounds(ctx(), 0, 0, text, bounds);
        return bounds[3] - bounds[1];
    }

    public static long ctx() {
        return ClientStartup.getInstance().getNvgContext();
    }

}
