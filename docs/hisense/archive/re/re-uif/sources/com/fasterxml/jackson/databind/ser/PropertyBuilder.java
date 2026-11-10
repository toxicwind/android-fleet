package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;

/* JADX INFO: loaded from: classes.dex */
public class PropertyBuilder {
    private static final Object NO_DEFAULT_MARKER = Boolean.FALSE;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonInclude.Value _defaultInclusion;
    protected final boolean _useRealPropertyDefaults;

    public PropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        this._config = serializationConfig;
        this._beanDesc = beanDescription;
        JsonInclude.Value valueMerge = JsonInclude.Value.merge(beanDescription.findPropertyInclusion(JsonInclude.Value.empty()), serializationConfig.getDefaultPropertyInclusion(beanDescription.getBeanClass(), JsonInclude.Value.empty()));
        this._defaultInclusion = JsonInclude.Value.merge(serializationConfig.getDefaultPropertyInclusion(), valueMerge);
        this._useRealPropertyDefaults = valueMerge.getValueInclusion() == JsonInclude.Include.NON_DEFAULT;
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    protected BeanPropertyWriter buildWriter(SerializerProvider serializerProvider, BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, TypeSerializer typeSerializer2, AnnotatedMember annotatedMember, boolean z) throws JsonMappingException {
        JavaType javaType2;
        Object defaultValue;
        Object defaultBean;
        Object obj;
        boolean z2;
        boolean zIncludeFilterSuppressNulls = false;
        try {
            JavaType javaTypeFindSerializationType = findSerializationType(annotatedMember, z, javaType);
            if (typeSerializer2 != null) {
                if (javaTypeFindSerializationType == null) {
                    javaTypeFindSerializationType = javaType;
                }
                if (javaTypeFindSerializationType.getContentType() == null) {
                    serializerProvider.reportBadPropertyDefinition(this._beanDesc, beanPropertyDefinition, "serialization type " + javaTypeFindSerializationType + " has no content", new Object[0]);
                    throw null;
                }
                JavaType javaTypeWithContentTypeHandler = javaTypeFindSerializationType.withContentTypeHandler(typeSerializer2);
                javaTypeWithContentTypeHandler.getContentType();
                javaType2 = javaTypeWithContentTypeHandler;
            } else {
                javaType2 = javaTypeFindSerializationType;
            }
            JavaType javaType3 = javaType2 == null ? javaType : javaType2;
            AnnotatedMember accessor = beanPropertyDefinition.getAccessor();
            if (accessor == null) {
                serializerProvider.reportBadPropertyDefinition(this._beanDesc, beanPropertyDefinition, "could not determine property type", new Object[0]);
                throw null;
            }
            JsonInclude.Value valueWithOverrides = this._config.getDefaultInclusion(javaType3.getRawClass(), accessor.getRawType(), this._defaultInclusion).withOverrides(beanPropertyDefinition.findInclusion());
            JsonInclude.Include valueInclusion = valueWithOverrides.getValueInclusion();
            if (valueInclusion == JsonInclude.Include.USE_DEFAULTS) {
                valueInclusion = JsonInclude.Include.ALWAYS;
            }
            int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[valueInclusion.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    if (javaType3.isReferenceType()) {
                        defaultValue = BeanPropertyWriter.MARKER_FOR_EMPTY;
                    } else {
                        obj = null;
                    }
                    z2 = true;
                } else if (i != 3) {
                    if (i == 4) {
                        defaultValue = serializerProvider.includeFilterInstance(beanPropertyDefinition, valueWithOverrides.getValueFilter());
                        if (defaultValue != null) {
                            zIncludeFilterSuppressNulls = serializerProvider.includeFilterSuppressNulls(defaultValue);
                        }
                    } else {
                        zIncludeFilterSuppressNulls = i == 5;
                        if (!javaType3.isContainerType() || this._config.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)) {
                            z2 = zIncludeFilterSuppressNulls;
                            obj = null;
                        } else {
                            defaultValue = BeanPropertyWriter.MARKER_FOR_EMPTY;
                        }
                    }
                    obj = defaultValue;
                    z2 = zIncludeFilterSuppressNulls;
                } else {
                    defaultValue = BeanPropertyWriter.MARKER_FOR_EMPTY;
                }
                obj = defaultValue;
                z2 = true;
            } else {
                if (this._useRealPropertyDefaults && (defaultBean = getDefaultBean()) != null) {
                    if (serializerProvider.isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                        annotatedMember.fixAccess(this._config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                    }
                    try {
                        defaultValue = annotatedMember.getValue(defaultBean);
                    } catch (Exception e) {
                        _throwWrapped(e, beanPropertyDefinition.getName(), defaultBean);
                        throw null;
                    }
                } else {
                    defaultValue = BeanUtil.getDefaultValue(javaType3);
                    zIncludeFilterSuppressNulls = true;
                }
                if (defaultValue != null) {
                    if (defaultValue.getClass().isArray()) {
                        defaultValue = ArrayBuilders.getArrayComparator(defaultValue);
                    }
                    obj = defaultValue;
                    z2 = zIncludeFilterSuppressNulls;
                }
                obj = defaultValue;
                z2 = true;
            }
            Class<?>[] clsArrFindViews = beanPropertyDefinition.findViews();
            if (clsArrFindViews == null) {
                clsArrFindViews = this._beanDesc.findDefaultViews();
            }
            BeanPropertyWriter beanPropertyWriter = new BeanPropertyWriter(beanPropertyDefinition, annotatedMember, this._beanDesc.getClassAnnotations(), javaType, jsonSerializer, typeSerializer, javaType2, z2, obj, clsArrFindViews);
            Object objFindNullSerializer = this._annotationIntrospector.findNullSerializer(annotatedMember);
            if (objFindNullSerializer != null) {
                beanPropertyWriter.assignNullSerializer(serializerProvider.serializerInstance(annotatedMember, objFindNullSerializer));
            }
            NameTransformer nameTransformerFindUnwrappingNameTransformer = this._annotationIntrospector.findUnwrappingNameTransformer(annotatedMember);
            return nameTransformerFindUnwrappingNameTransformer != null ? beanPropertyWriter.unwrappingWriter(nameTransformerFindUnwrappingNameTransformer) : beanPropertyWriter;
        } catch (JsonMappingException e2) {
            if (beanPropertyDefinition == null) {
                serializerProvider.reportBadDefinition(javaType, ClassUtil.exceptionMessage(e2));
                throw null;
            }
            serializerProvider.reportBadPropertyDefinition(this._beanDesc, beanPropertyDefinition, ClassUtil.exceptionMessage(e2), new Object[0]);
            throw null;
        }
    }

    /* JADX INFO: renamed from: com.fasterxml.jackson.databind.ser.PropertyBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include;

        static {
            int[] iArr = new int[JsonInclude.Include.values().length];
            $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = iArr;
            try {
                iArr[JsonInclude.Include.NON_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_ABSENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_EMPTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.CUSTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.ALWAYS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    protected JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) throws JsonMappingException {
        JavaType javaTypeRefineSerializationType = this._annotationIntrospector.refineSerializationType(this._config, annotated, javaType);
        if (javaTypeRefineSerializationType != javaType) {
            Class<?> rawClass = javaTypeRefineSerializationType.getRawClass();
            Class<?> rawClass2 = javaType.getRawClass();
            if (!rawClass.isAssignableFrom(rawClass2) && !rawClass2.isAssignableFrom(rawClass)) {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + rawClass.getName() + " not a super-type of (declared) class " + rawClass2.getName());
            }
            javaType = javaTypeRefineSerializationType;
            z = true;
        }
        JsonSerialize.Typing typingFindSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated);
        if (typingFindSerializationTyping != null && typingFindSerializationTyping != JsonSerialize.Typing.DEFAULT_TYPING) {
            z = typingFindSerializationTyping == JsonSerialize.Typing.STATIC;
        }
        if (z) {
            return javaType.withStaticTyping();
        }
        return null;
    }

    protected Object getDefaultBean() {
        Object obj = NO_DEFAULT_MARKER;
        Object objInstantiateBean = this._defaultBean;
        if (objInstantiateBean == null) {
            objInstantiateBean = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
            if (objInstantiateBean == null) {
                objInstantiateBean = obj;
            }
            this._defaultBean = objInstantiateBean;
        }
        if (objInstantiateBean == obj) {
            return null;
        }
        return this._defaultBean;
    }

    protected Object _throwWrapped(Exception exc, String str, Object obj) {
        Throwable cause = exc;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        ClassUtil.throwIfError(cause);
        ClassUtil.throwIfRTE(cause);
        throw new IllegalArgumentException("Failed to get property '" + str + "' of default " + obj.getClass().getName() + " instance");
    }
}
