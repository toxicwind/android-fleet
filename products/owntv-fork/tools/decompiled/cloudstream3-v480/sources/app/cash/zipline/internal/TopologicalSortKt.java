package app.cash.zipline.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: topologicalSort.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a8\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0000\u001a2\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0000¨\u0006\b"}, d2 = {"topologicalSort", "", ExifInterface.GPS_DIRECTION_TRUE, "sourceToTarget", "Lkotlin/Function1;", "", "isTopologicallySorted", "", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TopologicalSortKt {
    public static final <T> List<T> topologicalSort(List<? extends T> list, Function1<? super T, ? extends Iterable<? extends T>> sourceToTarget) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(sourceToTarget, "sourceToTarget");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayDeque arrayDeque = new ArrayDeque();
        for (T t : list) {
            boolean z = false;
            for (T t2 : sourceToTarget.invoke(t)) {
                Object obj = linkedHashMap.get(t2);
                if (obj == null) {
                    obj = (Set) new LinkedHashSet();
                    linkedHashMap.put(t2, obj);
                }
                ((Set) obj).add(t);
                z = true;
            }
            if (!z) {
                arrayDeque.add(t);
            }
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            ArrayDeque arrayDeque2 = arrayDeque;
            if (arrayDeque2.isEmpty()) {
                break;
            }
            Object objRemoveFirst = arrayDeque.removeFirst();
            arrayList.add(objRemoveFirst);
            Set setEmptySet = (Set) linkedHashMap.get(objRemoveFirst);
            if (setEmptySet == null) {
                setEmptySet = SetsKt.emptySet();
            }
            for (Object obj2 : setEmptySet) {
                if (!arrayDeque.contains(obj2)) {
                    Iterable<? extends T> iterableInvoke = sourceToTarget.invoke(obj2);
                    if (!(iterableInvoke instanceof Collection) || !((Collection) iterableInvoke).isEmpty()) {
                        Iterator<? extends T> it = iterableInvoke.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                T next = it.next();
                                if (!arrayList.contains(next) && !arrayDeque.contains(next)) {
                                    break;
                                }
                            }
                        }
                    }
                    arrayDeque2.add(obj2);
                    break;
                }
            }
        }
        if (arrayList.size() == list.size()) {
            return arrayList;
        }
        StringBuilder sb = new StringBuilder("No topological ordering is possible for these items:");
        ArrayList arrayList2 = arrayList;
        for (Object obj3 : SetsKt.minus(CollectionsKt.toSet(list), (Iterable) CollectionsKt.toSet(arrayList2))) {
            sb.append("\n  ");
            sb.append(obj3);
            CollectionsKt.joinTo(SetsKt.minus(CollectionsKt.toSet(sourceToTarget.invoke(obj3)), (Iterable) CollectionsKt.toSet(arrayList2)), sb, (60 & 2) != 0 ? ", " : ", ", (60 & 4) != 0 ? "" : " (", (60 & 8) != 0 ? "" : ")", (60 & 16) != 0 ? -1 : 0, (60 & 32) != 0 ? "..." : null, (60 & 64) != 0 ? null : null);
        }
        throw new IllegalArgumentException(sb.toString().toString());
    }

    public static final <T> boolean isTopologicallySorted(List<? extends T> list, Function1<? super T, ? extends Iterable<? extends T>> sourceToTarget) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(sourceToTarget, "sourceToTarget");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (T t : list) {
            Iterable<? extends T> iterableInvoke = sourceToTarget.invoke(t);
            if (!(iterableInvoke instanceof Collection) || !((Collection) iterableInvoke).isEmpty()) {
                Iterator<? extends T> it = iterableInvoke.iterator();
                while (it.hasNext()) {
                    if (!linkedHashSet.contains(it.next())) {
                        return false;
                    }
                }
            }
            linkedHashSet.add(t);
        }
        return true;
    }
}
