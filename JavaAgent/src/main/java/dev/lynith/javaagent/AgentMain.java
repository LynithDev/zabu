package dev.lynith.javaagent;

import dev.lynith.core.Logger;
import dev.lynith.javaagent.injectors.FabricInjector;
import dev.lynith.javaagent.injectors.VanillaInjector;
import dev.lynith.javaagent.mixin.ClientMixinTransformer;
import dev.lynith.javaagent.patches.PackageAccessVisitor;
import dev.lynith.javaagent.transformers.LegacyVanillaTransformer;
import dev.lynith.javaagent.transformers.ModernVanillaTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.security.ProtectionDomain;

public class AgentMain {

    private static final Logger logger = new Logger("agent");

    public static void main(String[] args) {
        System.out.println("This should be started as an agent. It is not intended to be run directly.");
        System.exit(1);
    }

    public static void premain(String args, Instrumentation inst) {
        init(args, inst, true);
    }

    public static void agentmain(String args, Instrumentation inst) {
        init(args, inst, false);
    }

    public static void init(String args, Instrumentation inst, boolean premain) {
        logger.log("Agent starting up");
        if (!premain) logger.log("## WARNING ##: Agent started dynamically. This is not recommended and *WILL* cause issues.");

        if (inst == null) {
            logger.error("Instrumentation instance is null");
            return;
        }

        IInject[] injectors = new IInject[] {
            new FabricInjector(),
            new VanillaInjector(),
        };

        boolean success = false;
        for (IInject injector : injectors) {
            try {
                Class.forName(injector.getClassName());
                success = injector.inject(inst);
                if (success) {
                    logger.log("Injected into the " + injector.getEnvironmentName() + " environment successfully");
                    break;
                }
            } catch (Exception ignored) {
                // Class not found, continue to the next injector
            }
        }

        if (!success) {
            logger.error("Unable to find a main class to inject to. Aborting...");
            return;
        }

        logger.log("Agent injected successfully");
    }

    // I am starting to hate Java and my life
    private static Integer __launchedVersion = null;
    public static Integer getLaunchedVersion() {
        if (__launchedVersion != null) {
            return __launchedVersion;
        }

        try {
            Class<?> versionClazz = Class.forName("dev.lynith.start.VersionMain");
            Object version = versionClazz.getConstructor().newInstance();
            String versionStr = version.getClass().getMethod("getVersionString").invoke(version).toString();
            __launchedVersion = Integer.parseInt(versionStr.split("\\.")[1]);
            return __launchedVersion;
        } catch (Exception ignored) {}
        return null;
    }

    public static void fixPackageAccessors(Instrumentation inst) {
        if (inst == null || System.getProperty("fabric.dli.env") == null || getLaunchedVersion() == null) {
            return;
        }

        try {
            inst.addTransformer(new ClassFileTransformer() {

                @Override
                public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                    ClassWriter writer = new ClassWriter(0);
                    ClassReader reader = new ClassReader(classfileBuffer);

                    if (className.startsWith("net/minecraft/")) {
                        reader.accept(new PackageAccessVisitor(Opcodes.ASM7, writer), 0);
                        return writer.toByteArray();
                    }

                    return classfileBuffer;
                }

            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}