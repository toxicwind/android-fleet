package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.OpenEndRange;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: YearMonthRange.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001\u0016B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u001a\u0010\f\u001a\u00020\u00038VX\u0097\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\n¨\u0006\u0017"}, d2 = {"Lkotlinx/datetime/YearMonthRange;", "Lkotlinx/datetime/YearMonthProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlinx/datetime/YearMonth;", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "<init>", "(Lkotlinx/datetime/YearMonth;Lkotlinx/datetime/YearMonth;)V", "getStart", "()Lkotlinx/datetime/YearMonth;", "getEndInclusive", "endExclusive", "getEndExclusive$annotations", "()V", "getEndExclusive", "contains", "", ES6Iterator.VALUE_PROPERTY, "isEmpty", "toString", "", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class YearMonthRange extends YearMonthProgression implements ClosedRange<YearMonth>, OpenEndRange<YearMonth> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final YearMonthRange EMPTY = new YearMonthRange(new YearMonth(0, 2), new YearMonth(0, 1));

    @Deprecated(level = DeprecationLevel.WARNING, message = "This throws an exception if the exclusive end if not inside the platform-specific boundaries for YearMonth. The 'endInclusive' property does not throw and should be preferred.")
    public static /* synthetic */ void getEndExclusive$annotations() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public YearMonthRange(YearMonth start, YearMonth endInclusive) {
        super(start, endInclusive, 1L);
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(endInclusive, "endInclusive");
    }

    @Override // kotlin.ranges.ClosedRange
    public YearMonth getStart() {
        return getFirst();
    }

    @Override // kotlin.ranges.ClosedRange
    public YearMonth getEndInclusive() {
        return getLast();
    }

    @Override // kotlin.ranges.OpenEndRange
    public YearMonth getEndExclusive() {
        if (Intrinsics.areEqual(getLast(), YearMonthKt.getMAX(YearMonth.INSTANCE))) {
            throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes YearMonth.MAX.".toString());
        }
        return YearMonthKt.plus(getEndInclusive(), 1, DateTimeUnit.INSTANCE.getMONTH());
    }

    @Override // kotlin.ranges.ClosedRange
    public boolean contains(YearMonth value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return getFirst().compareTo(value) <= 0 && value.compareTo(getLast()) <= 0;
    }

    @Override // kotlinx.datetime.YearMonthProgression, java.util.Collection
    public boolean isEmpty() {
        return getFirst().compareTo(getLast()) > 0;
    }

    @Override // kotlinx.datetime.YearMonthProgression
    public String toString() {
        return getFirst() + ".." + getLast();
    }

    /* JADX INFO: compiled from: YearMonthRange.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\fJ\u001d\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/YearMonthRange$Companion;", "", "<init>", "()V", "EMPTY", "Lkotlinx/datetime/YearMonthRange;", "getEMPTY", "()Lkotlinx/datetime/YearMonthRange;", "fromRangeUntil", "start", "Lkotlinx/datetime/YearMonth;", "endExclusive", "fromRangeUntil$kotlinx_datetime", "fromRangeTo", "endInclusive", "fromRangeTo$kotlinx_datetime", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final YearMonthRange getEMPTY() {
            return YearMonthRange.EMPTY;
        }

        public final YearMonthRange fromRangeUntil$kotlinx_datetime(YearMonth start, YearMonth endExclusive) {
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(endExclusive, "endExclusive");
            return Intrinsics.areEqual(endExclusive, YearMonthKt.getMIN(YearMonth.INSTANCE)) ? getEMPTY() : fromRangeTo$kotlinx_datetime(start, YearMonthKt.minus(endExclusive, 1, DateTimeUnit.INSTANCE.getMONTH()));
        }

        public final YearMonthRange fromRangeTo$kotlinx_datetime(YearMonth start, YearMonth endInclusive) {
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(endInclusive, "endInclusive");
            return new YearMonthRange(start, endInclusive);
        }
    }
}
