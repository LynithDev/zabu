package dev.lynith.core.hud

import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.bridge.gui.MCScreen
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.styles.impl.Color

class InGameHUD {

    fun render(delta: Float) {
        Platform.nvg.createFrame()

        if (Screen.currentScreen?.javaClass != HudConfigScreen::class.java) {
            Platform.hudManager.render(delta)
        }

        Platform.nvg.endFrame()
    }

}