package kotlinx.datetime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Clock;
import kotlin.time.ComparableTimeMark;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlin.time.Instant;

/* JADX INFO: compiled from: Clock.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0001H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0012\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\f\u0010\u001c\u001a\u00020\u0016*\u00020\u0003H\u0002J\u001b\u0010\u001d\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003H\u0002¢\u0006\u0004\b#\u0010$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlinx/datetime/InstantTimeMark;", "Lkotlin/time/ComparableTimeMark;", "instant", "Lkotlin/time/Instant;", "clock", "Lkotlin/time/Clock;", "<init>", "(Lkotlin/time/Instant;Lkotlin/time/Clock;)V", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "()J", "plus", TypedValues.TransitionType.S_DURATION, "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "minus", "minus-LRDsOJo", "other", "minus-5sfh64U", "(Lkotlin/time/ComparableTimeMark;)J", "equals", "", "", "hashCode", "", "toString", "", "isSaturated", "saturatingAdd", "saturatingAdd-HG0u8IE", "(Lkotlin/time/Instant;J)Lkotlin/time/Instant;", "saturatingDiff", "instant1", "instant2", "saturatingDiff-3nIYWDw", "(Lkotlin/time/Instant;Lkotlin/time/Instant;)J", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class InstantTimeMark implements ComparableTimeMark {
    private final Clock clock;
    private final Instant instant;

    @Override // java.lang.Comparable
    public int compareTo(ComparableTimeMark comparableTimeMark) {
        return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
    }

    @Override // kotlin.time.TimeMark
    public boolean hasNotPassedNow() {
        return ComparableTimeMark.DefaultImpls.hasNotPassedNow(this);
    }

    @Override // kotlin.time.TimeMark
    public boolean hasPassedNow() {
        return ComparableTimeMark.DefaultImpls.hasPassedNow(this);
    }

    public InstantTimeMark(Instant instant, Clock clock) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        Intrinsics.checkNotNullParameter(clock, "clock");
        this.instant = instant;
        this.clock = clock;
    }

    @Override // kotlin.time.TimeMark
    /* JADX INFO: renamed from: elapsedNow-UwyO8pc */
    public long mo10306elapsedNowUwyO8pc() {
        return m10561saturatingDiff3nIYWDw(this.clock.now(), this.instant);
    }

    @Override // kotlin.time.TimeMark
    /* JADX INFO: renamed from: plus-LRDsOJo */
    public ComparableTimeMark mo10309plusLRDsOJo(long duration) {
        return new InstantTimeMark(m10560saturatingAddHG0u8IE(this.instant, duration), this.clock);
    }

    @Override // kotlin.time.TimeMark
    /* JADX INFO: renamed from: minus-LRDsOJo */
    public ComparableTimeMark mo10307minusLRDsOJo(long duration) {
        return new InstantTimeMark(m10560saturatingAddHG0u8IE(this.instant, Duration.m10362unaryMinusUwyO8pc(duration)), this.clock);
    }

    @Override // kotlin.time.ComparableTimeMark
    /* JADX INFO: renamed from: minus-5sfh64U, reason: not valid java name and merged with bridge method [inline-methods] */
    public long mo10308minusUwyO8pc(ComparableTimeMark other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (other instanceof InstantTimeMark) {
            InstantTimeMark instantTimeMark = (InstantTimeMark) other;
            if (Intrinsics.areEqual(instantTimeMark.clock, this.clock)) {
                return m10561saturatingDiff3nIYWDw(this.instant, instantTimeMark.instant);
            }
        }
        throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
    }

    @Override // kotlin.time.ComparableTimeMark
    public boolean equals(Object other) {
        if (!(other instanceof InstantTimeMark)) {
            return false;
        }
        InstantTimeMark instantTimeMark = (InstantTimeMark) other;
        return Intrinsics.areEqual(this.clock, instantTimeMark.clock) && Intrinsics.areEqual(this.instant, instantTimeMark.instant);
    }

    @Override // kotlin.time.ComparableTimeMark
    public int hashCode() {
        return this.instant.hashCode();
    }

    public String toString() {
        return "InstantTimeMark(" + this.instant + ", " + this.clock + ')';
    }

    private final boolean isSaturated(Instant instant) {
        Duration.Companion companion = Duration.INSTANCE;
        if (!Intrinsics.areEqual(instant.m10423plusLRDsOJo(DurationKt.toDuration(1, DurationUnit.SECONDS)), instant)) {
            Duration.Companion companion2 = Duration.INSTANCE;
            if (!Intrinsics.areEqual(instant.m10423plusLRDsOJo(DurationKt.toDuration(-1, DurationUnit.SECONDS)), instant)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: saturatingAdd-HG0u8IE, reason: not valid java name */
    private final Instant m10560saturatingAddHG0u8IE(Instant instant, long j) {
        if (isSaturated(instant)) {
            if (Duration.m10343isInfiniteimpl(j)) {
                if (Duration.m10345isPositiveimpl(j) != (instant.compareTo(Instant.INSTANCE.getDISTANT_FUTURE()) >= 0)) {
                    throw new IllegalArgumentException("Summing infinities of different signs");
                }
            }
            return instant;
        }
        return instant.m10423plusLRDsOJo(j);
    }

    /* JADX INFO: renamed from: saturatingDiff-3nIYWDw, reason: not valid java name */
    private final long m10561saturatingDiff3nIYWDw(Instant instant1, Instant instant2) {
        if (Intrinsics.areEqual(instant1, instant2)) {
            return Duration.INSTANCE.m10413getZEROUwyO8pc();
        }
        if (isSaturated(instant1) || isSaturated(instant2)) {
            return Duration.m10348timesUwyO8pc(instant1.m10422minusUwyO8pc(instant2), Double.POSITIVE_INFINITY);
        }
        return instant1.m10422minusUwyO8pc(instant2);
    }
}
