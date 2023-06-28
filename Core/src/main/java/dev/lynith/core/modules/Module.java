package dev.lynith.core.modules;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Logger;
import dev.lynith.core.input.Key;
import dev.lynith.core.versions.IVersion;
import lombok.Getter;
import lombok.Setter;

public abstract class Module {

    @Getter
    private ModuleSettings settings;

    @Getter @Setter
    private boolean enabled;

    protected final IVersion bridge;
    protected final Logger logger;

    public Module() {
        this.bridge = ClientStartup.getInstance().getBridge();
        this.logger = new Logger(this.getClass().getSimpleName());
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
