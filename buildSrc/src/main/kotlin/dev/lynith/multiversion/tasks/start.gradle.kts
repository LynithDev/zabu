package dev.lynith.multiversion.tasks

import dev.lynith.multiversion.VersionExtension
import net.fabricmc.loom.LoomGradleExtension
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.task.RunGameTask
import org.gradle.jvm.toolchain.internal.JavaToolchain

val extension = project.extensions.getByType(VersionExtension::class.java)

extension.buildTargets.forEach { target ->
    tasks.register("start-${target.key}-${project.name}") {
        group = "multiversion ${project.name}"

        val coreExport = rootProject.project("Core").tasks.named("export-Core").get()
        val agentExport = rootProject.project("JavaAgent").tasks.named("export-JavaAgent").get()
        val versionExport = project.tasks.named("export-${project.name}").get()
        val mergeTask = project.tasks.named("merge-${project.name}").get()
        val zipTask = project.tasks.named("zip-${project.name}").get()
        val cleanTask = project.tasks.named("clean-${project.name}").get()
        val remapTask = project.tasks.named("remap-${target.value}-${project.name}").get()

        agentExport.dependsOn(coreExport)
        versionExport.dependsOn(agentExport)
        mergeTask.dependsOn(versionExport)

        zipTask.finalizedBy(cleanTask)
        mergeTask.finalizedBy(zipTask)
        zipTask.finalizedBy(cleanTask)
        cleanTask.finalizedBy(remapTask)

        dependsOn(coreExport, agentExport, versionExport, mergeTask, zipTask, cleanTask, remapTask)

        finalizedBy(project.tasks.named("run-${target.key}-${project.name}"))
    }
}