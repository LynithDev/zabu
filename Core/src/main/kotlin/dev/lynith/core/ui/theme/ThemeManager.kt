package dev.lynith.core.ui.theme

import dev.lynith.core.ClientStartup
import dev.lynith.core.ui.theme.base.ThemeBase
import dev.lynith.core.ui.theme.dark.ThemeDark

class ThemeManager {
    companion object {
        val instance: ThemeManager
            get() = ClientStartup.instance.themeManager
    }

    private val themes = HashSet<AbstractTheme>()
    var currentTheme: AbstractTheme
        get set

    init {
        val themeBase = ThemeBase()
        currentTheme = themeBase
        themes.add(themeBase)
        themes.add(ThemeDark())
    }

    fun setCurrentTheme(currentTheme: Class<out AbstractTheme?>): Boolean {
        val theme = getTheme(currentTheme)
        if (theme != null)
            this.currentTheme = theme

        return theme != null
    }

    fun setCurrentTheme(currentTheme: String): Boolean {
        val theme = getTheme(currentTheme)
        if (theme != null)
            this.currentTheme = theme

        return theme != null
    }

    fun getTheme(name: String): AbstractTheme? {
        for (theme in themes) {
            if (theme.name == name) {
                return theme
            }
        }
        return null
    }

    fun getTheme(clazz: Class<out AbstractTheme?>): AbstractTheme? {
        for (theme in themes) {
            if (theme.javaClass == clazz) {
                return theme
            }
        }
        return null
    }
}
