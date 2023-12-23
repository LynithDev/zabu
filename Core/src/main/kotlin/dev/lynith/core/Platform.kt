package dev.lynith.core

import dev.lynith.core.bridge.IMinecraft
import dev.lynith.core.bridge.IVersion
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.events.Event
import dev.lynith.core.events.EventBus
import dev.lynith.core.ui.nvg.NanoVGHelper
import dev.lynith.core.ui.theme.ThemeManager
import dev.lynith.core.ui.nvg.FontHelper
import dev.lynith.core.ui.nvg.NanoVGManager

object Platform {

    @JvmStatic
    val nvgContext: Long = NanoVGManager.createContext()

    @JvmStatic
    lateinit var clientStartup: ClientStartup
        internal set

    @JvmStatic
    lateinit var renderer: IRenderer
        internal set

    @JvmStatic
    lateinit var minecraft: IMinecraft
        internal set

    @JvmStatic
    lateinit var bridge: IVersion
        internal set

    @JvmStatic
    lateinit var eventBus: EventBus<Event>
        internal set

    @JvmStatic
    lateinit var themeManager: ThemeManager
        internal set

    @JvmStatic
    lateinit var nvg: NanoVGHelper
        internal set

    @JvmStatic
    lateinit var fontHelper: FontHelper
        internal set

}