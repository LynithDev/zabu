package dev.lynith.core.ui.screen;

import dev.lynith.core.ui.ComponentWithChildren;
import dev.lynith.core.ui.styles.AbstractComponentStyles;
import lombok.Getter;

public abstract class Screen<C extends Screen<C, S>, S extends AbstractComponentStyles<C, S>> extends ComponentWithChildren<C, S> {

    @Getter
    private final String name;

    public Screen(String name) {
        this.name = name;
    }

    public Screen() {
        this("Unknown");
    }

}
