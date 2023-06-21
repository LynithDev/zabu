package dev.lynith.core;

public class Logger {

    private final String name;

    public Logger(String name) {
        this.name = name;
    }

    public Logger(String... name) {
        this(String.join(".", name));
    }

    public Logger(Class<?> clazz) {
        this(clazz.getSimpleName());
    }

    public Logger(Object obj) {
        this(obj.getClass());
    }

    public Logger() {
        this("unknown");
    }

    public void log(Object message) {
        System.out.println(getString(message.toString()));
    }

    public void log(Object message, Object... args) {
        System.out.printf((getString(message.toString())) + "%n", args);
    }

    public void error(Object message) {
        System.err.println(getString(message.toString()));
    }

    public void error(Object message, Object... args) {
        System.err.printf((getString(message.toString())) + "%n", args);
    }

    private String getString(String message) {
        return "[" + name + "] " + message;
    }

}
