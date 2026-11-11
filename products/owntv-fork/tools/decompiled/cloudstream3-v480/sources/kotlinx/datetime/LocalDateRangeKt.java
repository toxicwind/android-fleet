package kotlinx.datetime;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlinx.datetime.internal.MathKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LocalDateRange.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\f\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0002*\u00020\u0002\u001a\u001a\u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u001a\u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0015\u0010\r\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0086\u0004\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010¨\u0006\u0012"}, d2 = {"first", "Lkotlinx/datetime/LocalDate;", "Lkotlinx/datetime/LocalDateProgression;", "last", "firstOrNull", "lastOrNull", "reversed", "step", ES6Iterator.VALUE_PROPERTY, "", "unit", "Lkotlinx/datetime/DateTimeUnit$DayBased;", "", "downTo", "that", "random", "Lkotlin/random/Random;", "randomOrNull", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LocalDateRangeKt {
    public static final LocalDate first(LocalDateProgression localDateProgression) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        if (localDateProgression.isEmpty()) {
            throw new NoSuchElementException("Progression " + localDateProgression + " is empty.");
        }
        return localDateProgression.getFirst();
    }

    public static final LocalDate last(LocalDateProgression localDateProgression) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        if (localDateProgression.isEmpty()) {
            throw new NoSuchElementException("Progression " + localDateProgression + " is empty.");
        }
        return localDateProgression.getLast();
    }

    public static final LocalDate firstOrNull(LocalDateProgression localDateProgression) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        if (localDateProgression.isEmpty()) {
            return null;
        }
        return localDateProgression.getFirst();
    }

    public static final LocalDate lastOrNull(LocalDateProgression localDateProgression) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        if (localDateProgression.isEmpty()) {
            return null;
        }
        return localDateProgression.getLast();
    }

    public static final LocalDateProgression reversed(LocalDateProgression localDateProgression) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        return new LocalDateProgression(RangesKt.reversed(localDateProgression.getLongProgression()));
    }

    public static final LocalDateProgression step(LocalDateProgression localDateProgression, int i, DateTimeUnit.DayBased unit) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return step(localDateProgression, i, unit);
    }

    public static final LocalDateProgression step(LocalDateProgression localDateProgression, long j, DateTimeUnit.DayBased unit) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new LocalDateProgression(RangesKt.step(localDateProgression.getLongProgression(), MathKt.safeMultiplyOrClamp(j, unit.getDays())));
    }

    public static final LocalDateProgression downTo(LocalDate localDate, LocalDate that) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(that, "that");
        return LocalDateProgression.INSTANCE.fromClosedRange$kotlinx_datetime(localDate, that, -1L, DateTimeUnit.INSTANCE.getDAY());
    }

    public static /* synthetic */ LocalDate random$default(LocalDateProgression localDateProgression, Random random, int i, Object obj) {
        if ((i & 1) != 0) {
            random = Random.INSTANCE;
        }
        return random(localDateProgression, random);
    }

    public static final LocalDate random(LocalDateProgression localDateProgression, Random random) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (localDateProgression.isEmpty()) {
            throw new NoSuchElementException("Cannot get random in empty range: " + localDateProgression);
        }
        return LocalDate.INSTANCE.fromEpochDays(MathKt.randomUnsafe(localDateProgression.getLongProgression(), random));
    }

    public static final LocalDate randomOrNull(LocalDateProgression localDateProgression, Random random) {
        Intrinsics.checkNotNullParameter(localDateProgression, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        Long lRandomUnsafeOrNull = MathKt.randomUnsafeOrNull(localDateProgression.getLongProgression(), random);
        if (lRandomUnsafeOrNull != null) {
            return LocalDate.INSTANCE.fromEpochDays(lRandomUnsafeOrNull.longValue());
        }
        return null;
    }

    public static /* synthetic */ LocalDate randomOrNull$default(LocalDateProgression localDateProgression, Random random, int i, Object obj) {
        if ((i & 1) != 0) {
            random = Random.INSTANCE;
        }
        return randomOrNull(localDateProgression, random);
    }
}
