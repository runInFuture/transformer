package com.wuyi.transformer

import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.tasks.ProcessAndroidResources
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * When apply to a android project, we hook the build process of ABP(Android Build Plugin).
 * To be precise, let ABP's xxx tasks depends of our tasks, make sure our tasks be execute
 * in each single build.
 */
class TransformerPlugin implements Plugin<Project> {
    Logger logger

    // fixme
    @Override
    void apply(Project project) {
        // the entrance of plugin
        Config config = project.extensions.create("transformer", Config)
        logger = new Logger({ config.loggable })

//        LayoutViewTask layoutViewTask = new LayoutViewTask()
//        project.tasks.add(layoutViewTask.name(), layoutViewTask)

        def variants = project.plugins.hasPlugin(LibraryPlugin) ?
                project.android.libraryVariants : project.android.applicationVariants
        logger.log("==================" + variants)
        project.afterEvaluate {
            variants.all { BaseVariant variant ->
                String varName = variant.name.capitalize()
                ProcessAndroidResources processResourcesTask =
                        project.tasks.findByName("process${varName}Resources")
                logger.log("==================" + variant)
//                println "==================" + processResourcesTask.sourceOutputDir.path
            }
        }
    }
}