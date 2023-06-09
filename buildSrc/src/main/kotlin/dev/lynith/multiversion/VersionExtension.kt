package dev.lynith.multiversion

import org.gradle.api.JavaVersion
import org.gradle.api.Project

open class VersionExtension(project: Project) {

    var minecraftVersion: String = "1.8.9"
    var fabricVersion: String = "" // if empty, will not use fabric

    var javaVersion: JavaVersion = JavaVersion.VERSION_1_8
    var isLegacy: Boolean = true

    var buildTargets: Map<String, String> = mapOf("vanilla" to "official")

    /** Shouldn't really override these */
    var mappingNamespaces = listOf("official", "intermediary")

}