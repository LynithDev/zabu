package dev.lynith.core.ui;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Logger;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

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

    @Getter
    protected boolean fillWidth, fillHeight;

    /**
     * The offset of the mouse to the declared axis of the component. Used for dragging
     */
    private int offX, offY;

    public Component() {
        this.bridge = ClientStartup.getInstance().getBridge();
        this.logger = new Logger(getClass().getSimpleName() + " Component");
    }

    public void setHeight(int height) {
        this.height = Math.abs(height);
    }

    public void setWidth(int width) {
        this.width = Math.abs(width);
    }

    public void setFillWidth(boolean fillWidth) {
        this.fillWidth = fillWidth;
        if (fillWidth && parent != null) {
            this.width = parent.getWidth();
        }
    }

    public void setFillHeight(boolean fillHeight) {
        this.fillHeight = fillHeight;
        if (fillHeight && parent != null) {
            this.height = parent.getHeight();
        }
    }

    /**
     * Component rendering code. This is where you should render your component.
     */
    public abstract void render(IRenderer renderer);

    /**
     * Called when the component is added to a screen NOT when it is created.
     */
    public abstract void init();

    /**
     * Called when the component needs to be updated, such as its size or position changing.
     */
    public void update() {}

    /**
     * Called when the component is removed / destroyed. This is where you should clean up any resources.
     */
    @Setter @Getter
    private ComponentCallback onDestroy = () -> {};

    /**
     * Called when the mouse is clicked inside the component.
     */
    @Getter @Setter
    private MouseCallback onClick = (mouseX, mouseY) -> {};

    /**
     * Called when the mouse is clicked inside the component.
     */
    @Getter @Setter
    private MouseCallback onPress = (mouseX, mouseY) -> {
        this.offX = mouseX - getX();
        this.offY = mouseY - getY();
    };

    /**
     * Called when the mouse is clicked inside the component.
     */
    @Getter @Setter
    private MouseCallback onRelease = (mouseX, mouseY) -> {};

    /**
     * Called when the mouse intersects the component. In other words, this is the hover event.
     */
    @Getter @Setter
    private MouseCallback onEnter = (mouseX, mouseY) -> {};

    /**
     * Called when the mouse leaves the component. Also known as the mouse exit event.
     */
    @Getter @Setter
    private MouseCallback onLeave = (mouseX, mouseY) -> {};

    /**
     * Called when the mouse is dragged (click and hold) inside the component.
     * This is not the same as the click event.
     * @apiNote This is not tested properly and may not work as expected.
     */
    @Getter @Setter
    private MouseCallback onDrag = (mouseX, mouseY) -> {
        if (draggable) {
            setX(mouseX - this.offX);
            setY(mouseY - this.offY);
        }
    };

    /**
     * TODO: Only activate when the component is focused.
     * <br><br>
     * Called when a key is typed while the component is focused.
     * @apiNote Not implemented properly yet. Gets called for every key press and not just when the component is focused.
     */
    @Getter @Setter
    private KeyTypedCallback onKeyTyped = (typedChar, keyCode) -> {};

    // # ------------------- #
    // # Interface callbacks #
    // # ------------------- #

    @FunctionalInterface
    public interface ComponentCallback {
        void handle();
    }

    @FunctionalInterface
    public interface MouseCallback {
        void handle(int mouseX, int mouseY);
    }

    @FunctionalInterface
    public interface KeyTypedCallback {
        void handle(char typedChar, int keyCode);
    }

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
