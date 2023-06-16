package dev.lynith.multiversion.tasks

tasks.register<Copy>("merge-${project.name}") {
    group = "multiversion ${project.name}"

    val versionFile = "$rootDir/build/Versions/${project.name}/${project.name}.jar"
    val coreFile = "$rootDir/build/Core.jar"
    val agentFile = "$rootDir/build/JavaAgent.jar"

    val mergedFile = "$rootDir/build/Versions/${project.name}/merged"
    println("Merging '${project.name}.jar', 'Core.jar' and 'JavaAgent.jar' to $mergedFile")

    from(zipTree(agentFile))
    from(zipTree(coreFile))
    from(zipTree(versionFile))

    into(mergedFile)

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

}