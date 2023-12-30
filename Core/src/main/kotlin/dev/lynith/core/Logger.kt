package dev.lynith.core

class Logger @JvmOverloads constructor(private val name: String = "unknown") {
    constructor(vararg name: String?) : this(java.lang.String.join(".", *name))
    constructor(clazz: Class<*>) : this(clazz.getSimpleName())
    constructor(obj: Any) : this(obj.javaClass)

    fun log(message: Any) {
        println(getString(message.toString()))
    }

    fun log(message: Any, vararg args: Any) {
        println(getString(format(message.toString(), *args)))
    }

    fun error(message: Any) {
        System.err.println(getString(message.toString()))
    }

    fun error(message: Any, vararg args: Any) {
        System.err.println(getString(format(message.toString(), *args)))
    }

    private fun format(message: String, vararg args: Any): String {
        var message = message
        for (arg in args) {
            val argString = arg.toString()
            message = message.replaceFirst("\\{\\}".toRegex(), argString)
        }
        return message
    }

    private fun getString(message: String?): String {
        return "[$name] $message"
    }
}
