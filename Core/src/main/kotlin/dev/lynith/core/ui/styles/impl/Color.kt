package dev.lynith.core.ui.styles.impl

import dev.lynith.core.ui.styles.Style

class Color : Style<Color> {

    var red: Int = 0
    var green: Int = 0
    var blue: Int = 0
    var alpha: Int = 255

    @JvmOverloads
    constructor(red: Int, green: Int, blue: Int, alpha: Int = 255) {
        this.red = red
        this.green = green
        this.blue = blue
        this.alpha = alpha
    }

    @JvmOverloads
    constructor(rgb: Int, alpha: Int = 255)
        : this(
            red = rgb,
            green = rgb,
            blue = rgb,
            alpha = alpha
        )

    @JvmOverloads
    constructor(red: Float, green: Float, blue: Float, alpha: Float = 1f)
        : this(
            red = (red * 255).toInt(),
            green = (green * 255).toInt(),
            blue = (blue * 255).toInt(),
            alpha = (alpha * 255).toInt()
        )

    @JvmOverloads
    constructor(rgb: Float, alpha: Float = 1f)
        : this(rgb, rgb, rgb, alpha)

    override val propertyName = "color"

    override val valueSerialized = "rgba($red, $green, $blue, $alpha)"

}
