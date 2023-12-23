package dev.lynith.core.ui.screens

import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.components.callbacks.Clicked
import dev.lynith.core.ui.components.callbacks.Pressed
import dev.lynith.core.ui.components.callbacks.Released
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.utils.ScheduleUtils
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
        val label = Label()

        children (
            label.configure {
                text = "Hello World!"

                bounds = BoundingBox(
                    x = 100f,
                    y = 150f,
                    width = 200f,
                    height = 40f
                )

                style {
                    fontStyles.change {
                        size = 24f
                        weight = Font.FontWeight.BOLD
                        color = Color(255, 0, 0)
                        align = Font.FontAlign.LEFT
                    }
                }
            },

            Button().configure {
                text = "START"

                style {
                    fontStyles.change {
                        size = 18f
                        weight = Font.FontWeight.BOLD
                    }
                }

                bounds = BoundingBox(
                    x = 100f,
                    y = 100f,
                    width = 150f,
                    height = 40f
                )

                on<Clicked> {
                    label.text = ""

                    fun changeLabelXTimes(x: Int) {
                        if (x == 0) {
                            label.text = "Hello World!"
                            return
                        }

                        label.text = "Countdown: $x"
                        ScheduleUtils.scheduleTask({
                            changeLabelXTimes(x - 1)
                        }, 1000)
                    }

                    changeLabelXTimes(10)
                }

            }

        )
    }
}
