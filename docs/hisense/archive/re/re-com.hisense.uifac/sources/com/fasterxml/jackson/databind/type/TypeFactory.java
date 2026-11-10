package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class TypeFactory implements Serializable {
    private static final long serialVersionUID = 1;
    protected final ClassLoader _classLoader;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final LRUMap<Object, JavaType> _typeCache;
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    protected static final TypeBindings EMPTY_BINDINGS = TypeBindings.emptyBindings();
    private static final Class<?> CLS_STRING = String.class;
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final Class<?> CLS_COMPARABLE = Comparable.class;
    private static final Class<?> CLS_CLASS = Class.class;
    private static final Class<?> CLS_ENUM = Enum.class;
    private static final Class<?> CLS_JSON_NODE = JsonNode.class;
    private static final Class<?> CLS_BOOL = Boolean.TYPE;
    private static final Class<?> CLS_INT = Integer.TYPE;
    private static final Class<?> CLS_LONG = Long.TYPE;
    protected static final SimpleType CORE_TYPE_BOOL = new SimpleType(CLS_BOOL);
    protected static final SimpleType CORE_TYPE_INT = new SimpleType(CLS_INT);
    protected static final SimpleType CORE_TYPE_LONG = new SimpleType(CLS_LONG);
    protected static final SimpleType CORE_TYPE_STRING = new SimpleType(CLS_STRING);
    protected static final SimpleType CORE_TYPE_OBJECT = new SimpleType(CLS_OBJECT);
    protected static final SimpleType CORE_TYPE_COMPARABLE = new SimpleType(CLS_COMPARABLE);
    protected static final SimpleType CORE_TYPE_ENUM = new SimpleType(CLS_ENUM);
    protected static final SimpleType CORE_TYPE_CLASS = new SimpleType(CLS_CLASS);
    protected static final SimpleType CORE_TYPE_JSON_NODE = new SimpleType(CLS_JSON_NODE);

    private TypeFactory() {
        this(null);
    }

    protected TypeFactory(LRUMap<Object, JavaType> lRUMap) {
        this._typeCache = lRUMap == null ? new LRUMap<>(16, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR) : lRUMap;
        this._parser = new TypeParser(this);
        this._modifiers = null;
        this._classLoader = null;
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public ClassLoader getClassLoader() {
        return this._classLoader;
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        Class<?> cls_findPrimitive;
        if (str.indexOf(46) < 0 && (cls_findPrimitive = _findPrimitive(str)) != null) {
            return cls_findPrimitive;
        }
        Throwable rootCause = null;
        ClassLoader classLoader = getClassLoader();
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        if (classLoader != null) {
            try {
                return classForName(str, true, classLoader);
            } catch (Exception e) {
                rootCause = ClassUtil.getRootCause(e);
            }
        }
        try {
            return classForName(str);
        } catch (Exception e2) {
            if (rootCause == null) {
                rootCause = ClassUtil.getRootCause(e2);
            }
            ClassUtil.throwIfRTE(rootCause);
            throw new ClassNotFoundException(rootCause.getMessage(), rootCause);
        }
    }

    protected Class<?> classForName(String str, boolean z, ClassLoader classLoader) throws ClassNotFoundException {
        return Class.forName(str, true, classLoader);
    }

    protected Class<?> classForName(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    protected Class<?> _findPrimitive(String str) {
        if ("int".equals(str)) {
            return Integer.TYPE;
        }
        if ("long".equals(str)) {
            return Long.TYPE;
        }
        if ("float".equals(str)) {
            return Float.TYPE;
        }
        if ("double".equals(str)) {
            return Double.TYPE;
        }
        if ("boolean".equals(str)) {
            return Boolean.TYPE;
        }
        if ("byte".equals(str)) {
            return Byte.TYPE;
        }
        if ("char".equals(str)) {
            return Character.TYPE;
        }
        if ("short".equals(str)) {
            return Short.TYPE;
        }
        if ("void".equals(str)) {
            return Void.TYPE;
        }
        return null;
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) throws IllegalArgumentException {
        return constructSpecializedType(javaType, cls, false);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0070  */
    /* JADX WARN: Code duplicated, block: B:40:0x007a A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Code duplicated, block: B:41:0x0081  */
    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls, boolean z) throws IllegalArgumentException {
        int length;
        JavaType javaType_fromClass;
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            return javaType;
        }
        if (rawClass == Object.class) {
            javaType_fromClass = _fromClass(null, cls, EMPTY_BINDINGS);
        } else {
            if (!rawClass.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(String.format("Class %s not subtype of %s", ClassUtil.nameOf(cls), ClassUtil.getTypeDescription(javaType)));
            }
            if (javaType.isContainerType()) {
                if (javaType.isMapLikeType()) {
                    if (cls == HashMap.class || cls == LinkedHashMap.class || cls == EnumMap.class || cls == TreeMap.class) {
                        javaType_fromClass = _fromClass(null, cls, TypeBindings.create(cls, javaType.getKeyType(), javaType.getContentType()));
                    } else if (!javaType.getBindings().isEmpty()) {
                        javaType_fromClass = _fromClass(null, cls, EMPTY_BINDINGS);
                    } else {
                        javaType_fromClass = _fromClass(null, cls, _bindingsForSubtype(javaType, length, cls, z));
                    }
                } else if (javaType.isCollectionLikeType()) {
                    if (cls == ArrayList.class || cls == LinkedList.class || cls == HashSet.class || cls == TreeSet.class) {
                        javaType_fromClass = _fromClass(null, cls, TypeBindings.create(cls, javaType.getContentType()));
                    } else {
                        if (rawClass == EnumSet.class) {
                            return javaType;
                        }
                        if (!javaType.getBindings().isEmpty()) {
                            javaType_fromClass = _fromClass(null, cls, EMPTY_BINDINGS);
                        } else {
                            javaType_fromClass = _fromClass(null, cls, _bindingsForSubtype(javaType, length, cls, z));
                        }
                    }
                } else if (!javaType.getBindings().isEmpty()) {
                    javaType_fromClass = _fromClass(null, cls, EMPTY_BINDINGS);
                } else {
                    javaType_fromClass = _fromClass(null, cls, _bindingsForSubtype(javaType, length, cls, z));
                }
            } else if (!javaType.getBindings().isEmpty() || (length = cls.getTypeParameters().length) == 0) {
                javaType_fromClass = _fromClass(null, cls, EMPTY_BINDINGS);
            } else {
                javaType_fromClass = _fromClass(null, cls, _bindingsForSubtype(javaType, length, cls, z));
            }
        }
        return javaType_fromClass.withHandlersFrom(javaType);
    }

    private TypeBindings _bindingsForSubtype(JavaType javaType, int i, Class<?> cls, boolean z) {
        PlaceholderForType[] placeholderForTypeArr = new PlaceholderForType[i];
        for (int i2 = 0; i2 < i; i2++) {
            placeholderForTypeArr[i2] = new PlaceholderForType(i2);
        }
        JavaType javaTypeFindSuperType = _fromClass(null, cls, TypeBindings.create(cls, placeholderForTypeArr)).findSuperType(javaType.getRawClass());
        if (javaTypeFindSuperType == null) {
            throw new IllegalArgumentException(String.format("Internal error: unable to locate supertype (%s) from resolved subtype %s", javaType.getRawClass().getName(), cls.getName()));
        }
        String str_resolveTypePlaceholders = _resolveTypePlaceholders(javaType, javaTypeFindSuperType);
        if (str_resolveTypePlaceholders != null && !z) {
            throw new IllegalArgumentException("Failed to specialize base type " + javaType.toCanonical() + " as " + cls.getName() + ", problem: " + str_resolveTypePlaceholders);
        }
        JavaType[] javaTypeArr = new JavaType[i];
        for (int i3 = 0; i3 < i; i3++) {
            JavaType javaTypeActualType = placeholderForTypeArr[i3].actualType();
            if (javaTypeActualType == null) {
                javaTypeActualType = unknownType();
            }
            javaTypeArr[i3] = javaTypeActualType;
        }
        return TypeBindings.create(cls, javaTypeArr);
    }

    private String _resolveTypePlaceholders(JavaType javaType, JavaType javaType2) throws IllegalArgumentException {
        List<JavaType> typeParameters = javaType.getBindings().getTypeParameters();
        List<JavaType> typeParameters2 = javaType2.getBindings().getTypeParameters();
        int size = typeParameters2.size();
        int size2 = typeParameters.size();
        int i = 0;
        while (i < size2) {
            JavaType javaType3 = typeParameters.get(i);
            JavaType javaTypeUnknownType = i < size ? typeParameters2.get(i) : unknownType();
            if (!_verifyAndResolvePlaceholders(javaType3, javaTypeUnknownType) && !javaType3.hasRawClass(Object.class) && ((i != 0 || !javaType.isMapLikeType() || !javaTypeUnknownType.hasRawClass(Object.class)) && (!javaType3.isInterface() || !javaType3.isTypeOrSuperTypeOf(javaTypeUnknownType.getRawClass())))) {
                return String.format("Type parameter #%d/%d differs; can not specialize %s with %s", Integer.valueOf(i + 1), Integer.valueOf(size2), javaType3.toCanonical(), javaTypeUnknownType.toCanonical());
            }
            i++;
        }
        return null;
    }

    private boolean _verifyAndResolvePlaceholders(JavaType javaType, JavaType javaType2) {
        if (javaType2 instanceof PlaceholderForType) {
            ((PlaceholderForType) javaType2).actualType(javaType);
            return true;
        }
        if (javaType.getRawClass() != javaType2.getRawClass()) {
            return false;
        }
        List<JavaType> typeParameters = javaType.getBindings().getTypeParameters();
        List<JavaType> typeParameters2 = javaType2.getBindings().getTypeParameters();
        int size = typeParameters.size();
        for (int i = 0; i < size; i++) {
            if (!_verifyAndResolvePlaceholders(typeParameters.get(i), typeParameters2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public JavaType constructGeneralizedType(JavaType javaType, Class<?> cls) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            return javaType;
        }
        JavaType javaTypeFindSuperType = javaType.findSuperType(cls);
        if (javaTypeFindSuperType != null) {
            return javaTypeFindSuperType;
        }
        if (!cls.isAssignableFrom(rawClass)) {
            throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", cls.getName(), javaType));
        }
        throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", cls.getName(), javaType));
    }

    public JavaType constructFromCanonical(String str) throws IllegalArgumentException {
        return this._parser.parse(str);
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        JavaType javaTypeFindSuperType = javaType.findSuperType(cls);
        if (javaTypeFindSuperType == null) {
            return NO_TYPES;
        }
        return javaTypeFindSuperType.getBindings().typeParameterArray();
    }

    public JavaType constructType(Type type) {
        return _fromAny(null, type, EMPTY_BINDINGS);
    }

    public JavaType constructType(Type type, TypeBindings typeBindings) {
        return _fromAny(null, type, typeBindings);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return constructCollectionType(cls, _fromClass(null, cls2, EMPTY_BINDINGS));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, JavaType javaType) {
        TypeBindings typeBindingsCreateIfNeeded = TypeBindings.createIfNeeded(cls, javaType);
        CollectionType collectionType = (CollectionType) _fromClass(null, cls, typeBindingsCreateIfNeeded);
        if (typeBindingsCreateIfNeeded.isEmpty() && javaType != null) {
            JavaType contentType = collectionType.findSuperType(Collection.class).getContentType();
            if (!contentType.equals(javaType)) {
                throw new IllegalArgumentException(String.format("Non-generic Collection class %s did not resolve to something with element type %s but %s ", ClassUtil.nameOf(cls), javaType, contentType));
            }
        }
        return collectionType;
    }

    public MapType constructMapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        JavaType javaType_fromClass;
        JavaType javaType_fromClass2;
        if (cls == Properties.class) {
            javaType_fromClass = CORE_TYPE_STRING;
            javaType_fromClass2 = javaType_fromClass;
        } else {
            javaType_fromClass = _fromClass(null, cls2, EMPTY_BINDINGS);
            javaType_fromClass2 = _fromClass(null, cls3, EMPTY_BINDINGS);
        }
        return constructMapType(cls, javaType_fromClass, javaType_fromClass2);
    }

    public MapType constructMapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        TypeBindings typeBindingsCreateIfNeeded = TypeBindings.createIfNeeded(cls, new JavaType[]{javaType, javaType2});
        MapType mapType = (MapType) _fromClass(null, cls, typeBindingsCreateIfNeeded);
        if (typeBindingsCreateIfNeeded.isEmpty()) {
            JavaType javaTypeFindSuperType = mapType.findSuperType(Map.class);
            JavaType keyType = javaTypeFindSuperType.getKeyType();
            if (!keyType.equals(javaType)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with key type %s but %s ", ClassUtil.nameOf(cls), javaType, keyType));
            }
            JavaType contentType = javaTypeFindSuperType.getContentType();
            if (!contentType.equals(javaType2)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with value type %s but %s ", ClassUtil.nameOf(cls), javaType2, contentType));
            }
        }
        return mapType;
    }

    @Deprecated
    public JavaType uncheckedSimpleType(Class<?> cls) {
        return _constructSimple(cls, EMPTY_BINDINGS, null, null);
    }

    private JavaType _mapType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType_unknownType;
        JavaType javaType2;
        JavaType javaType3;
        if (cls == Properties.class) {
            javaType_unknownType = CORE_TYPE_STRING;
        } else {
            List<JavaType> typeParameters = typeBindings.getTypeParameters();
            int size = typeParameters.size();
            if (size == 0) {
                javaType_unknownType = _unknownType();
            } else if (size == 2) {
                javaType2 = typeParameters.get(0);
                javaType3 = typeParameters.get(1);
            } else {
                throw new IllegalArgumentException("Strange Map type " + cls.getName() + ": cannot determine type parameters");
            }
            return MapType.construct(cls, typeBindings, javaType, javaTypeArr, javaType2, javaType3);
        }
        javaType2 = javaType_unknownType;
        javaType3 = javaType2;
        return MapType.construct(cls, typeBindings, javaType, javaTypeArr, javaType2, javaType3);
    }

    private JavaType _collectionType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType_unknownType;
        List<JavaType> typeParameters = typeBindings.getTypeParameters();
        if (typeParameters.isEmpty()) {
            javaType_unknownType = _unknownType();
        } else if (typeParameters.size() == 1) {
            javaType_unknownType = typeParameters.get(0);
        } else {
            throw new IllegalArgumentException("Strange Collection type " + cls.getName() + ": cannot determine type parameters");
        }
        return CollectionType.construct(cls, typeBindings, javaType, javaTypeArr, javaType_unknownType);
    }

    private JavaType _referenceType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType_unknownType;
        List<JavaType> typeParameters = typeBindings.getTypeParameters();
        if (typeParameters.isEmpty()) {
            javaType_unknownType = _unknownType();
        } else if (typeParameters.size() == 1) {
            javaType_unknownType = typeParameters.get(0);
        } else {
            throw new IllegalArgumentException("Strange Reference type " + cls.getName() + ": cannot determine type parameters");
        }
        return ReferenceType.construct(cls, typeBindings, javaType, javaTypeArr, javaType_unknownType);
    }

    protected JavaType _constructSimple(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType_findWellKnownSimple;
        return (!typeBindings.isEmpty() || (javaType_findWellKnownSimple = _findWellKnownSimple(cls)) == null) ? _newSimpleType(cls, typeBindings, javaType, javaTypeArr) : javaType_findWellKnownSimple;
    }

    protected JavaType _newSimpleType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new SimpleType(cls, typeBindings, javaType, javaTypeArr);
    }

    protected JavaType _unknownType() {
        return CORE_TYPE_OBJECT;
    }

    protected JavaType _findWellKnownSimple(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == CLS_BOOL) {
                return CORE_TYPE_BOOL;
            }
            if (cls == CLS_INT) {
                return CORE_TYPE_INT;
            }
            if (cls == CLS_LONG) {
                return CORE_TYPE_LONG;
            }
            return null;
        }
        if (cls == CLS_STRING) {
            return CORE_TYPE_STRING;
        }
        if (cls == CLS_OBJECT) {
            return CORE_TYPE_OBJECT;
        }
        if (cls == CLS_JSON_NODE) {
            return CORE_TYPE_JSON_NODE;
        }
        return null;
    }

    protected JavaType _fromAny(ClassStack classStack, Type type, TypeBindings typeBindings) {
        JavaType javaType_fromWildcard;
        if (type instanceof Class) {
            javaType_fromWildcard = _fromClass(classStack, (Class) type, EMPTY_BINDINGS);
        } else if (type instanceof ParameterizedType) {
            javaType_fromWildcard = _fromParamType(classStack, (ParameterizedType) type, typeBindings);
        } else {
            if (type instanceof JavaType) {
                return (JavaType) type;
            }
            if (type instanceof GenericArrayType) {
                javaType_fromWildcard = _fromArrayType(classStack, (GenericArrayType) type, typeBindings);
            } else if (type instanceof TypeVariable) {
                javaType_fromWildcard = _fromVariable(classStack, (TypeVariable) type, typeBindings);
            } else if (type instanceof WildcardType) {
                javaType_fromWildcard = _fromWildcard(classStack, (WildcardType) type, typeBindings);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unrecognized Type: ");
                sb.append(type == null ? "[null]" : type.toString());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        if (this._modifiers != null) {
            TypeBindings bindings = javaType_fromWildcard.getBindings();
            if (bindings == null) {
                bindings = EMPTY_BINDINGS;
            }
            TypeModifier[] typeModifierArr = this._modifiers;
            int length = typeModifierArr.length;
            int i = 0;
            while (i < length) {
                TypeModifier typeModifier = typeModifierArr[i];
                JavaType javaTypeModifyType = typeModifier.modifyType(javaType_fromWildcard, type, bindings, this);
                if (javaTypeModifyType == null) {
                    throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", typeModifier, typeModifier.getClass().getName(), javaType_fromWildcard));
                }
                i++;
                javaType_fromWildcard = javaTypeModifyType;
            }
        }
        return javaType_fromWildcard;
    }

    protected JavaType _fromClass(ClassStack classStack, Class<?> cls, TypeBindings typeBindings) {
        ClassStack classStackChild;
        JavaType javaType_resolveSuperClass;
        JavaType[] javaTypeArr_resolveSuperInterfaces;
        JavaType javaType_newSimpleType;
        JavaType javaType_findWellKnownSimple = _findWellKnownSimple(cls);
        if (javaType_findWellKnownSimple != null) {
            return javaType_findWellKnownSimple;
        }
        Object objAsKey = (typeBindings == null || typeBindings.isEmpty()) ? cls : typeBindings.asKey(cls);
        JavaType javaTypeRefine = this._typeCache.get(objAsKey);
        if (javaTypeRefine != null) {
            return javaTypeRefine;
        }
        if (classStack == null) {
            classStackChild = new ClassStack(cls);
        } else {
            ClassStack classStackFind = classStack.find(cls);
            if (classStackFind != null) {
                ResolvedRecursiveType resolvedRecursiveType = new ResolvedRecursiveType(cls, EMPTY_BINDINGS);
                classStackFind.addSelfReference(resolvedRecursiveType);
                return resolvedRecursiveType;
            }
            classStackChild = classStack.child(cls);
        }
        if (cls.isArray()) {
            javaType_newSimpleType = ArrayType.construct(_fromAny(classStackChild, cls.getComponentType(), typeBindings), typeBindings);
        } else {
            if (cls.isInterface()) {
                javaType_resolveSuperClass = null;
                javaTypeArr_resolveSuperInterfaces = _resolveSuperInterfaces(classStackChild, cls, typeBindings);
            } else {
                javaType_resolveSuperClass = _resolveSuperClass(classStackChild, cls, typeBindings);
                javaTypeArr_resolveSuperInterfaces = _resolveSuperInterfaces(classStackChild, cls, typeBindings);
            }
            JavaType javaType = javaType_resolveSuperClass;
            JavaType[] javaTypeArr = javaTypeArr_resolveSuperInterfaces;
            if (cls == Properties.class) {
                SimpleType simpleType = CORE_TYPE_STRING;
                javaTypeRefine = MapType.construct(cls, typeBindings, javaType, javaTypeArr, simpleType, simpleType);
            } else if (javaType != null) {
                javaTypeRefine = javaType.refine(cls, typeBindings, javaType, javaTypeArr);
            }
            javaType_newSimpleType = (javaTypeRefine == null && (javaTypeRefine = _fromWellKnownClass(classStackChild, cls, typeBindings, javaType, javaTypeArr)) == null && (javaTypeRefine = _fromWellKnownInterface(classStackChild, cls, typeBindings, javaType, javaTypeArr)) == null) ? _newSimpleType(cls, typeBindings, javaType, javaTypeArr) : javaTypeRefine;
        }
        classStackChild.resolveSelfReferences(javaType_newSimpleType);
        if (!javaType_newSimpleType.hasHandlers()) {
            this._typeCache.putIfAbsent(objAsKey, javaType_newSimpleType);
        }
        return javaType_newSimpleType;
    }

    protected JavaType _resolveSuperClass(ClassStack classStack, Class<?> cls, TypeBindings typeBindings) {
        Type genericSuperclass = ClassUtil.getGenericSuperclass(cls);
        if (genericSuperclass == null) {
            return null;
        }
        return _fromAny(classStack, genericSuperclass, typeBindings);
    }

    protected JavaType[] _resolveSuperInterfaces(ClassStack classStack, Class<?> cls, TypeBindings typeBindings) {
        Type[] genericInterfaces = ClassUtil.getGenericInterfaces(cls);
        if (genericInterfaces == null || genericInterfaces.length == 0) {
            return NO_TYPES;
        }
        int length = genericInterfaces.length;
        JavaType[] javaTypeArr = new JavaType[length];
        for (int i = 0; i < length; i++) {
            javaTypeArr[i] = _fromAny(classStack, genericInterfaces[i], typeBindings);
        }
        return javaTypeArr;
    }

    protected JavaType _fromWellKnownClass(ClassStack classStack, Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        if (typeBindings == null) {
            typeBindings = EMPTY_BINDINGS;
        }
        if (cls == Map.class) {
            return _mapType(cls, typeBindings, javaType, javaTypeArr);
        }
        if (cls == Collection.class) {
            return _collectionType(cls, typeBindings, javaType, javaTypeArr);
        }
        if (cls == AtomicReference.class) {
            return _referenceType(cls, typeBindings, javaType, javaTypeArr);
        }
        return null;
    }

    protected JavaType _fromWellKnownInterface(ClassStack classStack, Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        for (JavaType javaType2 : javaTypeArr) {
            JavaType javaTypeRefine = javaType2.refine(cls, typeBindings, javaType, javaTypeArr);
            if (javaTypeRefine != null) {
                return javaTypeRefine;
            }
        }
        return null;
    }

    protected JavaType _fromParamType(ClassStack classStack, ParameterizedType parameterizedType, TypeBindings typeBindings) {
        TypeBindings typeBindingsCreate;
        Class<?> cls = (Class) parameterizedType.getRawType();
        if (cls == CLS_ENUM) {
            return CORE_TYPE_ENUM;
        }
        if (cls == CLS_COMPARABLE) {
            return CORE_TYPE_COMPARABLE;
        }
        if (cls == CLS_CLASS) {
            return CORE_TYPE_CLASS;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        if (length == 0) {
            typeBindingsCreate = EMPTY_BINDINGS;
        } else {
            JavaType[] javaTypeArr = new JavaType[length];
            for (int i = 0; i < length; i++) {
                javaTypeArr[i] = _fromAny(classStack, actualTypeArguments[i], typeBindings);
            }
            typeBindingsCreate = TypeBindings.create(cls, javaTypeArr);
        }
        return _fromClass(classStack, cls, typeBindingsCreate);
    }

    protected JavaType _fromArrayType(ClassStack classStack, GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_fromAny(classStack, genericArrayType.getGenericComponentType(), typeBindings), typeBindings);
    }

    protected JavaType _fromVariable(ClassStack classStack, TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        Type[] bounds;
        String name = typeVariable.getName();
        if (typeBindings == null) {
            throw new IllegalArgumentException("Null `bindings` passed (type variable \"" + name + "\")");
        }
        JavaType javaTypeFindBoundType = typeBindings.findBoundType(name);
        if (javaTypeFindBoundType != null) {
            return javaTypeFindBoundType;
        }
        if (typeBindings.hasUnbound(name)) {
            return CORE_TYPE_OBJECT;
        }
        TypeBindings typeBindingsWithUnboundVariable = typeBindings.withUnboundVariable(name);
        synchronized (typeVariable) {
            bounds = typeVariable.getBounds();
        }
        return _fromAny(classStack, bounds[0], typeBindingsWithUnboundVariable);
    }

    protected JavaType _fromWildcard(ClassStack classStack, WildcardType wildcardType, TypeBindings typeBindings) {
        return _fromAny(classStack, wildcardType.getUpperBounds()[0], typeBindings);
    }
}
