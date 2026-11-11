package io.ktor.http;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: RangesSpecifier.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\t\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\nJ#\u0010\u000e\u001a\u00020\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00042\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00042\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0017J\u0017\u0010\u0018\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u001c*\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001bJ\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b \u0010!J*\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\"\u0010#J\u001b\u0010%\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b%\u0010&J\u0011\u0010'\u001a\u00020\u0012HÖ\u0081\u0004¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010\u001bR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010+\u001a\u0004\b,\u0010!¨\u0006-"}, d2 = {"Lio/ktor/http/RangesSpecifier;", "", "", "unit", "", "Lio/ktor/http/ContentRange;", "ranges", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "Lio/ktor/http/RangeUnits;", "(Lio/ktor/http/RangeUnits;Ljava/util/List;)V", "Lkotlin/Function1;", "", "rangeUnitPredicate", "isValid", "(Lkotlin/jvm/functions/Function1;)Z", "", "length", "", "maxRangeCount", "Lkotlin/ranges/LongRange;", "merge", "(JI)Ljava/util/List;", "(J)Ljava/util/List;", "mergeToSingle", "(J)Lkotlin/ranges/LongRange;", "toString", "()Ljava/lang/String;", ExifInterface.GPS_DIRECTION_TRUE, "toList", "(Ljava/lang/Object;)Ljava/util/List;", "component1", "component2", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/util/List;)Lio/ktor/http/RangesSpecifier;", "other", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "()I", "Ljava/lang/String;", "getUnit", "Ljava/util/List;", "getRanges", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class RangesSpecifier {
    private final List<ContentRange> ranges;
    private final String unit;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RangesSpecifier copy$default(RangesSpecifier rangesSpecifier, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rangesSpecifier.unit;
        }
        if ((i & 2) != 0) {
            list = rangesSpecifier.ranges;
        }
        return rangesSpecifier.copy(str, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUnit() {
        return this.unit;
    }

    public final List<ContentRange> component2() {
        return this.ranges;
    }

    public final RangesSpecifier copy(String unit, List<? extends ContentRange> ranges) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(ranges, "ranges");
        return new RangesSpecifier(unit, ranges);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RangesSpecifier)) {
            return false;
        }
        RangesSpecifier rangesSpecifier = (RangesSpecifier) other;
        return Intrinsics.areEqual(this.unit, rangesSpecifier.unit) && Intrinsics.areEqual(this.ranges, rangesSpecifier.ranges);
    }

    public int hashCode() {
        return (this.unit.hashCode() * 31) + this.ranges.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RangesSpecifier(String unit, List<? extends ContentRange> ranges) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(ranges, "ranges");
        this.unit = unit;
        this.ranges = ranges;
        if (ranges.isEmpty()) {
            throw new IllegalArgumentException("It should be at least one range".toString());
        }
    }

    public /* synthetic */ RangesSpecifier(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? RangeUnits.Bytes.getUnitToken() : str, (List<? extends ContentRange>) list);
    }

    public final List<ContentRange> getRanges() {
        return this.ranges;
    }

    public final String getUnit() {
        return this.unit;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RangesSpecifier(RangeUnits unit, List<? extends ContentRange> ranges) {
        this(unit.getUnitToken(), ranges);
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(ranges, "ranges");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean isValid$default(RangesSpecifier rangesSpecifier, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: io.ktor.http.RangesSpecifier$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(RangesSpecifier.isValid$lambda$0((String) obj2));
                }
            };
        }
        return rangesSpecifier.isValid(function1);
    }

    static final boolean isValid$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it, RangeUnits.Bytes.getUnitToken());
    }

    public final boolean isValid(Function1<? super String, Boolean> rangeUnitPredicate) {
        Intrinsics.checkNotNullParameter(rangeUnitPredicate, "rangeUnitPredicate");
        if (!rangeUnitPredicate.invoke(this.unit).booleanValue()) {
            return false;
        }
        List<ContentRange> list = this.ranges;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        for (ContentRange contentRange : list) {
            if (contentRange instanceof ContentRange.Bounded) {
                ContentRange.Bounded bounded = (ContentRange.Bounded) contentRange;
                if (bounded.getFrom() < 0 || bounded.getTo() < bounded.getFrom()) {
                    return false;
                }
            } else if (contentRange instanceof ContentRange.TailFrom) {
                if (((ContentRange.TailFrom) contentRange).getFrom() < 0) {
                    return false;
                }
            } else {
                if (!(contentRange instanceof ContentRange.Suffix)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (((ContentRange.Suffix) contentRange).getLastCount() < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static /* synthetic */ List merge$default(RangesSpecifier rangesSpecifier, long j, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 50;
        }
        return rangesSpecifier.merge(j, i);
    }

    public final List<LongRange> merge(long length, int maxRangeCount) {
        if (this.ranges.size() > maxRangeCount) {
            return toList(mergeToSingle(length));
        }
        return merge(length);
    }

    public final List<LongRange> merge(long length) {
        return RangesKt.mergeRangesKeepOrder(RangesKt.toLongRanges(this.ranges, length));
    }

    public final LongRange mergeToSingle(long length) {
        Object next;
        List<LongRange> longRanges = RangesKt.toLongRanges(this.ranges, length);
        Object next2 = null;
        if (longRanges.isEmpty()) {
            return null;
        }
        List<LongRange> list = longRanges;
        Iterator<T> it = list.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                long first = ((LongRange) next).getFirst();
                do {
                    Object next3 = it.next();
                    long first2 = ((LongRange) next3).getFirst();
                    if (first > first2) {
                        next = next3;
                        first = first2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Intrinsics.checkNotNull(next);
        long first3 = ((LongRange) next).getFirst();
        Iterator<T> it2 = list.iterator();
        if (it2.hasNext()) {
            next2 = it2.next();
            if (it2.hasNext()) {
                long last = ((LongRange) next2).getLast();
                do {
                    Object next4 = it2.next();
                    long last2 = ((LongRange) next4).getLast();
                    if (last < last2) {
                        next2 = next4;
                        last = last2;
                    }
                } while (it2.hasNext());
            }
        }
        Intrinsics.checkNotNull(next2);
        return new LongRange(first3, kotlin.ranges.RangesKt.coerceAtMost(((LongRange) next2).getLast(), length - 1));
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.ranges, ",", this.unit + '=', null, 0, null, null, 60, null);
    }

    private final <T> List<T> toList(T t) {
        return t == null ? CollectionsKt.emptyList() : CollectionsKt.listOf(t);
    }
}
