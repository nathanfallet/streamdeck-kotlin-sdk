plugins {
    kotlin("jvm")
    id("streamdeck-gradle-plugin")
}

application {
    mainClass.set("me.nathanfallet.streamdeck.sample.SamplePluginKt")
}

dependencies {
    implementation(project(":streamdeck-kotlin-sdk"))
}
