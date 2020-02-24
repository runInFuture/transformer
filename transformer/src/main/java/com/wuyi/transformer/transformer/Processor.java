package com.wuyi.transformer.transformer;

import com.wuyi.transformer.annotation.Layout;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * This process analyze
 */
public class Processor extends AbstractProcessor {
    private final List<Class<? extends Annotation>> supportAnnotations = new ArrayList<>();

    {
        supportAnnotations.add(Layout.class);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supports = new HashSet<>();
        for (Class<? extends Annotation> clazz : supportAnnotations) {
            supports.add(clazz.getCanonicalName());
        }
        return supports;
    }
}
