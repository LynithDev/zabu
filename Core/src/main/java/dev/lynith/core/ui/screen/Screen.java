package dev.lynith.core.ui.screen;

import dev.lynith.core.ui.ComponentWithChildren;
import dev.lynith.core.ui.styles.ComponentStyles;
import lombok.Getter;
import lombok.Setter;

public abstract class Screen<C extends Screen<C, S>, S extends ComponentStyles<C, S>> extends ComponentWithChildren<C, S> {

    @Getter
    private final String name;

    public Screen(String name) {
        this.name = name;
    }

    public Screen() {
        this("Unknown");
    }

}
