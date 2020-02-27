package com.wuyi.transformer;

import com.squareup.javapoet.JavaFile;

public class JavaPoetBackedClassFile implements ClassFile {
    JavaFile javaFile;

    JavaPoetBackedClassFile(JavaFile javaFile) {
        this.javaFile = javaFile;
    }

    @Override
    public void writeToFile() {

    }
}
