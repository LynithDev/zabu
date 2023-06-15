package dev.lynith.multiversion

import net.fabricmc.loom.LoomGradleExtension
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.bootstrap.LoomGradlePluginBootstrap
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.exclude
import org.gradle.kotlin.dsl.maven

import dev.lynith.multiversion.tasks.*
import net.fabricmc.loom.task.RemapJarTask
import org.gradle.kotlin.dsl.get

class VersionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply {
            plugin(JavaPlugin::class.java)
        }

        val extension = target.extensions.create("multiversion", VersionExtension::class.java, target)

        target.afterEvaluate {
            target.apply {
                plugin(LoomGradlePluginBootstrap::class.java)

                plugin(ExportPlugin::class.java)
                plugin(MergePlugin::class.java)
                plugin(StartPlugin::class.java)
                plugin(ZipPlugin::class.java)
                plugin(RemapPlugin::class.java)
            }

            target.repositories.maven("https://repo.legacyfabric.net/repository/legacyfabric/")

            target.dependencies.apply {
                add("compileOnly", target.rootProject.project(":Core"))

                add("implementation", "net.fabricmc:fabric-loom:1.2.5")
                add("implementation", "net.fabricmc:tiny-remapper:0.8.6")

                add("compileOnly", "org.projectlombok:lombok:1.18.20")
                add("annotationProcessor", "org.projectlombok:lombok:1.18.20")

                add("compileOnly", "net.fabricmc:sponge-mixin:0.12.4+mixin.0.8.5") {
                    exclude("launchwrapper")
                    exclude("guava")
                    exclude("gson")
                    exclude("commons-io")
                }

                println("Minecraft version: ${extension.minecraftVersion}")
                add("minecraft", "com.mojang:minecraft:${extension.minecraftVersion}")
                add("mappings",
                    if (extension.isLegacy) "net.legacyfabric:yarn:${extension.minecraftVersion}+build.+"
                    else "net.fabricmc:yarn:${extension.minecraftVersion}+build.+"
                )
            }

            val loom = target.extensions.findByName("loom") as LoomGradleExtensionAPI
            val remapJar = target.tasks.getByName("remapJar") as RemapJarTask

            remapJar.apply {
                sourceNamespace.set("named")
                targetNamespace.set("intermediary")
            }

            loom.apply {
                mixin.useLegacyMixinAp.set(false)

                extension.buildTargets.forEach { target ->
                    runs.register("-${target.key}-${extension.minecraftVersion}") {
                        group = "multiversion ${extension.minecraftVersion}"
                        runDir("run-${target.key}")
                        vmArgs("-javaagent:${rootDir}/build/Versions/${extension.minecraftVersion}/${target.value}.jar")

                        environment = "client"
                        defaultMainClass = "net.minecraft.client.main.Main"
                        ideConfigGenerated(true)

    //                        defaultMainClass = "net.fabricmc.loader.launch.knot.KnotClient"

                        programArgs("--version=${extension.minecraftVersion}")
                        programArgs("--accessToken=0")
                    }
                }
            }
        }
    }

}