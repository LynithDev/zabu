package dev.lynith.core.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Array;
import java.util.*;

public class JsonUtils {

    public static Object getAsObject(Object object, JsonPrimitive primitive) {
        switch (object.getClass().getName()) {
            case "java.lang.String":
                return primitive.getAsString();
            case "java.lang.Integer":
                return primitive.getAsInt();
            case "java.lang.Boolean":
                return primitive.getAsBoolean();
            case "java.lang.Double":
                return primitive.getAsDouble();
            case "java.lang.Float":
                return primitive.getAsFloat();
            case "java.lang.Long":
                return primitive.getAsLong();
            case "java.lang.Short":
                return primitive.getAsShort();
            case "java.lang.Byte":
                return primitive.getAsByte();
            default:
                return null;
        }
    }

    public static Object[] getAsArray(JsonArray array) {
        List<Object> arrayObjects = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            arrayObjects.add(array.get(i));
        }
        return arrayObjects.toArray();
    }

    public static List<Object> getAsList(JsonArray array) {
        List<Object> arrayObjects = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            arrayObjects.add(array.get(i));
        }
        return arrayObjects;
    }

    public static boolean isPrimitiveArray(Object object) {
        return object != null
            && object.getClass().isArray()
            && object.getClass().getComponentType() != null
            && object.getClass().getComponentType().isPrimitive();
    }

    private static final List<String> primitives = Arrays.asList("java.lang.String", "java.lang.Integer", "java.lang.Boolean", "java.lang.Double", "java.lang.Float", "java.lang.Long", "java.lang.Short", "java.lang.Byte");
    public static boolean isPrimitive(Object object) {
        return primitives.contains(object.getClass().getName());
    }

}
