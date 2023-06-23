package dev.lynith.core.ui;

import dev.lynith.core.Logger;
import dev.lynith.core.ui.callbacks.ComponentCallbacks;
import dev.lynith.core.ui.callbacks.impl.MouseClick;
import dev.lynith.core.ui.callbacks.impl.MouseEnter;
import dev.lynith.core.ui.callbacks.impl.MouseLeave;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.utils.MathHelper;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class Component<C extends Component<C, S>, S extends ComponentStyles<C, S>> {

    @Accessors(fluent = true)
    @Getter @Setter
    private int x, y, width, height;
    private final ComponentCallbacks callbacks;

    private S activeStyle;

    @Getter
    private S styles, hoverStyles, clickStyles;

    protected void setStyles(S styles, S hoverStyles, S clickStyles) {
        this.styles = styles;
        this.hoverStyles = hoverStyles;
        this.clickStyles = clickStyles;
    }

    protected void setStyles(S styles) {
        setStyles(styles, styles, styles);
    }

    protected void setActiveStyle(S activeStyle) {
        this.activeStyle = activeStyle;
    }

    protected final Logger logger = new Logger(this.getClass().getSimpleName());

    public Component() {
        this.callbacks = new ComponentCallbacks(this);
        this.activeStyle = styles;
    }

    public void render(IRenderer ctx) {}
    public void update() {}
    public void init() {
        listener(MouseEnter.class, (mouseX, mouseY) -> {
            setActiveStyle(hoverStyles);
        });

        listener(MouseClick.class, (mouseX, mouseY) -> {
            setActiveStyle(clickStyles);
        });

        listener(MouseLeave.class, (mouseX, mouseY) -> {
            setActiveStyle(styles);
        });
    }

    public C style(Styleable<C, S> styleable) {
        styleable.applyStyle(styles);
        return styles.getComponent();
    }

    public C styleHover(Styleable<C, S> styleable) {
        styleable.applyStyle(hoverStyles);
        return hoverStyles.getComponent();
    }

    public C styleClick(Styleable<C, S> styleable) {
        styleable.applyStyle(clickStyles);
        return clickStyles.getComponent();
    }

    public interface Styleable<T extends Component<T, S>, S extends ComponentStyles<T, S>> {
        void applyStyle(S styles);
    }

    public <CB extends ComponentCallbacks.CallbackInterface> C listener(Class<CB> callback, CB callbackInstance) {
        callbacks.addCallback(callback, callbackInstance);
        return (C) this;
    }

    public <CB extends ComponentCallbacks.CallbackInterface> C listener(CB callbackInstance) {
        callbacks.addCallback(callbackInstance);
        return (C) this;
    }

    public void callCallbacks(Class<? extends ComponentCallbacks.CallbackInterface> callback, Object... args) {
        callbacks.callCallbacks(callback, args);
    }

    // --- Helpers ---
    public static boolean intersecting(Component<?, ?> component, int x, int y) {
        return MathHelper.intersecting(component.x, component.y, component.x + component.width, component.y + component.height, x, y);
    }

}
