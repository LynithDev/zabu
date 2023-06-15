package dev.lynith.multiversion

import org.gradle.api.JavaVersion
import org.gradle.api.Project

open class VersionExtension(project: Project) {

    var minecraftVersion: String = "1.8.9"
    var javaVersion: JavaVersion = JavaVersion.VERSION_1_8
    var isLegacy: Boolean = true
    var mappingNamespaces = listOf("official", "intermediary")
    var buildTargets = mapOf(
        "vanilla" to "official",
        "fabric" to "intermediary",
    )

}