package dev.lynith.core.ui.nvg

import java.nio.ByteBuffer

class Font @JvmOverloads constructor(
    val name: String,
    val path: String,
    val weight: FontWeight,
    val offset: Float = 0f
) {
    var buffer: ByteBuffer? = null

    fun formatted(): String {
        return "$name-$weight"
    }

    override fun toString(): String {
        return "Font{" +
            "name='" + name + '\'' +
            ", path='" + path + '\'' +
            ", weight=" + weight +
            ", offset=" + offset +
            '}'
    }

    enum class FontWeight(private val weight: String) {
        LIGHT("Light"),
        REGULAR("Regular"),
        MEDIUM("Medium"),
        BOLD("Bold");

        override fun toString(): String {
            return weight
        }
    }

    enum class FontAlign {
        LEFT,
        CENTER,
        RIGHT
    }
}
