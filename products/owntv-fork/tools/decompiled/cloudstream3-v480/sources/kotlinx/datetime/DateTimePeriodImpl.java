package kotlinx.datetime;

import kotlin.Metadata;

/* JADX INFO: compiled from: DateTimePeriod.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lkotlinx/datetime/DateTimePeriodImpl;", "Lkotlinx/datetime/DateTimePeriod;", "totalMonths", "", "days", "", "totalNanoseconds", "<init>", "(JIJ)V", "getTotalMonths$kotlinx_datetime", "()J", "getDays", "()I", "getTotalNanoseconds$kotlinx_datetime", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class DateTimePeriodImpl extends DateTimePeriod {
    private final int days;
    private final long totalMonths;
    private final long totalNanoseconds;

    @Override // kotlinx.datetime.DateTimePeriod
    /* JADX INFO: renamed from: getTotalMonths$kotlinx_datetime, reason: from getter */
    public long getTotalMonths() {
        return this.totalMonths;
    }

    @Override // kotlinx.datetime.DateTimePeriod
    public int getDays() {
        return this.days;
    }

    @Override // kotlinx.datetime.DateTimePeriod
    /* JADX INFO: renamed from: getTotalNanoseconds$kotlinx_datetime, reason: from getter */
    public long getTotalNanoseconds() {
        return this.totalNanoseconds;
    }

    public DateTimePeriodImpl(long j, int i, long j2) {
        super(null);
        this.totalMonths = j;
        this.days = i;
        this.totalNanoseconds = j2;
    }
}
