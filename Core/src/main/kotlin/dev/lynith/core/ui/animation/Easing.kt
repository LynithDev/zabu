package dev.lynith.core.ui.animation

import kotlin.math.cos
import kotlin.math.sin

abstract class Easing {

    abstract fun ease(x: Float): Float

    class SineIn : Easing() {
        override fun ease(x: Float): Float {
            return sin(x * (Math.PI / 2)).toFloat()
        }
    }

    class SineOut : Easing() {
        override fun ease(x: Float): Float {
            return (sin((x + 1) * (Math.PI / 2))).toFloat()
        }
    }

    class SineInOut : Easing() {
        override fun ease(x: Float): Float {
            return (-0.5f * (cos(Math.PI * x) - 1)).toFloat()
        }
    }

}