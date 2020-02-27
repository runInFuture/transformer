package com.wuyi.transformer.processor;

import com.wuyi.transformer.FileFinder;
import com.wuyi.transformer.Util;
import com.wuyi.transformer.annotation.Layout;
import com.wuyi.transformer.log.AdapterLogger;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Dispatch annotated elements to other controller.
 */
// todo: Called by javac via SPI. Consider use google's AutoService instead of register manually
public class TransformerProcessor extends AbstractProcessor {
    private final List<Class<? extends Annotation>> supportAnnotations = new ArrayList<>();
    {
        supportAnnotations.add(Layout.class);
    }

    private AdapterLogger adapterLogger = AdapterLogger.getInstance();
    // avoid generate more than once
    private Set<Integer> processedLayouts = new HashSet<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        adapterLogger.inject(processingEnv.getMessager());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        processLayout(roundEnv);
        return false;
    }

    private void processLayout(RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Layout.class);
        if (elements != null && !elements.isEmpty()) {
            for (Element element : elements) {
                Layout layout = element.getAnnotation(Layout.class);
                if (layout != null) {
                    int layoutId = layout.value();
                    adapterLogger.info("process layoutId: " + layoutId);
                    processLayout(layoutId);
                }
            }
        }
    }

    private void processLayout(int layoutId) {
        if (!processedLayouts.contains(layoutId)) {
            File layoutXmlFile = FileFinder.layout(layoutId);
            if (layoutXmlFile != null) {
                // read
            }
            processedLayouts.add(layoutId);
        }
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
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
