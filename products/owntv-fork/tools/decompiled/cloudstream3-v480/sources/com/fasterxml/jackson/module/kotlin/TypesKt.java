package com.fasterxml.jackson.module.kotlin;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.KTypesJvm;

/* JADX INFO: compiled from: Types.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0000\u001a\u0014\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001*\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"erasedType", "Ljava/lang/Class;", "", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "jackson-module-kotlin"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class TypesKt {
    public static final Class<Object> erasedType(Type type) {
        Intrinsics.checkNotNullParameter(type, "<this>");
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            Intrinsics.checkNotNullExpressionValue(rawType, "this.getRawType()");
            return erasedType(rawType);
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            Intrinsics.checkNotNullExpressionValue(genericComponentType, "this.getGenericComponentType()");
            Object testArray = Array.newInstance((Class<?>) erasedType(genericComponentType), 0);
            Intrinsics.checkNotNullExpressionValue(testArray, "testArray");
            return testArray.getClass();
        }
        if (type instanceof TypeVariable) {
            throw new IllegalStateException("Not sure what to do here yet");
        }
        if (type instanceof WildcardType) {
            Type type2 = ((WildcardType) type).getUpperBounds()[0];
            Intrinsics.checkNotNullExpressionValue(type2, "this.getUpperBounds()[0]");
            return erasedType(type2);
        }
        throw new IllegalStateException("Should not get here.");
    }

    public static final Class<? extends Object> erasedType(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        return JvmClassMappingKt.getJavaClass((KClass) KTypesJvm.getJvmErasure(kType));
    }
}
