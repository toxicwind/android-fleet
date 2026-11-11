package androidx.navigation;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import androidx.savedstate.SavedStateReader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NavType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a9\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\t\u001aC\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u0002H\u0001H\u0000¢\u0006\u0002\u0010\u000b\u001a\u0018\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0000\u001a\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0000\u001a\u001a\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u000fH\u0000\u001a9\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\t\u001aA\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u000b¨\u0006\u0012"}, d2 = {"navTypeParseAndPut", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/navigation/NavType;", "bundle", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "key", "", ES6Iterator.VALUE_PROPERTY, "(Landroidx/navigation/NavType;Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "previousValue", "(Landroidx/navigation/NavType;Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "navTypeFromArgType", "type", "navTypeInferFromValue", "", "navTypeInferFromValueType", "parseAndPutFromUri", "navigation-common_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavTypeKt {
    public static final <T> T navTypeParseAndPut(NavType<T> navType, Bundle bundle, String key, String value) {
        Intrinsics.checkNotNullParameter(navType, "<this>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        T value2 = navType.parseValue(value);
        navType.put(bundle, key, value2);
        return value2;
    }

    public static final NavType<?> navTypeFromArgType(String str) {
        if (Intrinsics.areEqual(NavType.IntType.getName(), str)) {
            return NavType.IntType;
        }
        if (Intrinsics.areEqual(NavType.IntArrayType.getName(), str)) {
            return NavType.IntArrayType;
        }
        if (Intrinsics.areEqual(NavType.IntListType.getName(), str)) {
            return NavType.IntListType;
        }
        if (Intrinsics.areEqual(NavType.LongType.getName(), str)) {
            return NavType.LongType;
        }
        if (Intrinsics.areEqual(NavType.LongArrayType.getName(), str)) {
            return NavType.LongArrayType;
        }
        if (Intrinsics.areEqual(NavType.LongListType.getName(), str)) {
            return NavType.LongListType;
        }
        if (Intrinsics.areEqual(NavType.BoolType.getName(), str)) {
            return NavType.BoolType;
        }
        if (Intrinsics.areEqual(NavType.BoolArrayType.getName(), str)) {
            return NavType.BoolArrayType;
        }
        if (Intrinsics.areEqual(NavType.BoolListType.getName(), str)) {
            return NavType.BoolListType;
        }
        if (Intrinsics.areEqual(NavType.StringType.getName(), str)) {
            return NavType.StringType;
        }
        if (Intrinsics.areEqual(NavType.StringArrayType.getName(), str)) {
            return NavType.StringArrayType;
        }
        if (Intrinsics.areEqual(NavType.StringListType.getName(), str)) {
            return NavType.StringListType;
        }
        if (Intrinsics.areEqual(NavType.FloatType.getName(), str)) {
            return NavType.FloatType;
        }
        if (Intrinsics.areEqual(NavType.FloatArrayType.getName(), str)) {
            return NavType.FloatArrayType;
        }
        if (Intrinsics.areEqual(NavType.FloatListType.getName(), str)) {
            return NavType.FloatListType;
        }
        return null;
    }

    public static final NavType<Object> navTypeInferFromValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            try {
                try {
                    try {
                        NavType.IntType.parseValue(value);
                        NavType<Integer> navType = NavType.IntType;
                        Intrinsics.checkNotNull(navType, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                        return navType;
                    } catch (IllegalArgumentException unused) {
                        NavType.LongType.parseValue(value);
                        NavType<Long> navType2 = NavType.LongType;
                        Intrinsics.checkNotNull(navType2, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                        return navType2;
                    }
                } catch (IllegalArgumentException unused2) {
                    NavType<String> navType3 = NavType.StringType;
                    Intrinsics.checkNotNull(navType3, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                    return navType3;
                }
            } catch (IllegalArgumentException unused3) {
                NavType.FloatType.parseValue(value);
                NavType<Float> navType4 = NavType.FloatType;
                Intrinsics.checkNotNull(navType4, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
                return navType4;
            }
        } catch (IllegalArgumentException unused4) {
            NavType.BoolType.parseValue(value);
            NavType<Boolean> navType5 = NavType.BoolType;
            Intrinsics.checkNotNull(navType5, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType5;
        }
    }

    public static final NavType<Object> navTypeInferFromValueType(Object obj) {
        if (obj instanceof Integer) {
            NavType<Integer> navType = NavType.IntType;
            Intrinsics.checkNotNull(navType, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType;
        }
        if (obj instanceof int[]) {
            NavType<int[]> navType2 = NavType.IntArrayType;
            Intrinsics.checkNotNull(navType2, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType2;
        }
        if (obj instanceof Long) {
            NavType<Long> navType3 = NavType.LongType;
            Intrinsics.checkNotNull(navType3, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType3;
        }
        if (obj instanceof long[]) {
            NavType<long[]> navType4 = NavType.LongArrayType;
            Intrinsics.checkNotNull(navType4, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType4;
        }
        if (obj instanceof Float) {
            NavType<Float> navType5 = NavType.FloatType;
            Intrinsics.checkNotNull(navType5, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType5;
        }
        if (obj instanceof float[]) {
            NavType<float[]> navType6 = NavType.FloatArrayType;
            Intrinsics.checkNotNull(navType6, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType6;
        }
        if (obj instanceof Boolean) {
            NavType<Boolean> navType7 = NavType.BoolType;
            Intrinsics.checkNotNull(navType7, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType7;
        }
        if (obj instanceof boolean[]) {
            NavType<boolean[]> navType8 = NavType.BoolArrayType;
            Intrinsics.checkNotNull(navType8, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
            return navType8;
        }
        if (!(obj instanceof String) && obj != null) {
            return null;
        }
        NavType<String> navType9 = NavType.StringType;
        Intrinsics.checkNotNull(navType9, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any>");
        return navType9;
    }

    public static final <T> T parseAndPutFromUri(NavType<T> navType, Bundle bundle, String key, String value) {
        Intrinsics.checkNotNullParameter(navType, "<this>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return navType.parseAndPut(bundle, key, NavUriUtils.INSTANCE.decode(value));
    }

    public static final <T> T parseAndPutFromUri(NavType<T> navType, Bundle bundle, String key, String value, T t) {
        Intrinsics.checkNotNullParameter(navType, "<this>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return navType.parseAndPut(bundle, key, NavUriUtils.INSTANCE.decode(value), t);
    }

    public static final <T> T navTypeParseAndPut(NavType<T> navType, Bundle bundle, String key, String str, T t) {
        Intrinsics.checkNotNullParameter(navType, "<this>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        if (!SavedStateReader.m7931containsimpl(SavedStateReader.m7930constructorimpl(bundle), key)) {
            throw new IllegalArgumentException("There is no previous value in this savedState.");
        }
        if (str == null) {
            return t;
        }
        T value = navType.parseValue(str, t);
        navType.put(bundle, key, value);
        return value;
    }
}
