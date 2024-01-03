package dev.lynith.core

import dev.lynith.core.bridge.IMinecraft
import dev.lynith.core.bridge.IVersion
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.config.Config
import dev.lynith.core.events.Event
import dev.lynith.core.events.EventBus
import dev.lynith.core.hud.HudManager
import dev.lynith.core.ui.callbacks.ComponentEventBus
import dev.lynith.core.ui.nvg.NanoVGHelper
import dev.lynith.core.ui.theme.ThemeManager
import dev.lynith.core.ui.nvg.FontHelper
import dev.lynith.core.ui.nvg.NanoVGManager

object Platform {

    val nvgContext: Long = NanoVGManager.createContext()

    lateinit var config: Config
        internal set

    lateinit var clientStartup: ClientStartup
        internal set

    lateinit var renderer: IRenderer
        internal set

    lateinit var minecraft: IMinecraft
        internal set

    lateinit var bridge: IVersion
        internal set

    lateinit var eventBus: EventBus<Event>
        internal set

    lateinit var componentEventBus: ComponentEventBus
        internal set

    lateinit var themeManager: ThemeManager
        internal set

    lateinit var nvg: NanoVGHelper
        internal set

    lateinit var fontHelper: FontHelper
        internal set

    lateinit var hudManager: HudManager
        internal set

}