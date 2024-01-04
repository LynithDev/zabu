package dev.lynith.core.ui.screens

import dev.lynith.core.Platform
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.components.impl.Block
import dev.lynith.core.ui.layouts.LayoutProperties
import dev.lynith.core.ui.styles.impl.Border
import dev.lynith.core.ui.styles.impl.CornerRadius

class ZabuSettingsMain : Screen() {

    override fun init() {
        layout {
            direction = LayoutProperties.Direction.Vertical
            justify = LayoutProperties.Justify.Center
            align = LayoutProperties.Align.Center
        }

        children(
            Block().configure {
                layout {
                    direction = LayoutProperties.Direction.Vertical
                    justify = LayoutProperties.Justify.Center
                }

                style {
                    backgroundColor = Platform.themeManager.currentTheme.colorScheme.background
                    border = Border(
                        thickness = 0.5f,
                        color = Platform.themeManager.currentTheme.colorScheme.border,
                    )
                    borderRadius = CornerRadius(7)
                }
            }
        )
    }

}