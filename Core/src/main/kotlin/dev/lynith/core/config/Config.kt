package dev.lynith.core.config

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import dev.lynith.core.Platform
import dev.lynith.core.events.impl.ShutdownEvent
import dev.lynith.core.utils.FileUtils
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.reflect.Field

class Config {

    private val registeredClasses = mutableListOf<Any>()
    private val configLocation = File(FileUtils.CONFIG_FOLDER, "config.json")
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private var storedConfig: JsonObject? = null

    fun init() {
        Platform.eventBus.once<ShutdownEvent> {
            save()
        }

        load()
    }

    fun register(instance: Any) {
        if (registeredClasses.contains(instance)) {
            throw IllegalArgumentException("Class ${instance.javaClass.name} is already registered")
        }
        registeredClasses.add(instance)
    }

    fun save(): Boolean {
        if (storedConfig == null) {
            if (!loadFromFile()) {
                return false
            }
        }

        for (clazz in registeredClasses) {
            loadFromClass(clazz)
        }

        return true
    }

    private fun writeToFile() {
        try {
            FileWriter(configLocation).use {
                it.write(gson.toJson(storedConfig))
                it.flush()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun save(clazz: Any): Boolean {
        if (storedConfig == null) {
            if (!loadFromFile()) {
                return false
            }
        }

        try {
            val success = loadFromClass(clazz)
            writeToFile()
            return success
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun load(): Boolean {
        if (storedConfig == null) {
            if (!loadFromFile()) {
                return false
            }
        }

        var allClassesSuccess = true
        for (clazz in registeredClasses) {
            if (!loadIntoClass(clazz)) {
                allClassesSuccess = false
            }
        }

        return allClassesSuccess
    }

    private fun loadFromClass(obj: Any): Boolean {
        if (!registeredClasses.contains(obj)) {
            return false
        }

        if (storedConfig == null) {
            if (!loadFromFile()) {
                return false
            }
        }

        val clazz = obj.javaClass
        if (clazz.name.equals("java.lang.Class")) return false

        val clazzValues = JsonObject()
        for (field in clazz.declaredFields) {
            if (field == null || !field.isAnnotationPresent(ConfigOption::class.java)) {
                continue
            }

            field.isAccessible = true
            val option = field.getAnnotation(ConfigOption::class.java)
            val name = option.name_serialized.ifEmpty { field.name }

            val value = field.get(obj)

            if (value != null) {
                if (isPrimitive(value)) {
                    clazzValues.add(name, gson.toJsonTree(value))
                } else {
                    clazzValues.add(name, gson.toJsonTree(value))
                }
            }
        }
        storedConfig?.add(clazz.name, clazzValues)
        return true
    }

    private fun loadIntoClass(obj: Any): Boolean {
        if (!registeredClasses.contains(obj)) {
            return false
        }

        if (storedConfig == null) {
            if (!loadFromFile()) {
                return false
            }
        }

        val clazz = obj.javaClass
        if (clazz.name.equals("java.lang.Class")) return false

        val json = storedConfig?.get(clazz.name)

        var fields = clazz.declaredFields
        if (clazz.superclass != null) {
            fields += clazz.superclass.declaredFields
        }

        try {
            if (json == null) {
                loadFromClass(obj)
                return false
            }

            for (field in fields) {
                if (field == null || !field.isAnnotationPresent(ConfigOption::class.java)) {
                    continue
                }

                field.isAccessible = true
                val option = field.getAnnotation(ConfigOption::class.java)
                val name = option.name_serialized.ifEmpty { field.name }
                val value = json.asJsonObject.get(name)

                if (value != null) {
                    println(field.get(obj))
                    if (value.isJsonPrimitive) {
                        setValue(obj, field, value)
                    } else if (value.isJsonObject) {
                        field.set(obj, gson.fromJson(value, field.type))
                    }

                    println(field.get(obj))

                    continue
                }

            }

            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    private fun isPrimitive(obj: Any): Boolean {
        return obj is Boolean || obj is Int || obj is Float || obj is Double || obj is String
    }

    private fun setValue(clazz: Any, field: Field, value: JsonElement) {
        when (field.type) {
            Boolean::class.java -> {
                field.setBoolean(clazz, value.asBoolean)
            }
            Int::class.java -> {
                field.setInt(clazz, value.asInt)
            }
            Float::class.java -> {
                field.setFloat(clazz, value.asFloat)
            }
            Double::class.java -> {
                field.setDouble(clazz, value.asDouble)
            }
            String::class.java -> {
                field.set(clazz, value.asString)
            }
        }
    }

    private fun loadFromFile(): Boolean {
        try {
            if (!configLocation.exists() || (configLocation.canRead() && configLocation.readBytes().isEmpty())) {
                configLocation.createNewFile()
                FileWriter(configLocation).use {
                    it.write("{}")
                    it.flush()
                }
                storedConfig = JsonObject()
                return true
            }

            val json = gson.fromJson(FileReader(configLocation), JsonObject::class.java)
            if (json == null) {
                storedConfig = JsonObject()
                return false
            }

            storedConfig = json
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

}