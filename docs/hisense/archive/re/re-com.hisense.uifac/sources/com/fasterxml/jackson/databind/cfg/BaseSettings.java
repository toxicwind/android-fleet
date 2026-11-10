package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class BaseSettings implements Serializable {
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
    private static final long serialVersionUID = 1;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final ClassIntrospector _classIntrospector;
    protected final DateFormat _dateFormat;
    protected final Base64Variant _defaultBase64;
    protected final HandlerInstantiator _handlerInstantiator;
    protected final Locale _locale;
    protected final PropertyNamingStrategy _propertyNamingStrategy;
    protected final TimeZone _timeZone;
    protected final TypeFactory _typeFactory;
    protected final TypeResolverBuilder<?> _typeResolverBuilder;
    protected final PolymorphicTypeValidator _typeValidator;

    public BaseSettings(ClassIntrospector classIntrospector, AnnotationIntrospector annotationIntrospector, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, TypeResolverBuilder<?> typeResolverBuilder, DateFormat dateFormat, HandlerInstantiator handlerInstantiator, Locale locale, TimeZone timeZone, Base64Variant base64Variant, PolymorphicTypeValidator polymorphicTypeValidator) {
        this._classIntrospector = classIntrospector;
        this._annotationIntrospector = annotationIntrospector;
        this._propertyNamingStrategy = propertyNamingStrategy;
        this._typeFactory = typeFactory;
        this._typeResolverBuilder = typeResolverBuilder;
        this._dateFormat = dateFormat;
        this._handlerInstantiator = handlerInstantiator;
        this._locale = locale;
        this._timeZone = timeZone;
        this._defaultBase64 = base64Variant;
        this._typeValidator = polymorphicTypeValidator;
    }

    public BaseSettings withClassIntrospector(ClassIntrospector classIntrospector) {
        return this._classIntrospector == classIntrospector ? this : new BaseSettings(classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public ClassIntrospector getClassIntrospector() {
        return this._classIntrospector;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._propertyNamingStrategy;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public TypeResolverBuilder<?> getTypeResolverBuilder() {
        return this._typeResolverBuilder;
    }

    public PolymorphicTypeValidator getPolymorphicTypeValidator() {
        return this._typeValidator;
    }

    public DateFormat getDateFormat() {
        return this._dateFormat;
    }

    public HandlerInstantiator getHandlerInstantiator() {
        return this._handlerInstantiator;
    }

    public Locale getLocale() {
        return this._locale;
    }

    public TimeZone getTimeZone() {
        TimeZone timeZone = this._timeZone;
        return timeZone == null ? DEFAULT_TIMEZONE : timeZone;
    }

    public Base64Variant getBase64Variant() {
        return this._defaultBase64;
    }
}
