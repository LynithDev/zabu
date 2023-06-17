package dev.lynith.javaagent;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Logger;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.versions.IVersionMain;
import dev.lynith.javaagent.mixin.ClientMixinTransformer;
import dev.lynith.javaagent.patches.FabricTransformer;
import dev.lynith.javaagent.patches.PackageAccessTransformer;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.lang.instrument.Instrumentation;
import java.net.URL;

public class AgentMain {

    private static Logger logger;

    public static void premain(String agentArgs, Instrumentation inst) {
        logger = new Logger("agent");

        try {
            // Check if we're running in a Fabric environment, if the class is not found, we're not
            Class.forName("net.fabricmc.loader.impl.entrypoint.EntrypointUtils");
            logger.log("Detected Fabric environment");

            inst.addTransformer(new FabricTransformer(), true);
            return;
        } catch (ClassNotFoundException ignored) {
            logger.log("Detected non-Fabric environment");
        }

        try(ClassWrapper ignored = new ClassWrapper(new URL[0])) {

            MixinBootstrap.init();
            MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);

            if (inst != null) {
                inst.addTransformer(new ClientMixinTransformer(), true);
                inst.addTransformer(new PackageAccessTransformer(), true);
            }

            Mixins.addConfiguration("client.mixins.json");

            Class<?> versionMain = Class.forName("dev.lynith.start.VersionMain");
            IVersionMain versionMainInstance = (IVersionMain) versionMain.getConstructor().newInstance();

            Class<? extends IVersion> versionClass = versionMainInstance.getVersion();

            IVersion version = versionClass.getConstructor().newInstance();
            ClientStartup.start(version);

            logger.log("Hooked");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
    }
}
