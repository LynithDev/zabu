package dev.lynith.core.ui.styles.impl

import dev.lynith.core.ui.styles.Style
import dev.lynith.core.utils.nvg.Font

class FontStyles @JvmOverloads constructor(
    var name: String = "Roboto",
    var size: Float = 16f,
    var weight: Font.FontWeight = Font.FontWeight.REGULAR,
    var lineHeight: Float = 1f,
    var align: Font.FontAlign = Font.FontAlign.LEFT,
) : Style<FontStyles>() {

    override val propertyName = "font"
    override val valueSerialized = "$size, $weight, $name"

}
