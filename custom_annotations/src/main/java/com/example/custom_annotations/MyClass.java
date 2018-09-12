package com.example.custom_annotations;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

@SupportedAnnotationTypes("com.example.custom_annotations.MyTestAnnotations")
public final class MyClass extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set.isEmpty()) {
            return false;
        }

        final Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(MyTestAnnotations.class);

        for (Element element : elements) {
            MyTestAnnotations annotation = element.getAnnotation(MyTestAnnotations.class);
            String param = annotation.name();

            TypeMirror typeMirror = element.asType();
            String name = element.getSimpleName().toString();

            TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        }


        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(MyTestAnnotations.class.getCanonicalName());
    }

    private PackageElement getPackage(Element element) {
        while (element.getKind() != ElementKind.PACKAGE) {
            element = element.getEnclosingElement();
        }
        return (PackageElement) element;
    }

    public static class ViewBinding {
        private final TypeMirror typeMirror;
        private final String name;
        private final String param;

        public ViewBinding(TypeMirror typeMirror, String name, String param) {
            this.typeMirror = typeMirror;
            this.name = name;
            this.param = param;
        }
    }
}
