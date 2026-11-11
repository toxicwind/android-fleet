package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: HotReloader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\b\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u001e\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\b\u0012\u00060\u000bj\u0002`\f\u0012\u0004\u0012\u00020\r0\n0\tH\u0007\u001a\u001a\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\r0\n0\tH\u0007\u001a\b\u0010\u0010\u001a\u00020\u0001H\u0007¨\u0006\u0011"}, d2 = {"simulateHotReload", "", "context", "", "invalidateGroupsWithKey", "key", "", "disableHotReloadMode", "currentCompositionErrors", "", "Lkotlin/Pair;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "getCurrentCompositionErrors", "", "clearCompositionErrors", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class HotReloaderKt {
    public static final void simulateHotReload(Object obj) {
        HotReloader.INSTANCE.simulateHotReload$runtime(obj);
    }

    public static final void invalidateGroupsWithKey(int i) {
        HotReloader.INSTANCE.invalidateGroupsWithKey(i);
    }

    public static final void disableHotReloadMode() {
        Recomposer.INSTANCE.setHotReloadEnabled$runtime(false);
    }

    @Deprecated(message = "currentCompositionErrors only reports errors that extend from Exception. This method is unsupported outside of Compose runtime tests. Internally, getCurrentCompositionErrors should be used instead.")
    public static final List<Pair<Exception, Boolean>> currentCompositionErrors() {
        List<Pair<Throwable, Boolean>> currentCompositionErrors = getCurrentCompositionErrors();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = currentCompositionErrors.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            Throwable th = (Throwable) pair.component1();
            Boolean bool = (Boolean) pair.component2();
            bool.booleanValue();
            Exception exc = th instanceof Exception ? (Exception) th : null;
            Pair pair2 = exc != null ? TuplesKt.to(exc, bool) : null;
            if (pair2 != null) {
                arrayList.add(pair2);
            }
        }
        return arrayList;
    }

    public static final List<Pair<Throwable, Boolean>> getCurrentCompositionErrors() {
        List<RecomposerErrorInfo> currentErrors = HotReloader.INSTANCE.getCurrentErrors();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(currentErrors, 10));
        for (RecomposerErrorInfo recomposerErrorInfo : currentErrors) {
            arrayList.add(TuplesKt.to(recomposerErrorInfo.getCause(), Boolean.valueOf(recomposerErrorInfo.getRecoverable())));
        }
        return arrayList;
    }

    public static final void clearCompositionErrors() {
        HotReloader.INSTANCE.clearErrors();
    }
}
