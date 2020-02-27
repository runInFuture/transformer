package com.wuyi.transformer;

import com.squareup.javapoet.JavaFile;
import com.wuyi.transformer.log.AdapterLogger;

import java.io.File;

public class T implements Transformer {
    @Override
    public ClassFile transform(File xml) {
        AdapterLogger logger = AdapterLogger.getInstance();
        logger.info("start process " + xml.getName() + "...");

        // todo: the most import part: 1. read and understand the xml content 2. compose the .java class
        JavaFile javaFile = null;

        ClassFile classFile = new JavaPoetBackedClassFile(javaFile);
        logger.info("end process " + xml.getName());
        return classFile;
    }
}
