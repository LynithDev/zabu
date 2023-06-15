plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

group = "dev.lynith.multiversion"
version = "1.0.0"

repositories {
    mavenCentral()

    maven {
        name = "Fabric"
        url = uri("https://maven.fabricmc.net/")
    }

    maven {
        name = "legacy-fabric"
        url = uri("https://repo.legacyfabric.net/repository/legacyfabric/")
    }

}

dependencies {
    implementation("net.fabricmc:fabric-loom:1.2.5")
    implementation("net.fabricmc:tiny-remapper:0.8.6")

}

gradlePlugin {
    plugins {
        create("dev.lynith.multiversion.version") {
            id = "dev.lynith.multiversion.version"
            implementationClass = "dev.lynith.multiversion.VersionPlugin"
        }

        create("dev.lynith.multiversion.root") {
            id = "dev.lynith.multiversion.root"
            implementationClass = "dev.lynith.multiversion.MultiRootPlugin"
        }

        create("dev.lynith.multiversion.part") {
            id = "dev.lynith.multiversion.part"
            implementationClass = "dev.lynith.multiversion.MultiPartPlugin"
        }
    }
}