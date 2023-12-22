package dev.lynith.core.ui.styles


abstract class Style<S : Style<S>> {

    abstract val propertyName: String
    abstract val valueSerialized: String

    override fun toString(): String {
        return "$propertyName: $valueSerialized"
    }

    fun change(block: S.() -> Unit): S {
        block(this as S)
        return this as S
    }

}
