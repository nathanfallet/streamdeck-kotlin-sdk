plugins {
    kotlin("multiplatform").version("2.0.0").apply(false)
    kotlin("plugin.serialization").version("2.0.0").apply(false)
    id("org.jetbrains.kotlinx.kover").version("0.8.0").apply(false)
    id("com.google.devtools.ksp").version("2.0.0-1.0.21").apply(false)
    id("com.vanniktech.maven.publish").version("0.28.0").apply(false)
}

allprojects {
    group = "me.nathanfallet.streamdeck"
    version = "1.2.0"

    repositories {
        mavenCentral()
    }
}
