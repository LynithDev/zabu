package dev.lynith.core.ui.styles.impl

import dev.lynith.core.ui.styles.Style

class Shadow(
    var offsetX: Float = 0f,
    var offsetY: Float = 0f,
    var spread: Float = 0f,
    var radius: CornerRadius = CornerRadius(),
    var blur: Float = 0f,
    var outerColor: Color = Color(0f, 0f, 0f, 0f),
    var innerColor: Color = Color(0f, 0f, 0f, 0f),
) : Style<Shadow>() {

    override val propertyName: String = "shadow"
    override val valueSerialized: String
        get() = "$offsetX, $offsetY, $spread, $radius, $blur, ${innerColor.valueSerialized}, ${outerColor.valueSerialized}"

}