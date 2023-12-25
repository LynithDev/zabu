package dev.lynith.core.ui.animation

import dev.lynith.core.ui.units.ms

open class Animation(
    var duration: Int = 0.ms,
    var easing: Easing = Easing.SineIn(),
    var delay: Int = 0.ms,
    var loop: Boolean = false,
) {
    private var onStart: () -> Unit = {}
    private var onUpdate: (Float) -> Unit = {}
    private var onFinish: () -> Unit = {}

    fun configure(func: Animation.() -> Unit): Animation {
        func()
        return this
    }

    fun onStart(func: () -> Unit) {
        onStart = func
    }

    fun onUpdate(func: (Float) -> Unit) {
        onUpdate = func
    }

    fun onFinish(func: () -> Unit) {
        onFinish = func
    }

    private var startTime: Long = 0
    private var currentTime: Long = 0
    private var endTime: Long = 0
    private var paused: Boolean = false

    private var thread: Thread? = null

    fun isRunning(): Boolean {
        return thread != null && thread!!.isAlive
    }

    fun isPaused(): Boolean {
        return paused
    }

    fun stop() {
        if (thread != null) {
            paused = true
            thread!!.interrupt()
        }
    }

    fun pause() {
        if (thread != null) {
            paused = true
        }
    }

    fun resume() {
        if (thread != null) {
            paused = false

            val elapsed = currentTime - startTime

            startTime = System.currentTimeMillis() - elapsed
            endTime = startTime + duration

        }
    }

    fun animate() {
        onStart()

        startTime = System.currentTimeMillis()
        endTime = startTime + duration
        currentTime = startTime

        thread = Thread { // I NEED A BETTER WAY OF DOING THIS
            while (currentTime < endTime) {
                if (!paused) {
                    val progress = easing.ease((currentTime - startTime).toFloat() / duration.toFloat())
                    onUpdate(progress)

                    currentTime = System.currentTimeMillis()
                } else {
                    Thread.sleep(100)
                }
            }

            onFinish()
        }
        thread!!.start()

    }

}