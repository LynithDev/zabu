package dev.lynith.oneeightnine.lwjgl;

import dev.lynith.oneeightnine.mixins.lwjgl.MixinGLContext;
import org.lwjgl.MemoryUtil;
import org.lwjgl.system.FunctionProvider;

import java.nio.ByteBuffer;

public final class LwjglFunctionProvider implements FunctionProvider {

    @Override
    public long getFunctionAddress(CharSequence functionName) {
        return MixinGLContext.getFunctionAddress(functionName.toString());
    }

    @Override
    public long getFunctionAddress(ByteBuffer paramByteBuffer) {
        return MixinGLContext.ngetFunctionAddress(MemoryUtil.getAddress(paramByteBuffer));
    }

}
