package dev.lynith.start;

import dev.lynith.core.bridge.IVersion;
import dev.lynith.core.bridge.IVersionMain;
import dev.lynith.onetwentytwo.Version;

public class VersionMain implements IVersionMain {

    @Override
    public Class<? extends IVersion> getVersion() {
        return Version.class;
    }

    @Override
    public String getVersionString() {
        return "1.20.2";
    }
}
