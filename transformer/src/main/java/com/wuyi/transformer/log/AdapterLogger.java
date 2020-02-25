package com.wuyi.transformer.log;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class AdapterLogger implements Logger {
    private final Messager delegate;
    private int logLevel = LEVEL_DEBUG;

    public AdapterLogger(Messager messager) {
        delegate = messager;
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
