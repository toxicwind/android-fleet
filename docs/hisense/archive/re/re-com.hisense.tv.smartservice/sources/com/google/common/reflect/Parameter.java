package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class Parameter implements AnnotatedElement {
    public final ImmutableList<Annotation> annotations;
    public final Invokable<?, ?> declaration;
    public final int position;
    public final TypeToken<?> type;

    public Parameter(Invokable<?, ?> invokable, int i, TypeToken<?> typeToken, Annotation[] annotationArr) {
        this.declaration = invokable;
        this.position = i;
        this.type = typeToken;
        this.annotations = ImmutableList.copyOf(annotationArr);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Parameter)) {
            return false;
        }
        Parameter parameter = (Parameter) obj;
        return this.position == parameter.position && this.declaration.equals(parameter.declaration);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (cls == null) {
            throw new NullPointerException();
        }
        UnmodifiableIterator<Annotation> it = this.annotations.iterator();
        while (it.hasNext()) {
            Annotation next = it.next();
            if (cls.isInstance(next)) {
                return cls.cast(next);
            }
        }
        return null;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getAnnotations() {
        return getDeclaredAnnotations();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> cls) {
        return (A[]) getDeclaredAnnotationsByType(cls);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> cls) {
        if (cls != null) {
            return (A) FluentIterable.from(this.annotations).filter(cls).first().orNull();
        }
        throw new NullPointerException();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getDeclaredAnnotations() {
        return (Annotation[]) this.annotations.toArray(new Annotation[0]);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> cls) {
        return (A[]) ((Annotation[]) FluentIterable.from(this.annotations).filter(cls).toArray(cls));
    }

    public Invokable<?, ?> getDeclaringInvokable() {
        return this.declaration;
    }

    public TypeToken<?> getType() {
        return this.type;
    }

    public int hashCode() {
        return this.position;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
        return getAnnotation(cls) != null;
    }

    public String toString() {
        String strValueOf = String.valueOf(this.type);
        int i = this.position;
        StringBuilder sb = new StringBuilder(strValueOf.length() + 15);
        sb.append(strValueOf);
        sb.append(" arg");
        sb.append(i);
        return sb.toString();
    }
}
