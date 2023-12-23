package dev.lynith.core.ui.styles.impl

import dev.lynith.core.ui.styles.Style

class Border @JvmOverloads constructor(
    var thickness: Int = 0,
    var color: Color = Color(0)
) : Style<Border>() {

    override val propertyName = "border"

    override val valueSerialized: String
        get() = "$thickness ${color.valueSerialized}"

}
