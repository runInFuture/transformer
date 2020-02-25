package com.wuyi.transformer.log;

public interface Logger {
    int LEVEL_DEBUG = 1;
    int LEVEL_INFO = LEVEL_DEBUG + 1;
    int LEVEL_ERROR = LEVEL_DEBUG + 1;

    void info(String msg);

    void debug(String msg);

    void error(String msg);
}
