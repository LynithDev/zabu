plugins {
    id("java")
    id("dev.lynith.multiversion.root")
    kotlin("jvm") version "1.9.10"
}

group = "dev.lynith"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

kotlin {
    jvmToolchain(8)
}