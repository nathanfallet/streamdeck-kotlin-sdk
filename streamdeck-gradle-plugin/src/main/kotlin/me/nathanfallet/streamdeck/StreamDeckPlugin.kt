package me.nathanfallet.streamdeck

import me.nathanfallet.streamdeck.extensions.StreamDeckPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register

abstract class StreamDeckPlugin : Plugin<Project> {

    private val buildGroup = "streamdeck"

    override fun apply(project: Project) {
        applyPlugins(project)
        configureExtensions(project)
        configureTasks(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply(ApplicationPlugin::class.java)
    }

    private fun configureExtensions(project: Project) {
        project.extensions.create<StreamDeckPluginExtension>("streamDeckPlugin")
    }

    private fun configureTasks(project: Project) {
        val extension = project.extensions.getByType<StreamDeckPluginExtension>()

        project.tasks.register<Copy>("buildStreamDeckPlugin") {
            group = buildGroup
            dependsOn("jar")
            dependsOn("startScripts")

            into("build/${extension.pluginId.get()}.sdPlugin")

            from(project.tasks.named("jar")) {
                into("lib")
            }
            from(project.tasks.named("startScripts")) {
                into("bin")
            }
            from("src/main/resources")
        }
    }

}
