package androidx.navigation.serialization;

import android.os.Bundle;
import androidx.navigation.CollectionNavType;
import androidx.navigation.NavType;
import androidx.navigation.NavUriUtils;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: NavTypeConverter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\bR!\u0010\u001a\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u001b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR!\u0010\u001d\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u001e0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\bR\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u001f\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u001e0\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\b¨\u0006%"}, d2 = {"Landroidx/navigation/serialization/InternalNavType;", "", "<init>", "()V", "IntNullableType", "Landroidx/navigation/NavType;", "", "getIntNullableType", "()Landroidx/navigation/NavType;", "BoolNullableType", "", "getBoolNullableType", "DoubleType", "", "getDoubleType", "DoubleNullableType", "getDoubleNullableType", "FloatNullableType", "", "getFloatNullableType", "LongNullableType", "", "getLongNullableType", "StringNonNullableType", "", "getStringNonNullableType", "StringNullableArrayType", "", "getStringNullableArrayType", "StringNullableListType", "", "getStringNullableListType", "DoubleArrayType", "", "getDoubleArrayType", "DoubleListType", "getDoubleListType", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class InternalNavType {
    public static final InternalNavType INSTANCE = new InternalNavType();
    private static final NavType<Integer> IntNullableType = new NavType<Integer>() { // from class: androidx.navigation.serialization.InternalNavType$IntNullableType$1
        @Override // androidx.navigation.NavType
        public String getName() {
            return "integer_nullable";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Integer parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, AbstractJsonLexerKt.NULL)) {
                return null;
            }
            return NavType.IntType.parseValue(value);
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Integer value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(SavedStateWriter.m8016constructorimpl(bundle), key);
            } else {
                NavType.IntType.put(bundle, key, value);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Integer get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            return Integer.valueOf(SavedStateReader.m7961getIntimpl(bundleM7930constructorimpl, key));
        }
    };
    private static final NavType<Boolean> BoolNullableType = new NavType<Boolean>() { // from class: androidx.navigation.serialization.InternalNavType$BoolNullableType$1
        @Override // androidx.navigation.NavType
        public String getName() {
            return "boolean_nullable";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Boolean parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, AbstractJsonLexerKt.NULL)) {
                return null;
            }
            return NavType.BoolType.parseValue(value);
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Boolean value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(SavedStateWriter.m8016constructorimpl(bundle), key);
            } else {
                NavType.BoolType.put(bundle, key, value);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Boolean get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            return Boolean.valueOf(SavedStateReader.m7939getBooleanimpl(bundleM7930constructorimpl, key));
        }
    };
    private static final NavType<Double> DoubleType = new NavType<Double>() { // from class: androidx.navigation.serialization.InternalNavType$DoubleType$1
        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Double d) {
            put(bundle, str, d.doubleValue());
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "double";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Double parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return Double.valueOf(Double.parseDouble(value));
        }

        public void put(Bundle bundle, String key, double value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            SavedStateWriter.m8029putDoubleimpl(SavedStateWriter.m8016constructorimpl(bundle), key, value);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Double get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            return Double.valueOf(SavedStateReader.m7953getDoubleimpl(SavedStateReader.m7930constructorimpl(bundle), key));
        }
    };
    private static final NavType<Double> DoubleNullableType = new NavType<Double>() { // from class: androidx.navigation.serialization.InternalNavType$DoubleNullableType$1
        @Override // androidx.navigation.NavType
        public String getName() {
            return "double_nullable";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Double parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, AbstractJsonLexerKt.NULL)) {
                return null;
            }
            return InternalNavType.INSTANCE.getDoubleType().parseValue(value);
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Double value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(SavedStateWriter.m8016constructorimpl(bundle), key);
            } else {
                InternalNavType.INSTANCE.getDoubleType().put(bundle, key, value);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Double get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            return Double.valueOf(SavedStateReader.m7953getDoubleimpl(bundleM7930constructorimpl, key));
        }
    };
    private static final NavType<Float> FloatNullableType = new NavType<Float>() { // from class: androidx.navigation.serialization.InternalNavType$FloatNullableType$1
        @Override // androidx.navigation.NavType
        public String getName() {
            return "float_nullable";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Float parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, AbstractJsonLexerKt.NULL)) {
                return null;
            }
            return NavType.FloatType.parseValue(value);
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Float value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(SavedStateWriter.m8016constructorimpl(bundle), key);
            } else {
                NavType.FloatType.put(bundle, key, value);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Float get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            return Float.valueOf(SavedStateReader.m7957getFloatimpl(bundleM7930constructorimpl, key));
        }
    };
    private static final NavType<Long> LongNullableType = new NavType<Long>() { // from class: androidx.navigation.serialization.InternalNavType$LongNullableType$1
        @Override // androidx.navigation.NavType
        public String getName() {
            return "long_nullable";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Long parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (Intrinsics.areEqual(value, AbstractJsonLexerKt.NULL)) {
                return null;
            }
            return NavType.LongType.parseValue(value);
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, Long value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(SavedStateWriter.m8016constructorimpl(bundle), key);
            } else {
                NavType.LongType.put(bundle, key, value);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.navigation.NavType
        public Long get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            return Long.valueOf(SavedStateReader.m7971getLongimpl(bundleM7930constructorimpl, key));
        }
    };
    private static final NavType<String> StringNonNullableType = new NavType<String>() { // from class: androidx.navigation.serialization.InternalNavType$StringNonNullableType$1
        @Override // androidx.navigation.NavType
        public String parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return value;
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "string_non_nullable";
        }

        @Override // androidx.navigation.NavType
        public String serializeAsValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return NavUriUtils.encode$default(NavUriUtils.INSTANCE, value, null, 2, null);
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, String value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            SavedStateWriter.m8049putStringimpl(SavedStateWriter.m8016constructorimpl(bundle), key, value);
        }

        @Override // androidx.navigation.NavType
        public String get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            return (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) ? AbstractJsonLexerKt.NULL : SavedStateReader.m8001getStringimpl(bundleM7930constructorimpl, key);
        }
    };
    private static final NavType<String[]> StringNullableArrayType = new CollectionNavType<String[]>() { // from class: androidx.navigation.serialization.InternalNavType$StringNullableArrayType$1
        @Override // androidx.navigation.CollectionNavType
        public String[] emptyCollection() {
            return new String[0];
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "string_nullable[]";
        }

        @Override // androidx.navigation.NavType
        public String[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new String[]{NavType.StringType.parseValue(value)};
        }

        @Override // androidx.navigation.NavType
        public String[] parseValue(String value, String[] previousValue) {
            String[] strArr;
            Intrinsics.checkNotNullParameter(value, "value");
            return (previousValue == null || (strArr = (String[]) ArraysKt.plus((Object[]) previousValue, (Object[]) parseValue(value))) == null) ? parseValue(value) : strArr;
        }

        @Override // androidx.navigation.NavType
        public boolean valueEquals(String[] value, String[] other) {
            return ArraysKt.contentDeepEquals(value, other);
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, String[] value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM8016constructorimpl = SavedStateWriter.m8016constructorimpl(bundle);
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(bundleM8016constructorimpl, key);
                return;
            }
            ArrayList arrayList = new ArrayList(value.length);
            for (String str : value) {
                if (str == null) {
                    str = AbstractJsonLexerKt.NULL;
                }
                arrayList.add(str);
            }
            SavedStateWriter.m8050putStringArrayimpl(bundleM8016constructorimpl, key, (String[]) arrayList.toArray(new String[0]));
        }

        @Override // androidx.navigation.NavType
        public String[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            String[] strArrM8002getStringArrayimpl = SavedStateReader.m8002getStringArrayimpl(bundleM7930constructorimpl, key);
            ArrayList arrayList = new ArrayList(strArrM8002getStringArrayimpl.length);
            for (String str : strArrM8002getStringArrayimpl) {
                arrayList.add(NavType.StringType.parseValue(str));
            }
            return (String[]) arrayList.toArray(new String[0]);
        }

        @Override // androidx.navigation.CollectionNavType
        public List<String> serializeAsValues(String[] value) {
            String strEncode$default;
            if (value == null) {
                return CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList(value.length);
            for (String str : value) {
                if (str == null || (strEncode$default = NavUriUtils.encode$default(NavUriUtils.INSTANCE, str, null, 2, null)) == null) {
                    strEncode$default = AbstractJsonLexerKt.NULL;
                }
                arrayList.add(strEncode$default);
            }
            return arrayList;
        }
    };
    private static final NavType<List<String>> StringNullableListType = new CollectionNavType<List<? extends String>>() { // from class: androidx.navigation.serialization.InternalNavType$StringNullableListType$1
        @Override // androidx.navigation.CollectionNavType
        public /* bridge */ /* synthetic */ List serializeAsValues(List<? extends String> list) {
            return serializeAsValues2((List<String>) list);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "List<String?>";
        }

        @Override // androidx.navigation.NavType
        public List<String> parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return CollectionsKt.listOf(NavType.StringType.parseValue(value));
        }

        @Override // androidx.navigation.NavType
        public List<String> parseValue(String value, List<String> previousValue) {
            List<String> listPlus;
            Intrinsics.checkNotNullParameter(value, "value");
            return (previousValue == null || (listPlus = CollectionsKt.plus((Collection) previousValue, (Iterable) parseValue(value))) == null) ? parseValue(value) : listPlus;
        }

        @Override // androidx.navigation.NavType
        public boolean valueEquals(List<String> value, List<String> other) {
            return ArraysKt.contentDeepEquals(value != null ? (String[]) value.toArray(new String[0]) : null, other != null ? (String[]) other.toArray(new String[0]) : null);
        }

        /* JADX INFO: renamed from: serializeAsValues, reason: avoid collision after fix types in other method */
        public List<String> serializeAsValues2(List<String> value) {
            String strEncode$default;
            if (value != null) {
                List<String> list = value;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (String str : list) {
                    if (str == null || (strEncode$default = NavUriUtils.encode$default(NavUriUtils.INSTANCE, str, null, 2, null)) == null) {
                        strEncode$default = AbstractJsonLexerKt.NULL;
                    }
                    arrayList.add(strEncode$default);
                }
                return arrayList;
            }
            return CollectionsKt.emptyList();
        }

        @Override // androidx.navigation.CollectionNavType
        public List<? extends String> emptyCollection() {
            return CollectionsKt.emptyList();
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, List<String> value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM8016constructorimpl = SavedStateWriter.m8016constructorimpl(bundle);
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(bundleM8016constructorimpl, key);
                return;
            }
            List<String> list = value;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str : list) {
                if (str == null) {
                    str = AbstractJsonLexerKt.NULL;
                }
                arrayList.add(str);
            }
            SavedStateWriter.m8050putStringArrayimpl(bundleM8016constructorimpl, key, (String[]) arrayList.toArray(new String[0]));
        }

        @Override // androidx.navigation.NavType
        public List<String> get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            List list = ArraysKt.toList(SavedStateReader.m8002getStringArrayimpl(bundleM7930constructorimpl, key));
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(NavType.StringType.parseValue((String) it.next()));
            }
            return arrayList;
        }
    };
    private static final NavType<double[]> DoubleArrayType = new CollectionNavType<double[]>() { // from class: androidx.navigation.serialization.InternalNavType$DoubleArrayType$1
        @Override // androidx.navigation.CollectionNavType
        public double[] emptyCollection() {
            return new double[0];
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "double[]";
        }

        @Override // androidx.navigation.NavType
        public double[] parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new double[]{InternalNavType.INSTANCE.getDoubleType().parseValue(value).doubleValue()};
        }

        @Override // androidx.navigation.NavType
        public double[] parseValue(String value, double[] previousValue) {
            double[] dArrPlus;
            Intrinsics.checkNotNullParameter(value, "value");
            return (previousValue == null || (dArrPlus = ArraysKt.plus(previousValue, parseValue(value))) == null) ? parseValue(value) : dArrPlus;
        }

        @Override // androidx.navigation.NavType
        public boolean valueEquals(double[] value, double[] other) {
            return ArraysKt.contentDeepEquals(value != null ? ArraysKt.toTypedArray(value) : null, other != null ? ArraysKt.toTypedArray(other) : null);
        }

        @Override // androidx.navigation.CollectionNavType
        public List<String> serializeAsValues(double[] value) {
            List<Double> list;
            if (value == null || (list = ArraysKt.toList(value)) == null) {
                return CollectionsKt.emptyList();
            }
            List<Double> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(String.valueOf(((Number) it.next()).doubleValue()));
            }
            return arrayList;
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, double[] value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM8016constructorimpl = SavedStateWriter.m8016constructorimpl(bundle);
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(bundleM8016constructorimpl, key);
            } else {
                SavedStateWriter.m8030putDoubleArrayimpl(bundleM8016constructorimpl, key, value);
            }
        }

        @Override // androidx.navigation.NavType
        public double[] get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            return SavedStateReader.m7954getDoubleArrayimpl(bundleM7930constructorimpl, key);
        }
    };
    private static final NavType<List<Double>> DoubleListType = new CollectionNavType<List<? extends Double>>() { // from class: androidx.navigation.serialization.InternalNavType$DoubleListType$1
        @Override // androidx.navigation.CollectionNavType
        public /* bridge */ /* synthetic */ List serializeAsValues(List<? extends Double> list) {
            return serializeAsValues2((List<Double>) list);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return "List<Double>";
        }

        @Override // androidx.navigation.NavType
        public List<Double> parseValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return CollectionsKt.listOf(InternalNavType.INSTANCE.getDoubleType().parseValue(value));
        }

        @Override // androidx.navigation.NavType
        public List<Double> parseValue(String value, List<Double> previousValue) {
            List<Double> listPlus;
            Intrinsics.checkNotNullParameter(value, "value");
            return (previousValue == null || (listPlus = CollectionsKt.plus((Collection) previousValue, (Iterable) parseValue(value))) == null) ? parseValue(value) : listPlus;
        }

        @Override // androidx.navigation.NavType
        public boolean valueEquals(List<Double> value, List<Double> other) {
            return ArraysKt.contentDeepEquals(value != null ? (Double[]) value.toArray(new Double[0]) : null, other != null ? (Double[]) other.toArray(new Double[0]) : null);
        }

        /* JADX INFO: renamed from: serializeAsValues, reason: avoid collision after fix types in other method */
        public List<String> serializeAsValues2(List<Double> value) {
            if (value != null) {
                List<Double> list = value;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(String.valueOf(((Number) it.next()).doubleValue()));
                }
                return arrayList;
            }
            return CollectionsKt.emptyList();
        }

        @Override // androidx.navigation.CollectionNavType
        public List<? extends Double> emptyCollection() {
            return CollectionsKt.emptyList();
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String key, List<Double> value) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM8016constructorimpl = SavedStateWriter.m8016constructorimpl(bundle);
            if (value == null) {
                SavedStateWriter.m8039putNullimpl(bundleM8016constructorimpl, key);
            } else {
                SavedStateWriter.m8030putDoubleArrayimpl(bundleM8016constructorimpl, key, CollectionsKt.toDoubleArray(value));
            }
        }

        @Override // androidx.navigation.NavType
        public List<Double> get(Bundle bundle, String key) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(key, "key");
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
                return null;
            }
            return ArraysKt.toList(SavedStateReader.m7954getDoubleArrayimpl(bundleM7930constructorimpl, key));
        }
    };

    private InternalNavType() {
    }

    public final NavType<Integer> getIntNullableType() {
        return IntNullableType;
    }

    public final NavType<Boolean> getBoolNullableType() {
        return BoolNullableType;
    }

    public final NavType<Double> getDoubleType() {
        return DoubleType;
    }

    public final NavType<Double> getDoubleNullableType() {
        return DoubleNullableType;
    }

    public final NavType<Float> getFloatNullableType() {
        return FloatNullableType;
    }

    public final NavType<Long> getLongNullableType() {
        return LongNullableType;
    }

    public final NavType<String> getStringNonNullableType() {
        return StringNonNullableType;
    }

    public final NavType<String[]> getStringNullableArrayType() {
        return StringNullableArrayType;
    }

    public final NavType<List<String>> getStringNullableListType() {
        return StringNullableListType;
    }

    public final NavType<double[]> getDoubleArrayType() {
        return DoubleArrayType;
    }

    public final NavType<List<Double>> getDoubleListType() {
        return DoubleListType;
    }
}
