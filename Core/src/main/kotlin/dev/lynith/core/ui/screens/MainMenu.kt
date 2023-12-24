package dev.lynith.core.ui.screens

import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.components.callbacks.Clicked
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.nvg.Font

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
                    y = 110f,
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

            Button().configure {
                text = "Multiplayer"

                bounds = BoundingBox(
                    x = 100f,
                    y = 210f,
                    width = 150f,
                    height = 40f
                )

                on<Clicked> {
                    Platform.renderer.setScreen(IRenderer.GuiType.MULTIPLAYER_SELECTOR, toMCScreen())
                }
            },
        )
    }
}
