package dev.lynith.core.input

class Key(
    val keyCode: Int
) {

    override fun toString(): String {
        return "Key(keyCode=$keyCode)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Key) return false
        if (this === other) return true
        return keyCode == other.keyCode
    }

}