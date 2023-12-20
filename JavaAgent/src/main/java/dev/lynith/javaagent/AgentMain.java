package dev.lynith.javaagent;

import dev.lynith.core.Logger;
import dev.lynith.javaagent.mixin.ClientMixinTransformer;
import dev.lynith.javaagent.transformers.LegacyVanillaTransformer;

import java.lang.instrument.Instrumentation;
import java.net.URL;

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

        System.getProperties().list(System.out);

        if (!premain) {
            logger.log("## WARNING ##: Agent started dynamically");
        }

        try(ClassWrapper ignored = new ClassWrapper(new URL[0])) {
            if (inst == null) {
                logger.error("Instrumentation instance is null");
                return;
            }

            boolean transformSuccess = transformMain(inst);
            if (!transformSuccess) {
                logger.error("Unable to transform main class");
                return;
            }

            inst.addTransformer(new ClientMixinTransformer(), true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean transformMain(Instrumentation inst) {
        // Vanilla
        try {
            Class<?> main = Class.forName("net.minecraft.client.main.Main");
            IStartTransformer transformer = new LegacyVanillaTransformer();

            inst.addTransformer(transformer, true);
            inst.retransformClasses(main);
            inst.removeTransformer(transformer);

            return true;
        } catch (Exception ignored) {}

        logger.error("Unable to find a main class. Aborting...");
        return false;
    }

}