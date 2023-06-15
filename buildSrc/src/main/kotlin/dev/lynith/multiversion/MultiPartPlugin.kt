package dev.lynith.multiversion

import dev.lynith.multiversion.tasks.ExportPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

class MultiPartPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply {
            plugin(JavaPlugin::class.java)

            plugin(ExportPlugin::class.java)
        }

        target.dependencies.apply {
            add("compileOnly", "org.projectlombok:lombok:1.18.20")
            add("annotationProcessor", "org.projectlombok:lombok:1.18.20")
        }
    }

}