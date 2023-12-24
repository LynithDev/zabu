package dev.lynith.core.ui.nvg

import dev.lynith.core.ClientStartup
import dev.lynith.core.Platform
import dev.lynith.core.utils.IOUtil.resourceToByteBuffer
import org.lwjgl.nanovg.NanoVG

class FontHelper {
    private val fontMap = HashMap<String, Font>()

    init {
        addAll(
            Font("Inter", "/fonts/inter/Inter-Light.ttf", Font.FontWeight.LIGHT, 1f),
            Font("Inter", "/fonts/inter/Inter-Regular.ttf", Font.FontWeight.REGULAR, 1f),
            Font("Inter", "/fonts/inter/Inter-Medium.ttf", Font.FontWeight.MEDIUM, 1f),
            Font("Inter", "/fonts/inter/Inter-SemiBold.ttf", Font.FontWeight.SEMIBOLD, 1f),
            Font("Inter", "/fonts/inter/Inter-Bold.ttf", Font.FontWeight.BOLD, 1f),
        )
    }

    private fun add(font: Font) {
        try {
            val buffer = resourceToByteBuffer(font.path, ClientStartup::class.java)

            NanoVG.nvgCreateFontMem(
                Platform.nvg.ctx,
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

    private fun addAll(vararg fonts: Font) {
        for (font in fonts) {
            add(font)
        }
    }

    @JvmOverloads
    fun get(name: String, weight: Font.FontWeight = Font.FontWeight.REGULAR): Font? {
        return fontMap["$name-$weight"]
    }

    fun getOrDefault(name: String): Font {
        return getOrDefault(name, Font.FontWeight.REGULAR)
    }

    fun getOrDefault(name: String, weight: Font.FontWeight = Font.FontWeight.REGULAR): Font {
        return getOrDefault(name, weight, fontMap["Inter-Regular"]!!)
    }

    fun getOrDefault(name: String, weight: Font.FontWeight, defaultFont: Font): Font {
        return fontMap["$name-$weight"] ?: defaultFont
    }
}
