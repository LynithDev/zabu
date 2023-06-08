package dev.lynith.core.modules.impl;

import dev.lynith.core.modules.Module;
import dev.lynith.core.utils.KeyboardHelper;

public class ConsoleSpammerModule extends Module {

    public ConsoleSpammerModule() {
        super("ConsoleSpammer", "Test Module", KeyboardHelper.KEY_C);
    }

    @Override
    public void onEnable() {
        System.out.println("Enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("Disabled");
    }

}
