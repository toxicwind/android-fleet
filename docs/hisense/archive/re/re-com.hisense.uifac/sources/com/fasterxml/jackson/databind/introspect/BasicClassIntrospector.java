package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BasicClassIntrospector extends ClassIntrospector implements Serializable {
    protected static final BasicBeanDescription BOOLEAN_DESC;
    private static final Class<?> CLS_JSON_NODE;
    private static final Class<?> CLS_OBJECT;
    private static final Class<?> CLS_STRING;
    protected static final BasicBeanDescription INT_DESC;
    protected static final BasicBeanDescription LONG_DESC;
    protected static final BasicBeanDescription OBJECT_DESC;
    protected static final BasicBeanDescription STRING_DESC;
    private static final long serialVersionUID = 2;

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    public /* bridge */ /* synthetic */ BeanDescription forClassAnnotations(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return forClassAnnotations((MapperConfig<?>) mapperConfig, javaType, mixInResolver);
    }

    static {
        Class cls = Boolean.TYPE;
        CLS_OBJECT = Object.class;
        CLS_STRING = String.class;
        CLS_JSON_NODE = JsonNode.class;
        STRING_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(String.class), AnnotatedClassResolver.createPrimordial(CLS_STRING));
        BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(cls), AnnotatedClassResolver.createPrimordial(cls));
        INT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Integer.TYPE), AnnotatedClassResolver.createPrimordial(Integer.TYPE));
        LONG_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Long.TYPE), AnnotatedClassResolver.createPrimordial(Long.TYPE));
        OBJECT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Object.class), AnnotatedClassResolver.createPrimordial(CLS_OBJECT));
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    public BasicBeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription basicBeanDescription_findStdTypeDesc = _findStdTypeDesc(serializationConfig, javaType);
        if (basicBeanDescription_findStdTypeDesc != null) {
            return basicBeanDescription_findStdTypeDesc;
        }
        BasicBeanDescription basicBeanDescription_findStdJdkCollectionDesc = _findStdJdkCollectionDesc(serializationConfig, javaType);
        return basicBeanDescription_findStdJdkCollectionDesc == null ? BasicBeanDescription.forSerialization(collectProperties(serializationConfig, javaType, mixInResolver, true, "set")) : basicBeanDescription_findStdJdkCollectionDesc;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    public BasicBeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription basicBeanDescription_findStdTypeDesc = _findStdTypeDesc(deserializationConfig, javaType);
        if (basicBeanDescription_findStdTypeDesc != null) {
            return basicBeanDescription_findStdTypeDesc;
        }
        BasicBeanDescription basicBeanDescription_findStdJdkCollectionDesc = _findStdJdkCollectionDesc(deserializationConfig, javaType);
        return basicBeanDescription_findStdJdkCollectionDesc == null ? BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false, "set")) : basicBeanDescription_findStdJdkCollectionDesc;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    public BasicBeanDescription forDeserializationWithBuilder(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder(deserializationConfig, javaType, mixInResolver, false));
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    public BasicBeanDescription forCreation(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription basicBeanDescription_findStdTypeDesc = _findStdTypeDesc(deserializationConfig, javaType);
        if (basicBeanDescription_findStdTypeDesc != null) {
            return basicBeanDescription_findStdTypeDesc;
        }
        BasicBeanDescription basicBeanDescription_findStdJdkCollectionDesc = _findStdJdkCollectionDesc(deserializationConfig, javaType);
        return basicBeanDescription_findStdJdkCollectionDesc == null ? BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false, "set")) : basicBeanDescription_findStdJdkCollectionDesc;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector
    public BasicBeanDescription forClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription basicBeanDescription_findStdTypeDesc = _findStdTypeDesc(mapperConfig, javaType);
        return basicBeanDescription_findStdTypeDesc == null ? BasicBeanDescription.forOtherUse(mapperConfig, javaType, _resolveAnnotatedClass(mapperConfig, javaType, mixInResolver)) : basicBeanDescription_findStdTypeDesc;
    }

    protected POJOPropertiesCollector collectProperties(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z, String str) {
        return constructPropertyCollector(mapperConfig, _resolveAnnotatedClass(mapperConfig, javaType, mixInResolver), javaType, z, str);
    }

    protected POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z) {
        AnnotatedClass annotatedClass_resolveAnnotatedClass = _resolveAnnotatedClass(mapperConfig, javaType, mixInResolver);
        AnnotationIntrospector annotationIntrospector = mapperConfig.isAnnotationProcessingEnabled() ? mapperConfig.getAnnotationIntrospector() : null;
        JsonPOJOBuilder.Value valueFindPOJOBuilderConfig = annotationIntrospector != null ? annotationIntrospector.findPOJOBuilderConfig(annotatedClass_resolveAnnotatedClass) : null;
        return constructPropertyCollector(mapperConfig, annotatedClass_resolveAnnotatedClass, javaType, z, valueFindPOJOBuilderConfig == null ? "with" : valueFindPOJOBuilderConfig.withPrefix);
    }

    protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType, boolean z, String str) {
        return new POJOPropertiesCollector(mapperConfig, z, javaType, annotatedClass, str);
    }

    protected BasicBeanDescription _findStdTypeDesc(MapperConfig<?> mapperConfig, JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass.isPrimitive()) {
            if (rawClass == Integer.TYPE) {
                return INT_DESC;
            }
            if (rawClass == Long.TYPE) {
                return LONG_DESC;
            }
            if (rawClass == Boolean.TYPE) {
                return BOOLEAN_DESC;
            }
            return null;
        }
        if (ClassUtil.isJDKClass(rawClass)) {
            if (rawClass == CLS_OBJECT) {
                return OBJECT_DESC;
            }
            if (rawClass == CLS_STRING) {
                return STRING_DESC;
            }
            if (rawClass == Integer.class) {
                return INT_DESC;
            }
            if (rawClass == Long.class) {
                return LONG_DESC;
            }
            if (rawClass == Boolean.class) {
                return BOOLEAN_DESC;
            }
            return null;
        }
        if (CLS_JSON_NODE.isAssignableFrom(rawClass)) {
            return BasicBeanDescription.forOtherUse(mapperConfig, javaType, AnnotatedClassResolver.createPrimordial(rawClass));
        }
        return null;
    }

    protected boolean _isStdJDKCollection(JavaType javaType) {
        if (javaType.isContainerType() && !javaType.isArrayType()) {
            Class<?> rawClass = javaType.getRawClass();
            if (ClassUtil.isJDKClass(rawClass) && (Collection.class.isAssignableFrom(rawClass) || Map.class.isAssignableFrom(rawClass))) {
                return true;
            }
        }
        return false;
    }

    protected BasicBeanDescription _findStdJdkCollectionDesc(MapperConfig<?> mapperConfig, JavaType javaType) {
        if (_isStdJDKCollection(javaType)) {
            return BasicBeanDescription.forOtherUse(mapperConfig, javaType, _resolveAnnotatedClass(mapperConfig, javaType, mapperConfig));
        }
        return null;
    }

    protected AnnotatedClass _resolveAnnotatedClass(MapperConfig<?> mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        return AnnotatedClassResolver.resolve(mapperConfig, javaType, mixInResolver);
    }
}
