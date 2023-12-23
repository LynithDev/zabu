package dev.lynith.core.utils.nvg;

import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.nanovg.NanoVGGL3;

public class NanoVGManager {

    public static long createContext() {
        try {
            final long nvg = NanoVGGL2.nvgCreate(NanoVGGL2.NVG_ANTIALIAS | NanoVGGL2.NVG_STENCIL_STROKES);

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
