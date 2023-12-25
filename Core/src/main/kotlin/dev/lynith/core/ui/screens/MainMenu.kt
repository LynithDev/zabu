package dev.lynith.core.ui.screens

import dev.lynith.core.Platform
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.components.callbacks.Clicked
import dev.lynith.core.ui.components.impl.Block
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.layouts.LayoutProperties
import dev.lynith.core.ui.nvg.Font
import dev.lynith.core.ui.styles.impl.Position
import dev.lynith.core.ui.units.px

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
                layout.properties.apply {
                    direction = LayoutProperties.Direction.Vertical
                    align = LayoutProperties.Align.Center
                    justify = LayoutProperties.Justify.Center
                    gap = LayoutProperties.Gap(
                        y = 10.px
                    )
                }

                children(
                    Label().configure {
                        text = "ZABU"

                        style {
                            fontStyles.change {
                                size = 56.px
                                weight = Font.FontWeight.BOLD
                                align = Font.FontAlign.LEFT
                                letterSpacing = 5.px
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

                    Block().configure {
                        layout.properties.apply {
                            direction = LayoutProperties.Direction.Horizontal
                            align = LayoutProperties.Align.Center
                            justify = LayoutProperties.Justify.Center
                            gap = LayoutProperties.Gap(
                                x = 10.px
                            )
                        }

                        children(
                            Button().configure {
                                text = "Options"

                                on<Clicked> {
                                    Platform.renderer.displayOptionsScreen()
                                }
                            },

                            Button().configure {
                                text = "Quit"

                                on<Clicked> {
                                    Platform.minecraft.scheduleStop()
                                }
                            }
                        )
                    }
                )
            }
        )
    }
}
