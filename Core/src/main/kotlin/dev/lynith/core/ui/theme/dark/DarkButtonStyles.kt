package dev.lynith.core.ui.theme.dark

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.styles.impl.Padding
import dev.lynith.core.ui.theme.base.ButtonStyles

class DarkButtonStyles(component: Button) : ButtonStyles(component) {
    override var padding = Padding(10, 10, 10, 10)
}
