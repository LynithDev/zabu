package dev.lynith.core.ui.theme.dark;

import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.theme.base.ButtonStyles;
import dev.lynith.core.ui.styles.impl.Padding;
import lombok.Getter;
import lombok.Setter;

class DarkButtonStyles extends ButtonStyles {

    public DarkButtonStyles(Button component) {
        super(component);
    }

    @Override
    public Padding getPadding() {
        return new Padding(10, 10, 10, 10);
    }

}
