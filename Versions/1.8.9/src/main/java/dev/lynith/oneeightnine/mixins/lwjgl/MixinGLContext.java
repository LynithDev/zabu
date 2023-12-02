package dev.lynith.oneeightnine.mixins.lwjgl;

import org.lwjgl.opengl.GLContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GLContext.class)
public class MixinGLContext {

    @Invoker("getFunctionAddress")
    public static long getFunctionAddress(String name) {
        throw new UnsupportedOperationException();
    }

    @Invoker("ngetFunctionAddress")
    public static long ngetFunctionAddress(long name) {
        throw new UnsupportedOperationException();
    }

}
