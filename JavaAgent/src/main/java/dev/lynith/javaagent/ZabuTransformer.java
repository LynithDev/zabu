package dev.lynith.javaagent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ZabuTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassWriter writer = new ClassWriter(0);
        ClassReader reader = new ClassReader(classfileBuffer);

        if (className.startsWith("net/minecraft/")) {
            reader.accept(new PackageAccessFixer(Opcodes.ASM7, writer), 0);
            return writer.toByteArray();
        }

        return classfileBuffer;
    }

}
