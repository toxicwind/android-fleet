package androidx.navigation;

import android.os.Bundle;
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
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NavType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J%\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0096\u0002J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J(\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\u0010\u001a\u00020\u00072\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J(\u0010\u0014\u001a\u00020\u00032\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00022\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Landroidx/navigation/BoolListNavType;", "Landroidx/navigation/CollectionNavType;", "", "", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "put", "", "bundle", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "key", ES6Iterator.VALUE_PROPERTY, "get", "parseValue", "previousValue", "valueEquals", "other", "serializeAsValues", "emptyCollection", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BoolListNavType extends CollectionNavType<List<? extends Boolean>> {
    public BoolListNavType() {
        super(true);
    }

    @Override // androidx.navigation.CollectionNavType
    public /* bridge */ /* synthetic */ List serializeAsValues(List<? extends Boolean> list) {
        return serializeAsValues2((List<Boolean>) list);
    }

    @Override // androidx.navigation.NavType
    public String getName() {
        return "List<Boolean>";
    }

    @Override // androidx.navigation.NavType
    public List<Boolean> parseValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return CollectionsKt.listOf(NavType.BoolType.parseValue(value));
    }

    @Override // androidx.navigation.NavType
    public List<Boolean> parseValue(String value, List<Boolean> previousValue) {
        List<Boolean> listPlus;
        Intrinsics.checkNotNullParameter(value, "value");
        return (previousValue == null || (listPlus = CollectionsKt.plus((Collection) previousValue, (Iterable) parseValue(value))) == null) ? parseValue(value) : listPlus;
    }

    @Override // androidx.navigation.NavType
    public boolean valueEquals(List<Boolean> value, List<Boolean> other) {
        return ArraysKt.contentDeepEquals(value != null ? (Boolean[]) value.toArray(new Boolean[0]) : null, other != null ? (Boolean[]) other.toArray(new Boolean[0]) : null);
    }

    /* JADX INFO: renamed from: serializeAsValues, reason: avoid collision after fix types in other method */
    public List<String> serializeAsValues2(List<Boolean> value) {
        if (value != null) {
            List<Boolean> list = value;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(String.valueOf(((Boolean) it.next()).booleanValue()));
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    @Override // androidx.navigation.CollectionNavType
    public List<? extends Boolean> emptyCollection() {
        return CollectionsKt.emptyList();
    }

    @Override // androidx.navigation.NavType
    public void put(Bundle bundle, String key, List<Boolean> value) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle bundleM8016constructorimpl = SavedStateWriter.m8016constructorimpl(bundle);
        if (value != null) {
            SavedStateWriter.m8023putBooleanArrayimpl(bundleM8016constructorimpl, key, CollectionsKt.toBooleanArray(value));
        } else {
            SavedStateWriter.m8039putNullimpl(bundleM8016constructorimpl, key);
        }
    }

    @Override // androidx.navigation.NavType
    public List<Boolean> get(Bundle bundle, String key) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
        if (!SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, key) || SavedStateReader.m8009isNullimpl(bundleM7930constructorimpl, key)) {
            return null;
        }
        return ArraysKt.toList(SavedStateReader.m7940getBooleanArrayimpl(bundleM7930constructorimpl, key));
    }
}
