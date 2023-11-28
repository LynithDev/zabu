package dev.lynith.core.ui.theme.base;

import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.styles.impl.Padding;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ButtonStyles extends ComponentStyles<Button, ButtonStyles> {

    public ButtonStyles(Button component) {
        super(component);
    }

    private Padding padding = new Padding(5, 5, 10, 10);

}
