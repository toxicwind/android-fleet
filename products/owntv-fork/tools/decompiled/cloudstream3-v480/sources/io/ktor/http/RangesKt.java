package io.ktor.http;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a'\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0005*\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0005*\b\u0012\u0004\u0012\u00020\t0\u0005H\u0000¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"", "rangeSpec", "Lio/ktor/http/RangesSpecifier;", "parseRangesSpecifier", "(Ljava/lang/String;)Lio/ktor/http/RangesSpecifier;", "", "Lio/ktor/http/ContentRange;", "", "contentLength", "Lkotlin/ranges/LongRange;", "toLongRanges", "(Ljava/util/List;J)Ljava/util/List;", "mergeRangesKeepOrder", "(Ljava/util/List;)Ljava/util/List;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class RangesKt {
    public static final RangesSpecifier parseRangesSpecifier(String rangeSpec) {
        Pair pair;
        ContentRange bounded;
        Intrinsics.checkNotNullParameter(rangeSpec, "rangeSpec");
        try {
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) rangeSpec, "=", 0, false, 6, (Object) null);
            if (iIndexOf$default == -1) {
                return null;
            }
            String strSubstring = rangeSpec.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            String strSubstring2 = rangeSpec.substring(iIndexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            Pair pair2 = TuplesKt.to(strSubstring, strSubstring2);
            String str = (String) pair2.component1();
            List<String> listSplit$default = StringsKt.split$default((CharSequence) pair2.component2(), new char[]{AbstractJsonLexerKt.COMMA}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            for (String str2 : listSplit$default) {
                if (StringsKt.startsWith$default(str2, "-", false, 2, (Object) null)) {
                    bounded = new ContentRange.Suffix(Long.parseLong(StringsKt.removePrefix(str2, (CharSequence) "-")));
                } else {
                    int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str2, "-", 0, false, 6, (Object) null);
                    if (iIndexOf$default2 == -1) {
                        pair = TuplesKt.to("", "");
                    } else {
                        String strSubstring3 = str2.substring(0, iIndexOf$default2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                        String strSubstring4 = str2.substring(iIndexOf$default2 + 1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
                        pair = TuplesKt.to(strSubstring3, strSubstring4);
                    }
                    String str3 = (String) pair.component1();
                    String str4 = (String) pair.component2();
                    bounded = str4.length() > 0 ? new ContentRange.Bounded(Long.parseLong(str3), Long.parseLong(str4)) : new ContentRange.TailFrom(Long.parseLong(str3));
                }
                arrayList.add(bounded);
            }
            ArrayList arrayList2 = arrayList;
            if (!arrayList2.isEmpty() && str.length() != 0) {
                RangesSpecifier rangesSpecifier = new RangesSpecifier(str, arrayList2);
                if (RangesSpecifier.isValid$default(rangesSpecifier, null, 1, null)) {
                    return rangesSpecifier;
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static final List<LongRange> toLongRanges(List<? extends ContentRange> list, long j) {
        LongRange longRangeUntil;
        Intrinsics.checkNotNullParameter(list, "<this>");
        List<? extends ContentRange> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (ContentRange contentRange : list2) {
            if (contentRange instanceof ContentRange.Bounded) {
                ContentRange.Bounded bounded = (ContentRange.Bounded) contentRange;
                longRangeUntil = new LongRange(bounded.getFrom(), kotlin.ranges.RangesKt.coerceAtMost(bounded.getTo(), j - 1));
            } else if (contentRange instanceof ContentRange.TailFrom) {
                longRangeUntil = kotlin.ranges.RangesKt.until(((ContentRange.TailFrom) contentRange).getFrom(), j);
            } else {
                if (!(contentRange instanceof ContentRange.Suffix)) {
                    throw new NoWhenBranchMatchedException();
                }
                longRangeUntil = kotlin.ranges.RangesKt.until(kotlin.ranges.RangesKt.coerceAtLeast(j - ((ContentRange.Suffix) contentRange).getLastCount(), 0L), j);
            }
            arrayList.add(longRangeUntil);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!((LongRange) obj).isEmpty()) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public static final List<LongRange> mergeRangesKeepOrder(List<LongRange> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        List<LongRange> listSortedWith = CollectionsKt.sortedWith(list, new Comparator() { // from class: io.ktor.http.RangesKt$mergeRangesKeepOrder$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((LongRange) t).getFirst()), Long.valueOf(((LongRange) t2).getFirst()));
            }
        });
        ArrayList arrayList = new ArrayList(list.size());
        for (LongRange longRange : listSortedWith) {
            if (arrayList.isEmpty()) {
                arrayList.add(longRange);
            } else {
                ArrayList arrayList2 = arrayList;
                if (((LongRange) CollectionsKt.last((List) arrayList2)).getLast() < longRange.getFirst() - 1) {
                    arrayList.add(longRange);
                } else {
                    LongRange longRange2 = (LongRange) CollectionsKt.last((List) arrayList2);
                    arrayList.set(CollectionsKt.getLastIndex(arrayList2), new LongRange(longRange2.getFirst(), Math.max(longRange2.getLast(), longRange.getLast())));
                }
            }
        }
        LongRange[] longRangeArr = new LongRange[list.size()];
        Iterator it = arrayList.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Object next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            LongRange longRange3 = (LongRange) next;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (io.ktor.util.RangesKt.contains(longRange3, list.get(i))) {
                    longRangeArr[i] = longRange3;
                    break;
                }
            }
        }
        return ArraysKt.filterNotNull(longRangeArr);
    }
}
