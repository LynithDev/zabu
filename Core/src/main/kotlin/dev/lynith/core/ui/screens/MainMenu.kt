package dev.lynith.core.ui.screens

import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.components.callbacks.Clicked
import dev.lynith.core.ui.components.callbacks.WindowResized
import dev.lynith.core.ui.components.impl.Block
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.layouts.LayoutProperties
import dev.lynith.core.ui.nvg.Font
import dev.lynith.core.ui.styles.impl.Position

class MainMenu : Screen() {

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        background()
    }

    override fun init() {
        layout.properties.apply {
            direction = LayoutProperties.Direction.Vertical
            align = LayoutProperties.Align.Center
            justify = LayoutProperties.Justify.Center
        }

        children (
            Block().configure {
                styles.position = Position(Position.PositionType.ABSOLUTE)

                bounds = BoundingBox(
                    width = Platform.renderer.windowWidth.toFloat(),
                    height = Platform.renderer.windowHeight.toFloat()
                )

                on<WindowResized> {
                    bounds = BoundingBox(
                        width = Platform.renderer.windowWidth.toFloat(),
                        height = Platform.renderer.windowHeight.toFloat()
                    )
                }

                children(
                    Button().configure {
                        text = "Settings"

                        on<Clicked> {
                            Platform.renderer.displayOptionsScreen()
                        }
                    }
                )
            },

            Block().configure {
                layout.properties.apply {
                    direction = LayoutProperties.Direction.Vertical
                    align = LayoutProperties.Align.Center
                    justify = LayoutProperties.Justify.Center
                    gap = LayoutProperties.Gap(
                        y = 5f
                    )
                }

                children(
                    Label().configure {
                        text = "ZABU"

                        style {
                            fontStyles.change {
                                size = 56f
                                weight = Font.FontWeight.BOLD
                                align = Font.FontAlign.LEFT
                                letterSpacing = 5f
                            }
                        }
                    },

                    Button().configure {
                        text = "Singleplayer"

                        on<Clicked> {
                            Platform.renderer.displaySingleplayerSelectorScreen()
                        }
                    },

                    Button().configure {
                        text = "Multiplayer"

                        on<Clicked> {
                            Platform.renderer.displayMultiplayerSelectorScreen()
                        }
                    },
                )
            }
        )
    }
}
