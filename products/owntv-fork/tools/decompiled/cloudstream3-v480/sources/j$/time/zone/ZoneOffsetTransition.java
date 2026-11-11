package j$.time.zone;

import j$.desugar.sun.nio.fs.DesugarLinuxFileSystem;
import j$.time.Duration;
import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes3.dex */
public final class ZoneOffsetTransition implements Comparable, Serializable {
    private static final long serialVersionUID = -6946044323557704546L;
    private final long epochSecond;
    private final ZoneOffset offsetAfter;
    private final ZoneOffset offsetBefore;
    private final LocalDateTime transition;

    ZoneOffsetTransition(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
        this.epochSecond = localDateTime.toEpochSecond(zoneOffset);
        this.transition = localDateTime;
        this.offsetBefore = zoneOffset;
        this.offsetAfter = zoneOffset2;
    }

    ZoneOffsetTransition(long j, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
        this.epochSecond = j;
        this.transition = LocalDateTime.ofEpochSecond(j, 0, zoneOffset);
        this.offsetBefore = zoneOffset;
        this.offsetAfter = zoneOffset2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    void writeExternal(DataOutput dataOutput) {
        Ser.writeEpochSec(this.epochSecond, dataOutput);
        Ser.writeOffset(this.offsetBefore, dataOutput);
        Ser.writeOffset(this.offsetAfter, dataOutput);
    }

    static ZoneOffsetTransition readExternal(DataInput dataInput) throws IOException {
        long epochSec = Ser.readEpochSec(dataInput);
        ZoneOffset offset = Ser.readOffset(dataInput);
        ZoneOffset offset2 = Ser.readOffset(dataInput);
        if (offset.equals(offset2)) {
            ZoneId.AnonymousClass4.m("Offsets must not be equal");
            return null;
        }
        return new ZoneOffsetTransition(epochSec, offset, offset2);
    }

    public long toEpochSecond() {
        return this.epochSecond;
    }

    public LocalDateTime getDateTimeBefore() {
        return this.transition;
    }

    public LocalDateTime getDateTimeAfter() {
        return this.transition.plusSeconds(getDurationSeconds());
    }

    public ZoneOffset getOffsetBefore() {
        return this.offsetBefore;
    }

    public ZoneOffset getOffsetAfter() {
        return this.offsetAfter;
    }

    public Duration getDuration() {
        return Duration.ofSeconds(getDurationSeconds());
    }

    private int getDurationSeconds() {
        return getOffsetAfter().getTotalSeconds() - getOffsetBefore().getTotalSeconds();
    }

    public boolean isGap() {
        return getOffsetAfter().getTotalSeconds() > getOffsetBefore().getTotalSeconds();
    }

    List getValidOffsets() {
        return isGap() ? Collections.EMPTY_LIST : DesugarLinuxFileSystem.AnonymousClass1.m(new Object[]{getOffsetBefore(), getOffsetAfter()});
    }

    @Override // java.lang.Comparable
    public int compareTo(ZoneOffsetTransition zoneOffsetTransition) {
        return Long.compare(this.epochSecond, zoneOffsetTransition.epochSecond);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ZoneOffsetTransition) {
            ZoneOffsetTransition zoneOffsetTransition = (ZoneOffsetTransition) obj;
            if (this.epochSecond == zoneOffsetTransition.epochSecond && this.offsetBefore.equals(zoneOffsetTransition.offsetBefore) && this.offsetAfter.equals(zoneOffsetTransition.offsetAfter)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Integer.rotateLeft(this.offsetAfter.hashCode(), 16) ^ (this.transition.hashCode() ^ this.offsetBefore.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Transition[");
        sb.append(isGap() ? "Gap" : "Overlap");
        sb.append(" at ");
        sb.append(this.transition);
        sb.append(this.offsetBefore);
        sb.append(" to ");
        sb.append(this.offsetAfter);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
