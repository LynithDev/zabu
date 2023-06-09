package dev.lynith.core.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.lynith.core.Logger;
import dev.lynith.core.utils.FileUtils;
import dev.lynith.core.utils.JsonUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigManager {

    private static ConfigManager instance;
    public static ConfigManager getInstance() {
        if (instance == null) instance = new ConfigManager();
        return instance;
    }

    private final Logger logger = new Logger("ConfigManager");

    private final List<Object> classes = new ArrayList<>();
    private File configFile;
    private JsonObject configJson = new JsonObject();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigManager() {}

    public void init() {
        configFile = new File(FileUtils.CONFIG_PATH.toString(), "config.json");
        loadFromFile();
        logger.log("Loaded config manager");
    }

    public void register(Object object) {
        classes.add(object);
        loadToClass(object);
    }

    public void loadFromFile() {
        try {
            if (configFile.exists()) {
                configJson = gson.fromJson(new FileReader(configFile), JsonObject.class);
            } else {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
                configJson = new JsonObject();
            }
        } catch (Exception ignored) {}
    }

    private void loadToClass(Object o) {
        if (configJson == null) configJson = new JsonObject();

        Class<?> clazz = o.getClass();
        if (clazz.getName().equals("java.lang.Class")) return;

        JsonObject classJson = configJson.has(clazz.getName()) ? configJson.get(clazz.getName()).getAsJsonObject() : new JsonObject();

        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        if (clazz.getSuperclass() != null) {
            fields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(ConfigOption.class)) {

                ConfigOption option = field.getAnnotation(ConfigOption.class);
                String name = option.nameSerialized().equals("") ? field.getName() : option.nameSerialized();

                try {
                    field.setAccessible(true);
                    if (classJson.getAsJsonObject().has(name)) {
                        JsonElement element = classJson.getAsJsonObject().get(name);
                        if (element.isJsonPrimitive()) {
                            field.set(o, JsonUtils.getAsObject(field.get(o), element.getAsJsonPrimitive()));
                        }

                        if (element.isJsonObject() && element.getAsJsonObject().has("config_type_name")) {
                            String className = element.getAsJsonObject().get("config_type_name").getAsString();
                            Class<?> type = Class.forName(className);
                            Object object = gson.fromJson(element.getAsJsonObject(), type);

                            field.set(o, object);
                        }

                        continue;
                    }

                    if (JsonUtils.isPrimitive(field.get(o))) {
                        classJson.add(name, gson.toJsonTree(field.get(o)));
                        continue;
                    }

                    JsonObject obj = gson.toJsonTree(field.get(o)).getAsJsonObject();
                    obj.addProperty("config_type_name", field.get(o).getClass().getName());
                    classJson.add(name, obj);

                } catch (Exception ignored) {}
            }
        }
        configJson.add(clazz.getName(), classJson);
    }

    private void loadToRAM(Object o) {
        Class<?> clazz = o.getClass();
        JsonObject classJson = new JsonObject();

        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        if (clazz.getSuperclass() != null) {
            fields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(ConfigOption.class)) {

                ConfigOption option = field.getAnnotation(ConfigOption.class);
                String name = option.nameSerialized().equals("") ? field.getName() : option.nameSerialized();

                try {
                    field.setAccessible(true);

                    if (JsonUtils.isPrimitive(field.get(o))) {
                        classJson.add(name, gson.toJsonTree(field.get(o)));
                        continue;
                    }

                    JsonObject obj = gson.toJsonTree(field.get(o)).getAsJsonObject();
                    obj.addProperty("config_type_name", field.get(o).getClass().getName());
                    classJson.add(name, obj);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        configJson.add(clazz.getName(), classJson);
    }

    public void forceSave() {
        for (Object clazz : classes)
            loadToRAM(clazz);
        save();
    }

    public void save() {
        try {
            configFile.getParentFile().createNewFile();
            FileWriter fw = new FileWriter(configFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(configJson));
            bw.close();
            fw.close();
        } catch (Exception ignored) {}
    }

}
