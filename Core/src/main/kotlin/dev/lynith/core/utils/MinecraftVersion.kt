package dev.lynith.core.utils

enum class MinecraftVersion(private val version: String) {
    V1_8_9("1.8.9"),
    V1_12_2("1.12.2"),
    V1_16_5("1.16.5"),
    V1_18_2("1.18.2"),
    V1_19_4("1.19.4"),
    V1_20_2("1.20.2");

    val major: Int
        get() = version.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].toInt()

    val minor: Int
        get() = version.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toInt()

    val patch: Int
        get() = version.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[2].toInt()

    companion object {
        fun fromString(version: String): MinecraftVersion? {
            for (value in entries) {
                if (value.version == version) {
                    return value
                }
            }
            return null
        }

        fun fromInt(minor: Int, patch: Int): MinecraftVersion? {
            for (value in entries) {
                if (value.version.endsWith("$minor.$patch")) {
                    return value
                }
            }
            return null
        }

        fun isNewer(v1: MinecraftVersion?, v2: MinecraftVersion?): Boolean {
            return if (v1 == null || v2 == null) {
                false
            } else v1.major > v2.major && v1.minor > v2.minor && v1.patch > v2.patch
        }

        fun isOlder(v1: MinecraftVersion?, v2: MinecraftVersion?): Boolean {
            return if (v1 == null || v2 == null) {
                false
            } else v1.major < v2.major && v1.minor < v2.minor && v1.patch < v2.patch
        }
    }
}
