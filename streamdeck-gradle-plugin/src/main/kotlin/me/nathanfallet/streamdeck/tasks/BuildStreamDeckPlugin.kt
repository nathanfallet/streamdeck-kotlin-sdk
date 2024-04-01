package me.nathanfallet.streamdeck.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class BuildStreamDeckPlugin : DefaultTask() {

    @TaskAction
    fun build() {
        // Bundle the project
    }

}
