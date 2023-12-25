package dev.lynith.core.ui.units

import dev.lynith.core.Platform

inline val Int.px get() = this.toFloat()
inline val Float.px get() = this
inline val Double.px get() = this.toFloat()

inline val Int.ms get() = this
inline val Float.ms get() = this.toInt()
inline val Double.ms get() = this.toInt()

inline val Int.s get() = (this * 1000).ms
inline val Float.s get() = (this * 1000).ms
inline val Double.s get() = (this * 1000).ms
