package androidx.navigation.serialization;

import androidx.navigation.NavType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.internal.CollectionDescriptorsKt;

/* JADX INFO: compiled from: NavTypeConverter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\t"}, d2 = {"getNavType", "Landroidx/navigation/NavType;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "toInternalType", "Landroidx/navigation/serialization/InternalType;", "matchKType", "", "kType", "Lkotlin/reflect/KType;", "navigation-common_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavTypeConverterKt {

    /* JADX INFO: compiled from: NavTypeConverter.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InternalType.values().length];
            try {
                iArr[InternalType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InternalType.STRING_NULLABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InternalType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[InternalType.BOOL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[InternalType.DOUBLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[InternalType.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[InternalType.LONG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[InternalType.ENUM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[InternalType.INT_NULLABLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[InternalType.BOOL_NULLABLE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[InternalType.DOUBLE_NULLABLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[InternalType.FLOAT_NULLABLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[InternalType.LONG_NULLABLE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[InternalType.INT_ARRAY.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[InternalType.BOOL_ARRAY.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[InternalType.DOUBLE_ARRAY.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[InternalType.FLOAT_ARRAY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[InternalType.LONG_ARRAY.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[InternalType.ARRAY.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[InternalType.LIST.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[InternalType.ENUM_NULLABLE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final NavType<?> getNavType(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[toInternalType(serialDescriptor).ordinal()]) {
            case 1:
                return InternalNavType.INSTANCE.getStringNonNullableType();
            case 2:
                return NavType.StringType;
            case 3:
                return NavType.IntType;
            case 4:
                return NavType.BoolType;
            case 5:
                return InternalNavType.INSTANCE.getDoubleType();
            case 6:
                return NavType.FloatType;
            case 7:
                return NavType.LongType;
            case 8:
                return NavTypeConverter_androidKt.parseEnum(serialDescriptor);
            case 9:
                return InternalNavType.INSTANCE.getIntNullableType();
            case 10:
                return InternalNavType.INSTANCE.getBoolNullableType();
            case 11:
                return InternalNavType.INSTANCE.getDoubleNullableType();
            case 12:
                return InternalNavType.INSTANCE.getFloatNullableType();
            case 13:
                return InternalNavType.INSTANCE.getLongNullableType();
            case 14:
                return NavType.IntArrayType;
            case 15:
                return NavType.BoolArrayType;
            case 16:
                return InternalNavType.INSTANCE.getDoubleArrayType();
            case 17:
                return NavType.FloatArrayType;
            case 18:
                return NavType.LongArrayType;
            case 19:
                int i = WhenMappings.$EnumSwitchMapping$0[toInternalType(serialDescriptor.getElementDescriptor(0)).ordinal()];
                if (i == 1) {
                    return NavType.StringArrayType;
                }
                if (i == 2) {
                    return InternalNavType.INSTANCE.getStringNullableArrayType();
                }
                return UNKNOWN.INSTANCE;
            case 20:
                switch (WhenMappings.$EnumSwitchMapping$0[toInternalType(serialDescriptor.getElementDescriptor(0)).ordinal()]) {
                    case 1:
                        return NavType.StringListType;
                    case 2:
                        return InternalNavType.INSTANCE.getStringNullableListType();
                    case 3:
                        return NavType.IntListType;
                    case 4:
                        return NavType.BoolListType;
                    case 5:
                        return InternalNavType.INSTANCE.getDoubleListType();
                    case 6:
                        return NavType.FloatListType;
                    case 7:
                        return NavType.LongListType;
                    case 8:
                        return NavTypeConverter_androidKt.parseEnumList(serialDescriptor);
                    default:
                        return UNKNOWN.INSTANCE;
                }
            case 21:
                return NavTypeConverter_androidKt.parseNullableEnum(serialDescriptor);
            default:
                return UNKNOWN.INSTANCE;
        }
    }

    private static final InternalType toInternalType(SerialDescriptor serialDescriptor) {
        String strReplace$default = StringsKt.replace$default(serialDescriptor.getSerialName(), "?", "", false, 4, (Object) null);
        if (Intrinsics.areEqual(serialDescriptor.getKind(), SerialKind.ENUM.INSTANCE)) {
            return serialDescriptor.isNullable() ? InternalType.ENUM_NULLABLE : InternalType.ENUM;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.Int")) {
            return serialDescriptor.isNullable() ? InternalType.INT_NULLABLE : InternalType.INT;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.Boolean")) {
            return serialDescriptor.isNullable() ? InternalType.BOOL_NULLABLE : InternalType.BOOL;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.Double")) {
            return serialDescriptor.isNullable() ? InternalType.DOUBLE_NULLABLE : InternalType.DOUBLE;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.Float")) {
            return serialDescriptor.isNullable() ? InternalType.FLOAT_NULLABLE : InternalType.FLOAT;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.Long")) {
            return serialDescriptor.isNullable() ? InternalType.LONG_NULLABLE : InternalType.LONG;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.String")) {
            return serialDescriptor.isNullable() ? InternalType.STRING_NULLABLE : InternalType.STRING;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.IntArray")) {
            return InternalType.INT_ARRAY;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.DoubleArray")) {
            return InternalType.DOUBLE_ARRAY;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.BooleanArray")) {
            return InternalType.BOOL_ARRAY;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.FloatArray")) {
            return InternalType.FLOAT_ARRAY;
        }
        if (Intrinsics.areEqual(strReplace$default, "kotlin.LongArray")) {
            return InternalType.LONG_ARRAY;
        }
        if (Intrinsics.areEqual(strReplace$default, CollectionDescriptorsKt.ARRAY_NAME)) {
            return InternalType.ARRAY;
        }
        return StringsKt.startsWith$default(strReplace$default, CollectionDescriptorsKt.ARRAY_LIST_NAME, false, 2, (Object) null) ? InternalType.LIST : InternalType.UNKNOWN;
    }

    public static final boolean matchKType(SerialDescriptor serialDescriptor, KType kType) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(kType, "kType");
        if (serialDescriptor.isNullable() != kType.getIsMarkedNullable()) {
            return false;
        }
        KSerializer<Object> kSerializerSerializerOrNull = SerializersKt.serializerOrNull(kType);
        if (kSerializerSerializerOrNull == null) {
            throw new IllegalStateException(("Cannot find KSerializer for [" + serialDescriptor.getSerialName() + "]. If applicable, custom KSerializers for custom and third-party KType is currently not supported when declared directly on a class field via @Serializable(with = ...). Please use @Serializable or @Serializable(with = ...) on the class or object declaration.").toString());
        }
        return Intrinsics.areEqual(serialDescriptor, kSerializerSerializerOrNull.getDescriptor());
    }
}
