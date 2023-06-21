package dev.lynith.core.modules;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Logger;
import dev.lynith.core.input.Key;
import dev.lynith.core.versions.IVersion;
import lombok.Getter;
import lombok.Setter;

public abstract class Module {

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final Key key;

    @Getter
    private boolean enabled;

    @Getter @Setter
    private boolean showsInMenu;

    protected final IVersion bridge;
    protected final Logger logger;

    public Module(String name, String description, Key key, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
        this.description = description;
        this.showsInMenu = true;
        this.key = key;

        this.bridge = ClientStartup.getInstance().getBridge();
        this.logger = new Logger(name);
    }

    public Module(String name, String description, Key key) {
        this(name, description, key, false);
    }

    public Module(String name, String description) {
        this(name, description, null);
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }

    public void toggle() {
        if (this.enabled) {
            disable();
        } else {
            enable();
        }
    }

}
