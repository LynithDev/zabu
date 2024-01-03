package dev.lynith.core.ui.styles.impl

import dev.lynith.core.ui.styles.Style
import dev.lynith.core.ui.nvg.Font

class FontStyles @JvmOverloads constructor(
    var family: String = "Inter",
    var size: Float = 16f,
    var weight: Font.FontWeight = Font.FontWeight.REGULAR,
    var lineHeight: Float = 1f,
    var horizontalAlign: Font.HorizontalAlign = Font.HorizontalAlign.LEFT,
    var verticalAlign: Font.VerticalAlign = Font.VerticalAlign.TOP,
    var letterSpacing: Float = 0f,
) : Style<FontStyles>() {

    override val propertyName = "font"
    override val valueSerialized: String
        get() = "'$family', $size, $weight, $lineHeight, ${horizontalAlign.name}, $letterSpacing"

}
