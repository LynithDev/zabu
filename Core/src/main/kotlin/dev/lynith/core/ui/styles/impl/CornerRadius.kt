package dev.lynith.core.ui.styles.impl

import dev.lynith.core.ui.styles.Style

class CornerRadius @JvmOverloads constructor(
    var value: Int = 0
) : Style<CornerRadius>() {

    override val propertyName = "corner-radius"
    override val valueSerialized = value.toString()
}
