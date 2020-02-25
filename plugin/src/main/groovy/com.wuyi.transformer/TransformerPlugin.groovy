package com.wuyi.transformer

import org.gradle.api.Plugin

/**
 * When apply to a android project, we hook the build process of ABP(Android Build Plugin).
 * To be precise, let ABP's xxx tasks depends of our tasks, make sure our tasks be execute
 * in each single build.
 */
class TransformerPlugin implements Plugin<Config> {
    Logger logger = new Logger()

    @Override
    void apply(Config config) {
        // the entrance of plugin
        applyConfig(config)
        work()
    }

    void applyConfig(Config config) {
        logger.loggale = config.loggable
    }

    void work() {

    }
}