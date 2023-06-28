package dev.lynith.core.utils;

import lombok.Getter;

/**
 * Enum representing Minecraft versions. The newer the version, the higher the ordinal.
 */
public enum MinecraftVersion {
    UNKNOWN(""),
    V1_8_9("1.8.9"),
    V1_9_4("1.9.4"),
    V1_10_2("1.10.2"),
    V1_11_2("1.11.2"),
    V1_12_2("1.12.2"),
    V1_13_2("1.13.2"),
    V1_14_4("1.14.4"),
    V1_15_2("1.15.2"),
    V1_16_5("1.16.5"),
    V1_17_1("1.17.1"),
    V1_18_1("1.18.2"),
    V1_19_4("1.19.4"),
    V1_20_2("1.20.1");

    @Getter
    private final String version;
    MinecraftVersion(String version) {
        this.version = version;
    }
}
