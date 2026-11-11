package kotlinx.datetime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.datetime.internal.DateCalculationsKt;
import kotlinx.datetime.internal.MathJvmKt;
import kotlinx.datetime.serializers.DateBasedDateTimeUnitSerializer;
import kotlinx.datetime.serializers.DateTimeUnitSerializer;
import kotlinx.datetime.serializers.DayBasedDateTimeUnitSerializer;
import kotlinx.datetime.serializers.MonthBasedDateTimeUnitSerializer;
import kotlinx.datetime.serializers.TimeBasedDateTimeUnitSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimeUnit.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u00102\u00020\u0001:\u0005\f\r\u000e\u000f\u0010B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H¦\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\bH\u0004\u0082\u0001\u0002\u0011\u0012¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/DateTimeUnit;", "", "<init>", "()V", "times", "scalar", "", "formatToString", "", ES6Iterator.VALUE_PROPERTY, "unit", "", "TimeBased", "DateBased", "DayBased", "MonthBased", "Companion", "Lkotlinx/datetime/DateTimeUnit$DateBased;", "Lkotlinx/datetime/DateTimeUnit$TimeBased;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@Serializable(with = DateTimeUnitSerializer.class)
public abstract class DateTimeUnit {
    private static final MonthBased CENTURY;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DayBased DAY;
    private static final TimeBased HOUR;
    private static final TimeBased MICROSECOND;
    private static final TimeBased MILLISECOND;
    private static final TimeBased MINUTE;
    private static final MonthBased MONTH;
    private static final TimeBased NANOSECOND;
    private static final MonthBased QUARTER;
    private static final TimeBased SECOND;
    private static final DayBased WEEK;
    private static final MonthBased YEAR;

    public /* synthetic */ DateTimeUnit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract DateTimeUnit times(int scalar);

    private DateTimeUnit() {
    }

