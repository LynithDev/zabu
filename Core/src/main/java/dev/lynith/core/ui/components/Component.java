package dev.lynith.core.ui.components;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.ui.BoundingBox;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.ui.theme.ThemeManager;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Component<S extends ComponentStyles<?, ?>> {

    @Setter
    private BoundingBox bounds;

    private final EventBus<ComponentCallback> eventBus = new EventBus<>();

    public abstract S getStyles();
    public abstract void render(IRenderer ctx, int mouseX, int mouseY, float delta);
    public abstract void init();

    public <T extends ComponentCallback> void on(Class<T> event, T callback) {
        eventBus.on(event, callback);
    }

    public <T extends ComponentCallback> void once(Class<T> event, T callback) {
        eventBus.once(event, callback);
    }

    public <T extends ComponentCallback> void emit(Class<T> callbackClass, Object... args) {
        eventBus.emit(callbackClass, args);
    }
}