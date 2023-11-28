package dev.lynith.core.ui.theme.base;

import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.theme.AbstractTheme;

public class ThemeBase extends AbstractTheme {

    public ThemeBase() {
        super("Default", ThemeType.LIGHT);
    }

    @Override
    public ButtonStyles getButtonStyles(Button button) {
        return new ButtonStyles(button);
    }

}
