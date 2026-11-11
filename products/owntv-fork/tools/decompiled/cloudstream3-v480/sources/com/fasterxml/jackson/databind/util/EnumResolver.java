package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class EnumResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Enum<?> _defaultValue;
    protected final Class<Enum<?>> _enumClass;
    protected final Enum<?>[] _enums;
    protected final HashMap<String, Enum<?>> _enumsById;
    protected final boolean _isFromIntValue;
    protected final boolean _isIgnoreCase;

    /* JADX WARN: Multi-variable type inference failed */
    protected static Class<Enum<?>> _enumClass(Class<?> cls) {
        return cls;
    }

    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> map, Enum<?> r4, boolean z, boolean z2) {
        this._enumClass = cls;
        this._enums = enumArr;
        this._enumsById = map;
        this._defaultValue = r4;
        this._isIgnoreCase = z;
        this._isFromIntValue = z2;
    }

    @Deprecated
    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> map, Enum<?> r11, boolean z) {
        this(cls, enumArr, map, r11, z, false);
    }

    public static EnumResolver constructFor(DeserializationConfig deserializationConfig, Class<?> cls) {
        return _constructFor(cls, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    protected static EnumResolver _constructFor(Class<?> cls, AnnotationIntrospector annotationIntrospector, boolean z) {
        Class<Enum<?>> cls_enumClass = _enumClass(cls);
        Enum<?>[] enumArr_enumConstants = _enumConstants(cls);
        String[] strArrFindEnumValues = annotationIntrospector.findEnumValues(cls_enumClass, enumArr_enumConstants, new String[enumArr_enumConstants.length]);
        String[][] strArr = new String[strArrFindEnumValues.length][];
        annotationIntrospector.findEnumAliases(cls_enumClass, enumArr_enumConstants, strArr);
        HashMap map = new HashMap();
        int length = enumArr_enumConstants.length;
        for (int i = 0; i < length; i++) {
            Enum<?> r7 = enumArr_enumConstants[i];
            String strName = strArrFindEnumValues[i];
            if (strName == null) {
                strName = r7.name();
            }
            map.put(strName, r7);
            String[] strArr2 = strArr[i];
            if (strArr2 != null) {
                for (String str : strArr2) {
                    if (!map.containsKey(str)) {
                        map.put(str, r7);
                    }
                }
            }
        }
        return new EnumResolver(cls_enumClass, enumArr_enumConstants, map, _enumDefault(annotationIntrospector, cls_enumClass), z, false);
    }

    public static EnumResolver constructUsingToString(DeserializationConfig deserializationConfig, Class<?> cls) {
        return _constructUsingToString(cls, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    protected static EnumResolver _constructUsingToString(Class<?> cls, AnnotationIntrospector annotationIntrospector, boolean z) {
        Class<Enum<?>> cls_enumClass = _enumClass(cls);
        Enum<?>[] enumArr_enumConstants = _enumConstants(cls);
        HashMap map = new HashMap();
        String[][] strArr = new String[enumArr_enumConstants.length][];
        if (annotationIntrospector != null) {
            annotationIntrospector.findEnumAliases(cls_enumClass, enumArr_enumConstants, strArr);
        }
        int length = enumArr_enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r4 = enumArr_enumConstants[length];
                map.put(r4.toString(), r4);
                String[] strArr2 = strArr[length];
                if (strArr2 != null) {
                    for (String str : strArr2) {
                        if (!map.containsKey(str)) {
                            map.put(str, r4);
                        }
                    }
                }
            } else {
                return new EnumResolver(cls_enumClass, enumArr_enumConstants, map, _enumDefault(annotationIntrospector, cls_enumClass), z, false);
            }
        }
    }

    public static EnumResolver constructUsingMethod(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMember annotatedMember) {
        return _constructUsingMethod(cls, annotatedMember, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    protected static EnumResolver _constructUsingMethod(Class<?> cls, AnnotatedMember annotatedMember, AnnotationIntrospector annotationIntrospector, boolean z) {
        Class<Enum<?>> cls_enumClass = _enumClass(cls);
        Enum<?>[] enumArr_enumConstants = _enumConstants(cls);
        HashMap map = new HashMap();
        int length = enumArr_enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r4 = enumArr_enumConstants[length];
                try {
                    Object value = annotatedMember.getValue(r4);
                    if (value != null) {
                        map.put(value.toString(), r4);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + r4 + ": " + e.getMessage());
                }
            } else {
                return new EnumResolver(cls_enumClass, enumArr_enumConstants, map, _enumDefault(annotationIntrospector, cls_enumClass), z, _isIntType(annotatedMember.getRawType()));
            }
        }
    }

    public CompactStringObjectMap constructLookup() {
        return CompactStringObjectMap.construct(this._enumsById);
    }

    protected static Enum<?>[] _enumConstants(Class<?> cls) {
        Enum<?>[] enumConstants = _enumClass(cls).getEnumConstants();
        if (enumConstants != null) {
            return enumConstants;
        }
        throw new IllegalArgumentException("No enum constants for class " + cls.getName());
    }

    protected static Enum<?> _enumDefault(AnnotationIntrospector annotationIntrospector, Class<?> cls) {
        if (annotationIntrospector != null) {
            return annotationIntrospector.findDefaultEnumValue(_enumClass(cls));
        }
        return null;
    }

    protected static boolean _isIntType(Class<?> cls) {
        if (cls.isPrimitive()) {
            cls = ClassUtil.wrapperType(cls);
        }
        return cls == Long.class || cls == Integer.class || cls == Short.class || cls == Byte.class;
    }

    @Deprecated
    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> map, Enum<?> r11) {
        this(cls, enumArr, map, r11, false, false);
    }

    @Deprecated
    public static EnumResolver constructFor(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        return _constructFor(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return _constructFor(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUsingToString(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        return _constructUsingToString(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUnsafeUsingToString(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return _constructUsingToString(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUsingToString(Class<Enum<?>> cls) {
        return _constructUsingToString(cls, null, false);
    }

    @Deprecated
    public static EnumResolver constructUsingMethod(Class<Enum<?>> cls, AnnotatedMember annotatedMember, AnnotationIntrospector annotationIntrospector) {
        return _constructUsingMethod(cls, annotatedMember, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUnsafeUsingMethod(Class<?> cls, AnnotatedMember annotatedMember, AnnotationIntrospector annotationIntrospector) {
        return _constructUsingMethod(cls, annotatedMember, annotationIntrospector, false);
    }

    public Enum<?> findEnum(String str) {
        Enum<?> r0 = this._enumsById.get(str);
        return (r0 == null && this._isIgnoreCase) ? _findEnumCaseInsensitive(str) : r0;
    }

    protected Enum<?> _findEnumCaseInsensitive(String str) {
        for (Map.Entry<String, Enum<?>> entry : this._enumsById.entrySet()) {
            if (str.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Enum<?> getEnum(int i) {
        if (i < 0) {
            return null;
        }
        Enum<?>[] enumArr = this._enums;
        if (i >= enumArr.length) {
            return null;
        }
        return enumArr[i];
    }

    public Enum<?> getDefaultValue() {
        return this._defaultValue;
    }

    public Enum<?>[] getRawEnums() {
        return this._enums;
    }

    public List<Enum<?>> getEnums() {
        ArrayList arrayList = new ArrayList(this._enums.length);
        for (Enum<?> r0 : this._enums) {
            arrayList.add(r0);
        }
        return arrayList;
    }

    public Collection<String> getEnumIds() {
        return this._enumsById.keySet();
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }

    public boolean isFromIntValue() {
        return this._isFromIntValue;
    }
}
