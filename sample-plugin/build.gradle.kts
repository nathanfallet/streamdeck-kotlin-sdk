plugins {
    kotlin("jvm")
    id("me.nathanfallet.streamdeck")
}

application {
    mainClass = "me.nathanfallet.streamdeck.sample.SamplePluginKt"
}

streamDeckPlugin {
    pluginId = "me.nathanfallet.streamdeck.sample"
}

dependencies {
    implementation(project(":streamdeck-kotlin-sdk"))
}
