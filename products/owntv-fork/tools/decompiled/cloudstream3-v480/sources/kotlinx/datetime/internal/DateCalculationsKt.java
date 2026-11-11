package kotlinx.datetime.internal;

import kotlin.Metadata;

/* JADX INFO: compiled from: dateCalculations.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"SECONDS_PER_HOUR", "", "SECONDS_PER_MINUTE", "MINUTES_PER_HOUR", "HOURS_PER_DAY", "SECONDS_PER_DAY", "NANOS_PER_ONE", "NANOS_PER_MILLI", "MILLIS_PER_ONE", "NANOS_PER_DAY", "", "NANOS_PER_MINUTE", "NANOS_PER_HOUR", "MILLIS_PER_DAY", "isLeapYear", "", "year", "monthLength", "SECONDS_PER_10000_YEARS", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DateCalculationsKt {
    public static final int HOURS_PER_DAY = 24;
    public static final int MILLIS_PER_DAY = 86400000;
    public static final int MILLIS_PER_ONE = 1000;
    public static final int MINUTES_PER_HOUR = 60;
    public static final long NANOS_PER_DAY = 86400000000000L;
    public static final long NANOS_PER_HOUR = 3600000000000L;
    public static final int NANOS_PER_MILLI = 1000000;
    public static final long NANOS_PER_MINUTE = 60000000000L;
    public static final int NANOS_PER_ONE = 1000000000;
    public static final long SECONDS_PER_10000_YEARS = 315569520000L;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_MINUTE = 60;

    public static final int monthLength(int i, boolean z) {
        if (i != 2) {
            return (i == 4 || i == 6 || i == 9 || i == 11) ? 30 : 31;
        }
        return z ? 29 : 28;
    }

    public static final boolean isLeapYear(int i) {
        long j = i;
        if ((3 & j) == 0) {
            return j % 100 != 0 || j % 400 == 0;
        }
        return false;
    }
}
