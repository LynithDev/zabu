package dev.lynith.core.ui.nvg

import org.lwjgl.nanovg.NanoVG
import org.lwjgl.nanovg.NanoVGGL2
import org.lwjgl.nanovg.NanoVGGL3

object NanoVGManager {
    fun createContext(): Long {
        try {
            val nvg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS or NanoVGGL3.NVG_STENCIL_STROKES)
            if (nvg == 0L) throw RuntimeException("Could not initialize NanoVG")

            NanoVG.nvgShapeAntiAlias(nvg, true)

            return nvg
        } catch (e: Exception) {
            e.printStackTrace()
            System.exit(1)
        }

        // Does this ever get reached?
        return -1
    }
}
