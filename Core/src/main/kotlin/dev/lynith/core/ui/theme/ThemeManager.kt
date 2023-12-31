package dev.lynith.core.ui.theme

import dev.lynith.core.Platform
import dev.lynith.core.config.Category
import dev.lynith.core.config.ConfigOption
import dev.lynith.core.ui.callbacks.impl.ThemeChanged
import dev.lynith.core.ui.theme.base.ThemeBase
import dev.lynith.core.ui.theme.dark.ThemeDark
import dev.lynith.core.utils.ScheduleUtils

class ThemeManager {
    private val themes: HashSet<AbstractTheme> = hashSetOf(
        ThemeBase(),
        ThemeDark()
    )

    @ConfigOption(
        name_display = "Current Theme",
        description = "The current theme to use",
        category = Category.Rendering
    )
    private var currentThemeName: String = "Default"
        set(value) {
            field = value
            Platform.config.save(this)
            Platform.componentEventBus.emit(ThemeChanged())
        }

    val currentTheme: AbstractTheme
        get() {
            return getTheme(currentThemeName) ?: themes.elementAt(0)
        }

    init {
        Platform.config.register(this)
    }

    fun nextTheme() {
        val index = themes.indexOf(currentTheme)
        if (index == -1) {
            return
        }

        val nextIndex = if (index + 1 >= themes.size) 0 else index + 1
        setCurrentTheme(nextIndex)
    }

    fun setCurrentTheme(index: Int): Boolean {
        if (index < 0 || index >= themes.size) {
            return false
        }

        currentThemeName = themes.elementAt(index).name
        return true
    }

    fun setCurrentTheme(theme: Class<out AbstractTheme?>): Boolean {
        val index = getThemeIndex(theme)
        if (index == -1) {
            return false
        }

        return setCurrentTheme(index)
    }

    private fun getThemeIndex(clazz: Class<out AbstractTheme?>): Int {
        for (i in themes.indices) {
            if (themes.elementAt(i).javaClass == clazz) {
                return i
            }
        }
        return -1
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
