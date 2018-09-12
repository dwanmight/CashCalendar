package com.example.custom_annotations;

import com.squareup.javapoet.CodeBlock;

import javax.lang.model.util.ElementScanner7;

import sun.reflect.generics.tree.Tree;

public class MyTestAnnotationsVisitor extends ElementScanner7<Void, Void> {

    private final CodeBlock.Builder codeBuilder = CodeBlock.builder(); //poet square
}
