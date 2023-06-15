package dev.lynith.multiversion.tasks

tasks.register<Copy>("export-${project.name}")  {
    group = "multiversion ${project.name}"

    dependsOn(tasks.named("build").getOrElse(tasks.named("jar").get()))
    val inFile = "$projectDir/build/libs/${project.name}.jar"
    var outDir = "$rootDir/build/"

    if (project.parent != null && project.parent!!.name == "Versions") {
        outDir += "Versions/${project.name}/"
    }

    println("[${project.name}] Copying $inFile to $outDir")
    from(inFile)
    into(outDir)
}