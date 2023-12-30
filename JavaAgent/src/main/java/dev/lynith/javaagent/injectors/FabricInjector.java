package dev.lynith.javaagent.injectors;

import dev.lynith.javaagent.AgentMain;
import dev.lynith.javaagent.IInject;
import dev.lynith.javaagent.IStartTransformer;
import dev.lynith.javaagent.transformers.FabricTransformer;
import dev.lynith.javaagent.transformers.LegacyVanillaTransformer;
import dev.lynith.javaagent.transformers.ModernVanillaTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;

public class FabricInjector implements IInject {

    @Override
    public String getClassName() {
        return "net.fabricmc.loader.impl.FabricLoaderImpl";
    }

    @Override
    public String getEnvironmentName() {
        return "fabric";
    }

    @Override
    public boolean inject(Instrumentation inst) {
        try {
            Class<?> loaderImpl = Class.forName(getClassName());

            Integer version = AgentMain.getLaunchedVersion();
            if (version == null) {
                return false;
            }

            IStartTransformer transformer = new FabricTransformer();
            inst.addTransformer(transformer, true);
            inst.retransformClasses(loaderImpl);
            inst.removeTransformer(transformer);
            return true;
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return false;
    }

}
