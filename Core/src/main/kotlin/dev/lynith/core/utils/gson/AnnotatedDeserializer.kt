package dev.lynith.core.utils.gson

import com.google.gson.*
import java.lang.reflect.Type

// https://stackoverflow.com/a/21634867
class AnnotatedDeserializer<T> : JsonDeserializer<T> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, type: Type, ctx: JsonDeserializationContext): T {
        val clazz = Gson().fromJson<T>(element, type) ?: throw JsonParseException("Cannot parse JSON")

        for (field in clazz::class.java.getDeclaredFields()) {
            if (field.getAnnotation<JsonRequired>(JsonRequired::class.java) != null) {
                try {
                    field.setAccessible(true)
                    if (field[clazz] == null) {
                        throw JsonParseException("Missing field '" + field.name + "' in JSON")
                    }
                } catch (ex: IllegalArgumentException) {
                    throw JsonParseException("Illegal argument: " + field.name)
                } catch (ex: IllegalAccessException) {
                    throw JsonParseException("Illegal access: " + field.name)
                }
            }
        }
        return clazz
    }

}
