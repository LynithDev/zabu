package dev.lynith.javaagent.injectors;

import dev.lynith.javaagent.AgentMain;
import dev.lynith.javaagent.ClassWrapper;
import dev.lynith.javaagent.IInject;
import dev.lynith.javaagent.IStartTransformer;
import dev.lynith.javaagent.mixin.ClientMixinTransformer;
import dev.lynith.javaagent.transformers.LegacyVanillaTransformer;
import dev.lynith.javaagent.transformers.ModernVanillaTransformer;

import java.lang.instrument.Instrumentation;
import java.net.URL;

public class VanillaInjector implements IInject {

    @Override
    public String getClassName() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String getEnvironmentName() {
        return "vanilla";
    }

    @Override
    public boolean inject(Instrumentation inst) {
        try(ClassWrapper ignored = new ClassWrapper(new URL[0])) {
            boolean transformSuccess = transformMain(inst);
            if (!transformSuccess) return false;

            AgentMain.fixPackageAccessors(inst);
            inst.addTransformer(new ClientMixinTransformer(), true);
            return true;
        } catch (Exception ignored) {}

        return false;
    }

    private boolean transformMain(Instrumentation inst) {
        try {
            ClassLoader classLoader = VanillaInjector.class.getClassLoader();
            Class<?> main = classLoader.loadClass("net.minecraft.client.main.Main");

            IStartTransformer transformer;
            Integer version = AgentMain.getLaunchedVersion();
            if (version == null) {
                return false;
            }

            if (version <= 12) {
                transformer = new LegacyVanillaTransformer();
            } else {
                transformer = new ModernVanillaTransformer();
            }

            inst.addTransformer(transformer, true);
            inst.retransformClasses(main);
            inst.removeTransformer(transformer);
            return true;
        } catch (Exception ignored) {}

        return false;
    }

}
