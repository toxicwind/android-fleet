package j$.nio.file.attribute;

import androidx.media3.common.C;
import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.ZoneOffset;
import j$.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlinx.datetime.internal.DateCalculationsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes3.dex */
public final class FileTime implements Comparable<FileTime> {
    private Instant instant;
    private final TimeUnit unit;
    private final long value;
    private String valueAsString;

    /* JADX INFO: renamed from: j$.nio.file.attribute.FileTime$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ long m(long j, long j2) {
            long j3 = j / j2;
            return (j - (j2 * j3) != 0 && (((j ^ j2) >> 63) | 1) < 0) ? j3 - 1 : j3;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.attribute.FileTime$2, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass2 {
        public static /* synthetic */ long m(long j, long j2) {
            long j3 = j % j2;
            if (j3 == 0) {
                return 0L;
            }
            return (((j ^ j2) >> 63) | 1) > 0 ? j3 : j3 + j2;
        }
    }

    private static long scale(long j, long j2, long j3) {
        if (j > j3) {
            return Long.MAX_VALUE;
        }
        if (j < (-j3)) {
            return Long.MIN_VALUE;
        }
        return j * j2;
    }

    private FileTime(long j, TimeUnit timeUnit, Instant instant) {
        this.value = j;
        this.unit = timeUnit;
        this.instant = instant;
    }

    public static FileTime from(long j, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "unit");
        return new FileTime(j, timeUnit, null);
    }

    public static FileTime fromMillis(long j) {
        return new FileTime(j, TimeUnit.MILLISECONDS, null);
    }

    public long to(TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "unit");
        TimeUnit timeUnit2 = this.unit;
        if (timeUnit2 != null) {
            return timeUnit.convert(this.value, timeUnit2);
        }
        long jConvert = timeUnit.convert(this.instant.getEpochSecond(), TimeUnit.SECONDS);
        if (jConvert == Long.MIN_VALUE || jConvert == Long.MAX_VALUE) {
            return jConvert;
        }
        long jConvert2 = timeUnit.convert(this.instant.getNano(), TimeUnit.NANOSECONDS);
        long j = jConvert + jConvert2;
        if (((jConvert2 ^ j) & (jConvert ^ j)) < 0) {
            return jConvert < 0 ? Long.MIN_VALUE : Long.MAX_VALUE;
        }
        return j;
    }

    public long toMillis() {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return timeUnit.toMillis(this.value);
        }
        long epochSecond = this.instant.getEpochSecond();
        int nano = this.instant.getNano();
        long j = epochSecond * 1000;
        if (((Math.abs(epochSecond) | 1000) >>> 31) == 0 || j / 1000 == epochSecond) {
            return j + ((long) (nano / 1000000));
        }
        return epochSecond < 0 ? Long.MIN_VALUE : Long.MAX_VALUE;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x008c  */
    /* JADX WARN: Code duplicated, block: B:19:0x0091  */
    /* JADX WARN: Code duplicated, block: B:21:0x009a  */
    /* JADX WARN: Code duplicated, block: B:22:0x009f  */
    public Instant toInstant() {
        long jScale;
        int iM;
        long jM;
        if (this.instant == null) {
            switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[this.unit.ordinal()]) {
                case 1:
                    jScale = scale(this.value, 86400L, 106751991167300L);
                    long j = jScale;
                    iM = 0;
                    jM = j;
                    if (jM <= -31557014167219200L) {
                        this.instant = Instant.MIN;
                    } else if (jM >= 31556889864403199L) {
                        this.instant = Instant.MAX;
                    } else {
                        this.instant = Instant.ofEpochSecond(jM, iM);
                    }
                    break;
                case 2:
                    jScale = scale(this.value, 3600L, 2562047788015215L);
                    long j2 = jScale;
                    iM = 0;
                    jM = j2;
                    if (jM <= -31557014167219200L) {
                        this.instant = Instant.MIN;
                    } else if (jM >= 31556889864403199L) {
                        this.instant = Instant.MAX;
                    } else {
                        this.instant = Instant.ofEpochSecond(jM, iM);
                    }
                    break;
                case 3:
                    jScale = scale(this.value, 60L, 153722867280912930L);
                    long j3 = jScale;
                    iM = 0;
                    jM = j3;
                    if (jM <= -31557014167219200L) {
                        this.instant = Instant.MIN;
                    } else if (jM >= 31556889864403199L) {
                        this.instant = Instant.MAX;
                    } else {
                        this.instant = Instant.ofEpochSecond(jM, iM);
                    }
                    break;
                case 4:
                    jScale = this.value;
                    long j4 = jScale;
                    iM = 0;
                    jM = j4;
                    if (jM <= -31557014167219200L) {
                        this.instant = Instant.MIN;
                    } else if (jM >= 31556889864403199L) {
                        this.instant = Instant.MAX;
                    } else {
                        this.instant = Instant.ofEpochSecond(jM, iM);
                    }
                    break;
                case 5:
                    jM = AnonymousClass0.m(this.value, 1000L);
                    iM = ((int) AnonymousClass2.m(this.value, 1000L)) * 1000000;
                    if (jM <= -31557014167219200L) {
                        this.instant = Instant.MIN;
                    } else if (jM >= 31556889864403199L) {
                        this.instant = Instant.MAX;
                    } else {
                        this.instant = Instant.ofEpochSecond(jM, iM);
                    }
                    break;
                case 6:
                    jM = AnonymousClass0.m(this.value, 1000000L);
                    iM = ((int) AnonymousClass2.m(this.value, 1000000L)) * 1000;
                    if (jM <= -31557014167219200L) {
                        this.instant = Instant.MIN;
                    } else if (jM >= 31556889864403199L) {
                        this.instant = Instant.MAX;
                    } else {
                        this.instant = Instant.ofEpochSecond(jM, iM);
                    }
                    break;
                case 7:
                    jM = AnonymousClass0.m(this.value, C.NANOS_PER_SECOND);
                    iM = (int) AnonymousClass2.m(this.value, C.NANOS_PER_SECOND);
                    if (jM <= -31557014167219200L) {
                        this.instant = Instant.MIN;
                    } else if (jM >= 31556889864403199L) {
                        this.instant = Instant.MAX;
                    } else {
                        this.instant = Instant.ofEpochSecond(jM, iM);
                    }
                    break;
                default:
                    throw new AssertionError("Unit not handled");
            }
        }
        return this.instant;
    }

    /* JADX INFO: renamed from: j$.nio.file.attribute.FileTime$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            $SwitchMap$java$util$concurrent$TimeUnit = iArr;
            try {
                iArr[TimeUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.NANOSECONDS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof FileTime) && compareTo((FileTime) obj) == 0;
    }

    public int hashCode() {
        return toInstant().hashCode();
    }

    private long toDays() {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return timeUnit.toDays(this.value);
        }
        return toInstant().getEpochSecond() / 86400;
    }

    private long toExcessNanos(long j) {
        TimeUnit timeUnit = this.unit;
        TimeUnit timeUnit2 = TimeUnit.DAYS;
        if (timeUnit != null) {
            return timeUnit.toNanos(this.value - timeUnit.convert(j, timeUnit2));
        }
        return TimeUnit.SECONDS.toNanos(toInstant().getEpochSecond() - timeUnit2.toSeconds(j));
    }

    @Override // java.lang.Comparable
    public int compareTo(FileTime fileTime) {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null && timeUnit == fileTime.unit) {
            return Long.compare(this.value, fileTime.value);
        }
        long epochSecond = toInstant().getEpochSecond();
        int iCompare = Long.compare(epochSecond, fileTime.toInstant().getEpochSecond());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = Long.compare(toInstant().getNano(), fileTime.toInstant().getNano());
        if (iCompare2 != 0) {
            return iCompare2;
        }
        if (epochSecond != 31556889864403199L && epochSecond != -31557014167219200L) {
            return 0;
        }
        long days = toDays();
        long days2 = fileTime.toDays();
        if (days == days2) {
            return Long.compare(toExcessNanos(days), fileTime.toExcessNanos(days2));
        }
        return Long.compare(days, days2);
    }

    private StringBuilder append(StringBuilder sb, int i, int i2) {
        while (i > 0) {
            sb.append((char) ((i2 / i) + 48));
            i2 %= i;
            i /= 10;
        }
        return sb;
    }

    public String toString() {
        long epochSecond;
        int nano;
        long jM;
        LocalDateTime localDateTimeOfEpochSecond;
        int year;
        if (this.valueAsString == null) {
            if (this.instant == null && this.unit.compareTo(TimeUnit.SECONDS) >= 0) {
                epochSecond = this.unit.toSeconds(this.value);
                nano = 0;
            } else {
                epochSecond = toInstant().getEpochSecond();
                nano = toInstant().getNano();
            }
            if (epochSecond >= -62167219200L) {
                long j = epochSecond - 253402300800L;
                jM = AnonymousClass0.m(j, DateCalculationsKt.SECONDS_PER_10000_YEARS) + 1;
                localDateTimeOfEpochSecond = LocalDateTime.ofEpochSecond(AnonymousClass2.m(j, DateCalculationsKt.SECONDS_PER_10000_YEARS) - 62167219200L, nano, ZoneOffset.UTC);
                year = localDateTimeOfEpochSecond.getYear();
            } else {
                long j2 = epochSecond + 62167219200L;
                jM = j2 / DateCalculationsKt.SECONDS_PER_10000_YEARS;
                localDateTimeOfEpochSecond = LocalDateTime.ofEpochSecond((j2 % DateCalculationsKt.SECONDS_PER_10000_YEARS) - 62167219200L, nano, ZoneOffset.UTC);
                year = localDateTimeOfEpochSecond.getYear();
            }
            int i = year + (((int) jM) * 10000);
            if (i <= 0) {
                i--;
            }
            int nano2 = localDateTimeOfEpochSecond.getNano();
            StringBuilder sb = new StringBuilder(64);
            sb.append(i < 0 ? "-" : "");
            int iAbs = Math.abs(i);
            if (iAbs < 10000) {
                append(sb, 1000, Math.abs(iAbs));
            } else {
                sb.append(String.valueOf(iAbs));
            }
            sb.append('-');
            append(sb, 10, localDateTimeOfEpochSecond.getMonthValue());
            sb.append('-');
            append(sb, 10, localDateTimeOfEpochSecond.getDayOfMonth());
            sb.append('T');
            append(sb, 10, localDateTimeOfEpochSecond.getHour());
            sb.append(AbstractJsonLexerKt.COLON);
            append(sb, 10, localDateTimeOfEpochSecond.getMinute());
            sb.append(AbstractJsonLexerKt.COLON);
            append(sb, 10, localDateTimeOfEpochSecond.getSecond());
            if (nano2 != 0) {
                sb.append('.');
                int i2 = 100000000;
                while (nano2 % 10 == 0) {
                    nano2 /= 10;
                    i2 /= 10;
                }
                append(sb, i2, nano2);
            }
            sb.append('Z');
            this.valueAsString = sb.toString();
        }
        return this.valueAsString;
    }
}
