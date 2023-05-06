package dev.lynith.Core.ui;

import dev.lynith.Core.ClientStartup;
import dev.lynith.Core.versions.IVersion;
import dev.lynith.Core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

public abstract class Component {

    @Getter @Setter
    protected int x, y, width, height;

    @Getter @Setter
    protected boolean mouseInside = false;

    @Getter @Setter
    protected Component parent;

    protected IVersion bridge;

    public Component() {
        this.bridge = ClientStartup.getInstance().getBridge();
    }

    public abstract void render(IRenderer renderer);

    public abstract void init();

    public void update() {}

    @Setter @Getter
    private ComponentCallback onDestroy = () -> {};

    @Getter @Setter
    private MouseCallback onClick, onEnter, onLeave, onDrag = (mouseX, mouseY) -> {};

    @Getter @Setter
    private KeyTypedCallback onKeyTyped = (typedChar, keyCode) -> {};

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

}
