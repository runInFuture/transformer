package com.wuyi.transformer

import org.gradle.api.DefaultTask

abstract class BaseTask extends DefaultTask {
    abstract String name();
    abstract void work();
}
