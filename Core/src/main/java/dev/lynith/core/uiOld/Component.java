package dev.lynith.core.uiOld;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Logger;
import dev.lynith.core.utils.Tuple;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dev.lynith.core.utils.ZabuColor;

/**
 * The base class for everything in the UI. This is where you should start when creating a new component.
 * @apiNote If making an abstract component, you should override the logger.
 */
public abstract class Component {

    protected IVersion bridge;
    protected Logger logger;

    /**
     * Whether or not the mouse is inside the component. Used for enter and exit events.
     * @apiNote Can be used inside the render method if needed though strongly discouraged.
     */
    @Getter @Setter
    protected boolean mouseInside = false;

    @Getter @Setter
    private boolean draggable = false;

    @Getter @Setter
    protected Component parent;

    @Getter
    protected int width, height;

    @Getter @Setter
    protected int x, y;

    /**
     * Whether or not the component should fill the width or height of it's parent component.
     * Used for things like a flex layout.
     */
    @Getter
    protected boolean fillWidth, fillHeight;

    /**
     * The offset of the mouse to the declared axis of the component. Used for dragging
     */
    private int offX, offY;

    public Component() {
        this.bridge = ClientStartup.getInstance().getBridge();
        this.logger = new Logger(getClass().getSimpleName() + " Component");

        addCallback(PressCallback.class, (mouseX, mouseY) -> {
            if (draggable) {
                this.offX = mouseX - getX();
                this.offY = mouseY - getY();
            }
        });

        addCallback(DragCallback.class, (mouseX, mouseY) -> {
            if (draggable) {
                setX(mouseX - offX);
                setY(mouseY - offY);
            }
        });
    }

    public void setHeight(int height) {
        this.height = Math.abs(height);
    }

    public void setWidth(int width) {
        this.width = Math.abs(width);
    }

    public void setBounds(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setBounds(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     * Mostly internal use. Used to set the width of the parent component.
     * @param fillWidth whether to fill the width with the parent component
     */
    public void setFillWidth(boolean fillWidth) {
        this.fillWidth = fillWidth;
        if (fillWidth && parent != null) {
            this.width = parent.getWidth();
        }
    }

    /**
     * Mostly internal use. Used to set the height of the parent component.
     * @param fillHeight whether to fill the height with the parent component
     */
    public void setFillHeight(boolean fillHeight) {
        this.fillHeight = fillHeight;
        if (fillHeight && parent != null) {
            this.height = parent.getHeight();
        }
    }

    /**
     * Component rendering code. This is where you should render your component.
     */
    public void render(IRenderer renderer) {}

    /**
     * Called when the component is added to a screen NOT when it is created.
     */
    public void init() {}

    /**
     * Called when the component needs to be updated, such as its size or position changing.
     * Identical to init() except it is used for when the component has already been initialized.
     */
    public void update() {}

    public ZabuColor from(int color) {
        return new ZabuColor(color);
    }

    public ZabuColor from(int r, int g, int b) {
        return new ZabuColor(r, g, b);
    }

    public ZabuColor from(int r, int g, int b, int a) {
        return new ZabuColor(r, g, b, a);
    }

    // Callbacks
    private final List<Tuple<Class<? extends CallbackInterface>, CallbackInterface>> callbacks = new ArrayList<>();

    protected interface CallbackInterface {}

    public <T extends CallbackInterface> void addCallback(Class<T> callback, T callbackInstance) {
        callbacks.add(new Tuple<>(callback, callbackInstance));
    }

    public <T extends CallbackInterface> void callCallbacks(Class<T> callback, Object... args) {
        for (Tuple<Class<? extends CallbackInterface>, CallbackInterface> entry : callbacks) {
            Class<? extends CallbackInterface> callbackClass = entry.getKey();
            CallbackInterface callbackInstance = entry.getValue();
            if (callbackClass.equals(callback)) {
                try {
                    Method[] methods = callbackClass.getMethods();
                    inner:
                    for (Method method : methods) {
                        if (method.getName().equals("handle")) {
                            List<Object> params = new ArrayList<>();
                            for (Class<?> param : method.getParameterTypes()) {
                                if (!param.isPrimitive()) {
                                    params.add(param.cast(args[params.size()]));
                                } else {
                                    params.add(args[params.size()]);
                                }
                            }
                            method.invoke(callbackInstance, params.toArray());
                            break inner;
                        }
                    }
                } catch (Exception e) {
                    logger.error("Failed to call callback " + callbackClass.getSimpleName() + " with args " + args);
                    e.printStackTrace();
                }
            }
        }
    }

    // # ------------------- #
    // # Interface callbacks #
    // # ------------------- #

    @FunctionalInterface
    private interface EmptyInterface extends CallbackInterface {
        void handle();
    }

    @FunctionalInterface
    private interface MouseCallback extends CallbackInterface {
        void handle(int mouseX, int mouseY);
    }

    @FunctionalInterface
    private interface KeyCallback extends CallbackInterface {
        void handle(char typedChar, int keyCode);
    }

    /**
     * Called when the component is removed / destroyed. This is where you should clean up any resources.
     */
    public interface DestroyCallback extends EmptyInterface {}

    /**
     * Called when the mouse intersects the component.
     */
    public interface EnterCallback extends MouseCallback {}

    /**
     * Called when the mouse leaves the component.
     */
    public interface LeaveCallback extends MouseCallback {}

    /**
     * Called when the mouse is released inside the component.
     */
    public interface ReleaseCallback extends MouseCallback {}

    /**
     * Called when the mouse is pressed inside the component.
     */
    public interface PressCallback extends MouseCallback {}

    /**
     * Called when the mouse is clicked inside the component.
     */
    public interface ClickCallback extends MouseCallback {}

    /**
     * Called when the mouse is dragged inside the component.
     */
    public interface DragCallback extends MouseCallback {}

    /**
     * Called when a key is typed while the component is focused.
     */
    public interface KeyTypedCallback extends KeyCallback {}

    // # --------------- #
    // # Utility methods #
    // # --------------- #

    public static boolean isIntersecting(Component component, Component target) {
        return component.getX() >= target.getX() &&                          // Left border
                component.getX() <= target.getX() + target.getWidth() &&     // Right border
                component.getY() >= target.getY() &&                         // Top border
                component.getY() <= target.getY() + target.getHeight();      // Bottom
    }

    public static boolean isIntersecting(Component component, int pointX, int pointY) {
        return pointX >= component.getX() &&                          // Left border
                pointX <= component.getX() + component.getWidth() &&  // Right border
                pointY >= component.getY() &&                         // Top border
                pointY <= component.getY() + component.getHeight();   // Bottom
    }

}
