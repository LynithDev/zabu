package dev.lynith.core.ui.screens

import dev.lynith.core.Platform
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.animation.Animation
import dev.lynith.core.ui.animation.Easing
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.callbacks.impl.Clicked
import dev.lynith.core.ui.components.impl.Block
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.CustomWidget
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.layouts.LayoutProperties
import dev.lynith.core.ui.nvg.Font
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.styles.impl.Position
import dev.lynith.core.ui.styles.impl.PositionType
import dev.lynith.core.ui.units.ms
import dev.lynith.core.ui.units.px

class MainMenu : Screen() {

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        background()
    }

    override fun init() {
        layout {
            direction = LayoutProperties.Direction.Vertical
            align = LayoutProperties.Align.Center
            justify = LayoutProperties.Justify.Center
        }

        val rectangle = CustomWidget().configure {
            bounds = BoundingBox(
                width = 0.px,
                height = 300.px
            )

            styles.position = Position(Position.PositionType.ABSOLUTE)

            onRender {
                Platform.nvg.rectangle(bounds, Color(255, 0, 0))
            }
        }

        children (
            rectangle,

            Block().configure {
                layout {
                    direction = LayoutProperties.Direction.Vertical
                    align = LayoutProperties.Align.Center
                    justify = LayoutProperties.Justify.Center
                    childWidth = LayoutProperties.ChildSize.Fill
                    gap = LayoutProperties.Gap(
                        y = 10.px
                    )
                }

                bounds = BoundingBox(
                    width = 300.px
                )

                children(
                    Label().configure {
                        text = "ZABU"

                        style {
                            fontStyles.change {
                                size = 56.px
                                weight = Font.FontWeight.BOLD
                                align = Font.FontAlign.CENTER
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
                        text = "Play"

                        val becomeSkinny = Animation(1000.ms, Easing.SineInOut()).configure {
                            onUpdate { progress ->
                                rectangle.bounds.configure {
                                    width = 300.px * (1 - progress)
                                }
                            }
                        }

                        val becomeFatter = Animation(1000.ms, Easing.SineInOut()).configure {
                            onUpdate { progress ->
                                rectangle.bounds.configure {
                                    width = 300.px * progress
                                }
                            }
                        }

                        becomeSkinny.onFinish { becomeFatter.animate() }
                        becomeFatter.onFinish { becomeSkinny.animate() }

                        on<Clicked> {
                            if (becomeFatter.isRunning()) {
                                if (becomeFatter.isPaused()) {
                                    becomeFatter.resume()
                                } else {
                                    becomeFatter.pause()
                                } // terrible but its a proof of concept
                            } else if (becomeSkinny.isRunning()) {
                                if (becomeSkinny.isPaused()) {
                                    becomeSkinny.resume()
                                } else {
                                    becomeSkinny.pause()
                                }
                            } else {
                                becomeFatter.animate()
                            }
                        }
                    },

                    Button().configure {
                        text = "Multiplayer"

                        on<Clicked> {
                            Platform.renderer.displayMultiplayerSelectorScreen()
                        }
                    },

                    Block().configure {
                        layout {
                            direction = LayoutProperties.Direction.Horizontal
                            align = LayoutProperties.Align.Center
                            justify = LayoutProperties.Justify.Center
                            childWidth = LayoutProperties.ChildSize.Fill
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
