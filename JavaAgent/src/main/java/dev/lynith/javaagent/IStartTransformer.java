package dev.lynith.javaagent;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;

public interface IStartTransformer extends ClassFileTransformer {

    String getMainClass();
    String getMainMethod();

    default String getMethodDescriptor() {
        return null;
    }

    MethodVisitor getMethodVisitor(MethodVisitor mv);

    @Override
    default byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.startsWith(getMainClass().replaceAll("\\.", "/"))) {
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

            int api = AgentMain.getLaunchedVersion() != null
                    && AgentMain.getLaunchedVersion() >= 16 ? Opcodes.ASM7 : Opcodes.ASM5;

            cr.accept(new ClassVisitor(api, cw) {

                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

                    boolean isMainMethod = name.equals(getMainMethod());
                    boolean isMethodDescriptor = getMethodDescriptor() == null || descriptor.equals(getMethodDescriptor());
                    if (isMainMethod && isMethodDescriptor) {
                        return getMethodVisitor(mv);
                    }

                    return mv;
                }

            }, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        }

        return ClassFileTransformer.super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
    }

}
