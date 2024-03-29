package dev.lynith.javaagent.transformers;

import dev.lynith.javaagent.IStartTransformer;
import dev.lynith.javaagent.launchwrapper.LaunchWrapper;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;

public class LegacyVanillaTransformer implements IStartTransformer {

    @Override
    public String getMainClass() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String getMainMethod() {
        return "main";
    }

    @Override
    public String getMethodDescriptor() {
        return "([Ljava/lang/String;)V";
    }

    @Override
    public MethodVisitor getMethodVisitor(MethodVisitor mv) {
        return new MainMethodVisitor(mv);
    }

    private static class MainMethodVisitor extends MethodVisitor {

        public MainMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }

        @Override
        public void visitCode() {
            super.visitCode();

            // #START# dev.lynith.tweaker.TweakerWrapper.init(args);
            visitVarInsn(Opcodes.ALOAD, 0);
            visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "dev/lynith/javaagent/transformers/LegacyVanillaTransformer$TweakerWrapper",
                    "init",
                    "([Ljava/lang/String;)V",
                    false
            );
            // #END#

            // #START# return;
            visitInsn(Opcodes.RETURN);
            // #END#
        }
    }

    public static class TweakerWrapper {
        public static void init(String[] strings) {
            boolean isModificationApplied = System.getProperty("dev.lynith.internal.isModificationApplied", "false").equals("true");

            if (!isModificationApplied) {
                System.setProperty("dev.lynith.internal.isModificationApplied", "true");
                LaunchWrapper.main(strings);
            }
        }
    }

}
