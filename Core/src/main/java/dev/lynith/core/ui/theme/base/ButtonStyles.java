package dev.lynith.core.ui.theme.base;

import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.styles.impl.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ButtonStyles extends ComponentStyles<Button, ButtonStyles> {

    public ButtonStyles(Button component) {
        super(component);
    }

    private Padding padding = new Padding(5, 5, 10, 10);

    private Color backgroundColor = new Color(59, 66, 82);
    private Color foregroundColor = new Color(255);
    private Border border = new Border(2, new Color(67, 76, 94));
    private CornerRadius cornerRadius = new CornerRadius(5);
    private FontStyles fontStyles = new FontStyles("Roboto-Regular", 16);

}
