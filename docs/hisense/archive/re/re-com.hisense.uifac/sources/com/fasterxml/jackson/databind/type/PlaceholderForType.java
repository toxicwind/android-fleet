package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

/* JADX INFO: loaded from: classes.dex */
public class PlaceholderForType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _actualType;
    protected final int _ordinal;

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean isContainerType() {
        return false;
    }

    public PlaceholderForType(int i) {
        super(Object.class, TypeBindings.emptyBindings(), TypeFactory.unknownType(), null, 1, null, null, false);
        this._ordinal = i;
    }

    public JavaType actualType() {
        return this._actualType;
    }

    public void actualType(JavaType javaType) {
        this._actualType = javaType;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase
    protected String buildCanonicalName() {
        return toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        getErasedSignature(sb);
        return sb;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append('$');
        sb.append(this._ordinal + 1);
        return sb;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withTypeHandler(Object obj) {
        _unsupported();
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withContentTypeHandler(Object obj) {
        _unsupported();
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withValueHandler(Object obj) {
        _unsupported();
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withContentValueHandler(Object obj) {
        _unsupported();
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withContentType(JavaType javaType) {
        _unsupported();
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withStaticTyping() {
        _unsupported();
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        _unsupported();
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public String toString() {
        StringBuilder sb = new StringBuilder();
        getErasedSignature(sb);
        return sb.toString();
    }

    private <T> T _unsupported() {
        throw new UnsupportedOperationException("Operation should not be attempted on " + PlaceholderForType.class.getName());
    }
}
