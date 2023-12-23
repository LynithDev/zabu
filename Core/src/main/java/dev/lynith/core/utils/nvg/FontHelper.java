package dev.lynith.core.utils.nvg;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.utils.IOUtil;
import lombok.Getter;
import org.lwjgl.nanovg.NanoVG;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FontHelper {

    public static final HashMap<String, Font> fontMap = new HashMap<>();

    public static void init() {
        Font robotoLight = new Font("Roboto", "/fonts/roboto/Roboto-Light.ttf", Font.FontWeight.LIGHT, 1.5f);
        Font robotoRegular = new Font("Roboto", "/fonts/roboto/Roboto-Regular.ttf", Font.FontWeight.REGULAR, 1.5f);
        Font robotoMedium = new Font("Roboto", "/fonts/roboto/Roboto-Medium.ttf", Font.FontWeight.MEDIUM, 1.5f);
        Font robotoBold = new Font("Roboto", "/fonts/roboto/Roboto-Bold.ttf", Font.FontWeight.BOLD, 1.5f);

        addAll(robotoLight, robotoRegular, robotoMedium, robotoBold);
        for (Map.Entry<String, Font> font : fontMap.entrySet()) {
            System.out.println(font.getKey() + " -> " + font.getValue());
        }
    }

    public static void add(Font font) {
        try {
            ByteBuffer buffer = IOUtil.resourceToByteBuffer(font.getPath(), ClientStartup.class);

            NanoVG.nvgCreateFontMem(
                ClientStartup.getInstance().getNvgContext(),
                font.formatted(),
                buffer,
                1
            );

            font.setBuffer(buffer);

            fontMap.put(font.formatted(), font);
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
        return get(name, Font.FontWeight.REGULAR);
    }

    public static Font get(String name, Font.FontWeight weight) {
        return fontMap.get(name + "-" + weight.formatted());
    }

    public static Font getOrDefault(String name) {
        return getOrDefault(name, Font.FontWeight.REGULAR, fontMap.get("Roboto-Regular"));
    }

    public static Font getOrDefault(String name, Font.FontWeight weight, Font defaultFont) {
        return fontMap.getOrDefault(name + "-" + weight.formatted(), defaultFont);
    }

}
