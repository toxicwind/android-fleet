package com.google.firebase.firestore.util;

import b.a.a.a.a;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.ThrowOnExtraProperties;
import com.hisense.support.tv.HisenseCountryLanInfo;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public class CustomClassMapper {
    public static final int MAX_DEPTH = 500;
    public static final ConcurrentMap<Class<?>, BeanMapper<?>> mappers = new ConcurrentHashMap();

    private static class BeanMapper<T> {
        public final Class<T> clazz;
        public final Constructor<T> constructor;
        public final boolean throwOnUnknownProperties;
        public final boolean warnOnUnknownProperties;
        public final Map<String, String> properties = new HashMap();
        public final Map<String, Method> setters = new HashMap();
        public final Map<String, Method> getters = new HashMap();
        public final Map<String, Field> fields = new HashMap();
        public final HashSet<String> serverTimestamps = new HashSet<>();
        public final HashSet<String> documentIdPropertyNames = new HashSet<>();

        public BeanMapper(Class<T> cls) {
            Constructor<T> declaredConstructor;
            this.clazz = cls;
            this.throwOnUnknownProperties = cls.isAnnotationPresent(ThrowOnExtraProperties.class);
            this.warnOnUnknownProperties = !cls.isAnnotationPresent(IgnoreExtraProperties.class);
            try {
                declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                declaredConstructor = null;
            }
            this.constructor = declaredConstructor;
            for (Method method : cls.getMethods()) {
                if (shouldIncludeGetter(method)) {
                    String strPropertyName = propertyName(method);
                    addProperty(strPropertyName);
                    method.setAccessible(true);
                    if (this.getters.containsKey(strPropertyName)) {
                        StringBuilder sbA = a.a("Found conflicting getters for name ");
                        sbA.append(method.getName());
                        sbA.append(" on class ");
                        sbA.append(cls.getName());
                        throw new RuntimeException(sbA.toString());
                    }
                    this.getters.put(strPropertyName, method);
                    applyGetterAnnotations(method);
                }
            }
            for (Field field : cls.getFields()) {
                if (shouldIncludeField(field)) {
                    addProperty(propertyName(field));
                    applyFieldAnnotations(field);
                }
            }
            Class<T> superclass = cls;
            do {
                for (Method method2 : superclass.getDeclaredMethods()) {
                    if (shouldIncludeSetter(method2)) {
                        String strPropertyName2 = propertyName(method2);
                        String str = this.properties.get(strPropertyName2.toLowerCase(Locale.US));
                        if (str == null) {
                            continue;
                        } else {
                            if (!str.equals(strPropertyName2)) {
                                StringBuilder sbA2 = a.a("Found setter on ");
                                sbA2.append(superclass.getName());
                                sbA2.append(" with invalid case-sensitive name: ");
                                sbA2.append(method2.getName());
                                throw new RuntimeException(sbA2.toString());
                            }
                            Method method3 = this.setters.get(strPropertyName2);
                            if (method3 == null) {
                                method2.setAccessible(true);
                                this.setters.put(strPropertyName2, method2);
                                applySetterAnnotations(method2);
                            } else if (!isSetterOverride(method2, method3)) {
                                if (superclass == cls) {
                                    StringBuilder sbA3 = a.a("Class ");
                                    sbA3.append(cls.getName());
                                    sbA3.append(" has multiple setter overloads with name ");
                                    sbA3.append(method2.getName());
                                    throw new RuntimeException(sbA3.toString());
                                }
                                StringBuilder sbA4 = a.a("Found conflicting setters with name: ");
                                sbA4.append(method2.getName());
                                sbA4.append(" (conflicts with ");
                                sbA4.append(method3.getName());
                                sbA4.append(" defined on ");
                                sbA4.append(method3.getDeclaringClass().getName());
                                sbA4.append(")");
                                throw new RuntimeException(sbA4.toString());
                            }
                        }
                    }
                }
                for (Field field2 : superclass.getDeclaredFields()) {
                    String strPropertyName3 = propertyName(field2);
                    if (this.properties.containsKey(strPropertyName3.toLowerCase(Locale.US)) && !this.fields.containsKey(strPropertyName3)) {
                        field2.setAccessible(true);
                        this.fields.put(strPropertyName3, field2);
                        applyFieldAnnotations(field2);
                    }
                }
                superclass = superclass.getSuperclass();
                if (superclass == null) {
                    break;
                }
            } while (!superclass.equals(Object.class));
            if (this.properties.isEmpty()) {
                StringBuilder sbA5 = a.a("No properties to serialize found on class ");
                sbA5.append(cls.getName());
                throw new RuntimeException(sbA5.toString());
            }
            for (String str2 : this.documentIdPropertyNames) {
                if (!this.setters.containsKey(str2) && !this.fields.containsKey(str2)) {
                    StringBuilder sbB = a.b("@DocumentId is annotated on property ", str2, " of class ");
                    sbB.append(cls.getName());
                    sbB.append(" but no field or public setter was found");
                    throw new RuntimeException(sbB.toString());
                }
            }
        }

        private void addProperty(String str) {
            String strPut = this.properties.put(str.toLowerCase(Locale.US), str);
            if (strPut == null || str.equals(strPut)) {
                return;
            }
            StringBuilder sbA = a.a("Found two getters or fields with conflicting case sensitivity for property: ");
            sbA.append(str.toLowerCase(Locale.US));
            throw new RuntimeException(sbA.toString());
        }

        public static String annotatedName(AccessibleObject accessibleObject) {
            if (accessibleObject.isAnnotationPresent(PropertyName.class)) {
                return ((PropertyName) accessibleObject.getAnnotation(PropertyName.class)).value();
            }
            return null;
        }

        private void applyFieldAnnotations(Field field) {
            if (field.isAnnotationPresent(ServerTimestamp.class)) {
                Class<?> type = field.getType();
                if (type != Date.class && type != Timestamp.class) {
                    StringBuilder sbA = a.a("Field ");
                    sbA.append(field.getName());
                    sbA.append(" is annotated with @ServerTimestamp but is ");
                    sbA.append(type);
                    sbA.append(" instead of Date or Timestamp.");
                    throw new IllegalArgumentException(sbA.toString());
                }
                this.serverTimestamps.add(propertyName(field));
            }
            if (field.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Field", HisenseCountryLanInfo.LOCALE_ISL_CODE, field.getType());
                this.documentIdPropertyNames.add(propertyName(field));
            }
        }

        private void applyGetterAnnotations(Method method) {
            if (method.isAnnotationPresent(ServerTimestamp.class)) {
                Class<?> returnType = method.getReturnType();
                if (returnType != Date.class && returnType != Timestamp.class) {
                    StringBuilder sbA = a.a("Method ");
                    sbA.append(method.getName());
                    sbA.append(" is annotated with @ServerTimestamp but returns ");
                    sbA.append(returnType);
                    sbA.append(" instead of Date or Timestamp.");
                    throw new IllegalArgumentException(sbA.toString());
                }
                this.serverTimestamps.add(propertyName(method));
            }
            if (method.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Method", "returns", method.getReturnType());
                this.documentIdPropertyNames.add(propertyName(method));
            }
        }

        private void applySetterAnnotations(Method method) {
            if (method.isAnnotationPresent(ServerTimestamp.class)) {
                StringBuilder sbA = a.a("Method ");
                sbA.append(method.getName());
                sbA.append(" is annotated with @ServerTimestamp but should not be. @ServerTimestamp can only be applied to fields and getters, not setters.");
                throw new IllegalArgumentException(sbA.toString());
            }
            if (method.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Method", "accepts", method.getParameterTypes()[0]);
                this.documentIdPropertyNames.add(propertyName(method));
            }
        }

        private void ensureValidDocumentIdType(String str, String str2, Type type) {
            if (type == String.class || type == DocumentReference.class) {
                return;
            }
            throw new IllegalArgumentException(str + " is annotated with @DocumentId but " + str2 + " " + type + " instead of String or DocumentReference.");
        }

        public static boolean isSetterOverride(Method method, Method method2) {
            CustomClassMapper.hardAssert(method.getDeclaringClass().isAssignableFrom(method2.getDeclaringClass()), "Expected override from a base class");
            CustomClassMapper.hardAssert(method.getReturnType().equals(Void.TYPE), "Expected void return type");
            CustomClassMapper.hardAssert(method2.getReturnType().equals(Void.TYPE), "Expected void return type");
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?>[] parameterTypes2 = method2.getParameterTypes();
            CustomClassMapper.hardAssert(parameterTypes.length == 1, "Expected exactly one parameter");
            CustomClassMapper.hardAssert(parameterTypes2.length == 1, "Expected exactly one parameter");
            return method.getName().equals(method2.getName()) && parameterTypes[0].equals(parameterTypes2[0]);
        }

        private void populateDocumentIdProperties(Map<TypeVariable<Class<T>>, Type> map, DeserializeContext deserializeContext, T t, HashSet<String> hashSet) {
            for (String str : this.documentIdPropertyNames) {
                if (hashSet.contains(str)) {
                    StringBuilder sbB = a.b("'", str, "' was found from document ");
                    sbB.append(deserializeContext.documentRef.getPath());
                    sbB.append(", cannot apply @DocumentId on this property for class ");
                    sbB.append(this.clazz.getName());
                    throw new RuntimeException(sbB.toString());
                }
                ErrorPath errorPathChild = deserializeContext.errorPath.child(str);
                if (this.setters.containsKey(str)) {
                    Method method = this.setters.get(str);
                    Type[] genericParameterTypes = method.getGenericParameterTypes();
                    if (genericParameterTypes.length != 1) {
                        throw CustomClassMapper.deserializeError(errorPathChild, "Setter does not have exactly one parameter");
                    }
                    if (resolveType(genericParameterTypes[0], map) == String.class) {
                        ApiUtil.invoke(method, t, deserializeContext.documentRef.getId());
                    } else {
                        ApiUtil.invoke(method, t, deserializeContext.documentRef);
                    }
                } else {
                    Field field = this.fields.get(str);
                    try {
                        if (field.getType() == String.class) {
                            field.set(t, deserializeContext.documentRef.getId());
                        } else {
                            field.set(t, deserializeContext.documentRef);
                        }
                    } catch (IllegalAccessException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        }

        public static String propertyName(Field field) {
            String strAnnotatedName = annotatedName(field);
            return strAnnotatedName != null ? strAnnotatedName : field.getName();
        }

        private Type resolveType(Type type, Map<TypeVariable<Class<T>>, Type> map) {
            if (!(type instanceof TypeVariable)) {
                return type;
            }
            Type type2 = map.get(type);
            if (type2 != null) {
                return type2;
            }
            throw new IllegalStateException(a.a("Could not resolve type ", type));
        }

        public static String serializedName(String str) {
            String str2 = null;
            for (String str3 : new String[]{"get", "set", HisenseCountryLanInfo.LOCALE_ISL_CODE}) {
                if (str.startsWith(str3)) {
                    str2 = str3;
                }
            }
            if (str2 == null) {
                throw new IllegalArgumentException(a.a("Unknown Bean prefix for method: ", str));
            }
            char[] charArray = str.substring(str2.length()).toCharArray();
            for (int i = 0; i < charArray.length && Character.isUpperCase(charArray[i]); i++) {
                charArray[i] = Character.toLowerCase(charArray[i]);
            }
            return new String(charArray);
        }

        public static boolean shouldIncludeField(Field field) {
            return (field.getDeclaringClass().equals(Object.class) || !Modifier.isPublic(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers()) || field.isAnnotationPresent(Exclude.class)) ? false : true;
        }

        public static boolean shouldIncludeGetter(Method method) {
            return ((!method.getName().startsWith("get") && !method.getName().startsWith(HisenseCountryLanInfo.LOCALE_ISL_CODE)) || method.getDeclaringClass().equals(Object.class) || !Modifier.isPublic(method.getModifiers()) || Modifier.isStatic(method.getModifiers()) || method.getReturnType().equals(Void.TYPE) || method.getParameterTypes().length != 0 || method.isAnnotationPresent(Exclude.class)) ? false : true;
        }

        public static boolean shouldIncludeSetter(Method method) {
            return method.getName().startsWith("set") && !method.getDeclaringClass().equals(Object.class) && !Modifier.isStatic(method.getModifiers()) && method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 1 && !method.isAnnotationPresent(Exclude.class);
        }

        public T deserialize(Map<String, Object> map, DeserializeContext deserializeContext) {
            return deserialize(map, Collections.emptyMap(), deserializeContext);
        }

        public Map<String, Object> serialize(T t, ErrorPath errorPath) {
            Object objInvoke;
            if (!this.clazz.isAssignableFrom(t.getClass())) {
                StringBuilder sbA = a.a("Can't serialize object of class ");
                sbA.append(t.getClass());
                sbA.append(" with BeanMapper for class ");
                sbA.append(this.clazz);
                throw new IllegalArgumentException(sbA.toString());
            }
            HashMap map = new HashMap();
            for (String str : this.properties.values()) {
                if (!this.documentIdPropertyNames.contains(str)) {
                    if (this.getters.containsKey(str)) {
                        objInvoke = ApiUtil.invoke(this.getters.get(str), t, new Object[0]);
                    } else {
                        Field field = this.fields.get(str);
                        if (field == null) {
                            throw new IllegalStateException(a.a("Bean property without field or getter: ", str));
                        }
                        try {
                            objInvoke = field.get(t);
                        } catch (IllegalAccessException e2) {
                            throw new RuntimeException(e2);
                        }
                    }
                    map.put(str, (this.serverTimestamps.contains(str) && objInvoke == null) ? FieldValue.SERVER_TIMESTAMP_INSTANCE : CustomClassMapper.serialize(objInvoke, errorPath.child(str)));
                }
            }
            return map;
        }

        public T deserialize(Map<String, Object> map, Map<TypeVariable<Class<T>>, Type> map2, DeserializeContext deserializeContext) {
            Constructor<T> constructor = this.constructor;
            if (constructor == null) {
                ErrorPath errorPath = deserializeContext.errorPath;
                StringBuilder sbA = a.a("Class ");
                sbA.append(this.clazz.getName());
                sbA.append(" does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped");
                throw CustomClassMapper.deserializeError(errorPath, sbA.toString());
            }
            T t = (T) ApiUtil.newInstance(constructor);
            HashSet<String> hashSet = new HashSet<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                ErrorPath errorPathChild = deserializeContext.errorPath.child(key);
                if (this.setters.containsKey(key)) {
                    Method method = this.setters.get(key);
                    Type[] genericParameterTypes = method.getGenericParameterTypes();
                    if (genericParameterTypes.length != 1) {
                        throw CustomClassMapper.deserializeError(errorPathChild, "Setter does not have exactly one parameter");
                    }
                    ApiUtil.invoke(method, t, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(genericParameterTypes[0], map2), deserializeContext.newInstanceWithErrorPath(errorPathChild)));
                    hashSet.add(key);
                } else if (this.fields.containsKey(key)) {
                    Field field = this.fields.get(key);
                    try {
                        field.set(t, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(field.getGenericType(), map2), deserializeContext.newInstanceWithErrorPath(errorPathChild)));
                        hashSet.add(key);
                    } catch (IllegalAccessException e2) {
                        throw new RuntimeException(e2);
                    }
                } else {
                    StringBuilder sbB = a.b("No setter/field for ", key, " found on class ");
                    sbB.append(this.clazz.getName());
                    String string = sbB.toString();
                    if (this.properties.containsKey(key.toLowerCase(Locale.US))) {
                        string = a.a(string, " (fields/setters are case sensitive!)");
                    }
                    if (this.throwOnUnknownProperties) {
                        throw new RuntimeException(string);
                    }
                    if (this.warnOnUnknownProperties) {
                        Logger.warn(CustomClassMapper.class.getSimpleName(), "%s", string);
                    }
                }
            }
            populateDocumentIdProperties(map2, deserializeContext, t, hashSet);
            return t;
        }

        public static String propertyName(Method method) {
            String strAnnotatedName = annotatedName(method);
            return strAnnotatedName != null ? strAnnotatedName : serializedName(method.getName());
        }
    }

    static class DeserializeContext {
        public final DocumentReference documentRef;
        public final ErrorPath errorPath;

        public DeserializeContext(ErrorPath errorPath, DocumentReference documentReference) {
            this.errorPath = errorPath;
            this.documentRef = documentReference;
        }

        public DeserializeContext newInstanceWithErrorPath(ErrorPath errorPath) {
            return new DeserializeContext(errorPath, this.documentRef);
        }
    }

    static class ErrorPath {
        public static final ErrorPath EMPTY = new ErrorPath(null, null, 0);
        public final int length;
        public final String name;
        public final ErrorPath parent;

        public ErrorPath(ErrorPath errorPath, String str, int i) {
            this.parent = errorPath;
            this.name = str;
            this.length = i;
        }

        public ErrorPath child(String str) {
            return new ErrorPath(this, str, this.length + 1);
        }

        public int getLength() {
            return this.length;
        }

        public String toString() {
            int i = this.length;
            if (i == 0) {
                return "";
            }
            if (i == 1) {
                return this.name;
            }
            return this.parent.toString() + "." + this.name;
        }
    }

    public static <T> T convertBean(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        BeanMapper beanMapperLoadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
        if (obj instanceof Map) {
            return (T) beanMapperLoadOrCreateBeanMapperForClass.deserialize(expectMap(obj, deserializeContext), deserializeContext);
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Can't convert object of type ");
        sbA.append(obj.getClass().getName());
        sbA.append(" to type ");
        sbA.append(cls.getName());
        throw deserializeError(errorPath, sbA.toString());
    }

    public static Blob convertBlob(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Blob) {
            return (Blob) obj;
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Failed to convert value of type ");
        sbA.append(obj.getClass().getName());
        sbA.append(" to Blob");
        throw deserializeError(errorPath, sbA.toString());
    }

    public static Boolean convertBoolean(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Failed to convert value of type ");
        sbA.append(obj.getClass().getName());
        sbA.append(" to boolean");
        throw deserializeError(errorPath, sbA.toString());
    }

    public static Date convertDate(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Timestamp) {
            return ((Timestamp) obj).toDate();
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Failed to convert value of type ");
        sbA.append(obj.getClass().getName());
        sbA.append(" to Date");
        throw deserializeError(errorPath, sbA.toString());
    }

    public static DocumentReference convertDocumentReference(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof DocumentReference) {
            return (DocumentReference) obj;
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Failed to convert value of type ");
        sbA.append(obj.getClass().getName());
        sbA.append(" to DocumentReference");
        throw deserializeError(errorPath, sbA.toString());
    }

    public static Double convertDouble(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (!(obj instanceof Long)) {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            ErrorPath errorPath = deserializeContext.errorPath;
            StringBuilder sbA = a.a("Failed to convert a value of type ");
            sbA.append(obj.getClass().getName());
            sbA.append(" to double");
            throw deserializeError(errorPath, sbA.toString());
        }
        Long l = (Long) obj;
        Double dValueOf = Double.valueOf(l.doubleValue());
        if (dValueOf.longValue() == l.longValue()) {
            return dValueOf;
        }
        throw deserializeError(deserializeContext.errorPath, "Loss of precision while converting number to double: " + obj + ". Did you mean to use a 64-bit long instead?");
    }

    public static GeoPoint convertGeoPoint(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof GeoPoint) {
            return (GeoPoint) obj;
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Failed to convert value of type ");
        sbA.append(obj.getClass().getName());
        sbA.append(" to GeoPoint");
        throw deserializeError(errorPath, sbA.toString());
    }

    public static Integer convertInteger(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (!(obj instanceof Long) && !(obj instanceof Double)) {
            ErrorPath errorPath = deserializeContext.errorPath;
            StringBuilder sbA = a.a("Failed to convert a value of type ");
            sbA.append(obj.getClass().getName());
            sbA.append(" to int");
            throw deserializeError(errorPath, sbA.toString());
        }
        Number number = (Number) obj;
        double dDoubleValue = number.doubleValue();
        if (dDoubleValue >= -2.147483648E9d && dDoubleValue <= 2.147483647E9d) {
            return Integer.valueOf(number.intValue());
        }
        throw deserializeError(deserializeContext.errorPath, "Numeric value out of 32-bit integer range: " + dDoubleValue + ". Did you mean to use a long or double instead of an int?");
    }

    public static Long convertLong(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).longValue());
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (!(obj instanceof Double)) {
            ErrorPath errorPath = deserializeContext.errorPath;
            StringBuilder sbA = a.a("Failed to convert a value of type ");
            sbA.append(obj.getClass().getName());
            sbA.append(" to long");
            throw deserializeError(errorPath, sbA.toString());
        }
        Double d2 = (Double) obj;
        if (d2.doubleValue() >= -9.223372036854776E18d && d2.doubleValue() <= 9.223372036854776E18d) {
            return Long.valueOf(d2.longValue());
        }
        throw deserializeError(deserializeContext.errorPath, "Numeric value out of 64-bit long range: " + d2 + ". Did you mean to use a double instead of a long?");
    }

    public static String convertString(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof String) {
            return (String) obj;
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Failed to convert value of type ");
        sbA.append(obj.getClass().getName());
        sbA.append(" to String");
        throw deserializeError(errorPath, sbA.toString());
    }

    public static Timestamp convertTimestamp(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        }
        if (obj instanceof Date) {
            return new Timestamp((Date) obj);
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Failed to convert value of type ");
        sbA.append(obj.getClass().getName());
        sbA.append(" to Timestamp");
        throw deserializeError(errorPath, sbA.toString());
    }

    public static <T> T convertToCustomClass(Object obj, Class<T> cls, DocumentReference documentReference) {
        return (T) deserializeToClass(obj, cls, new DeserializeContext(ErrorPath.EMPTY, documentReference));
    }

    public static Object convertToPlainJavaTypes(Object obj) {
        return serialize(obj);
    }

    public static RuntimeException deserializeError(ErrorPath errorPath, String str) {
        String strA = a.a("Could not deserialize object. ", str);
        if (errorPath.getLength() > 0) {
            StringBuilder sbB = a.b(strA, " (found in field '");
            sbB.append(errorPath.toString());
            sbB.append("')");
            strA = sbB.toString();
        }
        return new RuntimeException(strA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T deserializeToClass(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (obj == 0) {
            return null;
        }
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls)) {
            return (T) deserializeToPrimitive(obj, cls, deserializeContext);
        }
        if (String.class.isAssignableFrom(cls)) {
            return (T) convertString(obj, deserializeContext);
        }
        if (Date.class.isAssignableFrom(cls)) {
            return (T) convertDate(obj, deserializeContext);
        }
        if (Timestamp.class.isAssignableFrom(cls)) {
            return (T) convertTimestamp(obj, deserializeContext);
        }
        if (Blob.class.isAssignableFrom(cls)) {
            return (T) convertBlob(obj, deserializeContext);
        }
        if (GeoPoint.class.isAssignableFrom(cls)) {
            return (T) convertGeoPoint(obj, deserializeContext);
        }
        if (DocumentReference.class.isAssignableFrom(cls)) {
            return (T) convertDocumentReference(obj, deserializeContext);
        }
        if (cls.isArray()) {
            throw deserializeError(deserializeContext.errorPath, "Converting to Arrays is not supported, please use Lists instead");
        }
        if (cls.getTypeParameters().length <= 0) {
            if (cls.equals(Object.class)) {
                return obj;
            }
            return cls.isEnum() ? (T) deserializeToEnum(obj, cls, deserializeContext) : (T) convertBean(obj, cls, deserializeContext);
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Class ");
        sbA.append(cls.getName());
        sbA.append(" has generic type parameters, please use GenericTypeIndicator instead");
        throw deserializeError(errorPath, sbA.toString());
    }

    public static <T> T deserializeToEnum(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (!(obj instanceof String)) {
            throw deserializeError(deserializeContext.errorPath, "Expected a String while deserializing to enum " + cls + " but got a " + obj.getClass());
        }
        String name = (String) obj;
        for (Field field : cls.getFields()) {
            if (field.isEnumConstant() && name.equals(BeanMapper.propertyName(field))) {
                name = field.getName();
                break;
            }
        }
        try {
            return (T) Enum.valueOf(cls, name);
        } catch (IllegalArgumentException unused) {
            ErrorPath errorPath = deserializeContext.errorPath;
            StringBuilder sbA = a.a("Could not find enum value of ");
            sbA.append(cls.getName());
            sbA.append(" for value \"");
            sbA.append(name);
            sbA.append("\"");
            throw deserializeError(errorPath, sbA.toString());
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r0v7, types: [T, java.util.ArrayList, java.util.List] */
    public static <T> T deserializeToParameterizedType(Object obj, ParameterizedType parameterizedType, DeserializeContext deserializeContext) {
        Class cls = (Class) parameterizedType.getRawType();
        int i = 0;
        if (List.class.isAssignableFrom(cls)) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (!(obj instanceof List)) {
                ErrorPath errorPath = deserializeContext.errorPath;
                StringBuilder sbA = a.a("Expected a List, but got a ");
                sbA.append(obj.getClass());
                throw deserializeError(errorPath, sbA.toString());
            }
            List list = (List) obj;
            ?? r0 = (T) new ArrayList(list.size());
            while (i < list.size()) {
                r0.add(deserializeToType(list.get(i), type, deserializeContext.newInstanceWithErrorPath(deserializeContext.errorPath.child("[" + i + "]"))));
                i++;
            }
            return r0;
        }
        if (!Map.class.isAssignableFrom(cls)) {
            if (Collection.class.isAssignableFrom(cls)) {
                throw deserializeError(deserializeContext.errorPath, "Collections are not supported, please use Lists instead");
            }
            Map<String, Object> mapExpectMap = expectMap(obj, deserializeContext);
            BeanMapper beanMapperLoadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
            HashMap map = new HashMap();
            TypeVariable<Class<T>>[] typeParameters = beanMapperLoadOrCreateBeanMapperForClass.clazz.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length != typeParameters.length) {
                throw new IllegalStateException("Mismatched lengths for type variables and actual types");
            }
            while (i < typeParameters.length) {
                map.put(typeParameters[i], actualTypeArguments[i]);
                i++;
            }
            return (T) beanMapperLoadOrCreateBeanMapperForClass.deserialize(mapExpectMap, map, deserializeContext);
        }
        Type type2 = parameterizedType.getActualTypeArguments()[0];
        Type type3 = parameterizedType.getActualTypeArguments()[1];
        if (!type2.equals(String.class)) {
            throw deserializeError(deserializeContext.errorPath, "Only Maps with string keys are supported, but found Map with key type " + type2);
        }
        Map<String, Object> mapExpectMap2 = expectMap(obj, deserializeContext);
        ?? r1 = (T) new HashMap();
        for (Map.Entry<String, Object> entry : mapExpectMap2.entrySet()) {
            r1.put(entry.getKey(), deserializeToType(entry.getValue(), type3, deserializeContext.newInstanceWithErrorPath(deserializeContext.errorPath.child(entry.getKey()))));
        }
        return r1;
    }

    public static <T> T deserializeToPrimitive(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (Integer.class.isAssignableFrom(cls) || Integer.TYPE.isAssignableFrom(cls)) {
            return (T) convertInteger(obj, deserializeContext);
        }
        if (Boolean.class.isAssignableFrom(cls) || Boolean.TYPE.isAssignableFrom(cls)) {
            return (T) convertBoolean(obj, deserializeContext);
        }
        if (Double.class.isAssignableFrom(cls) || Double.TYPE.isAssignableFrom(cls)) {
            return (T) convertDouble(obj, deserializeContext);
        }
        if (Long.class.isAssignableFrom(cls) || Long.TYPE.isAssignableFrom(cls)) {
            return (T) convertLong(obj, deserializeContext);
        }
        if (Float.class.isAssignableFrom(cls) || Float.TYPE.isAssignableFrom(cls)) {
            return (T) Float.valueOf(convertDouble(obj, deserializeContext).floatValue());
        }
        throw deserializeError(deserializeContext.errorPath, String.format("Deserializing values to %s is not supported", cls.getSimpleName()));
    }

    public static <T> T deserializeToType(Object obj, Type type, DeserializeContext deserializeContext) {
        if (obj == null) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return (T) deserializeToParameterizedType(obj, (ParameterizedType) type, deserializeContext);
        }
        if (type instanceof Class) {
            return (T) deserializeToClass(obj, (Class) type, deserializeContext);
        }
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            if (wildcardType.getLowerBounds().length > 0) {
                throw deserializeError(deserializeContext.errorPath, "Generic lower-bounded wildcard types are not supported");
            }
            Type[] upperBounds = wildcardType.getUpperBounds();
            hardAssert(upperBounds.length > 0, "Unexpected type bounds on wildcard " + type);
            return (T) deserializeToType(obj, upperBounds[0], deserializeContext);
        }
        if (type instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable) type).getBounds();
            hardAssert(bounds.length > 0, "Unexpected type bounds on type variable " + type);
            return (T) deserializeToType(obj, bounds[0], deserializeContext);
        }
        if (type instanceof GenericArrayType) {
            throw deserializeError(deserializeContext.errorPath, "Generic Arrays are not supported, please use Lists instead");
        }
        throw deserializeError(deserializeContext.errorPath, "Unknown type encountered: " + type);
    }

    public static Map<String, Object> expectMap(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Map) {
            return (Map) obj;
        }
        ErrorPath errorPath = deserializeContext.errorPath;
        StringBuilder sbA = a.a("Expected a Map while deserializing, but got a ");
        sbA.append(obj.getClass());
        throw deserializeError(errorPath, sbA.toString());
    }

    public static void hardAssert(boolean z) {
        hardAssert(z, "Internal inconsistency");
    }

    public static <T> BeanMapper<T> loadOrCreateBeanMapperForClass(Class<T> cls) {
        BeanMapper<T> beanMapper = (BeanMapper) mappers.get(cls);
        if (beanMapper != null) {
            return beanMapper;
        }
        BeanMapper<T> beanMapper2 = new BeanMapper<>(cls);
        mappers.put(cls, beanMapper2);
        return beanMapper2;
    }

    public static <T> Object serialize(T t) {
        return serialize(t, ErrorPath.EMPTY);
    }

    public static IllegalArgumentException serializeError(ErrorPath errorPath, String str) {
        String strA = a.a("Could not serialize object. ", str);
        if (errorPath.getLength() > 0) {
            StringBuilder sbB = a.b(strA, " (found in field '");
            sbB.append(errorPath.toString());
            sbB.append("')");
            strA = sbB.toString();
        }
        return new IllegalArgumentException(strA);
    }

    public static Map<String, Object> convertToPlainJavaTypes(Map<?, Object> map) {
        Object objSerialize = serialize(map);
        hardAssert(objSerialize instanceof Map, "Internal inconsistency");
        return (Map) objSerialize;
    }

    public static void hardAssert(boolean z, String str) {
        if (!z) {
            throw new RuntimeException(a.a("Hard assert failed: ", str));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Object serialize(T t, ErrorPath errorPath) {
        if (errorPath.getLength() > 500) {
            throw serializeError(errorPath, "Exceeded maximum depth of 500, which likely indicates there's an object cycle");
        }
        if (t == 0) {
            return null;
        }
        if (t instanceof Number) {
            if ((t instanceof Long) || (t instanceof Integer) || (t instanceof Double) || (t instanceof Float)) {
                return t;
            }
            throw serializeError(errorPath, String.format("Numbers of type %s are not supported, please use an int, long, float or double", t.getClass().getSimpleName()));
        }
        if ((t instanceof String) || (t instanceof Boolean)) {
            return t;
        }
        if (t instanceof Character) {
            throw serializeError(errorPath, "Characters are not supported, please use Strings");
        }
        if (t instanceof Map) {
            HashMap map = new HashMap();
            for (Map.Entry entry : ((Map) t).entrySet()) {
                Object key = entry.getKey();
                if (!(key instanceof String)) {
                    throw serializeError(errorPath, "Maps with non-string keys are not supported");
                }
                String str = (String) key;
                map.put(str, serialize(entry.getValue(), errorPath.child(str)));
            }
            return map;
        }
        if (!(t instanceof Collection)) {
            if (t.getClass().isArray()) {
                throw serializeError(errorPath, "Serializing Arrays is not supported, please use Lists instead");
            }
            if (!(t instanceof Enum)) {
                return ((t instanceof Date) || (t instanceof Timestamp) || (t instanceof GeoPoint) || (t instanceof Blob) || (t instanceof DocumentReference) || (t instanceof FieldValue)) ? t : loadOrCreateBeanMapperForClass(t.getClass()).serialize(t, errorPath);
            }
            String strName = ((Enum) t).name();
            try {
                return BeanMapper.propertyName(t.getClass().getField(strName));
            } catch (NoSuchFieldException unused) {
                return strName;
            }
        }
        if (!(t instanceof List)) {
            throw serializeError(errorPath, "Serializing Collections is not supported, please use Lists instead");
        }
        List list = (List) t;
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(serialize(list.get(i), errorPath.child("[" + i + "]")));
        }
        return arrayList;
    }
}
