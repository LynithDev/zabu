package dev.lynith.core.bridge

interface IMinecraft {

    val fps: Int
    val gameVersion: String
    var isFullscreen: Boolean

//    fun toggleFullscreen() {
//        isFullscreen = !isFullscreen
//    }

    fun scheduleStop()
}
