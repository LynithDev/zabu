package dev.lynith.core.modules;

import dev.lynith.core.Logger;
import dev.lynith.core.events.Subscribe;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.input.Key;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private static ModuleManager instance;
    public static ModuleManager getInstance() {
        if (instance == null) instance = new ModuleManager();
        return instance;
    }

    private final Logger logger = new Logger("ModuleManager");

    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {}

    public void init() {

        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onEnable();
            }
        }

        logger.log("Loaded module manager");
    }

    public void enableByKeyBind(Key key) {
        for (Module module : modules) {
            if (module.getSettings().getKey().getCode() == key.getCode()) {
                module.setEnabled(true);
            }
        }
    }

    public void disableByKeyBind(Key key) {
        for (Module module : modules) {
            if (module.getSettings().getKey().getCode() == key.getCode()) {
                module.setEnabled(false);
            }
        }
    }

    public void toggleByKeyBind(Key key) {
        for (Module module : modules) {
            if (module.getSettings().getKey().getCode() == key.getCode()) {
                module.setEnabled(!module.isEnabled());
            }
        }
    }

    @Subscribe
    public void onKeyPress(KeyPressEvent event) {
        toggleByKeyBind(event.getKey());
    }

    /**
     * Registers a module
     * @param module The instance of a module to register
     * @apiNote The module won't be added if it's already registered
     */
    public void register(Module module) {
        if (!module.getSettings().getIsAllowed().get()) return;

        if (modules.contains(module)) return;
        this.modules.add(module);
    }

    /**
     * Gets a module by name
     * @param name The name of the module
     * @return The module, or null if it doesn't exist
     */
    public Module getModuleByName(String name) {
        return this.modules.stream().filter(module -> module.getSettings().getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Gets a module by class
     * @param clazz The class of the module
     * @return The module, or null if it doesn't exist
     */
    public <M extends Module> M getModuleByClass(Class<M> clazz) {
        return (M) this.modules.stream().filter(module -> module.getClass().equals(clazz)).findFirst().orElse(null);
    }

}
