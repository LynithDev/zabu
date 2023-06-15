package dev.lynith.multiversion.tasks

import dev.lynith.multiversion.VersionExtension
import net.fabricmc.loom.LoomGradleExtension
import net.fabricmc.loom.LoomGradlePlugin
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.api.mappings.layered.MappingsNamespace
import net.fabricmc.loom.configuration.providers.mappings.MappingConfiguration
import net.fabricmc.tinyremapper.NonClassCopyMode
import net.fabricmc.tinyremapper.OutputConsumerPath
import net.fabricmc.tinyremapper.TinyRemapper
import net.fabricmc.tinyremapper.TinyRemapper.LinkedMethodPropagation
import net.fabricmc.tinyremapper.TinyUtils
import net.fabricmc.tinyremapper.extension.mixin.MixinExtension
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.io.path.notExists

val extension = project.extensions.getByType(VersionExtension::class.java)

extension.mappingNamespaces.forEach { mapping ->
    tasks.register("remap-${mapping}-${project.name}") {
        doLast {
            val loomExtension = project.extensions.getByType(LoomGradleExtensionAPI::class.java)
            val mappings = loomExtension.mappingsFile
            val intermediaryJar = LoomGradleExtension.get(project).getMinecraftJars(MappingsNamespace.INTERMEDIARY)[0]

            if (intermediaryJar.notExists()) {
                throw RuntimeException("intermediary jar not found")
            }

            val input = file("$rootDir/build/Versions/${project.name}/merged.jar")
            val output = file("$rootDir/build/Versions/${project.name}/${mapping}.jar")

            val reader = BufferedReader(InputStreamReader(FileInputStream(mappings.path)))

            val builder = TinyRemapper.newRemapper()
                .withMappings(TinyUtils.createTinyMappingProvider(reader, "intermediary", mapping))
                .ignoreFieldDesc(false)
                .withForcedPropagation(emptySet())
                .propagatePrivate(false)
                .propagateBridges(LinkedMethodPropagation.DISABLED)
                .removeFrames(false)
                .ignoreConflicts(false)
                .checkPackageAccess(false)
                .fixPackageAccess(false)
                .resolveMissing(false)
                .rebuildSourceFilenames(false)
                .skipLocalVariableMapping(false)
                .renameInvalidLocals(false)
                .invalidLvNamePattern(null)
                .threads(-1)
                .extension(MixinExtension())

            val remapper = builder.build();

            try {
                OutputConsumerPath.Builder(output.toPath()).build().use { outputConsumer ->
                    outputConsumer.addNonClassFiles(input.toPath(), NonClassCopyMode.FIX_META_INF, remapper)
                    remapper.readInputs(input.toPath())
                    remapper.readClassPath(intermediaryJar)
                    remapper.apply(outputConsumer)
                }
            } catch (e: IOException) {
                throw RuntimeException(e)
            } finally {
                remapper.finish()
            }
        }
    }
}