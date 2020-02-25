package com.wuyi.transformer;

import com.wuyi.transformer.annotation.Layout;

public class Transformer {
    public static <T> T getView(Object source) {
        // get the annotation
        Layout layout = null;
        return getView(layout);
    }

    public static <T> T getView(Layout layout) {
        return null;
    }
}
