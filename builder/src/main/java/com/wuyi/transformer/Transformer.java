package com.wuyi.transformer;


import java.io.File;

public interface Transformer {
    /**
     * Transform a xml layout xml file to a class file represent the same layout
     * @param xml the xml layout
     * @return the class file
     */
    ClassFile transform(File xml);
}
