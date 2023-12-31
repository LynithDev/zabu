package dev.lynith.core.bridge

interface IMinecraft {

    val fps: Int
    val gameVersion: String
    var isFullscreen: Boolean
    val gameDirectory: String

    fun scheduleStop()
}
