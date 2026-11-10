package com.google.common.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.dex */
public class Element extends AccessibleObject implements Member {
    public final AccessibleObject accessibleObject;
    public final Member member;

    public <M extends AccessibleObject & Member> Element(M m) {
        if (m == null) {
            throw new NullPointerException();
        }
        this.accessibleObject = m;
        this.member = m;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Element)) {
            return false;
        }
        Element element = (Element) obj;
        return getOwnerType().equals(element.getOwnerType()) && this.member.equals(element.member);
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this.accessibleObject.getAnnotation(cls);
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final Annotation[] getAnnotations() {
        return this.accessibleObject.getAnnotations();
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final Annotation[] getDeclaredAnnotations() {
        return this.accessibleObject.getDeclaredAnnotations();
    }

    @Override // java.lang.reflect.Member
    public Class<?> getDeclaringClass() {
        return this.member.getDeclaringClass();
    }

    @Override // java.lang.reflect.Member
    public final int getModifiers() {
        return this.member.getModifiers();
    }

    @Override // java.lang.reflect.Member
    public final String getName() {
        return this.member.getName();
    }

    public TypeToken<?> getOwnerType() {
        return new TypeToken.SimpleTypeToken(getDeclaringClass());
    }

    public int hashCode() {
        return this.member.hashCode();
    }

    public final boolean isAbstract() {
        return Modifier.isAbstract(getModifiers());
    }

    @Override // java.lang.reflect.AccessibleObject
    public final boolean isAccessible() {
        return this.accessibleObject.isAccessible();
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final boolean isAnnotationPresent(Class<? extends Annotation> cls) {
        return this.accessibleObject.isAnnotationPresent(cls);
    }

    public final boolean isFinal() {
        return Modifier.isFinal(getModifiers());
    }

    public final boolean isNative() {
        return Modifier.isNative(getModifiers());
    }

    public final boolean isPackagePrivate() {
        return (isPrivate() || isPublic() || isProtected()) ? false : true;
    }

    public final boolean isPrivate() {
        return Modifier.isPrivate(getModifiers());
    }

    public final boolean isProtected() {
        return Modifier.isProtected(getModifiers());
    }

    public final boolean isPublic() {
        return Modifier.isPublic(getModifiers());
    }

    public final boolean isStatic() {
        return Modifier.isStatic(getModifiers());
    }

    public final boolean isSynchronized() {
        return Modifier.isSynchronized(getModifiers());
    }

    @Override // java.lang.reflect.Member
    public final boolean isSynthetic() {
        return this.member.isSynthetic();
    }

    public final boolean isTransient() {
        return Modifier.isTransient(getModifiers());
    }

    public final boolean isVolatile() {
        return Modifier.isVolatile(getModifiers());
    }

    @Override // java.lang.reflect.AccessibleObject
    public final void setAccessible(boolean z) {
        this.accessibleObject.setAccessible(z);
    }

    public String toString() {
        return this.member.toString();
    }
}
