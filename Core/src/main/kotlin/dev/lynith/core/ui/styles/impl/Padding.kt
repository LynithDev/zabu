package dev.lynith.core.ui.styles.impl

import dev.lynith.core.ui.styles.Style

class Padding @JvmOverloads constructor(
    var top: Int = 0,
    var right: Int = 0,
    var bottom: Int = 0,
    var left: Int = 0
) : Style<Padding>() {

    override val propertyName = "padding"
    override val valueSerialized = "$top $right $bottom $left"

}
