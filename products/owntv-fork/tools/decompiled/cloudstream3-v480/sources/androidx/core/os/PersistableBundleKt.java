package androidx.core.os;

import android.os.PersistableBundle;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;

/* JADX INFO: compiled from: PersistableBundle.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\u001a=\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0018\u0010\b\u001a\u00020\u0001*\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\t¨\u0006\n"}, d2 = {"persistableBundleOf", "Landroid/os/PersistableBundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/PersistableBundle;", "toPersistableBundle", "", "core-ktx"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PersistableBundleKt {
    @Deprecated(message = "This method does not provide type safety at compile time. Use the platform `PersistableBundle` class directly instead.")
    public static final PersistableBundle persistableBundleOf(Pair<String, ? extends Object>... pairArr) {
        PersistableBundle persistableBundle = new PersistableBundle(pairArr.length);
        for (Pair<String, ? extends Object> pair : pairArr) {
            PersistableBundleApi21ImplKt.putValue(persistableBundle, pair.component1(), pair.component2());
        }
        return persistableBundle;
    }

    public static final PersistableBundle persistableBundleOf() {
        return new PersistableBundle(0);
    }

    public static final PersistableBundle toPersistableBundle(Map<String, ? extends Object> map) {
        PersistableBundle persistableBundle = new PersistableBundle(map.size());
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            PersistableBundleApi21ImplKt.putValue(persistableBundle, entry.getKey(), entry.getValue());
        }
        return persistableBundle;
    }
}
