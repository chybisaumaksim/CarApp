package com.godeltech.pt11.entity.enums;

import java.lang.annotation.Annotation;

public enum Colour implements Annotation {
    RED,
    BLUE,
    GREEN;

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
