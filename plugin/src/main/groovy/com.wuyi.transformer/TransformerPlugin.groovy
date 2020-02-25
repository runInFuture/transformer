package com.wuyi.transformer

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * When apply to a android project, we hook the build process of ABP(Android Build Plugin).
 * To be precise, let ABP's xxx tasks depends of our tasks, make sure our tasks be execute
 * in each single build.
 */
class TransformerPlugin implements Plugin<Project> {
    Logger logger

    @Override
    void apply(Project project) {
        // the entrance of plugin
        Config config = project.extensions.create("transformer", Config)
        logger = new Logger({ config.loggable })
        work()
    }

    void work() {

    }
}