package dev.lynith.core.ui.callbacks;

import dev.lynith.core.ui.Component;
import dev.lynith.core.utils.Tuple;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComponentCallbacks {

    private final List<Tuple<Class<? extends CallbackInterface>, CallbackInterface>> callbacks = new ArrayList<>();

    public interface CallbackInterface {}

    private final Component<?, ?> component;

    public ComponentCallbacks(Component<?, ?> component) {
        this.component = component;
    }

    public <T extends CallbackInterface> void addCallback(Class<T> callback, T callbackInstance) {
        callbacks.add(new Tuple<>(callback, callbackInstance));
    }

    public <T extends CallbackInterface> void addCallback(T callbackInstance) {
        callbacks.add(new Tuple<>(callbackInstance.getClass(), callbackInstance));
    }

    public <T extends CallbackInterface> void callCallbacks(Class<T> callback, Object... args) {
        for (Tuple<Class<? extends CallbackInterface>, CallbackInterface> entry : callbacks) {

            Class<? extends CallbackInterface> callbackClass = entry.getKey();
            CallbackInterface callbackInstance = entry.getValue();

            if (callbackClass.equals(callback)) {
                try {
                    Method handleMethod = null;
                    Method allowedMethod = null;

                    for (Method method : callbackClass.getMethods()) {
                        if (handleMethod != null && allowedMethod != null) {
                            break;
                        }

                        if (method.getName().equalsIgnoreCase("handle")) {
                            handleMethod = method;
                            continue;
                        }

                        if (method.getName().equalsIgnoreCase("allowed")) {
                            allowedMethod = method;
                        }
                    }

                    if (handleMethod == null || allowedMethod == null) {
                        throw new NoSuchMethodException("Callback interface does not have a handle or allowed method");
                    }

                    // Allowed method
                    List<Object> allowedParams = new ArrayList<>();
                    allowedParams.add(component);
                    allowedParams.addAll(Arrays.asList(args));
                    Boolean allowed = (Boolean) allowedMethod.invoke(callbackInstance, allowedParams.toArray());
                    if (!allowed) {
                        return;
                    }

                    // Handle method
                    List<Object> params = new ArrayList<>();
                    for (Class<?> param : handleMethod.getParameterTypes()) {

                        if (!param.isPrimitive()) {
                            params.add(param.cast(args[params.size()]));
                        } else {
                            params.add(args[params.size()]);
                        }

                    }
                    handleMethod.invoke(callbackInstance, params.toArray());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
