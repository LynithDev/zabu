package dev.lynith.javaagent.launchwrapper;

import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import dev.lynith.core.Logger;
import dev.lynith.javaagent.HookClient;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class LaunchWrapper {
    private static final Logger logger = new Logger("launchwrapper");
    public static Map<String,Object> blackboard;

    public static LaunchClassLoader classLoader;

    public static void main(String[] args) {
        String launchTarget = getOption(args, "--launchTarget");
        if (launchTarget == null) {
            launchTarget = "net.minecraft.client.main.Main";
        }

        new LaunchWrapper().launch(args, launchTarget);
    }

    private static String getOption(String[] args, String option) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(option)) {
                return args[i + 1];
            }
        }
        return null;
    }

    private LaunchWrapper() {
        final URLClassLoader ucl = (URLClassLoader) getClass().getClassLoader();
        classLoader = new LaunchClassLoader(ucl.getURLs());
        blackboard = new HashMap<>();
        Thread.currentThread().setContextClassLoader(classLoader);
    }

    private void launch(String[] args, String launchTarget) {
        HookClient.init(args);

        try {
            final Class<?> clazz = Class.forName(launchTarget, false, classLoader);
            final Method mainMethod = clazz.getMethod("main", String[].class);

            logger.log("Launching {}", launchTarget);
            mainMethod.invoke(null, (Object) args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
