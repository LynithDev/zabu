package dev.lynith.core.ui.components;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.BoundingBox;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.ui.theme.ThemeManager;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Component<S extends ComponentStyles<?, ?>> {

    @Setter
    private BoundingBox bounds;

    public String getComponentId() {
        return getClass().getSimpleName();
    }

    public abstract S getStyles();
    public abstract void render();
    public abstract void init();



}
