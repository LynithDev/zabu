package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.theme.AbstractColorScheme

class DefaultColorScheme : AbstractColorScheme() {

    override val accent: Color = Color(30, 141, 221)
    override val accentSecondary: Color = Color(26, 123, 193)
    override val accentTertiary: Color = Color(22, 106, 166)
    override val accentDisabled: Color = Color(19, 88, 138)
    override val accentHover: Color = Color(33, 154, 242)

    override val background: Color = Color(245, 245, 245)
    override val backgroundSecondary: Color = Color(223, 223, 223)
    override val backgroundTertiary: Color = Color(191, 191, 191)
    override val backgroundDisabled: Color = Color(170, 170, 170)
    override val backgroundHover: Color = Color(240, 240, 240)

    override val border: Color = Color(27, 31, 35, 0.15f)
    override val borderSecondary: Color = Color(10, 10, 10)

    override val foreground: Color = Color(0, 0, 0)
    override val foregroundSecondary: Color = Color(10, 10, 10)
    override val foregroundTertiary: Color = Color(20, 20, 20)
    override val foregroundDisabled: Color = Color(50, 50, 50)
    override val foregroundHover: Color = Color(30, 30, 30)

    override val danger: Color = Color(239, 86, 86)
    override val dangerSecondary: Color = Color(209, 75, 75)

    override val warning: Color = Color(248, 248, 101)
    override val warningSecondary: Color = Color(218, 218, 89)

    override val success: Color = Color(84, 238, 106)
    override val successSecondary: Color = Color(74, 208, 94)

    override val info: Color = Color(84, 178, 238)
    override val infoSecondary: Color = Color(74, 158, 208)

}