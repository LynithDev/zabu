package dev.lynith.multiversion.tasks

tasks.register("clean-${project.name}") {
    group = ""

    doLast {
        val mergedFile = "$rootDir/build/Versions/${project.name}/merged"
        println("Deleting $mergedFile")
        delete(mergedFile)
    }
}

tasks.register("zip-${project.name}", Zip::class.java) {
    group = "multiversion ${project.name}"

    println("Zipping ${project.name}")

    archiveFileName.set("merged.jar")
    from(file("${rootDir}/build/Versions/${project.name}/merged"))
    destinationDirectory.set(file("$rootDir/build/Versions/${project.name}"))

}