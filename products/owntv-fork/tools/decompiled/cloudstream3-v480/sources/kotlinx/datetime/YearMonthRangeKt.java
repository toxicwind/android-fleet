package kotlinx.datetime;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlinx.datetime.internal.MathKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: YearMonthRange.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\f\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0002*\u00020\u0002\u001a\u001a\u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u001a\u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0015\u0010\r\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0086\u0004\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010¨\u0006\u0012"}, d2 = {"first", "Lkotlinx/datetime/YearMonth;", "Lkotlinx/datetime/YearMonthProgression;", "last", "firstOrNull", "lastOrNull", "reversed", "step", ES6Iterator.VALUE_PROPERTY, "", "unit", "Lkotlinx/datetime/DateTimeUnit$MonthBased;", "", "downTo", "that", "random", "Lkotlin/random/Random;", "randomOrNull", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class YearMonthRangeKt {
    public static final YearMonth first(YearMonthProgression yearMonthProgression) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        if (yearMonthProgression.isEmpty()) {
            throw new NoSuchElementException("Progression " + yearMonthProgression + " is empty.");
        }
        return yearMonthProgression.getFirst();
    }

    public static final YearMonth last(YearMonthProgression yearMonthProgression) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        if (yearMonthProgression.isEmpty()) {
            throw new NoSuchElementException("Progression " + yearMonthProgression + " is empty.");
        }
        return yearMonthProgression.getLast();
    }

    public static final YearMonth firstOrNull(YearMonthProgression yearMonthProgression) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        if (yearMonthProgression.isEmpty()) {
            return null;
        }
        return yearMonthProgression.getFirst();
    }

    public static final YearMonth lastOrNull(YearMonthProgression yearMonthProgression) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        if (yearMonthProgression.isEmpty()) {
            return null;
        }
        return yearMonthProgression.getLast();
    }

    public static final YearMonthProgression reversed(YearMonthProgression yearMonthProgression) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        return new YearMonthProgression(RangesKt.reversed(yearMonthProgression.getLongProgression()));
    }

    public static final YearMonthProgression step(YearMonthProgression yearMonthProgression, int i, DateTimeUnit.MonthBased unit) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return step(yearMonthProgression, i, unit);
    }

    public static final YearMonthProgression step(YearMonthProgression yearMonthProgression, long j, DateTimeUnit.MonthBased unit) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new YearMonthProgression(RangesKt.step(yearMonthProgression.getLongProgression(), MathKt.safeMultiplyOrClamp(j, unit.getMonths())));
    }

    public static final YearMonthProgression downTo(YearMonth yearMonth, YearMonth that) {
        Intrinsics.checkNotNullParameter(yearMonth, "<this>");
        Intrinsics.checkNotNullParameter(that, "that");
        return YearMonthProgression.INSTANCE.fromClosedRange$kotlinx_datetime(yearMonth, that, -1L, DateTimeUnit.INSTANCE.getMONTH());
    }

    public static /* synthetic */ YearMonth random$default(YearMonthProgression yearMonthProgression, Random random, int i, Object obj) {
        if ((i & 1) != 0) {
            random = Random.INSTANCE;
        }
        return random(yearMonthProgression, random);
    }

    public static final YearMonth random(YearMonthProgression yearMonthProgression, Random random) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (yearMonthProgression.isEmpty()) {
            throw new NoSuchElementException("Cannot get random in empty range: " + yearMonthProgression);
        }
        return YearMonthKt.fromProlepticMonth(YearMonth.INSTANCE, MathKt.randomUnsafe(yearMonthProgression.getLongProgression(), random));
    }

    public static final YearMonth randomOrNull(YearMonthProgression yearMonthProgression, Random random) {
        Intrinsics.checkNotNullParameter(yearMonthProgression, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        Long lRandomUnsafeOrNull = MathKt.randomUnsafeOrNull(yearMonthProgression.getLongProgression(), random);
        if (lRandomUnsafeOrNull != null) {
            return YearMonthKt.fromProlepticMonth(YearMonth.INSTANCE, lRandomUnsafeOrNull.longValue());
        }
        return null;
    }

    public static /* synthetic */ YearMonth randomOrNull$default(YearMonthProgression yearMonthProgression, Random random, int i, Object obj) {
        if ((i & 1) != 0) {
            random = Random.INSTANCE;
        }
        return randomOrNull(yearMonthProgression, random);
    }
}
