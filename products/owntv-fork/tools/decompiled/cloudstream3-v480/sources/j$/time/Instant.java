package j$.time;

import androidx.media3.common.C;
import j$.nio.file.attribute.FileTime;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlinx.datetime.internal.DateCalculationsKt;

/* JADX INFO: loaded from: classes3.dex */
public final class Instant implements Temporal, TemporalAdjuster, Comparable<Instant>, Serializable {
    private static final long serialVersionUID = -665713676816604388L;
    private final int nanos;
    private final long seconds;
    public static final Instant EPOCH = new Instant(0, 0);
    public static final Instant MIN = ofEpochSecond(-31557014167219200L, 0);
    public static final Instant MAX = ofEpochSecond(31556889864403199L, 999999999);

    /* JADX INFO: renamed from: j$.time.Instant$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ long m(long j, long j2) {
            long j3 = j - j2;
            if (((j2 ^ j) >= 0) || ((j ^ j3) >= 0)) {
                return j3;
            }
            throw new ArithmeticException();
        }
    }

    public static Instant now() {
        return Clock.systemUTC().instant();
    }

    public static Instant ofEpochSecond(long j) {
        return create(j, 0);
    }

    public static Instant ofEpochSecond(long j, long j2) {
        return create(Clock$OffsetClock$0.m(j, FileTime.AnonymousClass0.m(j2, C.NANOS_PER_SECOND)), (int) FileTime.AnonymousClass2.m(j2, C.NANOS_PER_SECOND));
    }

    public static Instant ofEpochMilli(long j) {
        return create(FileTime.AnonymousClass0.m(j, 1000), DesugarLocalTime$0.m(j, 1000) * 1000000);
    }

    public static Instant from(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof Instant) {
            return (Instant) temporalAccessor;
        }
        Objects.requireNonNull(temporalAccessor, "temporal");
        try {
            return ofEpochSecond(temporalAccessor.getLong(ChronoField.INSTANT_SECONDS), temporalAccessor.get(ChronoField.NANO_OF_SECOND));
        } catch (DateTimeException e) {
            Year.AnonymousClass4.m("Unable to obtain Instant from TemporalAccessor: ", temporalAccessor, temporalAccessor.getClass().getName(), e);
            return null;
        }
    }

    public static Instant parse(CharSequence charSequence) {
        return (Instant) DateTimeFormatter.ISO_INSTANT.parse(charSequence, new TemporalQuery() { // from class: j$.time.Instant.2
            @Override // j$.time.temporal.TemporalQuery
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                return Instant.from(temporalAccessor);
            }
        });
    }

    private static Instant create(long j, int i) {
        if ((((long) i) | j) == 0) {
            return EPOCH;
        }
        if (j < -31557014167219200L || j > 31556889864403199L) {
            Year.AnonymousClass3.m("Instant exceeds minimum or maximum instant");
            return null;
        }
        return new Instant(j, i);
    }

    private Instant(long j, int i) {
        this.seconds = j;
        this.nanos = i;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.NANO_OF_SECOND || temporalField == ChronoField.MICRO_OF_SECOND || temporalField == ChronoField.MILLI_OF_SECOND;
        }
        return temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        return TemporalAccessor.CC.$default$range(this, temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i == 1) {
                return this.nanos;
            }
            if (i == 2) {
                return this.nanos / 1000;
            }
            if (i == 3) {
                return this.nanos / 1000000;
            }
            if (i == 4) {
                ChronoField.INSTANT_SECONDS.checkValidIntValue(this.seconds);
            }
            Year.AnonymousClass2.m("Unsupported field: ", temporalField);
            return 0;
        }
        return range(temporalField).checkValidIntValue(temporalField.getFrom(this), temporalField);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i == 1) {
                return this.nanos;
            }
            if (i == 2) {
                return this.nanos / 1000;
            }
            if (i == 3) {
                return this.nanos / 1000000;
            }
            if (i == 4) {
                return this.seconds;
            }
            Year.AnonymousClass2.m("Unsupported field: ", temporalField);
            return 0L;
        }
        return temporalField.getFrom(this);
    }

    public long getEpochSecond() {
        return this.seconds;
    }

    public int getNano() {
        return this.nanos;
    }

    @Override // j$.time.temporal.Temporal
    public Instant with(TemporalAdjuster temporalAdjuster) {
        return (Instant) temporalAdjuster.adjustInto(this);
    }

    @Override // j$.time.temporal.Temporal
    public Instant with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.checkValidValue(j);
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
            if (i == 1) {
                return j != ((long) this.nanos) ? create(this.seconds, (int) j) : this;
            }
            if (i == 2) {
                int i2 = ((int) j) * 1000;
                return i2 != this.nanos ? create(this.seconds, i2) : this;
            }
            if (i == 3) {
                int i3 = ((int) j) * 1000000;
                return i3 != this.nanos ? create(this.seconds, i3) : this;
            }
            if (i == 4) {
                return j != this.seconds ? create(j, this.nanos) : this;
            }
            Year.AnonymousClass2.m("Unsupported field: ", temporalField);
            return null;
        }
        return (Instant) temporalField.adjustInto(this, j);
    }

    /* JADX INFO: renamed from: j$.time.Instant$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField;
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            $SwitchMap$java$time$temporal$ChronoUnit = iArr;
            try {
                iArr[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DAYS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr2;
            try {
                iArr2[ChronoField.NANO_OF_SECOND.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MICRO_OF_SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MILLI_OF_SECOND.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.INSTANT_SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @Override // j$.time.temporal.Temporal
    public Instant plus(long j, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return plusNanos(j);
                case 2:
                    return plus(j / 1000000, (j % 1000000) * 1000);
                case 3:
                    return plusMillis(j);
                case 4:
                    return plusSeconds(j);
                case 5:
                    return plusSeconds(Duration.AnonymousClass0.m(j, 60));
                case 6:
                    return plusSeconds(Duration.AnonymousClass0.m(j, DateCalculationsKt.SECONDS_PER_HOUR));
                case 7:
                    return plusSeconds(Duration.AnonymousClass0.m(j, 43200));
                case 8:
                    return plusSeconds(Duration.AnonymousClass0.m(j, DateCalculationsKt.SECONDS_PER_DAY));
                default:
                    Year.AnonymousClass2.m("Unsupported unit: ", temporalUnit);
                    return null;
            }
        }
        return (Instant) temporalUnit.addTo(this, j);
    }

    public Instant plusSeconds(long j) {
        return plus(j, 0L);
    }

    public Instant plusMillis(long j) {
        return plus(j / 1000, (j % 1000) * 1000000);
    }

    public Instant plusNanos(long j) {
        return plus(0L, j);
    }

    private Instant plus(long j, long j2) {
        if ((j | j2) == 0) {
            return this;
        }
        return ofEpochSecond(Clock$OffsetClock$0.m(Clock$OffsetClock$0.m(this.seconds, j), j2 / C.NANOS_PER_SECOND), ((long) this.nanos) + (j2 % C.NANOS_PER_SECOND));
    }

    @Override // j$.time.temporal.Temporal
    public Instant minus(long j, TemporalUnit temporalUnit) {
        long j2;
        if (j == Long.MIN_VALUE) {
            this = plus(Long.MAX_VALUE, temporalUnit);
            j2 = 1;
        } else {
            j2 = -j;
        }
        return this.plus(j2, temporalUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == TemporalQueries.precision()) {
            return ChronoUnit.NANOS;
        }
        if (temporalQuery == TemporalQueries.chronology() || temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.offset() || temporalQuery == TemporalQueries.localDate() || temporalQuery == TemporalQueries.localTime()) {
            return null;
        }
        return temporalQuery.queryFrom(this);
    }

    @Override // j$.time.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.INSTANT_SECONDS, this.seconds).with(ChronoField.NANO_OF_SECOND, this.nanos);
    }

    @Override // j$.time.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        Instant instantFrom = from(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return nanosUntil(instantFrom);
                case 2:
                    return nanosUntil(instantFrom) / 1000;
                case 3:
                    return AnonymousClass0.m(instantFrom.toEpochMilli(), toEpochMilli());
                case 4:
                    return secondsUntil(instantFrom);
                case 5:
                    return secondsUntil(instantFrom) / 60;
                case 6:
                    return secondsUntil(instantFrom) / 3600;
                case 7:
                    return secondsUntil(instantFrom) / 43200;
                case 8:
                    return secondsUntil(instantFrom) / 86400;
                default:
                    Year.AnonymousClass2.m("Unsupported unit: ", temporalUnit);
                    return 0L;
            }
        }
        return temporalUnit.between(this, instantFrom);
    }

    private long nanosUntil(Instant instant) {
        return Clock$OffsetClock$0.m(Duration.AnonymousClass2.m(AnonymousClass0.m(instant.seconds, this.seconds), C.NANOS_PER_SECOND), instant.nanos - this.nanos);
    }

    private long secondsUntil(Instant instant) {
        long jM = AnonymousClass0.m(instant.seconds, this.seconds);
        long j = instant.nanos - this.nanos;
        if (jM <= 0 || j >= 0) {
            return (jM >= 0 || j <= 0) ? jM : jM + 1;
        }
        return jM - 1;
    }

    public OffsetDateTime atOffset(ZoneOffset zoneOffset) {
        return OffsetDateTime.ofInstant(this, zoneOffset);
    }

    public ZonedDateTime atZone(ZoneId zoneId) {
        return ZonedDateTime.ofInstant(this, zoneId);
    }

    public long toEpochMilli() {
        long j = this.seconds;
        if (j < 0 && this.nanos > 0) {
            return Clock$OffsetClock$0.m(Duration.AnonymousClass0.m(j + 1, 1000), (this.nanos / 1000000) - 1000);
        }
        return Clock$OffsetClock$0.m(Duration.AnonymousClass0.m(j, 1000), this.nanos / 1000000);
    }

    @Override // java.lang.Comparable
    public int compareTo(Instant instant) {
        int iCompare = Long.compare(this.seconds, instant.seconds);
        return iCompare != 0 ? iCompare : this.nanos - instant.nanos;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Instant) {
            Instant instant = (Instant) obj;
            if (this.seconds == instant.seconds && this.nanos == instant.nanos) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.seconds;
        return ((int) (j ^ (j >>> 32))) + (this.nanos * 51);
    }

    public String toString() {
        return DateTimeFormatter.ISO_INSTANT.format(this);
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput dataOutput) {
        dataOutput.writeLong(this.seconds);
        dataOutput.writeInt(this.nanos);
    }

    static Instant readExternal(DataInput dataInput) {
        return ofEpochSecond(dataInput.readLong(), dataInput.readInt());
    }
}
