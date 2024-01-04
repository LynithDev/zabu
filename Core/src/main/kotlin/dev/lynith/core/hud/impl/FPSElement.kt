package dev.lynith.core.hud.impl

import dev.lynith.core.Platform
import dev.lynith.core.config.Category
import dev.lynith.core.config.ConfigOption
import dev.lynith.core.hud.HudElement
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.nvg.Font
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.styles.impl.FontStyles

class FPSElement : HudElement("FPS Counter") {

    @ConfigOption(
        name_display = "FPS Display Format",
        name_serialized = "display_format",
        description = "The format to display the FPS in. Use %d for the FPS value.",
        category = Category.Rendering,
    )
    private var textFormat = "FPS: $$$"

    @ConfigOption
    override var bounds: BoundingBox = BoundingBox(
        width = 100f,
        height = 30f,
    )

    override fun render(delta: Float) {
        rectangle(
            bounds = bounds,
            color = Color(0, 0, 0, 128)
        )

        text(
            text = textFormat.replace(Regex.fromLiteral("\$\$\$"), Platform.minecraft.fps.toString()),
            bounds = bounds,
            font = FontStyles(
                horizontalAlign = Font.HorizontalAlign.CENTER,
                verticalAlign = Font.VerticalAlign.CENTER,
            ),
            color = Color(255, 255, 255),
        )
    }

}