package dev.lynith.javaagent.patches;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class FabricTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassWriter writer = new ClassWriter(0);
        ClassReader reader = new ClassReader(classfileBuffer);

        if (className.startsWith("net/fabricmc/loader/impl/launch/knot/Knot")) {
            reader.accept(new FabricInjector(Opcodes.ASM7, writer), 0);
            return writer.toByteArray();
        }

        return classfileBuffer;
    }

}
