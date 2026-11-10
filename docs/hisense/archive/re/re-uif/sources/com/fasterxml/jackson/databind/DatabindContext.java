package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public abstract class DatabindContext {
    public abstract MapperConfig<?> getConfig();

    public abstract TypeFactory getTypeFactory();

    protected abstract JsonMappingException invalidTypeIdException(JavaType javaType, String str, String str2);

    public abstract <T> T reportBadDefinition(JavaType javaType, String str) throws JsonMappingException;

    public JavaType constructType(Type type) {
        if (type == null) {
            return null;
        }
        return getTypeFactory().constructType(type);
    }

    public JavaType resolveAndValidateSubType(JavaType javaType, String str, PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException {
        int iIndexOf = str.indexOf(60);
        if (iIndexOf > 0) {
            return _resolveAndValidateGeneric(javaType, str, polymorphicTypeValidator, iIndexOf);
        }
        MapperConfig<?> config = getConfig();
        PolymorphicTypeValidator.Validity validityValidateSubClassName = polymorphicTypeValidator.validateSubClassName(config, javaType, str);
        if (validityValidateSubClassName == PolymorphicTypeValidator.Validity.DENIED) {
            _throwSubtypeNameNotAllowed(javaType, str, polymorphicTypeValidator);
            throw null;
        }
        try {
            Class<?> clsFindClass = getTypeFactory().findClass(str);
            if (!javaType.isTypeOrSuperTypeOf(clsFindClass)) {
                _throwNotASubtype(javaType, str);
                throw null;
            }
            JavaType javaTypeConstructSpecializedType = config.getTypeFactory().constructSpecializedType(javaType, clsFindClass);
            if (validityValidateSubClassName != PolymorphicTypeValidator.Validity.INDETERMINATE || polymorphicTypeValidator.validateSubType(config, javaType, javaTypeConstructSpecializedType) == PolymorphicTypeValidator.Validity.ALLOWED) {
                return javaTypeConstructSpecializedType;
            }
            _throwSubtypeClassNotAllowed(javaType, str, polymorphicTypeValidator);
            throw null;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Exception e) {
            throw invalidTypeIdException(javaType, str, String.format("problem: (%s) %s", e.getClass().getName(), ClassUtil.exceptionMessage(e)));
        }
    }

    private JavaType _resolveAndValidateGeneric(JavaType javaType, String str, PolymorphicTypeValidator polymorphicTypeValidator, int i) throws JsonMappingException {
        MapperConfig<?> config = getConfig();
        PolymorphicTypeValidator.Validity validityValidateSubClassName = polymorphicTypeValidator.validateSubClassName(config, javaType, str.substring(0, i));
        if (validityValidateSubClassName == PolymorphicTypeValidator.Validity.DENIED) {
            _throwSubtypeNameNotAllowed(javaType, str, polymorphicTypeValidator);
            throw null;
        }
        JavaType javaTypeConstructFromCanonical = getTypeFactory().constructFromCanonical(str);
        if (!javaTypeConstructFromCanonical.isTypeOrSubTypeOf(javaType.getRawClass())) {
            _throwNotASubtype(javaType, str);
            throw null;
        }
        if (validityValidateSubClassName == PolymorphicTypeValidator.Validity.ALLOWED || polymorphicTypeValidator.validateSubType(config, javaType, javaTypeConstructFromCanonical) == PolymorphicTypeValidator.Validity.ALLOWED) {
            return javaTypeConstructFromCanonical;
        }
        _throwSubtypeClassNotAllowed(javaType, str, polymorphicTypeValidator);
        throw null;
    }

    protected <T> T _throwNotASubtype(JavaType javaType, String str) throws JsonMappingException {
        throw invalidTypeIdException(javaType, str, "Not a subtype");
    }

    protected <T> T _throwSubtypeNameNotAllowed(JavaType javaType, String str, PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException {
        throw invalidTypeIdException(javaType, str, "Configured `PolymorphicTypeValidator` (of type " + ClassUtil.classNameOf(polymorphicTypeValidator) + ") denied resolution");
    }

    protected <T> T _throwSubtypeClassNotAllowed(JavaType javaType, String str, PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException {
        throw invalidTypeIdException(javaType, str, "Configured `PolymorphicTypeValidator` (of type " + ClassUtil.classNameOf(polymorphicTypeValidator) + ") denied resolution");
    }

    public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated annotated, ObjectIdInfo objectIdInfo) throws JsonMappingException {
        Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
        MapperConfig<?> config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        ObjectIdGenerator<?> objectIdGeneratorObjectIdGeneratorInstance = handlerInstantiator == null ? null : handlerInstantiator.objectIdGeneratorInstance(config, annotated, generatorType);
        if (objectIdGeneratorObjectIdGeneratorInstance == null) {
            objectIdGeneratorObjectIdGeneratorInstance = (ObjectIdGenerator) ClassUtil.createInstance(generatorType, config.canOverrideAccessModifiers());
        }
        return objectIdGeneratorObjectIdGeneratorInstance.forScope(objectIdInfo.getScope());
    }

    public ObjectIdResolver objectIdResolverInstance(Annotated annotated, ObjectIdInfo objectIdInfo) {
        Class<? extends ObjectIdResolver> resolverType = objectIdInfo.getResolverType();
        MapperConfig<?> config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        ObjectIdResolver objectIdResolverResolverIdGeneratorInstance = handlerInstantiator == null ? null : handlerInstantiator.resolverIdGeneratorInstance(config, annotated, resolverType);
        return objectIdResolverResolverIdGeneratorInstance == null ? (ObjectIdResolver) ClassUtil.createInstance(resolverType, config.canOverrideAccessModifiers()) : objectIdResolverResolverIdGeneratorInstance;
    }

    public Converter<Object, Object> converterInstance(Annotated annotated, Object obj) throws JsonMappingException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Converter) {
            return (Converter) obj;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + obj.getClass().getName() + "; expected type Converter or Class<Converter> instead");
        }
        Class<?> cls = (Class) obj;
        if (cls == Converter.None.class || ClassUtil.isBogusClass(cls)) {
            return null;
        }
        if (!Converter.class.isAssignableFrom(cls)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<Converter>");
        }
        MapperConfig<?> config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        Converter<?, ?> converterConverterInstance = handlerInstantiator != null ? handlerInstantiator.converterInstance(config, annotated, cls) : null;
        return converterConverterInstance == null ? (Converter) ClassUtil.createInstance(cls, config.canOverrideAccessModifiers()) : converterConverterInstance;
    }

    public <T> T reportBadDefinition(Class<?> cls, String str) throws JsonMappingException {
        return (T) reportBadDefinition(constructType(cls), str);
    }

    protected final String _format(String str, Object... objArr) {
        return objArr.length > 0 ? String.format(str, objArr) : str;
    }

    protected final String _truncate(String str) {
        if (str == null) {
            return "";
        }
        if (str.length() <= 500) {
            return str;
        }
        return str.substring(0, 500) + "]...[" + str.substring(str.length() - 500);
    }

    protected String _quotedString(String str) {
        return str == null ? "[N/A]" : String.format("\"%s\"", _truncate(str));
    }

    protected String _colonConcat(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        return str + ": " + str2;
    }
}
