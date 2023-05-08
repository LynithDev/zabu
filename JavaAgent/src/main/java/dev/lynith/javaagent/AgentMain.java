package dev.lynith.javaagent;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Logger;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.versions.IVersionMain;
import dev.lynith.javaagent.mixin.ClientMixinTransformer;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.URL;

public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) {
        Logger logger = new Logger("agent");

        try(ClassWrapper _wrapper = new ClassWrapper(new URL[0])) {
            MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
            Mixins.addConfiguration("client.mixins.json");

            inst.addTransformer(new ClientMixinTransformer(), true);

            logger.log("Hooked");
            try {
                Class<?> versionMain = Class.forName("%gradle.package%.start.VersionMain");
                IVersionMain versionMainInstance = (IVersionMain) versionMain.getConstructor().newInstance();

                Class<? extends IVersion> versionClass = versionMainInstance.getVersion();

                IVersion version = versionClass.getConstructor().newInstance();
                ClientStartup.start(version, inst);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
    }
}
