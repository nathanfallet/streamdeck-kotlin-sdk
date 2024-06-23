plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.kotlinx.kover")
    id("com.google.devtools.ksp")
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    pom {
        name.set("streamdeck-kotlin-sdk")
        description.set("A Kotlin SDK to create Stream Deck plugins.")
        url.set("https://github.com/nathanfallet/streamdeck-kotlin-sdk")

        licenses {
            license {
                name.set("GPL-3.0")
                url.set("https://opensource.org/licenses/GPL-3.0")
            }
        }
        developers {
            developer {
                id.set("NathanFallet")
                name.set("Nathan Fallet")
                email.set("contact@nathanfallet.me")
                url.set("https://www.nathanfallet.me")
            }
        }
        scm {
            url.set("https://github.com/nathanfallet/streamdeck-kotlin-sdk.git")
        }
    }
}

kotlin {
    macosX64()
    macosArm64()
    linuxX64()
    //mingwX64() // Not supported by CIO?
    jvmToolchain(21)
    jvm {
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
                implementation("io.ktor:ktor-client-cio:2.3.11")
                implementation("io.ktor:ktor-client-websockets:2.3.11")

                api("dev.kaccelero:core:0.2.0")
                api("com.github.ajalt.clikt:clikt:4.3.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.mockk:mockk:1.13.11")
            }
        }
    }
}
