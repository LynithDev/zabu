package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.styles.impl.FontStyles
import dev.lynith.core.utils.nvg.Font

open class LabelStyles(component: Label) : ComponentStyles<Label, LabelStyles>(component) {

    open var fontStyles = FontStyles(
        name = "Roboto-Regular",
        size = 16f,
        weight = Font.FontWeight.REGULAR,
        lineHeight = 1f,
        align = Font.FontAlign.CENTER
    )

    open var color = Color(
        red = 255,
        blue = 255,
        green = 255
    )

}