package dev.lynith.start;

import dev.lynith.core.bridge.IVersion;
import dev.lynith.core.bridge.IVersionMain;
import dev.lynith.oneeightnine.Version;
import net.minecraft.client.main.Main;

public class VersionMain implements IVersionMain {

    @Override
    public Class<? extends IVersion> getVersion() {
        return Version.class;
    }

    @Override
    public String getVersionString() {
        return "1.8.9";
    }
}
