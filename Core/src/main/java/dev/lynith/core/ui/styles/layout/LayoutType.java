package dev.lynith.core.ui.styles.layout;

import dev.lynith.core.ui.styles.layout.impl.AbsoluteLayout;
import dev.lynith.core.ui.styles.layout.impl.ConstraintLayout;
import lombok.Getter;

public enum LayoutType {

    CONSTRAINT(ConstraintLayout.class),
    ABSOLUTE(AbsoluteLayout.class);

    @Getter
    private final AbstractLayout instance;
    LayoutType(Class<? extends AbstractLayout> clazz) {
        try {
            this.instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
