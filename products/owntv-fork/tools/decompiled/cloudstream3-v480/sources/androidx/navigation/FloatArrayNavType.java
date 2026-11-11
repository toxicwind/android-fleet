package androidx.navigation;

import android.os.Bundle;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NavType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J&\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u001f\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0096\u0002J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016J\u001c\u0010\u0013\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00172\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0018\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/navigation/FloatArrayNavType;", "Landroidx/navigation/CollectionNavType;", "", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "put", "", "bundle", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "key", ES6Iterator.VALUE_PROPERTY, "get", "parseValue", "previousValue", "valueEquals", "", "other", "serializeAsValues", "", "emptyCollection", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FloatArrayNavType extends CollectionNavType<float[]> {
    @Override // androidx.navigation.CollectionNavType
    public float[] emptyCollection() {
        return new float[0];
    }

    public FloatArrayNavType() {
        super(true);
    }

    @Override // androidx.navigation.NavType
    public String getName() {
        return "float[]";
    }

    @Override // androidx.navigation.NavType
    public float[] parseValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new float[]{NavType.FloatType.parseValue(value).floatValue()};
    }

    @Override // androidx.navigation.NavType
    public float[] parseValue(String value, float[] previousValue) {
        float[] fArrPlus;
        Intrinsics.checkNotNullParameter(value, "value");
        return (previousValue == null || (fArrPlus = ArraysKt.plus(previousValue, parseValue(value))) == null) ? parseValue(value) : fArrPlus;
    }

    @Override // androidx.navigation.NavType
    public boolean valueEquals(float[] value, float[] other) {
        return ArraysKt.contentDeepEquals(value != null ? ArraysKt.toTypedArray(value) : null, other != null ? ArraysKt.toTypedArray(other) : null);
    }

    @Override // androidx.navigation.CollectionNavType
    public List<String> serializeAsValues(float[] value) {
        List<Float> list;
        if (value == null || (list = ArraysKt.toList(value)) == null) {
            return CollectionsKt.emptyList();
        }
        List<Float> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).floatValue()));
        }
        return arrayList;
    }

    @Override // androidx.navigation.NavType
    public void put(Bundle bundle, String key, float[] value) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle bundleM8016constructorimpl = SavedStateWriter.m8016constructorimpl(bundle);
        if (value != null) {
            SavedStateWriter.m8032putFloatArrayimpl(bundleM8016constructorimpl, key, value);
        } else {
            SavedStateWriter.m8039putNullimpl(bundleM8016constructorimpl, key);
        }
    }

    @Override // androidx.navigation.NavType
    public float[] get(Bundle bundle, String key) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
        if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
            return null;
        }
        return SavedStateReader.m7958getFloatArrayimpl(bundleM7930constructorimpl, key);
    }
}
