package dev.lynith.multiversion

import org.gradle.api.JavaVersion
import kotlin.properties.Delegates

open class MultiVersionExtension {

    // Start Required //
    lateinit var minecraftVersion: String
    lateinit var javaVersion: JavaVersion
    var legacy by Delegates.notNull<Boolean>()

    // Start Optional //
    var fabricVersion: String? = null
    var forgeVersion: String? = null
}