package dev.lynith.javaagent;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Logger;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.versions.IVersionMain;
import dev.lynith.javaagent.mixin.ClientMixinTransformer;
import org.apache.commons.io.IOUtils;
import org.objectweb.asm.*;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public class AgentMain {

    private static Logger logger;

    public static void premain(String agentArgs, Instrumentation inst) {
        logger = new Logger("agent");

        try(ClassWrapper ignored = new ClassWrapper(new URL[0])) {
            // TODO: Fabric.
//            try {
//                Class<?> clazz = Class.forName("net.fabricmc.loader.impl.launch.knot.Knot");
//            } catch (ClassNotFoundException e) {
//
//            }

            MixinBootstrap.init();
            MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);

            hook(inst);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void hook(Instrumentation inst) {
        logger.log("Hooking");
        try {
            Mixins.addConfiguration("client.mixins.json");

            inst.addTransformer(new ClientMixinTransformer(), true);

            Class<?> versionMain = Class.forName("%gradle.package%.start.VersionMain");
            IVersionMain versionMainInstance = (IVersionMain) versionMain.getConstructor().newInstance();

            Class<? extends IVersion> versionClass = versionMainInstance.getVersion();

            IVersion version = versionClass.getConstructor().newInstance();
            ClientStartup.start(version, inst);

            logger.log("Hooked");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
    }
}
