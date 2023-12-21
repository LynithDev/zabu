package dev.lynith.core.ui.components;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.ui.BoundingBox;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.utils.nvg.NanoVGHelper;
import lombok.Getter;

@Getter
public abstract class Component<C extends Component<?,?>, S extends ComponentStyles<?, ?>> extends NanoVGHelper {

    private BoundingBox bounds = new BoundingBox(0, 0, 0, 0);

    private final EventBus<ComponentCallback> eventBus = new EventBus<>();

    public abstract S getStyles();


    // ---- RENDER ----
    protected void preRender(IRenderer ctx, int mouseX, int mouseY, float delta) {
        NanoVGHelper.createFrame();
    }

    protected abstract void render(IRenderer ctx, int mouseX, int mouseY, float delta);

    protected void postRender(IRenderer ctx, int mouseX, int mouseY, float delta) {
        endFrame();
    }

    public void wrappedRender(IRenderer ctx, int mouseX, int mouseY, float delta) {
        preRender(ctx, mouseX, mouseY, delta);
        render(ctx, mouseX, mouseY, delta);
        postRender(ctx, mouseX, mouseY, delta);
    }

    // ---- RENDER ----

    // ---- INIT ----
    protected void preInit() {}
    protected abstract void init();
    protected void postInit() {}

    public void wrappedInit() {
        preInit();
        init();
        postInit();
    }
    // ---- INIT ----

    /**
     * Adds a callback to the components event bus
     * @param event The event to listen for, must be a subclass of ComponentCallback
     * @param callback The callback to run when the event is emitted
     */
    public <T extends ComponentCallback> void on(Class<T> event, T callback) {
        on(event, callback, false);
    }

    /**
     * Adds a callback to the components event bus
     * @param event The event to listen for, must be a subclass of ComponentCallback
     * @param callback The callback to run when the event is emitted
     */
    public <T extends ComponentCallback> void on(Class<T> event, T callback, boolean once) {
        if (once) {
            eventBus.once(event, callback);
        } else {
            eventBus.on(event, callback);
        }
    }

    /**
     * Adds a callback to the components event bus that <strong>will only be called once</strong>
     * @param event The event to listen for, must be a subclass of ComponentCallback
     * @param callback The callback to run when the event is emitted
     */
    public <T extends ComponentCallback> void once(Class<T> event, T callback) {
        on(event, callback, true);
    }
    /**
     * Emit Call all types of a callback registered on the component's event bus
     * @param callbackClass The class of the callback to emit
     * @param args The arguments to pass to the callback, must match the callback's <code>invoke</code> method
     */
    public <T extends ComponentCallback> void emit(Class<T> callbackClass, Object... args) {
        eventBus.emit(callbackClass, args);
    }

    // Helper Utils
    public C setBounds(BoundingBox bounds) {
        this.bounds = bounds;
        return (C) this;
    }

    public C setBounds(int x, int y, int width, int height) {
        setBounds(new BoundingBox(x, y, width, height));
        return (C) this;
    }

    public C setX(int x) {
        getBounds().setX(x);
        return (C) this;
    }

    public C setY(int y) {
        getBounds().setY(y);
        return (C) this;
    }

    public C setWidth(int width) {
        getBounds().setWidth(width);
        return (C) this;
    }

    public C setHeight(int height) {
        getBounds().setHeight(height);
        return (C) this;
    }

    public int getX() {
        return getBounds().getX();
    }

    public int getY() {
        return getBounds().getY();
    }

    public int getWidth() {
        return getBounds().getWidth();
    }

    public int getHeight() {
        return getBounds().getHeight();
    }
}
