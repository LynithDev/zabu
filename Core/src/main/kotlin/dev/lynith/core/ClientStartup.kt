package dev.lynith.core

import dev.lynith.core.bridge.IVersion
import dev.lynith.core.bridge.IVersionMain
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.config.Config
import dev.lynith.core.events.EventBus
import dev.lynith.core.events.impl.MinecraftScreenChangedEvent
import dev.lynith.core.events.impl.ShutdownEvent
import dev.lynith.core.ui.callbacks.ComponentEventBus
import dev.lynith.core.ui.nvg.NanoVGHelper
import dev.lynith.core.ui.screens.MainMenu
import dev.lynith.core.ui.theme.ThemeManager
import dev.lynith.core.ui.nvg.FontHelper
import dev.lynith.core.utils.FileUtils
import kotlin.system.exitProcess

class ClientStartup {

    fun launchClient(ver: IVersion) {
        Platform.apply {
            renderer = ver.renderer
            minecraft = ver.minecraft
            bridge = ver

            config = Config()

            componentEventBus = ComponentEventBus()
            eventBus = EventBus()
            logger.log("Initialized EventBus")

            clientStartup = this@ClientStartup
            logger.log("Initialized ClientStartup")

            nvg = NanoVGHelper()
            logger.log("Initialized NanoVG")

            fontHelper = FontHelper()
            logger.log("Initialized Fonts")

            themeManager = ThemeManager()
            logger.log("Initialized ThemeManager")

            config.init()
            logger.log("Initialized and loaded Config")

            logger.log("Initialized Platform")
        }

        Runtime.getRuntime().addShutdownHook(Thread {
            logger.log("Preparing for shut down...")
            Platform.eventBus.emit(ShutdownEvent())
        })

        Platform.eventBus.on<MinecraftScreenChangedEvent> {
            if (Platform.renderer.currentScreen == IRenderer.GuiType.MAIN_MENU) {
                Platform.renderer.setScreen(MainMenu())
            }
        }
    }

    companion object {
        private val logger = Logger("main")

        private lateinit var instance_: ClientStartup

        @JvmStatic
        var instance: ClientStartup
            get() {
                if (!::instance_.isInitialized) {
                    launch()
                }

                return instance_
            }
            set(value) = if (!::instance_.isInitialized) {
                instance_ = value
            } else {
                logger.log("ClientStartup instance already initialized. This shouldn't happen.")
            }


        @JvmStatic
        fun launch() {
            val versionMain: Class<*> = try {
                Class.forName("dev.lynith.start.VersionMain")
            } catch (e: Exception) {
                logger.error("Invalid build")
                logger.error("dev.lynith.start.VersionMain was not found!")
                return
            }

            try {
                val versionMainInstance = versionMain.getConstructor().newInstance() as IVersionMain
                val versionClass = versionMainInstance.version
                val version = versionClass.getConstructor().newInstance()
                logger.log("Found bridge")
                launch(version)
            } catch (e: Exception) {
                logger.error("Failed to launch client")
                e.printStackTrace()
            }

        }

        private fun launch(version: IVersion) {
            if (::instance_.isInitialized) {
                logger.log("ClientStartup instance already exists. This shouldn't happen.")
                return
            }

            logger.log("Launching version " + version.minecraft.gameVersion)
            instance_ = ClientStartup()
            instance.launchClient(version)
        }

        @JvmStatic
        fun main(args: Array<String>) {
            println("This shouldn't be run directly.")
            exitProcess(1)
        }
    }
}
