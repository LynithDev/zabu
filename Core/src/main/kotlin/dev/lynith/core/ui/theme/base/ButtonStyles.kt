package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.styles.impl.*
import dev.lynith.core.utils.nvg.Font
import lombok.Getter
import lombok.Setter

open class ButtonStyles(component: Button) : ComponentStyles<Button, ButtonStyles>(component) {

    open var padding = Padding(
        top = 5,
        right = 5,
        bottom = 10,
        left = 10
    )

    open var backgroundColor = Color(
        red = 59,
        green = 66,
        blue = 82
    )

    open var foregroundColor = Color(
        red = 255,
        blue = 255,
        green = 255
    )

    open var border = Border(
        thickness = 2,
        color = Color(
            red = 67,
            green = 76,
            blue = 94
        )
    )

    open var cornerRadius = CornerRadius(
        value = 5
    )

    open var fontStyles = FontStyles(
        name = "Roboto-Regular",
        size = 16f,
        weight = Font.FontWeight.REGULAR,
        lineHeight = 1f,
        align = Font.FontAlign.CENTER
    )
}
