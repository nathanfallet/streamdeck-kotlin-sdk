package me.nathanfallet.streamdeck

import me.nathanfallet.streamdeck.tasks.BuildStreamDeckPlugin
import me.nathanfallet.streamdeck.tasks.BundleStreamDeckPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin

abstract class StreamDeckPlugin : Plugin<Project> {

    private val buildGroup = "streamdeck"

    override fun apply(project: Project) {
        project.plugins.apply(ApplicationPlugin::class.java)


        project.tasks.register("buildStreamDeckPlugin", BuildStreamDeckPlugin::class.java) {
            group = buildGroup
            dependsOn("assemble")
        }
        project.tasks.register("bundleStreamDeckPlugin", BundleStreamDeckPlugin::class.java) {
            group = buildGroup
            dependsOn("buildStreamDeckPlugin")
        }
    }

}
