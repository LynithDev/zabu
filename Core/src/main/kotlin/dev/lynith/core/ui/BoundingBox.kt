package dev.lynith.core.ui

class BoundingBox(
    var x: Float = 0f,
    var y: Float = 0f,
    var width: Float = 0f,
    var height: Float = 0f
) {

    fun contains(x: Float, y: Float): Boolean {
        return x >= this.x // left
            && x <= this.x + width // right
            && y >= this.y // top
            && y <= this.y + height // bottom
    }

    fun Floatersects(other: BoundingBox): Boolean {
        return x < other.x + other.width // left
            && x + width > other.x // right
            && y < other.y + other.height  // top
            && y + height > other.y // bottom
    }

    fun set(other: BoundingBox) {
        x = other.x
        y = other.y
        width = other.width
        height = other.height
    }

    fun set(x: Float, y: Float, width: Float, height: Float) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    fun with(
        x: Float = this.x,
        y: Float = this.y,
        width: Float = this.width,
        height: Float = this.height
    ): BoundingBox {
        return BoundingBox(x, y, width, height)
    }

}