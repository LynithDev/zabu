package dev.lynith.core.ui.components.impl;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.ui.components.Component;
import dev.lynith.core.ui.theme.base.ButtonStyles;

public class Button extends Component<ButtonStyles> {

    @Override
    public ButtonStyles getStyles() {
        return ClientStartup.getInstance().getThemeManager().getCurrentTheme().getButtonStyles(this);
    }

    @Override
    public void render(IRenderer ctx, int mouseX, int mouseY, float delta) {
//        ctx.rect(getX(), getY(), getWidth(), getHeight(), getStyles().getBackgroundColor().getRGB());
    }

    @Override
    public void init() {

    }

}
