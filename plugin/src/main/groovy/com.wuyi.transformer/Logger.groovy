package com.wuyi.transformer

class Logger {
    Closure<Boolean> switcher

    Logger(Closure<Boolean> switcher) {
        this.switcher = switcher
    }

    void log(String msg) {
        if (switcher.call()) {
            println msg
        }
    }
}