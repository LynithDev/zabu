package dev.lynith.core.ui.styles.impl

import dev.lynith.core.ui.styles.Style

class Position @JvmOverloads constructor(
    var type: PositionType = PositionType.RELATIVE,
) : Style<Padding>() {

    override val propertyName = "position"
    override val valueSerialized: String
        get() = type.name.lowercase()

    enum class PositionType {
        ABSOLUTE,
        RELATIVE;
    }

}