package dev.lynith.javaagent.patches;

import dev.lynith.javaagent.AgentMain;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FabricInjector extends ClassVisitor {

    public FabricInjector(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    public static void invoke() {

        // What you are about to see, is some very weird crap that I have no idea how or why it works.
        // It just does

        try {
            ClassLoader knotClassLoader = Thread.currentThread().getContextClassLoader();
            Class<?> knotClassLoaderClass = knotClassLoader.getClass();

            Field classLoaderField = knotClassLoaderClass.getDeclaredField("urlLoader");
            classLoaderField.setAccessible(true);

            Object urlLoaderInstance = classLoaderField.get(knotClassLoader);

            Method addURL = urlLoaderInstance.getClass().getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);

            URL url = new File(AgentMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toURI().toURL();
            addURL.invoke(urlLoaderInstance, url);

            Class<?> versionMain = knotClassLoader.loadClass("dev.lynith.start.VersionMain");
            Mixins.addConfiguration("client.mixins.json");

            Method getVersion = versionMain.getDeclaredMethod("getVersion");
            getVersion.setAccessible(true);

            String versionClassPath = ((Class<?>) getVersion.invoke(versionMain.getDeclaredConstructor().newInstance())).getName();
            Class<?> versionClass = knotClassLoader.loadClass(versionClassPath);
            Class<?> versionInterface = knotClassLoader.loadClass("dev.lynith.core.versions.IVersion");
            Class<?> clientStartupClass = knotClassLoader.loadClass("dev.lynith.core.ClientStartup");

            Method start = clientStartupClass.getDeclaredMethod("start", versionInterface);

            start.setAccessible(true);
            start.invoke(null, versionClass.getConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

        if (name.equals("init")) {
            return new MethodVisitor(Opcodes.ASM7, methodVisitor) {
                @Override
                public void visitInsn(int opcode) {
                    List<Integer> opcodes = new ArrayList<>();
                    opcodes.add(Opcodes.ARETURN);
                    opcodes.add(Opcodes.RETURN);
                    opcodes.add(Opcodes.IRETURN);
                    opcodes.add(Opcodes.LRETURN);
                    opcodes.add(Opcodes.FRETURN);
                    opcodes.add(Opcodes.DRETURN);

                    if(opcodes.contains(opcode)) {
                        methodVisitor.visitMethodInsn(
                                Opcodes.INVOKESTATIC,
                                "dev/lynith/javaagent/patches/FabricEntrypointHook",
                                "invoke",
                                "()V",
                                false
                        );
                    }

                    super.visitInsn(opcode);
                }
            };
        }

        return methodVisitor;
    }
}
