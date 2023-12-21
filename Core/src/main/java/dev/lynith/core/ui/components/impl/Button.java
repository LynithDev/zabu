package dev.lynith.core.ui.components.impl;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.ui.components.Component;
import dev.lynith.core.ui.components.callbacks.ComponentMouseClicked;
import dev.lynith.core.ui.styles.impl.Color;
import dev.lynith.core.ui.theme.base.ButtonStyles;
import dev.lynith.core.utils.nvg.FontHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter @Getter @Accessors(chain = true)
public class Button extends Component<Button, ButtonStyles> {

    private String text = "";

    @Override
    public void render(IRenderer renderer, int mouseX, int mouseY, float delta) {
        rectangle(
            getBounds(),
            getStyles().getBackgroundColor(),
            getStyles().getBorder(),
            getStyles().getCornerRadius()
        );

        text(
            text,
            getBounds().withX(getX() + textWidthInt(text, getStyles().getFontStyles()) / 2),
            getStyles().getFontStyles(),
            getStyles().getForegroundColor()
        );
    }

    @Override
    public void init() {

    }

    @Override
    public ButtonStyles getStyles() {
        return ClientStartup.getInstance().getThemeManager().getCurrentTheme().getButtonStyles(this);
    }

}
