package dev.lynith.core.utils.nvg;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.utils.IOUtil;
import lombok.Getter;
import org.lwjgl.nanovg.NanoVG;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;

@Getter
public class FontHelper {

    public static final HashMap<String, Font> fontMap = new HashMap<>();

    public static void init() {
        Font robotoLight = new Font("Roboto-Light", "/fonts/roboto/Roboto-Light.ttf", Font.FontWeight.LIGHT, 1.5f);
        Font robotoRegular = new Font("Roboto-Regular", "/fonts/roboto/Roboto-Regular.ttf", Font.FontWeight.REGULAR, 1.5f);
        Font robotoMedium = new Font("Roboto-Medium", "/fonts/roboto/Roboto-Medium.ttf", Font.FontWeight.MEDIUM, 1.5f);
        Font robotoBold = new Font("Roboto-Bold", "/fonts/roboto/Roboto-Bold.ttf", Font.FontWeight.BOLD, 1.5f);

        addAll(robotoLight, robotoRegular, robotoMedium, robotoBold);
    }

    public static void add(Font font) {
        try {
            ByteBuffer buffer = IOUtil.resourceToByteBuffer(font.getPath(), ClientStartup.class);

            NanoVG.nvgCreateFontMem(ClientStartup.getInstance().getNvgContext(), font.getName(), buffer, 1);
            font.setBuffer(buffer);

            fontMap.put(font.getName(), font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addAll(Font... fonts) {
        for (Font font : fonts) {
            add(font);
        }
    }

    public static void addAll(List<Font> fonts) {
        fonts.forEach(FontHelper::add);
    }

    public static Font get(String name) {
        return fontMap.get(name);
    }

    public static Font getOrDefault(String name) {
        return fontMap.getOrDefault(name, fontMap.get("Roboto-Regular"));
    }

    public static Font getOrDefault(String name, Font defaultFont) {
        return fontMap.getOrDefault(name, defaultFont);
    }

}
