package dev.lynith.core.ui.screens

import dev.lynith.core.Platform
import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.styles.impl.Color

class InGameHUD : Screen() {

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        Platform.hudManager.render(delta)
    }

    override fun init() {

    }


}