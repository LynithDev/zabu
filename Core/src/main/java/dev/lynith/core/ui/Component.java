package dev.lynith.core.ui;

import dev.lynith.core.Logger;
import dev.lynith.core.ui.callbacks.ComponentCallbacks;
import dev.lynith.core.ui.callbacks.impl.MouseClick;
import dev.lynith.core.ui.callbacks.impl.MouseEnter;
import dev.lynith.core.ui.callbacks.impl.MouseLeave;
import dev.lynith.core.ui.styles.AbstractComponentStyles;
import dev.lynith.core.utils.MathHelper;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class Component<C extends Component<C, S>, S extends AbstractComponentStyles<C, S>> {

    @Accessors(fluent = true)
    @Getter @Setter
    private int x, y, width, height;
    private final ComponentCallbacks callbacks;

    private S activeStyle;

    public S getStyle() {
        return activeStyle;
    }

    private S styles, hoverStyles, clickStyles;

    /**
     * Sets the styles for the component. Used for custom component styles.
     * @param styles The default styles
     * @param hoverStyles The styles to apply when the mouse is hovering over the component
     * @param clickStyles The styles to apply when the mouse is clicking the component
     */
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

    /**
     * Checks if the given coordinates are within the bounds of the component
     * @param ctx The renderer to use for calculations
     */
    public void render(IRenderer ctx) {}

    /**
     * Used to update a component. Should be used instead of link() for updating the component
     */
    public void update() {}

    /**
     * Used for initializing the component before it is shown
     */
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

    /**
     * Styles the component
     * @param styleable The style to apply
     * @return this
     */
    public C style(Styleable<C, S> styleable) {
        styleable.applyStyle(styles);
        return styles.getComponent();
    }

    /**
     * Applies the given style to the hover style
     * @param styleable The style to apply
     * @return this
     */
    public C styleHover(Styleable<C, S> styleable) {
        styleable.applyStyle(hoverStyles);
        return hoverStyles.getComponent();
    }

    /**
     * Applies the given style to the click style
     * @param styleable The style to apply
     * @return this
     */
    public C styleClick(Styleable<C, S> styleable) {
        styleable.applyStyle(clickStyles);
        return clickStyles.getComponent();
    }

    public interface Styleable<T extends Component<T, S>, S extends AbstractComponentStyles<T, S>> {
        void applyStyle(S styles);
    }


    /**
     * Adds a callback to the component
     * @param callback The callback to add
     * @param callbackInstance The instance of the callback
     * @return this
     */
    public <CB extends ComponentCallbacks.CallbackInterface> C listener(Class<CB> callback, CB callbackInstance) {
        callbacks.addCallback(callback, callbackInstance);
        return (C) this;
    }

    /**
     * Adds a callback to the component
     * @param callbackInstance The instance of the callback, must be cast to the correct type
     * @return this
     */
    public <CB extends ComponentCallbacks.CallbackInterface> C listener(CB callbackInstance) {
        callbacks.addCallback(callbackInstance);
        return (C) this;
    }

    /**
     * Calls all callbacks of the given type
     * @param callback The callback type to call
     * @param args The arguments to pass to the callback
     */
    public void callCallbacks(Class<? extends ComponentCallbacks.CallbackInterface> callback, Object... args) {
        callbacks.callCallbacks(callback, args);
    }

    // --- Helpers ---

    /**
     * Checks if the given coordinates are within the bounds of the component
     * @param component The component to check
     * @param x The x coordinate to check
     * @param y The y coordinate to check
     * @return <code>true</code> if the coordinates are within the bounds of the component
     */
    public static boolean intersecting(Component<?, ?> component, int x, int y) {
        return MathHelper.intersecting(component.x, component.y, component.x + component.width, component.y + component.height, x, y);
    }

}
