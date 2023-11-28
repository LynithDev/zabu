package dev.lynith.core.ui.theme.dark;

import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.theme.base.ButtonStyles;
import dev.lynith.core.ui.theme.AbstractTheme;

public class ThemeDark extends AbstractTheme {

    public ThemeDark() {
        super("Default Dark", ThemeType.DARK);
    }

    @Override
    public ButtonStyles getButtonStyles(Button button) {
        return new DarkButtonStyles(button);
    }

}
