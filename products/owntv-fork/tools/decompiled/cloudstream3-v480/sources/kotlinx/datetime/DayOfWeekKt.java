package kotlinx.datetime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"kotlinx/datetime/DayOfWeekKt__DayOfWeekJvmKt", "kotlinx/datetime/DayOfWeekKt__DayOfWeekKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
public final class DayOfWeekKt {
    /* JADX INFO: renamed from: DayOfWeek, reason: collision with other method in class */
    public static final DayOfWeek m10558DayOfWeek(int i) {
        return DayOfWeekKt__DayOfWeekKt.DayOfWeek(i);
    }

    public static final int getIsoDayNumber(j$.time.DayOfWeek dayOfWeek) {
        return DayOfWeekKt__DayOfWeekJvmKt.getIsoDayNumber(dayOfWeek);
    }

    public static final int getIsoDayNumber(DayOfWeek dayOfWeek) {
        return DayOfWeekKt__DayOfWeekKt.getIsoDayNumber(dayOfWeek);
    }
}
