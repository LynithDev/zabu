package dev.lynith.start;

import dev.lynith.core.bridge.IVersion;
import dev.lynith.core.bridge.IVersionMain;
import dev.lynith.oneeightnine.Version;
import org.jetbrains.annotations.NotNull;

public class VersionMain implements IVersionMain {

    @Override
    public @NotNull Class<? extends IVersion> getVersion() {
        return Version.class;
    }

    @Override
    public @NotNull String getVersionString() {
        return "1.8.9";
    }
}
