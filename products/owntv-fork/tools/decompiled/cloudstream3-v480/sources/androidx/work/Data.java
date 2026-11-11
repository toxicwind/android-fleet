package androidx.work;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter;
import io.ktor.http.ContentDisposition;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Data_.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 B2\u00020\u0001:\u0002ABB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004B\u001b\b\u0010\u0012\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00020\u0007\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0003\u0010\bJ*\u0010\t\u001a\u0002H\n\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u0002H\nH\u0082\b¢\u0006\u0002\u0010\rJ}\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u0001\"\u0004\b\u0001\u0010\u000f2\u0006\u0010\u000b\u001a\u00020\u00072Q\u0010\u0010\u001aM\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012.\u0012,\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\n0\u0016¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\u000f0\u0011H\u0082\b¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u000b\u001a\u00020\u0007J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u001fJ\u0010\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u000b\u001a\u00020\u0007J\u0016\u0010\"\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0012J\u0010\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u000b\u001a\u00020\u0007J\u0016\u0010%\u001a\u00020&2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020&J\u0010\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\u000b\u001a\u00020\u0007J\u0016\u0010)\u001a\u00020*2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020*J\u0010\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010\u000b\u001a\u00020\u0007J\u0016\u0010-\u001a\u00020.2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020.J\u0010\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010\u000b\u001a\u00020\u0007J\u0010\u00101\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\u0007J\u001b\u00102\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001032\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0002\u00104J\u0006\u00108\u001a\u00020!J\"\u00109\u001a\u00020\u001b\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\u00072\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H\n0;J\u001e\u0010<\u001a\u00020\u001b\"\u0006\b\u0000\u0010\n\u0018\u00012\u0006\u0010\u000b\u001a\u00020\u0007H\u0080\b¢\u0006\u0002\b=J\b\u0010\u0015\u001a\u00020\u0012H\u0007J\u0013\u0010>\u001a\u00020\u001b2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010?\u001a\u00020\u0012H\u0016J\b\u0010@\u001a\u00020\u0007H\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00105\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00068F¢\u0006\u0006\u001a\u0004\b6\u00107¨\u0006C"}, d2 = {"Landroidx/work/Data;", "", "other", "<init>", "(Landroidx/work/Data;)V", "values", "", "", "(Ljava/util/Map;)V", "getOrDefault", ExifInterface.GPS_DIRECTION_TRUE, "key", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getTypedArray", "TArray", "constructor", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", ContentDisposition.Parameters.Size, "Lkotlin/Function1;", "index", "init", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "getBoolean", "", "getBooleanArray", "", "getByte", "", "getByteArray", "", "getInt", "getIntArray", "", "getLong", "", "getLongArray", "", "getFloat", "", "getFloatArray", "", "getDouble", "", "getDoubleArray", "", "getString", "getStringArray", "", "(Ljava/lang/String;)[Ljava/lang/String;", "keyValueMap", "getKeyValueMap", "()Ljava/util/Map;", "toByteArray", "hasKeyWithValueOfType", "klass", "Ljava/lang/Class;", "hasKey", "hasKey$work_runtime_release", "equals", "hashCode", "toString", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Data {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Data EMPTY = new Builder().build();
    public static final int MAX_DATA_BYTES = 10240;
    private static final String NULL_STRING_V1 = "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d";
    private static final short STREAM_MAGIC = -21521;
    private static final short STREAM_VERSION = 1;
    private static final byte TYPE_BOOLEAN = 1;
    private static final byte TYPE_BOOLEAN_ARRAY = 8;
    private static final byte TYPE_BYTE = 2;
    private static final byte TYPE_BYTE_ARRAY = 9;
    private static final byte TYPE_DOUBLE = 6;
    private static final byte TYPE_DOUBLE_ARRAY = 13;
    private static final byte TYPE_FLOAT = 5;
    private static final byte TYPE_FLOAT_ARRAY = 12;
    private static final byte TYPE_INTEGER = 3;
    private static final byte TYPE_INTEGER_ARRAY = 10;
    private static final byte TYPE_LONG = 4;
    private static final byte TYPE_LONG_ARRAY = 11;
    private static final byte TYPE_NULL = 0;
    private static final byte TYPE_STRING = 7;
    private static final byte TYPE_STRING_ARRAY = 14;
    private final Map<String, Object> values;

    @JvmStatic
    public static final Data fromByteArray(byte[] bArr) {
        return INSTANCE.fromByteArray(bArr);
    }

    @Deprecated(message = "This is kept for testing migration", replaceWith = @ReplaceWith(expression = "toByteArrayInternalV1", imports = {}))
    @JvmStatic
    public static final byte[] toByteArrayInternalV0(Data data) {
        return INSTANCE.toByteArrayInternalV0(data);
    }

    @JvmStatic
    public static final byte[] toByteArrayInternalV1(Data data) {
        return INSTANCE.toByteArrayInternalV1(data);
    }

    public Data(Data other) {
        Intrinsics.checkNotNullParameter(other, "other");
        this.values = new HashMap(other.values);
    }

    public Data(Map<String, ?> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        this.values = new HashMap(values);
    }

    private final /* synthetic */ <T> T getOrDefault(String key, T defaultValue) {
        T t = (T) this.values.get(key);
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        return t instanceof Object ? t : defaultValue;
    }

    private final /* synthetic */ <T, TArray> TArray getTypedArray(String key, Function2<? super Integer, ? super Function1<? super Integer, ? extends T>, ? extends TArray> constructor) {
        final Object obj = this.values.get(key);
        if (!(obj instanceof Object[])) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        if (!(objArr instanceof Object[])) {
            return null;
        }
        Integer numValueOf = Integer.valueOf(objArr.length);
        Intrinsics.needClassReification();
        return constructor.invoke(numValueOf, new Function1<Integer, T>() { // from class: androidx.work.Data.getTypedArray.1
            public final T invoke(int i) {
                T t = (T) ((Object[]) obj)[i];
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
                return t;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }
        });
    }

    public final boolean getBoolean(String key, boolean defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object objValueOf = Boolean.valueOf(defaultValue);
        Object obj = this.values.get(key);
        if (obj instanceof Boolean) {
            objValueOf = obj;
        }
        return ((Boolean) objValueOf).booleanValue();
    }

    public final byte getByte(String key, byte defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object objValueOf = Byte.valueOf(defaultValue);
        Object obj = this.values.get(key);
        if (obj instanceof Byte) {
            objValueOf = obj;
        }
        return ((Number) objValueOf).byteValue();
    }

    public final int getInt(String key, int defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object objValueOf = Integer.valueOf(defaultValue);
        Object obj = this.values.get(key);
        if (obj instanceof Integer) {
            objValueOf = obj;
        }
        return ((Number) objValueOf).intValue();
    }

    public final long getLong(String key, long defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object objValueOf = Long.valueOf(defaultValue);
        Object obj = this.values.get(key);
        if (obj instanceof Long) {
            objValueOf = obj;
        }
        return ((Number) objValueOf).longValue();
    }

    public final float getFloat(String key, float defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object objValueOf = Float.valueOf(defaultValue);
        Object obj = this.values.get(key);
        if (obj instanceof Float) {
            objValueOf = obj;
        }
        return ((Number) objValueOf).floatValue();
    }

    public final double getDouble(String key, double defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object objValueOf = Double.valueOf(defaultValue);
        Object obj = this.values.get(key);
        if (obj instanceof Double) {
            objValueOf = obj;
        }
        return ((Number) objValueOf).doubleValue();
    }

    public final String getString(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object obj = this.values.get(key);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public final Map<String, Object> getKeyValueMap() {
        Map<String, Object> mapUnmodifiableMap = DesugarCollections.unmodifiableMap(this.values);
        Intrinsics.checkNotNullExpressionValue(mapUnmodifiableMap, "unmodifiableMap(...)");
        return mapUnmodifiableMap;
    }

    public final byte[] toByteArray() {
        return INSTANCE.toByteArrayInternalV1(this);
    }

    public final <T> boolean hasKeyWithValueOfType(String key, Class<T> klass) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(klass, "klass");
        Object obj = this.values.get(key);
        return obj != null && klass.isAssignableFrom(obj.getClass());
    }

    public final /* synthetic */ <T> boolean hasKey$work_runtime_release(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return hasKeyWithValueOfType(key, Object.class);
    }

    public final int size() {
        return this.values.size();
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0067  */
    public boolean equals(Object other) {
        boolean zAreEqual;
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        Data data = (Data) other;
        Set<String> setKeySet = this.values.keySet();
        if (!Intrinsics.areEqual(setKeySet, data.values.keySet())) {
            return false;
        }
        for (String str : setKeySet) {
            Object obj = this.values.get(str);
            Object obj2 = data.values.get(str);
            if (obj == null || obj2 == null) {
                zAreEqual = obj == obj2;
            } else if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if ((objArr instanceof Object[]) && (obj2 instanceof Object[])) {
                    Object[] objArr2 = (Object[]) obj2;
                    if (objArr2 instanceof Object[]) {
                        zAreEqual = ArraysKt.contentDeepEquals(objArr, objArr2);
                    } else {
                        zAreEqual = Intrinsics.areEqual(obj, obj2);
                    }
                } else {
                    zAreEqual = Intrinsics.areEqual(obj, obj2);
                }
            } else {
                zAreEqual = Intrinsics.areEqual(obj, obj2);
            }
            if (!zAreEqual) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int iHashCode;
        int i = 0;
        for (Map.Entry<String, Object> entry : this.values.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Object[]) {
                iHashCode = Objects.hashCode(entry.getKey()) ^ ArraysKt.contentDeepHashCode((Object[]) value);
            } else {
                iHashCode = entry.hashCode();
            }
            i += iHashCode;
        }
        return i * 31;
    }

    public String toString() {
        return "Data {" + CollectionsKt.joinToString$default(this.values.entrySet(), null, null, null, 0, null, new Function1() { // from class: androidx.work.Data$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Data.toString$lambda$7$lambda$6((Map.Entry) obj);
            }
        }, 31, null) + "}";
    }

    static final CharSequence toString$lambda$7$lambda$6(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "<destruct>");
        String str = (String) entry.getKey();
        Object value = entry.getValue();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(RepoAdapter.SHAREABLE_REPO_SEPARATOR);
        if (value instanceof Object[]) {
            value = Arrays.toString((Object[]) value);
            Intrinsics.checkNotNullExpressionValue(value, "toString(...)");
        }
        sb.append(value);
        return sb.toString();
    }

    /* JADX INFO: compiled from: Data_.kt */
    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0002J\u0016\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u001fJ\u0016\u0010 \u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020!J\u0018\u0010\"\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0006J#\u0010#\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060$¢\u0006\u0002\u0010%J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(J\u001c\u0010&\u001a\u00020\u00002\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010)J\u001a\u0010*\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0007J\u0006\u0010+\u001a\u00020(R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Landroidx/work/Data$Builder;", "", "<init>", "()V", "values", "", "", "putDirect", "key", ES6Iterator.VALUE_PROPERTY, "putBoolean", "", "putBooleanArray", "", "putByte", "", "putByteArray", "", "putInt", "", "putIntArray", "", "putLong", "", "putLongArray", "", "putFloat", "", "putFloatArray", "", "putDouble", "", "putDoubleArray", "", "putString", "putStringArray", "", "(Ljava/lang/String;[Ljava/lang/String;)Landroidx/work/Data$Builder;", "putAll", "data", "Landroidx/work/Data;", "", "put", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder {
        private final Map<String, Object> values = new LinkedHashMap();

        private final Builder putDirect(String key, Object value) {
            this.values.put(key, value);
            return this;
        }

        public final Builder putBoolean(String key, boolean value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return putDirect(key, Boolean.valueOf(value));
        }

        public final Builder putBooleanArray(String key, boolean[] value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.values.put(key, Data_Kt.convertPrimitiveArray(value));
            return this;
        }

        public final Builder putByte(String key, byte value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return putDirect(key, Byte.valueOf(value));
        }

        public final Builder putByteArray(String key, byte[] value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.values.put(key, Data_Kt.convertPrimitiveArray(value));
            return this;
        }

        public final Builder putInt(String key, int value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return putDirect(key, Integer.valueOf(value));
        }

        public final Builder putIntArray(String key, int[] value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.values.put(key, Data_Kt.convertPrimitiveArray(value));
            return this;
        }

        public final Builder putLong(String key, long value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return putDirect(key, Long.valueOf(value));
        }

        public final Builder putLongArray(String key, long[] value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.values.put(key, Data_Kt.convertPrimitiveArray(value));
            return this;
        }

        public final Builder putFloat(String key, float value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return putDirect(key, Float.valueOf(value));
        }

        public final Builder putFloatArray(String key, float[] value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.values.put(key, Data_Kt.convertPrimitiveArray(value));
            return this;
        }

        public final Builder putDouble(String key, double value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return putDirect(key, Double.valueOf(value));
        }

        public final Builder putDoubleArray(String key, double[] value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.values.put(key, Data_Kt.convertPrimitiveArray(value));
            return this;
        }

        public final Builder putString(String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return putDirect(key, value);
        }

        public final Builder putStringArray(String key, String[] value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            return putDirect(key, value);
        }

        public final Builder putAll(Data data) {
            Intrinsics.checkNotNullParameter(data, "data");
            putAll(data.values);
            return this;
        }

        public final Builder put(String key, Object value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Map<String, Object> map = this.values;
            if (value == null) {
                value = null;
            } else {
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(value.getClass());
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Byte.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Byte[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double[].class)) && !Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String[].class))) {
                    if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(boolean[].class))) {
                        value = Data_Kt.convertPrimitiveArray((boolean[]) value);
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(byte[].class))) {
                        value = Data_Kt.convertPrimitiveArray((byte[]) value);
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(int[].class))) {
                        value = Data_Kt.convertPrimitiveArray((int[]) value);
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(long[].class))) {
                        value = Data_Kt.convertPrimitiveArray((long[]) value);
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(float[].class))) {
                        value = Data_Kt.convertPrimitiveArray((float[]) value);
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(double[].class))) {
                        value = Data_Kt.convertPrimitiveArray((double[]) value);
                    } else {
                        throw new IllegalArgumentException("Key " + key + " has invalid type " + orCreateKotlinClass);
                    }
                }
            }
            map.put(key, value);
            return this;
        }

        public final Data build() {
            Data data = new Data((Map<String, ?>) this.values);
            Data.INSTANCE.toByteArrayInternalV1(data);
            return data;
        }

        public final Builder putAll(Map<String, ? extends Object> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            for (Map.Entry<String, ? extends Object> entry : values.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return this;
        }
    }

    /* JADX INFO: compiled from: Data_.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0005H\u0007J\u0010\u0010 \u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0005H\u0007J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u001eH\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/work/Data$Companion;", "", "<init>", "()V", "EMPTY", "Landroidx/work/Data;", "MAX_DATA_BYTES", "", "TYPE_NULL", "", "TYPE_BOOLEAN", "TYPE_BYTE", "TYPE_INTEGER", "TYPE_LONG", "TYPE_FLOAT", "TYPE_DOUBLE", "TYPE_STRING", "TYPE_BOOLEAN_ARRAY", "TYPE_BYTE_ARRAY", "TYPE_INTEGER_ARRAY", "TYPE_LONG_ARRAY", "TYPE_FLOAT_ARRAY", "TYPE_DOUBLE_ARRAY", "TYPE_STRING_ARRAY", "NULL_STRING_V1", "", "STREAM_MAGIC", "", "STREAM_VERSION", "toByteArrayInternalV0", "", "data", "toByteArrayInternalV1", "fromByteArray", "bytes", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Deprecated(message = "This is kept for testing migration", replaceWith = @ReplaceWith(expression = "toByteArrayInternalV1", imports = {}))
        @JvmStatic
        public final byte[] toByteArrayInternalV0(Data data) {
            Intrinsics.checkNotNullParameter(data, "data");
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream2);
                    try {
                        ObjectOutputStream objectOutputStream2 = objectOutputStream;
                        objectOutputStream2.writeInt(data.size());
                        for (Map.Entry entry : data.values.entrySet()) {
                            String str = (String) entry.getKey();
                            Object value = entry.getValue();
                            objectOutputStream2.writeUTF(str);
                            objectOutputStream2.writeObject(value);
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(objectOutputStream, null);
                        CloseableKt.closeFinally(byteArrayOutputStream, null);
                        if (byteArrayOutputStream2.size() > 10240) {
                            throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
                        }
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        Intrinsics.checkNotNull(byteArray);
                        return byteArray;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(objectOutputStream, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(byteArrayOutputStream, th3);
                        throw th4;
                    }
                }
            } catch (IOException e) {
                Logger.get().error(Data_Kt.TAG, "Error in Data#toByteArray: ", e);
                return new byte[0];
            }
        }

        private static final void toByteArrayInternalV1$writeHeader(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeShort(-21521);
            dataOutputStream.writeShort(1);
        }

        private static final void toByteArrayInternalV1$writeArray(DataOutputStream dataOutputStream, Object[] objArr) throws IOException {
            int i;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(objArr.getClass());
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean[].class))) {
                i = 8;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Byte[].class))) {
                i = 9;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer[].class))) {
                i = 10;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long[].class))) {
                i = 11;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float[].class))) {
                i = 12;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double[].class))) {
                i = 13;
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String[].class))) {
                    throw new IllegalArgumentException("Unsupported value type " + Reflection.getOrCreateKotlinClass(objArr.getClass()).getQualifiedName());
                }
                i = 14;
            }
            dataOutputStream.writeByte(i);
            dataOutputStream.writeInt(objArr.length);
            for (Object obj : objArr) {
                if (i == 8) {
                    Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
                    dataOutputStream.writeBoolean(bool != null ? bool.booleanValue() : false);
                } else if (i == 9) {
                    Byte b = obj instanceof Byte ? (Byte) obj : null;
                    dataOutputStream.writeByte(b != null ? b.byteValue() : (byte) 0);
                } else if (i == 10) {
                    Integer num = obj instanceof Integer ? (Integer) obj : null;
                    dataOutputStream.writeInt(num != null ? num.intValue() : 0);
                } else if (i == 11) {
                    Long l = obj instanceof Long ? (Long) obj : null;
                    dataOutputStream.writeLong(l != null ? l.longValue() : 0L);
                } else if (i == 12) {
                    Float f = obj instanceof Float ? (Float) obj : null;
                    dataOutputStream.writeFloat(f != null ? f.floatValue() : 0.0f);
                } else if (i == 13) {
                    Double d = obj instanceof Double ? (Double) obj : null;
                    dataOutputStream.writeDouble(d != null ? d.doubleValue() : 0.0d);
                } else if (i == 14) {
                    String str = obj instanceof String ? (String) obj : null;
                    if (str == null) {
                        str = Data.NULL_STRING_V1;
                    }
                    dataOutputStream.writeUTF(str);
                }
            }
        }

        private static final void toByteArrayInternalV1$writeEntry(DataOutputStream dataOutputStream, String str, Object obj) throws IOException {
            if (obj == null) {
                dataOutputStream.writeByte(0);
            } else if (obj instanceof Boolean) {
                dataOutputStream.writeByte(1);
                dataOutputStream.writeBoolean(((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                dataOutputStream.writeByte(2);
                dataOutputStream.writeByte(((Number) obj).byteValue());
            } else if (obj instanceof Integer) {
                dataOutputStream.writeByte(3);
                dataOutputStream.writeInt(((Number) obj).intValue());
            } else if (obj instanceof Long) {
                dataOutputStream.writeByte(4);
                dataOutputStream.writeLong(((Number) obj).longValue());
            } else if (obj instanceof Float) {
                dataOutputStream.writeByte(5);
                dataOutputStream.writeFloat(((Number) obj).floatValue());
            } else if (obj instanceof Double) {
                dataOutputStream.writeByte(6);
                dataOutputStream.writeDouble(((Number) obj).doubleValue());
            } else if (obj instanceof String) {
                dataOutputStream.writeByte(7);
                dataOutputStream.writeUTF((String) obj);
            } else if (obj instanceof Object[]) {
                toByteArrayInternalV1$writeArray(dataOutputStream, (Object[]) obj);
            } else {
                throw new IllegalArgumentException("Unsupported value type " + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName());
            }
            dataOutputStream.writeUTF(str);
        }

        @JvmStatic
        public final byte[] toByteArrayInternalV1(Data data) {
            Intrinsics.checkNotNullParameter(data, "data");
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    DataOutputStream dataOutputStream2 = dataOutputStream;
                    toByteArrayInternalV1$writeHeader(dataOutputStream2);
                    dataOutputStream2.writeInt(data.size());
                    for (Map.Entry entry : data.values.entrySet()) {
                        toByteArrayInternalV1$writeEntry(dataOutputStream2, (String) entry.getKey(), entry.getValue());
                    }
                    dataOutputStream2.flush();
                    if (dataOutputStream2.size() > 10240) {
                        throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized".toString());
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    CloseableKt.closeFinally(dataOutputStream, null);
                    Intrinsics.checkNotNull(byteArray);
                    return byteArray;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(dataOutputStream, th);
                        throw th2;
                    }
                }
            } catch (IOException e) {
                Logger.get().error(Data_Kt.TAG, "Error in Data#toByteArray: ", e);
                return new byte[0];
            }
        }

        private static final boolean fromByteArray$isObjectStream(ByteArrayInputStream byteArrayInputStream) {
            byte[] bArr = new byte[2];
            byteArrayInputStream.read(bArr);
            boolean z = false;
            if (bArr[0] == -84 && bArr[1] == -19) {
                z = true;
            }
            byteArrayInputStream.reset();
            return z;
        }

        private static final void fromByteArray$readHeader(DataInputStream dataInputStream) throws IOException {
            short s = dataInputStream.readShort();
            if (s != -21521) {
                throw new IllegalStateException(("Magic number doesn't match: " + ((int) s)).toString());
            }
            short s2 = dataInputStream.readShort();
            if (s2 == 1) {
                return;
            }
            throw new IllegalStateException(("Unsupported version number: " + ((int) s2)).toString());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static final Object fromByteArray$readValue(DataInputStream dataInputStream, byte b) throws IOException {
            if (b == 0) {
                return null;
            }
            if (b == 1) {
                return Boolean.valueOf(dataInputStream.readBoolean());
            }
            if (b == 2) {
                return Byte.valueOf(dataInputStream.readByte());
            }
            if (b == 3) {
                return Integer.valueOf(dataInputStream.readInt());
            }
            if (b == 4) {
                return Long.valueOf(dataInputStream.readLong());
            }
            if (b == 5) {
                return Float.valueOf(dataInputStream.readFloat());
            }
            if (b == 6) {
                return Double.valueOf(dataInputStream.readDouble());
            }
            if (b == 7) {
                return dataInputStream.readUTF();
            }
            int i = 0;
            if (b == 8) {
                int i2 = dataInputStream.readInt();
                Boolean[] boolArr = new Boolean[i2];
                while (i < i2) {
                    boolArr[i] = Boolean.valueOf(dataInputStream.readBoolean());
                    i++;
                }
                return (Serializable) boolArr;
            }
            if (b == 9) {
                int i3 = dataInputStream.readInt();
                Byte[] bArr = new Byte[i3];
                while (i < i3) {
                    bArr[i] = Byte.valueOf(dataInputStream.readByte());
                    i++;
                }
                return (Serializable) bArr;
            }
            if (b == 10) {
                int i4 = dataInputStream.readInt();
                Integer[] numArr = new Integer[i4];
                while (i < i4) {
                    numArr[i] = Integer.valueOf(dataInputStream.readInt());
                    i++;
                }
                return (Serializable) numArr;
            }
            if (b == 11) {
                int i5 = dataInputStream.readInt();
                Long[] lArr = new Long[i5];
                while (i < i5) {
                    lArr[i] = Long.valueOf(dataInputStream.readLong());
                    i++;
                }
                return (Serializable) lArr;
            }
            if (b == 12) {
                int i6 = dataInputStream.readInt();
                Float[] fArr = new Float[i6];
                while (i < i6) {
                    fArr[i] = Float.valueOf(dataInputStream.readFloat());
                    i++;
                }
                return (Serializable) fArr;
            }
            if (b == 13) {
                int i7 = dataInputStream.readInt();
                Double[] dArr = new Double[i7];
                while (i < i7) {
                    dArr[i] = Double.valueOf(dataInputStream.readDouble());
                    i++;
                }
                return (Serializable) dArr;
            }
            if (b == 14) {
                int i8 = dataInputStream.readInt();
                String[] strArr = new String[i8];
                while (i < i8) {
                    String utf = dataInputStream.readUTF();
                    if (Intrinsics.areEqual(utf, Data.NULL_STRING_V1)) {
                        utf = null;
                    }
                    strArr[i] = utf;
                    i++;
                }
                return (Serializable) strArr;
            }
            throw new IllegalStateException("Unsupported type " + ((int) b));
        }

        @JvmStatic
        public final Data fromByteArray(byte[] bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (bytes.length > 10240) {
                throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized".toString());
            }
            if (bytes.length == 0) {
                return Data.EMPTY;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                int i = 0;
                if (fromByteArray$isObjectStream(byteArrayInputStream)) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        ObjectInputStream objectInputStream2 = objectInputStream;
                        int i2 = objectInputStream2.readInt();
                        while (i < i2) {
                            linkedHashMap.put(objectInputStream2.readUTF(), objectInputStream2.readObject());
                            i++;
                        }
                        CloseableKt.closeFinally(objectInputStream, null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(objectInputStream, th);
                            throw th2;
                        }
                    }
                } else {
                    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                    try {
                        DataInputStream dataInputStream2 = dataInputStream;
                        fromByteArray$readHeader(dataInputStream2);
                        int i3 = dataInputStream2.readInt();
                        while (i < i3) {
                            linkedHashMap.put(dataInputStream2.readUTF(), fromByteArray$readValue(dataInputStream2, dataInputStream2.readByte()));
                            i++;
                        }
                        CloseableKt.closeFinally(dataInputStream, null);
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(dataInputStream, th3);
                            throw th4;
                        }
                    }
                }
            } catch (IOException e) {
                Logger.get().error(Data_Kt.TAG, "Error in Data#fromByteArray: ", e);
            } catch (ClassNotFoundException e2) {
                Logger.get().error(Data_Kt.TAG, "Error in Data#fromByteArray: ", e2);
            }
            return new Data(linkedHashMap);
        }
    }

    public final boolean[] getBooleanArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        final Object obj = this.values.get(key);
        if (!(obj instanceof Object[])) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        if (!(objArr instanceof Object[])) {
            return null;
        }
        int length = objArr.length;
        Function1<Integer, Boolean> function1 = new Function1<Integer, Boolean>() { // from class: androidx.work.Data$getBooleanArray$$inlined$getTypedArray$1
            public final Boolean invoke(int i) {
                Object obj2 = ((Object[]) obj)[i];
                if (obj2 != null) {
                    return (Boolean) obj2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Boolean, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Integer num) {
                return invoke(num.intValue());
            }
        };
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = function1.invoke(Integer.valueOf(i)).booleanValue();
        }
        return zArr;
    }

    public final byte[] getByteArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        final Object obj = this.values.get(key);
        if (!(obj instanceof Object[])) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        if (!(objArr instanceof Object[])) {
            return null;
        }
        int length = objArr.length;
        Function1<Integer, Byte> function1 = new Function1<Integer, Byte>() { // from class: androidx.work.Data$getByteArray$$inlined$getTypedArray$1
            public final Byte invoke(int i) {
                Object obj2 = ((Object[]) obj)[i];
                if (obj2 != null) {
                    return (Byte) obj2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Byte");
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Byte, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Byte invoke(Integer num) {
                return invoke(num.intValue());
            }
        };
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = function1.invoke(Integer.valueOf(i)).byteValue();
        }
        return bArr;
    }

    public final int[] getIntArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        final Object obj = this.values.get(key);
        if (!(obj instanceof Object[])) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        if (!(objArr instanceof Object[])) {
            return null;
        }
        int length = objArr.length;
        Function1<Integer, Integer> function1 = new Function1<Integer, Integer>() { // from class: androidx.work.Data$getIntArray$$inlined$getTypedArray$1
            public final Integer invoke(int i) {
                Object obj2 = ((Object[]) obj)[i];
                if (obj2 != null) {
                    return (Integer) obj2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Integer, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }
        };
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = function1.invoke(Integer.valueOf(i)).intValue();
        }
        return iArr;
    }

    public final long[] getLongArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        final Object obj = this.values.get(key);
        if (!(obj instanceof Object[])) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        if (!(objArr instanceof Object[])) {
            return null;
        }
        int length = objArr.length;
        Function1<Integer, Long> function1 = new Function1<Integer, Long>() { // from class: androidx.work.Data$getLongArray$$inlined$getTypedArray$1
            public final Long invoke(int i) {
                Object obj2 = ((Object[]) obj)[i];
                if (obj2 != null) {
                    return (Long) obj2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Long, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Long invoke(Integer num) {
                return invoke(num.intValue());
            }
        };
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = function1.invoke(Integer.valueOf(i)).longValue();
        }
        return jArr;
    }

    public final float[] getFloatArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        final Object obj = this.values.get(key);
        if (!(obj instanceof Object[])) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        if (!(objArr instanceof Object[])) {
            return null;
        }
        int length = objArr.length;
        Function1<Integer, Float> function1 = new Function1<Integer, Float>() { // from class: androidx.work.Data$getFloatArray$$inlined$getTypedArray$1
            public final Float invoke(int i) {
                Object obj2 = ((Object[]) obj)[i];
                if (obj2 != null) {
                    return (Float) obj2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Float, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Integer num) {
                return invoke(num.intValue());
            }
        };
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            fArr[i] = function1.invoke(Integer.valueOf(i)).floatValue();
        }
        return fArr;
    }

    public final double[] getDoubleArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        final Object obj = this.values.get(key);
        if (!(obj instanceof Object[])) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        if (!(objArr instanceof Object[])) {
            return null;
        }
        int length = objArr.length;
        Function1<Integer, Double> function1 = new Function1<Integer, Double>() { // from class: androidx.work.Data$getDoubleArray$$inlined$getTypedArray$1
            public final Double invoke(int i) {
                Object obj2 = ((Object[]) obj)[i];
                if (obj2 != null) {
                    return (Double) obj2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Double");
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Double, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Double invoke(Integer num) {
                return invoke(num.intValue());
            }
        };
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = function1.invoke(Integer.valueOf(i)).doubleValue();
        }
        return dArr;
    }

    public final String[] getStringArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        final Object obj = this.values.get(key);
        if (!(obj instanceof Object[])) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        if (!(objArr instanceof Object[])) {
            return null;
        }
        int length = objArr.length;
        Function1<Integer, String> function1 = new Function1<Integer, String>() { // from class: androidx.work.Data$getStringArray$$inlined$getTypedArray$1
            public final String invoke(int i) {
                Object obj2 = ((Object[]) obj)[i];
                if (obj2 != null) {
                    return (String) obj2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.String] */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(Integer num) {
                return invoke(num.intValue());
            }
        };
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = function1.invoke(Integer.valueOf(i));
        }
        return strArr;
    }
}
