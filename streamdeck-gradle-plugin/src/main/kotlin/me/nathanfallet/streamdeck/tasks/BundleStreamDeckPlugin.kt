package me.nathanfallet.streamdeck.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class BundleStreamDeckPlugin : DefaultTask() {

    @TaskAction
    fun bundle() {
        // Bundle the project
    }

}
