plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("convention.publication")
    id("org.jetbrains.kotlinx.kover")
    id("com.google.devtools.ksp")
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("streamdeck-kotlin-sdk")
            description.set("A Kotlin SDK to create Stream Deck plugins.")
        }
    }
}

kotlin {
    macosX64()
    macosArm64()
    linuxX64()
    //mingwX64() // Not supported by CIO?
    jvm {
        jvmToolchain(19)
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
                implementation("io.ktor:ktor-client-cio:2.3.9")
                implementation("io.ktor:ktor-client-websockets:2.3.9")

                api("me.nathanfallet.usecases:usecases:1.6.0")
                api("com.github.ajalt.clikt:clikt:4.3.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.mockk:mockk:1.13.8")
            }
        }
    }
}