    /* JADX INFO: compiled from: DateTimeUnit.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0096\u0002J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0007¨\u0006\u0018"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$TimeBased;", "Lkotlinx/datetime/DateTimeUnit;", "nanoseconds", "", "<init>", "(J)V", "getNanoseconds", "()J", "unitName", "", "unitScale", "times", "scalar", "", TypedValues.TransitionType.S_DURATION, "Lkotlin/time/Duration;", "getDuration-UwyO8pc", "equals", "", "other", "", "hashCode", "toString", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @Serializable(with = TimeBasedDateTimeUnitSerializer.class)
    public static final class TimeBased extends DateTimeUnit {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final long nanoseconds;
        private final String unitName;
        private final long unitScale;

        /* JADX INFO: compiled from: DateTimeUnit.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$TimeBased$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/DateTimeUnit$TimeBased;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final KSerializer<TimeBased> serializer() {
                return TimeBasedDateTimeUnitSerializer.INSTANCE;
            }
        }

        public final long getNanoseconds() {
            return this.nanoseconds;
        }

        public TimeBased(long j) {
            super(null);
            this.nanoseconds = j;
            if (j <= 0) {
                throw new IllegalArgumentException(("Unit duration must be positive, but was " + j + " ns.").toString());
            }
            if (j % DateCalculationsKt.NANOS_PER_HOUR == 0) {
                this.unitName = "HOUR";
                this.unitScale = j / DateCalculationsKt.NANOS_PER_HOUR;
                return;
            }
            if (j % DateCalculationsKt.NANOS_PER_MINUTE == 0) {
                this.unitName = "MINUTE";
                this.unitScale = j / DateCalculationsKt.NANOS_PER_MINUTE;
                return;
            }
            if (j % C.NANOS_PER_SECOND == 0) {
                this.unitName = "SECOND";
                this.unitScale = j / C.NANOS_PER_SECOND;
            } else if (j % 1000000 == 0) {
                this.unitName = "MILLISECOND";
                this.unitScale = j / 1000000;
            } else if (j % 1000 == 0) {
                this.unitName = "MICROSECOND";
                this.unitScale = j / 1000;
            } else {
                this.unitName = "NANOSECOND";
                this.unitScale = j;
            }
        }

        @Override // kotlinx.datetime.DateTimeUnit
        public TimeBased times(int scalar) {
            return new TimeBased(MathJvmKt.safeMultiply(this.nanoseconds, scalar));
        }

        /* JADX INFO: renamed from: getDuration-UwyO8pc, reason: not valid java name */
        public final long m10557getDurationUwyO8pc() {
            Duration.Companion companion = Duration.INSTANCE;
            return DurationKt.toDuration(this.nanoseconds, DurationUnit.NANOSECONDS);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof TimeBased) && this.nanoseconds == ((TimeBased) other).nanoseconds;
            }
            return true;
        }

        public int hashCode() {
            long j = this.nanoseconds;
            return ((int) j) ^ ((int) (j >> 32));
        }

        public String toString() {
            return formatToString(this.unitScale, this.unitName);
        }
    }

    /* JADX INFO: compiled from: DateTimeUnit.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$DateBased;", "Lkotlinx/datetime/DateTimeUnit;", "<init>", "()V", "Companion", "Lkotlinx/datetime/DateTimeUnit$DayBased;", "Lkotlinx/datetime/DateTimeUnit$MonthBased;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @Serializable(with = DateBasedDateTimeUnitSerializer.class)
    public static abstract class DateBased extends DateTimeUnit {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        public /* synthetic */ DateBased(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: DateTimeUnit.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$DateBased$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/DateTimeUnit$DateBased;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final KSerializer<DateBased> serializer() {
                return DateBasedDateTimeUnitSerializer.INSTANCE;
            }
        }

        private DateBased() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: DateTimeUnit.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$DayBased;", "Lkotlinx/datetime/DateTimeUnit$DateBased;", "days", "", "<init>", "(I)V", "getDays", "()I", "times", "scalar", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @Serializable(with = DayBasedDateTimeUnitSerializer.class)
    public static final class DayBased extends DateBased {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final int days;

        /* JADX INFO: compiled from: DateTimeUnit.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$DayBased$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/DateTimeUnit$DayBased;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final KSerializer<DayBased> serializer() {
                return DayBasedDateTimeUnitSerializer.INSTANCE;
            }
        }

        public final int getDays() {
            return this.days;
        }

        public DayBased(int i) {
            super(null);
            this.days = i;
            if (i > 0) {
                return;
            }
            throw new IllegalArgumentException(("Unit duration must be positive, but was " + i + " days.").toString());
        }

        @Override // kotlinx.datetime.DateTimeUnit
        public DayBased times(int scalar) {
            return new DayBased(MathJvmKt.safeMultiply(this.days, scalar));
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof DayBased) && this.days == ((DayBased) other).days;
            }
            return true;
        }

        public int hashCode() {
            return this.days ^ 65536;
        }

        public String toString() {
            int i = this.days;
            if (i % 7 == 0) {
                return formatToString(i / 7, "WEEK");
            }
            return formatToString(i, "DAY");
        }
    }

    /* JADX INFO: compiled from: DateTimeUnit.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$MonthBased;", "Lkotlinx/datetime/DateTimeUnit$DateBased;", "months", "", "<init>", "(I)V", "getMonths", "()I", "times", "scalar", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @Serializable(with = MonthBasedDateTimeUnitSerializer.class)
    public static final class MonthBased extends DateBased {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final int months;

        /* JADX INFO: compiled from: DateTimeUnit.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$MonthBased$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/DateTimeUnit$MonthBased;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final KSerializer<MonthBased> serializer() {
                return MonthBasedDateTimeUnitSerializer.INSTANCE;
            }
        }

        public final int getMonths() {
            return this.months;
        }

        public MonthBased(int i) {
            super(null);
            this.months = i;
            if (i > 0) {
                return;
            }
            throw new IllegalArgumentException(("Unit duration must be positive, but was " + i + " months.").toString());
        }

        @Override // kotlinx.datetime.DateTimeUnit
        public MonthBased times(int scalar) {
            return new MonthBased(MathJvmKt.safeMultiply(this.months, scalar));
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof MonthBased) && this.months == ((MonthBased) other).months;
            }
            return true;
        }

        public int hashCode() {
            return this.months ^ 131072;
        }

        public String toString() {
            int i = this.months;
            if (i % 1200 == 0) {
                return formatToString(i / 1200, "CENTURY");
            }
            if (i % 12 == 0) {
                return formatToString(i / 12, "YEAR");
            }
            return i % 3 == 0 ? formatToString(i / 3, "QUARTER") : formatToString(i, "MONTH");
        }
    }

    protected final String formatToString(int value, String unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (value == 1) {
            return unit;
        }
        return value + '-' + unit;
    }

    protected final String formatToString(long value, String unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (value == 1) {
            return unit;
        }
        return value + '-' + unit;
    }

    /* JADX INFO: compiled from: DateTimeUnit.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010 \u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001b¨\u0006%"}, d2 = {"Lkotlinx/datetime/DateTimeUnit$Companion;", "", "<init>", "()V", "NANOSECOND", "Lkotlinx/datetime/DateTimeUnit$TimeBased;", "getNANOSECOND", "()Lkotlinx/datetime/DateTimeUnit$TimeBased;", "MICROSECOND", "getMICROSECOND", "MILLISECOND", "getMILLISECOND", "SECOND", "getSECOND", "MINUTE", "getMINUTE", "HOUR", "getHOUR", "DAY", "Lkotlinx/datetime/DateTimeUnit$DayBased;", "getDAY", "()Lkotlinx/datetime/DateTimeUnit$DayBased;", "WEEK", "getWEEK", "MONTH", "Lkotlinx/datetime/DateTimeUnit$MonthBased;", "getMONTH", "()Lkotlinx/datetime/DateTimeUnit$MonthBased;", "QUARTER", "getQUARTER", "YEAR", "getYEAR", "CENTURY", "getCENTURY", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/DateTimeUnit;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DateTimeUnit> serializer() {
            return DateTimeUnitSerializer.INSTANCE;
        }

        public final TimeBased getNANOSECOND() {
            return DateTimeUnit.NANOSECOND;
        }

        public final TimeBased getMICROSECOND() {
            return DateTimeUnit.MICROSECOND;
        }

        public final TimeBased getMILLISECOND() {
            return DateTimeUnit.MILLISECOND;
        }

        public final TimeBased getSECOND() {
            return DateTimeUnit.SECOND;
        }

        public final TimeBased getMINUTE() {
            return DateTimeUnit.MINUTE;
        }

        public final TimeBased getHOUR() {
            return DateTimeUnit.HOUR;
        }

        public final DayBased getDAY() {
            return DateTimeUnit.DAY;
        }

        public final DayBased getWEEK() {
            return DateTimeUnit.WEEK;
        }

        public final MonthBased getMONTH() {
            return DateTimeUnit.MONTH;
        }

        public final MonthBased getQUARTER() {
            return DateTimeUnit.QUARTER;
        }

        public final MonthBased getYEAR() {
            return DateTimeUnit.YEAR;
        }

        public final MonthBased getCENTURY() {
            return DateTimeUnit.CENTURY;
        }
    }

    static {
        TimeBased timeBased = new TimeBased(1L);
        NANOSECOND = timeBased;
        TimeBased timeBasedTimes = timeBased.times(1000);
        MICROSECOND = timeBasedTimes;
        TimeBased timeBasedTimes2 = timeBasedTimes.times(1000);
        MILLISECOND = timeBasedTimes2;
        TimeBased timeBasedTimes3 = timeBasedTimes2.times(1000);
        SECOND = timeBasedTimes3;
        TimeBased timeBasedTimes4 = timeBasedTimes3.times(60);
        MINUTE = timeBasedTimes4;
        HOUR = timeBasedTimes4.times(60);
        DayBased dayBased = new DayBased(1);
        DAY = dayBased;
        WEEK = dayBased.times(7);
        MonthBased monthBased = new MonthBased(1);
        MONTH = monthBased;
        QUARTER = monthBased.times(3);
        MonthBased monthBasedTimes = monthBased.times(12);
        YEAR = monthBasedTimes;
        CENTURY = monthBasedTimes.times(100);
    }
}
