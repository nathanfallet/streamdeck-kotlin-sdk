plugins {
    kotlin("multiplatform").version("1.9.20").apply(false)
    kotlin("plugin.serialization").version("1.9.20").apply(false)
    id("convention.publication").apply(false)
    id("org.jetbrains.kotlinx.kover").version("0.7.4").apply(false)
    id("com.google.devtools.ksp").version("1.9.20-1.0.13").apply(false)
}

allprojects {
    group = "me.nathanfallet.streamdeck"
    version = "1.1.0"

    repositories {
        mavenCentral()
    }
}
