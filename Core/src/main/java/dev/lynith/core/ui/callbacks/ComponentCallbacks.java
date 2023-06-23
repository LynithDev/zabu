package dev.lynith.core.ui.callbacks;

import dev.lynith.core.utils.Tuple;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ComponentCallbacks {

    private final List<Tuple<Class<? extends CallbackInterface>, CallbackInterface>> callbacks = new ArrayList<>();

    public interface CallbackInterface {}

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
                    Method[] methods = callbackClass.getMethods();
                    inner:
                    for (Method method : methods) {
                        if (method.getName().equals("handle")) {
                            List<Object> params = new ArrayList<>();
                            for (Class<?> param : method.getParameterTypes()) {
                                if (!param.isPrimitive()) {
                                    params.add(param.cast(args[params.size()]));
                                } else {
                                    params.add(args[params.size()]);
                                }
                            }
                            method.invoke(callbackInstance, params.toArray());
                            break inner;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
