package dev.lynith.javaagent.transformers;

import dev.lynith.javaagent.IStartTransformer;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;
import java.util.List;

public class FabricTransformer implements IStartTransformer {

    @Override
    public String getMainClass() {
        return "net.fabricmc.loader.impl.FabricLoaderImpl";
    }

    @Override
    public String getMainMethod() {
        return "invokeEntrypoints";
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

            // #START# dev.lynith.javaagent.HookClient.init(args);
            visitVarInsn(Opcodes.ALOAD, 0);
            visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "dev/lynith/javaagent/HookClient",
                    "init",
                    "([Ljava/lang/String;)V",
                    false
            );
            // #END#
        }

        //        @Override
//        public void visitInsn(int opcode) {
//            if (opcodes.contains(opcode)) {
//                // #START# dev.lynith.javaagent.HookClient.init(args);
//                visitVarInsn(Opcodes.ALOAD, 0);
//                visitMethodInsn(
//                        Opcodes.INVOKESTATIC,
//                        "dev/lynith/javaagent/HookClient",
//                        "init",
//                        "([Ljava/lang/String;)V",
//                        false
//                );
//                // #END#
//            }
//
//            super.visitInsn(opcode);
//        }
    }

}
