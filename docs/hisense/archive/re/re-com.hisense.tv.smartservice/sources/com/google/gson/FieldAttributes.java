package com.google.gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class FieldAttributes {
    public final Field field;

    public FieldAttributes(Field field) {
        if (field == null) {
            throw new NullPointerException();
        }
        this.field = field;
    }

    public Object get(Object obj) {
        return this.field.get(obj);
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        return (T) this.field.getAnnotation(cls);
    }

    public Collection<Annotation> getAnnotations() {
        return Arrays.asList(this.field.getAnnotations());
    }

    public Class<?> getDeclaredClass() {
        return this.field.getType();
    }

    public Type getDeclaredType() {
        return this.field.getGenericType();
    }

    public Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public String getName() {
        return this.field.getName();
    }

    public boolean hasModifier(int i) {
        return (i & this.field.getModifiers()) != 0;
    }

    public boolean isSynthetic() {
        return this.field.isSynthetic();
    }
}
