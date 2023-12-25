package dev.lynith.core.bridge.gui

import dev.lynith.core.ui.components.Screen
import dev.lynith.core.ui.screens.MainMenu

interface IRenderer {

    val currentScreen: GuiType?
    val screenMap: HashMap<Class<*>, GuiType>

    fun setScreen(screen: GuiType?, vararg args: Any?): Boolean
    fun setScreen(screen: Screen?, vararg args: Any?): Boolean

    fun displayOptionsScreen(parent: Screen = MainMenu())
    fun displaySingleplayerSelectorScreen(parent: Screen = MainMenu())
    fun displayMultiplayerSelectorScreen(parent: Screen = MainMenu())

    val windowWidth: Int
    val windowHeight: Int
    val scaleFactor: Int

    enum class GuiType {
        MAIN_MENU,
        PAUSE_MENU,
        MULTIPLAYER_SELECTOR,
        SINGLEPLAYER_SELECTOR,
        INGAME,
        CUSTOM,
        OPTIONS,
        UNKNOWN;

        var clazz: Class<*>? = null
    }
}
