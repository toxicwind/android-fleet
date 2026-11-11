package j$.time;

import androidx.media3.common.C;
import io.ktor.util.date.GMTDateParser;
import j$.nio.file.attribute.FileTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAmount;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class Duration implements TemporalAmount, Comparable<Duration>, Serializable {
    private static final long serialVersionUID = 3078945930695997490L;
    private final int nanos;
    private final long seconds;
    public static final Duration ZERO = new Duration(0, 0);
    private static final BigInteger BI_NANOS_PER_SECOND = BigInteger.valueOf(C.NANOS_PER_SECOND);

    /* JADX INFO: renamed from: j$.time.Duration$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ long m(long j, int i) {
            return AnonymousClass2.m(j, i);
        }
    }

    /* JADX INFO: renamed from: j$.time.Duration$2, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass2 {
        public static /* synthetic */ long m(long j, long j2) {
            int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2);
            if (iNumberOfLeadingZeros > 65) {
                return j * j2;
            }
            if (iNumberOfLeadingZeros >= 64) {
                if ((j2 != Long.MIN_VALUE) | (j >= 0)) {
                    long j3 = j * j2;
                    if (j == 0 || j3 / j == j2) {
                        return j3;
                    }
                }
            }
            throw new ArithmeticException();
        }
    }

    public static Duration ofSeconds(long j) {
        return create(j, 0);
    }

    public static Duration ofSeconds(long j, long j2) {
        return create(Clock$OffsetClock$0.m(j, FileTime.AnonymousClass0.m(j2, C.NANOS_PER_SECOND)), (int) FileTime.AnonymousClass2.m(j2, C.NANOS_PER_SECOND));
    }

    public static Duration ofMillis(long j) {
        long j2 = j / 1000;
        int i = (int) (j % 1000);
        if (i < 0) {
            i += 1000;
            j2--;
        }
        return create(j2, i * 1000000);
    }

    public static Duration ofNanos(long j) {
        long j2 = j / C.NANOS_PER_SECOND;
        int i = (int) (j % C.NANOS_PER_SECOND);
        if (i < 0) {
            i = (int) (((long) i) + C.NANOS_PER_SECOND);
            j2--;
        }
        return create(j2, i);
    }

    private static Duration create(long j, int i) {
        if ((((long) i) | j) == 0) {
            return ZERO;
        }
        return new Duration(j, i);
    }

    private Duration(long j, int i) {
        this.seconds = j;
        this.nanos = i;
    }

    public boolean isZero() {
        return (this.seconds | ((long) this.nanos)) == 0;
    }

    public boolean isNegative() {
        return this.seconds < 0;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int getNano() {
        return this.nanos;
    }

    @Override // j$.time.temporal.TemporalAmount
    public Temporal addTo(Temporal temporal) {
        long j = this.seconds;
        if (j != 0) {
            temporal = temporal.plus(j, ChronoUnit.SECONDS);
        }
        int i = this.nanos;
        return i != 0 ? temporal.plus(i, ChronoUnit.NANOS) : temporal;
    }

    public long toMillis() {
        long j = this.seconds;
        long j2 = this.nanos;
        if (j < 0) {
            j++;
            j2 -= C.NANOS_PER_SECOND;
        }
        return Clock$OffsetClock$0.m(AnonymousClass0.m(j, 1000), j2 / 1000000);
    }

    public long toNanos() {
        long j = this.seconds;
        long j2 = this.nanos;
        if (j < 0) {
            j++;
            j2 -= C.NANOS_PER_SECOND;
        }
        return Clock$OffsetClock$0.m(AnonymousClass2.m(j, C.NANOS_PER_SECOND), j2);
    }

    @Override // java.lang.Comparable
    public int compareTo(Duration duration) {
        int iCompare = Long.compare(this.seconds, duration.seconds);
        return iCompare != 0 ? iCompare : this.nanos - duration.nanos;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Duration) {
            Duration duration = (Duration) obj;
            if (this.seconds == duration.seconds && this.nanos == duration.nanos) {
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
        if (this == ZERO) {
            return "PT0S";
        }
        long j = this.seconds;
        if (j < 0 && this.nanos > 0) {
            j++;
        }
        long j2 = j / 3600;
        int i = (int) ((j % 3600) / 60);
        int i2 = (int) (j % 60);
        StringBuilder sb = new StringBuilder(24);
        sb.append("PT");
        if (j2 != 0) {
            sb.append(j2);
            sb.append('H');
        }
        if (i != 0) {
            sb.append(i);
            sb.append(GMTDateParser.MONTH);
        }
        if (i2 == 0 && this.nanos == 0 && sb.length() > 2) {
            return sb.toString();
        }
        if (this.seconds < 0 && this.nanos > 0 && i2 == 0) {
            sb.append("-0");
        } else {
            sb.append(i2);
        }
        if (this.nanos > 0) {
            int length = sb.length();
            long j3 = this.seconds;
            int i3 = this.nanos;
            if (j3 < 0) {
                sb.append(2000000000 - ((long) i3));
            } else {
                sb.append(((long) i3) + C.NANOS_PER_SECOND);
            }
            while (sb.charAt(sb.length() - 1) == '0') {
                sb.setLength(sb.length() - 1);
            }
            sb.setCharAt(length, '.');
        }
        sb.append('S');
        return sb.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.seconds);
        dataOutput.writeInt(this.nanos);
    }

    static Duration readExternal(DataInput dataInput) {
        return ofSeconds(dataInput.readLong(), dataInput.readInt());
    }
}
