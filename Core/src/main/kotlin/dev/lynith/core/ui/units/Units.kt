package dev.lynith.core.ui.units

import dev.lynith.core.Platform

inline val Int.px get() = this.toFloat()
inline val Float.px get() = this
inline val Double.px get() = this.toFloat()
