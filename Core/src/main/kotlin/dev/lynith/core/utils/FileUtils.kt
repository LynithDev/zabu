package dev.lynith.core.utils

import dev.lynith.core.Platform
import java.io.File

object FileUtils {
    val GAME_DIR: File
        get() {
            val path = System.getProperty("zabu.gameDir", Platform.bridge.minecraft.gameDirectory)
            val file = File(path)

            if (!file.exists()) {
                file.mkdirs()
            }
            return file
        }

    val ZABU_FOLDER: File
        get() {
            val file = File(GAME_DIR, "zabu")
            if (!file.exists()) {
                file.mkdirs()
            }
            return file
        }

    val CONFIG_FOLDER: File
        get() {
            val file = File(ZABU_FOLDER, "config")
            if (!file.exists()) {
                file.mkdirs()
            }
            return file
        }
}
