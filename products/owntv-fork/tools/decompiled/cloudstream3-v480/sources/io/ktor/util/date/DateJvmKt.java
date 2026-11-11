package io.ktor.util.date;

import j$.util.DesugarTimeZone;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: DateJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0006\u001a\u0019\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a=\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0003\u0010\u0012\u001a\u001b\u0010\u0014\u001a\u00020\u0002*\u00020\u00132\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0011\u0010\u0017\u001a\u00020\u0016*\u00020\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\u0000¢\u0006\u0004\b\u0019\u0010\u001a\"\u001c\u0010\u001d\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e\"\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!\"\u0014\u0010\"\u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010!\"\u0014\u0010#\u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010!\"\u0014\u0010$\u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010!¨\u0006%"}, d2 = {"", "timestamp", "Lio/ktor/util/date/GMTDate;", "GMTDate", "(Ljava/lang/Long;)Lio/ktor/util/date/GMTDate;", "timestampToGMTDate", "(J)Lio/ktor/util/date/GMTDate;", "", "year", "", "isLeapYear", "(I)Z", "seconds", "minutes", "hours", "dayOfMonth", "Lio/ktor/util/date/Month;", "month", "(IIIILio/ktor/util/date/Month;I)Lio/ktor/util/date/GMTDate;", "Ljava/util/Calendar;", "toDate", "(Ljava/util/Calendar;Ljava/lang/Long;)Lio/ktor/util/date/GMTDate;", "Ljava/util/Date;", "toJvmDate", "(Lio/ktor/util/date/GMTDate;)Ljava/util/Date;", "getTimeMillis", "()J", "Ljava/util/TimeZone;", "kotlin.jvm.PlatformType", "GMT_TIMEZONE", "Ljava/util/TimeZone;", "", "DAYS_IN_MONTH", "[I", "DAYS_IN_MONTH_LEAP", "DAYS_BEFORE_MONTH", "DAYS_BEFORE_MONTH_LEAP", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DateJvmKt {
    private static final TimeZone GMT_TIMEZONE = DesugarTimeZone.getTimeZone("GMT");
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] DAYS_IN_MONTH_LEAP = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] DAYS_BEFORE_MONTH = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    private static final int[] DAYS_BEFORE_MONTH_LEAP = {0, 31, 60, 91, 121, 152, 182, 213, HebrewProber.NORMAL_PE, 274, 305, 335};

    public static /* synthetic */ GMTDate GMTDate$default(Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        return GMTDate(l);
    }

    public static final GMTDate GMTDate(Long l) {
        return timestampToGMTDate(l != null ? l.longValue() : System.currentTimeMillis());
    }

    private static final GMTDate timestampToGMTDate(long j) {
        int i;
        int i2;
        long jM$1 = UByte$$ExternalSyntheticBackport0.m$1(j, 1000L);
        long jM$2 = UByte$$ExternalSyntheticBackport0.m$2(jM$1, 86400L);
        int i3 = (int) (jM$2 % 60);
        int i4 = (int) ((jM$2 / 60) % 60);
        int i5 = (int) (jM$2 / 3600);
        int iM$1 = (int) UByte$$ExternalSyntheticBackport0.m$1(jM$1, 86400L);
        WeekDay weekDayFrom = WeekDay.INSTANCE.from(((iM$1 % 7) + 10) % 7);
        int i6 = 719468 + iM$1;
        if (i6 >= 0) {
            i = i6 / 146097;
            i2 = i6 % 146097;
        } else {
            i = (iM$1 + 573372) / 146097;
            i2 = i6 - (146097 * i);
        }
        int i7 = (((i2 - (i2 / 1460)) + (i2 / 36524)) - (i2 / 146096)) / 365;
        int i8 = (i * 400) + i7;
        int i9 = i2 - (((i7 * 365) + (i7 / 4)) - (i7 / 100));
        int i10 = ((i9 * 5) + 2) / 153;
        int i11 = (i9 - (((i10 * 153) + 2) / 5)) + 1;
        int i12 = i10 < 10 ? i10 + 2 : i10 - 10;
        if (i12 <= 1) {
            i8++;
        }
        int i13 = i8;
        return new GMTDate(i3, i4, i5, weekDayFrom, i11, (isLeapYear(i13) ? DAYS_BEFORE_MONTH_LEAP : DAYS_BEFORE_MONTH)[i12] + i11, Month.INSTANCE.from(i12), i13, j);
    }

    private static final boolean isLeapYear(int i) {
        if (i % 4 == 0) {
            return i % 100 != 0 || i % 400 == 0;
        }
        return false;
    }

    public static final GMTDate GMTDate(int i, int i2, int i3, int i4, Month month, int i5) {
        Intrinsics.checkNotNullParameter(month, "month");
        Calendar calendar = Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT);
        Intrinsics.checkNotNull(calendar);
        calendar.set(1, i5);
        calendar.set(2, month.ordinal());
        calendar.set(5, i4);
        calendar.set(11, i3);
        calendar.set(12, i2);
        calendar.set(13, i);
        calendar.set(14, 0);
        return toDate(calendar, null);
    }

    public static final GMTDate toDate(Calendar calendar, Long l) {
        Intrinsics.checkNotNullParameter(calendar, "<this>");
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        int i = calendar.get(15) + calendar.get(16);
        return new GMTDate(calendar.get(13), calendar.get(12), calendar.get(11), WeekDay.INSTANCE.from((calendar.get(7) + 5) % 7), calendar.get(5), calendar.get(6), Month.INSTANCE.from(calendar.get(2)), calendar.get(1), calendar.getTimeInMillis() + ((long) i));
    }

    public static final Date toJvmDate(GMTDate gMTDate) {
        Intrinsics.checkNotNullParameter(gMTDate, "<this>");
        return new Date(gMTDate.getTimestamp());
    }

    public static final long getTimeMillis() {
        return System.currentTimeMillis();
    }
}
