package dev.lynith.javaagent;

import java.lang.instrument.Instrumentation;

public interface IInject {

    String getClassName();

    String getEnvironmentName();

    boolean inject(Instrumentation inst);

}
