package com.wuyi.transformer

class Logger {
    boolean loggale = true

    void log(String msg) {
        if (loggale) {
            print msg
        }
    }
}