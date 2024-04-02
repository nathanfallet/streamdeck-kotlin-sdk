plugins {
    kotlin("jvm")
    id("streamdeck-gradle-plugin")
}

application {
    mainClass.set("me.nathanfallet.streamdeck.sample.SamplePluginKt")
}

streamDeckPlugin {
    pluginId.set("me.nathanfallet.streamdeck.sample")
}

dependencies {
    implementation(project(":streamdeck-kotlin-sdk"))
}
