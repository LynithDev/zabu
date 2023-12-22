package dev.lynith.core.ui.screens

import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.utils.nvg.Font

class MainMenu : Screen() {
    override fun render(ctx: IRenderer, mouseX: Int, mouseY: Int, delta: Float) {
        rectangle(
            0f,
            0f,
            ctx.getWindowWidth().toFloat(),
            ctx.getWindowHeight().toFloat(),
            Color(0, 255)
        )
    }

    override fun init() {
        children(

            Label(
                text = "This should be a wrapped label.",
                bounds = BoundingBox(
                    x = 300f,
                    y = 50f,
                    width = 200f,
                ),
            ).style {
                fontStyles.change {
                    size = 24f
                    weight = Font.FontWeight.BOLD
                    color = Color(255, 0, 0)
                }
            },

            Button(
                text = "Hello World!",
                bounds = BoundingBox(
                    x = 100f,
                    y = 100f,
                    width = 150f,
                    height = 40f
                )
            )

        )
    }
}
