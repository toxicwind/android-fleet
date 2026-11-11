package io.ktor.util.converters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ConversionServiceJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u001a%\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0001\u001a\u00020\u00002\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0001\u001a\u00020\u00002\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002¢\u0006\u0004\b\u0007\u0010\u0006\u001a\u001f\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\b2\u0006\u0010\u0001\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"", ES6Iterator.VALUE_PROPERTY, "Lkotlin/reflect/KClass;", "klass", "", "platformDefaultFromValues", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "convertSimpleTypes", "", "platformDefaultToValues", "(Ljava/lang/Object;)Ljava/util/List;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ConversionServiceJvmKt {
    public static final Object platformDefaultFromValues(String value, KClass<?> klass) throws DataConversionException {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(klass, "klass");
        Object objConvertSimpleTypes = convertSimpleTypes(value, klass);
        if (objConvertSimpleTypes != null) {
            return objConvertSimpleTypes;
        }
        Object obj = null;
        if (!JvmClassMappingKt.getJavaClass((KClass) klass).isEnum()) {
            return null;
        }
        Object[] enumConstants = JvmClassMappingKt.getJavaClass((KClass) klass).getEnumConstants();
        if (enumConstants != null) {
            for (Object obj2 : enumConstants) {
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Enum<*>");
                if (Intrinsics.areEqual(((Enum) obj2).name(), value)) {
                    obj = obj2;
                    break;
                }
            }
            if (obj != null) {
                return obj;
            }
        }
        throw new DataConversionException("Value " + value + " is not a enum member name of " + klass);
    }

    private static final Object convertSimpleTypes(String str, KClass<?> kClass) {
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Integer.class))) {
            return Integer.valueOf(Integer.parseInt(str));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Float.class))) {
            return Float.valueOf(Float.parseFloat(str));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Double.class))) {
            return Double.valueOf(Double.parseDouble(str));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Long.class))) {
            return Long.valueOf(Long.parseLong(str));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Short.class))) {
            return Short.valueOf(Short.parseShort(str));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Boolean.class))) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(String.class))) {
            return str;
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Character.class))) {
            return Character.valueOf(str.charAt(0));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(BigDecimal.class))) {
            return new BigDecimal(str);
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(BigInteger.class))) {
            return new BigInteger(str);
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(UUID.class))) {
            return UUID.fromString(str);
        }
        return null;
    }

    public static final List<String> platformDefaultToValues(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof Enum) {
            return CollectionsKt.listOf(((Enum) value).name());
        }
        if (value instanceof Integer) {
            return CollectionsKt.listOf(((Integer) value).toString());
        }
        if (value instanceof Float) {
            return CollectionsKt.listOf(((Float) value).toString());
        }
        if (value instanceof Double) {
            return CollectionsKt.listOf(((Double) value).toString());
        }
        if (value instanceof Long) {
            return CollectionsKt.listOf(((Long) value).toString());
        }
        if (value instanceof Boolean) {
            return CollectionsKt.listOf(((Boolean) value).toString());
        }
        if (value instanceof Short) {
            return CollectionsKt.listOf(((Short) value).toString());
        }
        if (value instanceof String) {
            return CollectionsKt.listOf(((String) value).toString());
        }
        if (value instanceof Character) {
            return CollectionsKt.listOf(((Character) value).toString());
        }
        if (value instanceof BigDecimal) {
            return CollectionsKt.listOf(((BigDecimal) value).toString());
        }
        if (value instanceof BigInteger) {
            return CollectionsKt.listOf(((BigInteger) value).toString());
        }
        if (value instanceof UUID) {
            return CollectionsKt.listOf(((UUID) value).toString());
        }
        return null;
    }
}
