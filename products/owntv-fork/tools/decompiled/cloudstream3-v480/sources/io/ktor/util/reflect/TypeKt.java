package io.ktor.util.reflect;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;

/* JADX INFO: compiled from: Type.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0002\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0000\u0018\u0001H\u0086\b¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001d\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0004*\u00020\u0001H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001a\u0010\t\u001a\u0004\u0018\u00010\b\"\u0006\b\u0000\u0010\u0000\u0018\u0001H\u0081\b¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "()Lio/ktor/util/reflect/TypeInfo;", "Lkotlinx/serialization/KSerializer;", "", "serializer", "(Lio/ktor/util/reflect/TypeInfo;)Lkotlinx/serialization/KSerializer;", "Lkotlin/reflect/KType;", "typeOfOrNull", "()Lkotlin/reflect/KType;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TypeKt {
    public static final /* synthetic */ <T> TypeInfo typeInfo() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        try {
            Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        } catch (Throwable unused) {
        }
        return new TypeInfo(orCreateKotlinClass, null);
    }

    public static final KSerializer<? extends Object> serializer(TypeInfo typeInfo) {
        KSerializer<? extends Object> kSerializerSerializer;
        Intrinsics.checkNotNullParameter(typeInfo, "<this>");
        KType kotlinType = typeInfo.getKotlinType();
        return (kotlinType == null || (kSerializerSerializer = SerializersKt.serializer(kotlinType)) == null) ? SerializersKt.serializer(typeInfo.getType()) : kSerializerSerializer;
    }

    public static final /* synthetic */ <T> KType typeOfOrNull() {
        try {
            Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
