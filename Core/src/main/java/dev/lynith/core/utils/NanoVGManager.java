package dev.lynith.core.utils;

import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;

public class NanoVGManager {

    public static long createContext() {
        try {
            final long nvg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES);

            if (nvg == 0)
                throw new RuntimeException("Could not initialize NanoVG");

            NanoVG.nvgShapeAntiAlias(nvg, true);

            return nvg;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Does this ever get reached?
        return -1;
    }

}
