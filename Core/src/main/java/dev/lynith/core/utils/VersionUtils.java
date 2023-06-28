package dev.lynith.core.utils;

import dev.lynith.core.ClientStartup;
import lombok.Getter;

public class VersionUtils {

    private final MinecraftVersion version;

    private VersionUtils(String version) {
        this.version = enumFromString(version);
    }

    // --- Utils ---
    public boolean isNewerThan(MinecraftVersion version) {
        return this.version.ordinal() > version.ordinal();
    }

    public boolean isOlderThan(MinecraftVersion version) {
        return this.version.ordinal() < version.ordinal();
    }

    public boolean isNewerOrEqualThan(MinecraftVersion version) {
        return this.version.ordinal() >= version.ordinal();
    }

    public boolean isOlderOrEqualThan(MinecraftVersion version) {
        return this.version.ordinal() <= version.ordinal();
    }

    public boolean is(MinecraftVersion version) {
        return this.version.ordinal() == version.ordinal();
    }

    public boolean isBetweenOrEqual(MinecraftVersion min, MinecraftVersion max) {
        return this.version.ordinal() >= min.ordinal() && this.version.ordinal() <= max.ordinal();
    }

    public boolean isBetween(MinecraftVersion min, MinecraftVersion max) {
        return this.version.ordinal() > min.ordinal() && this.version.ordinal() < max.ordinal();
    }


    // --- Factory methods ---
    public static VersionUtils from(String version) {
        return new VersionUtils(version);
    }

    public static VersionUtils getCurrent() {
        return new VersionUtils(ClientStartup.getInstance().getBridge().getVersion());
    }

    // --- Helpers ---

    private static MinecraftVersion enumFromString(String version) {
        MinecraftVersion ret = MinecraftVersion.UNKNOWN;

        try {
            ret = MinecraftVersion.valueOf("V" + version.replace(".", "_"));
        } catch (IllegalArgumentException ignored) {}

        return ret;
    }

}
