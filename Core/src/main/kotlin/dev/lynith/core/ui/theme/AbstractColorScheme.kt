package dev.lynith.core.ui.theme

import dev.lynith.core.ui.styles.impl.Color

abstract class AbstractColorScheme {

    abstract val accent: Color
    abstract val accentSecondary: Color
    abstract val accentTertiary: Color
    abstract val accentDisabled: Color
    abstract val accentHover: Color

    abstract val background: Color
    abstract val backgroundSecondary: Color
    abstract val backgroundTertiary: Color
    abstract val backgroundDisabled: Color
    abstract val backgroundHover: Color

    abstract val border: Color
    abstract val borderSecondary: Color

    abstract val foreground: Color
    abstract val foregroundSecondary: Color
    abstract val foregroundTertiary: Color
    abstract val foregroundDisabled: Color
    abstract val foregroundHover: Color

    abstract val danger: Color
    abstract val dangerSecondary: Color

    abstract val warning: Color
    abstract val warningSecondary: Color

    abstract val success: Color
    abstract val successSecondary: Color

    abstract val info: Color
    abstract val infoSecondary: Color

}