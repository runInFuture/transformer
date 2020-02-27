package com.wuyi.transformer.log;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class AdapterLogger implements Logger {
    private Messager delegate;
    private int logLevel = LEVEL_DEBUG;

    private static class SingletonHolder {
        private static AdapterLogger instance = new AdapterLogger(null);
    }

    public static AdapterLogger getInstance() {
        return SingletonHolder.instance;
    }

    public AdapterLogger(Messager messager) {
        delegate = messager;
    }

    public void inject(Messager messager) {
        this.delegate = messager;
    }

    @Override
    public void info(String msg) {
        if (LEVEL_INFO >= logLevel) {
            delegate.printMessage(Diagnostic.Kind.NOTE, msg);
        }
    }

    @Override
    public void debug(String msg) {
        if (LEVEL_DEBUG >= logLevel) {
            delegate.printMessage(Diagnostic.Kind.WARNING, msg);
        }
    }

    @Override
    public void error(String msg) {
        if (LEVEL_ERROR >= logLevel) {
            delegate.printMessage(Diagnostic.Kind.ERROR, msg);
        }
    }
}
