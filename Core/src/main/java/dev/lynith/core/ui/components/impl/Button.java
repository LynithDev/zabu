package dev.lynith.core.ui.components.impl;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.components.Component;
import dev.lynith.core.ui.theme.base.ButtonStyles;

public class Button extends Component<ButtonStyles> {

    @Override
    public void render() {

    }

    @Override
    public void init() {
        System.out.println(getStyles().getPadding());
    }

    @Override
    public ButtonStyles getStyles() {
        return ClientStartup.getInstance().getThemeManager().getCurrentTheme().getButtonStyles(this);
    }

}
