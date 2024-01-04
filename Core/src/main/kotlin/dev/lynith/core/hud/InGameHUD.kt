package dev.lynith.core.hud

import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.bridge.gui.MCScreen
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.styles.impl.Color

class InGameHUD : Screen() {

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        if (Screen.currentScreen?.javaClass != HudConfigScreen::class.java) {
            Platform.hudManager.render(delta)
        }
    }

    override fun init() {

    }

    override fun toMCScreen(): MCScreen {
        return WrappedScreen(this)
    }

    private class WrappedScreen(val screen: Screen) : Screen.WrappedScreen(screen) {
        override fun render(mouseX: Int, mouseY: Int, delta: Float) {
            screen.wrappedRender(mouseX, mouseY, delta)
        }
    }

}