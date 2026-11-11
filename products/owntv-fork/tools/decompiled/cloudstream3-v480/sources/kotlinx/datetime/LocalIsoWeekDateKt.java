package kotlinx.datetime;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalIsoWeekDate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\b"}, d2 = {"toLocalIsoWeekDate", "Lkotlinx/datetime/LocalIsoWeekDate;", "Lkotlinx/datetime/LocalDate;", "startOfIsoWeekYear", "weekYear", "", "isIsoWeekLeapYear", "", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LocalIsoWeekDateKt {
    public static final LocalIsoWeekDate toLocalIsoWeekDate(LocalDate localDate) {
        LocalDate localDateStartOfIsoWeekYear;
        int year;
        int year2;
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        LocalDate localDateStartOfIsoWeekYear2 = startOfIsoWeekYear(localDate.getYear());
        if (localDate.compareTo(localDateStartOfIsoWeekYear2) < 0) {
            year2 = localDate.getYear() - 1;
            localDateStartOfIsoWeekYear = startOfIsoWeekYear(year2);
        } else {
            localDateStartOfIsoWeekYear = startOfIsoWeekYear(localDate.getYear() + 1);
            if (localDate.compareTo(localDateStartOfIsoWeekYear) >= 0) {
                year2 = localDate.getYear() + 1;
            } else {
                year = localDate.getYear();
            }
            return new LocalIsoWeekDate(year, ((int) LocalDateJvmKt.m10566until(localDateStartOfIsoWeekYear2, localDate, (DateTimeUnit.DateBased) DateTimeUnit.INSTANCE.getWEEK())) + 1, localDate.m10563getDayOfWeek());
        }
        LocalDate localDate2 = localDateStartOfIsoWeekYear;
        year = year2;
        localDateStartOfIsoWeekYear2 = localDate2;
        return new LocalIsoWeekDate(year, ((int) LocalDateJvmKt.m10566until(localDateStartOfIsoWeekYear2, localDate, (DateTimeUnit.DateBased) DateTimeUnit.INSTANCE.getWEEK())) + 1, localDate.m10563getDayOfWeek());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LocalDate startOfIsoWeekYear(int i) {
        return LocalDateKt.previousOrSame(new LocalDate(i, 1, 4), DayOfWeek.MONDAY);
    }

    private static final int isIsoWeekLeapYear$p(int i) {
        int i2 = i / 4;
        if ((i ^ 4) < 0 && i2 * 4 != i) {
            i2--;
        }
        int i3 = i2 + i;
        int i4 = i / 100;
        if ((i ^ 100) < 0 && i4 * 100 != i) {
            i4--;
        }
        int i5 = i3 - i4;
        int i6 = i / 400;
        if ((i ^ 400) < 0 && i6 * 400 != i) {
            i6--;
        }
        int i7 = (i5 + i6) % 7;
        return i7 + ((((i7 ^ 7) & ((-i7) | i7)) >> 31) & 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isIsoWeekLeapYear(int i) {
        return isIsoWeekLeapYear$p(i) == 4 || isIsoWeekLeapYear$p(i - 1) == 3;
    }
}
