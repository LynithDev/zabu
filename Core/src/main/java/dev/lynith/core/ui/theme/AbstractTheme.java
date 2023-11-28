package dev.lynith.core.ui.theme;

import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.theme.base.ButtonStyles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public abstract class AbstractTheme {

    private final String name;
    private final ThemeType type;

    public abstract ButtonStyles getButtonStyles(Button button);

    public enum ThemeType {
        DARK,
        LIGHT,
        HIGH_CONTRAST;
    }

}
