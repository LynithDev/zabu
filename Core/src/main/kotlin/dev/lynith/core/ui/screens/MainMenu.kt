package dev.lynith.core.ui.screens

import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.components.callbacks.Clicked
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.nvg.Font
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG

class MainMenu : Screen() {

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        background()
    }

    override fun init() {
        children (
            Label().configure {
                text = "ZABU"

                bounds = BoundingBox(
                    x = 100f,
                    y = 100f,
                )

                style {
                    fontStyles.change {
                        size = 48f
                        weight = Font.FontWeight.BOLD
                        align = Font.FontAlign.LEFT
                        letterSpacing = 5f
                    }
                }
            },

            Button().configure {
                text = "Singleplayer"

                style {
                    fontStyles.change {
                        size = 16f
                        weight = Font.FontWeight.MEDIUM
                    }
                }

                bounds = BoundingBox(
                    x = 100f,
                    y = 160f,
                    width = 150f,
                    height = 40f
                )

                on<Clicked> {
                    Platform.renderer.setScreen(IRenderer.GuiType.SINGLEPLAYER_SELECTOR, toMCScreen())
                }
            },
        )
    }
}
