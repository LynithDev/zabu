package dev.lynith.core.utils.nvg

import dev.lynith.core.ClientStartup
import dev.lynith.core.utils.IOUtil.resourceToByteBuffer
import org.lwjgl.nanovg.NanoVG

class FontHelper {
    companion object {
        private val fontMap = HashMap<String, Font>()

        @JvmStatic
        fun init() {
            val robotoLight = Font("Roboto", "/fonts/roboto/Roboto-Light.ttf", Font.FontWeight.LIGHT, 1.5f)
            val robotoRegular = Font("Roboto", "/fonts/roboto/Roboto-Regular.ttf", Font.FontWeight.REGULAR, 1.5f)
            val robotoMedium = Font("Roboto", "/fonts/roboto/Roboto-Medium.ttf", Font.FontWeight.MEDIUM, 1.5f)
            val robotoBold = Font("Roboto", "/fonts/roboto/Roboto-Bold.ttf", Font.FontWeight.BOLD, 1.5f)

            addAll(robotoLight, robotoRegular, robotoMedium, robotoBold)
        }

        @JvmStatic
        fun add(font: Font) {
            try {
                val buffer = resourceToByteBuffer(font.path, ClientStartup::class.java)

                NanoVG.nvgCreateFontMem(
                    ClientStartup.instance.nvgContext,
                    font.formatted(),
                    buffer,
                    1
                )
                font.buffer = buffer
                fontMap[font.formatted()] = font
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun addAll(vararg fonts: Font) {
            for (font in fonts) {
                add(font)
            }
        }

        @JvmStatic
        fun addAll(fonts: List<Font>) {
            for (font in fonts) {
                add(font)
            }
        }

        @JvmOverloads
        @JvmStatic
        fun get(name: String, weight: Font.FontWeight = Font.FontWeight.REGULAR): Font? {
            return fontMap["$name-$weight"]
        }

        @JvmStatic
        fun getOrDefault(name: String): Font {
            return getOrDefault(name, Font.FontWeight.REGULAR)
        }

        @JvmStatic
        fun getOrDefault(name: String, weight: Font.FontWeight = Font.FontWeight.REGULAR): Font {
            return getOrDefault(name, weight, fontMap["Roboto-Regular"]!!)
        }

        @JvmStatic
        fun getOrDefault(name: String, weight: Font.FontWeight, defaultFont: Font): Font {
            return fontMap["$name-$weight"] ?: defaultFont
        }
    }
}
