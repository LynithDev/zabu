package dev.lynith.core.ui.components.impl;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.ui.components.Component;
import dev.lynith.core.ui.components.callbacks.ComponentMouseClicked;
import dev.lynith.core.ui.theme.base.ButtonStyles;

public class Button extends Component<ButtonStyles> {

    @Override
    public void render(IRenderer renderer, int mouseX, int mouseY, float delta) {

    }

    @Override
    public void init() {
        on(ComponentMouseClicked.class, (mouseX, mouseY, button) -> {

        });
    }

    @Override
    public ButtonStyles getStyles() {
        return ClientStartup.getInstance().getThemeManager().getCurrentTheme().getButtonStyles(this);
    }

}
