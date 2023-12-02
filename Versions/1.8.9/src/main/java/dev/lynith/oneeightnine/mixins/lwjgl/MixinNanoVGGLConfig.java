package dev.lynith.oneeightnine.mixins.lwjgl;

import dev.lynith.oneeightnine.lwjgl.LwjglFunctionProvider;
import org.lwjgl.system.FunctionProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "org.lwjgl.nanovg.NanoVGGLConfig")
public class MixinNanoVGGLConfig {

    @Overwrite
    private static FunctionProvider getFunctionProvider(String className) {
        return new LwjglFunctionProvider();
    }

}