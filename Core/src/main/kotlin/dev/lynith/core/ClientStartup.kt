package dev.lynith.core

import dev.lynith.core.bridge.IVersion
import dev.lynith.core.bridge.IVersionMain
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.events.Event
import dev.lynith.core.events.EventBus
import dev.lynith.core.events.impl.MinecraftScreenChangedEvent
import dev.lynith.core.events.impl.ShutdownEvent
import dev.lynith.core.ui.screens.MainMenu
import dev.lynith.core.ui.theme.ThemeManager
import dev.lynith.core.utils.nvg.FontHelper
import dev.lynith.core.utils.nvg.NanoVGManager
import kotlin.system.exitProcess

class ClientStartup {

    lateinit var version: IVersion
    lateinit var eventBus: EventBus<Event>
    lateinit var themeManager: ThemeManager

    var nvgContext: Long = 0

    fun launchClient(ver: IVersion) {
        version = ver

        eventBus = EventBus()
        logger.log("Initialized EventBus")

        nvgContext = NanoVGManager.createContext()
        FontHelper.init()
        logger.log("Initialized NanoVG")

        themeManager = ThemeManager()
        logger.log("Initialized ThemeManager")

        Runtime.getRuntime().addShutdownHook(Thread {
            logger.log("Preparing for shut down...")
            eventBus.emit(ShutdownEvent())
        })

        eventBus.on<MinecraftScreenChangedEvent> {
            if (version.getRenderer().getCurrentScreen() == IRenderer.GuiType.MAIN_MENU) {
                version.getRenderer().displayScreen(MainMenu())
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
                val versionClass = versionMainInstance.getVersion()
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

            logger.log("Launching version " + version.getMinecraft().getGameVersion())
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
