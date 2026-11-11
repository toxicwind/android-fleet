package kotlinx.datetime;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Ser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/datetime/Ser;", "Ljava/io/Externalizable;", "typeTag", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(ILjava/lang/Object;)V", "()V", "writeExternal", "", "out", "Ljava/io/ObjectOutput;", "readExternal", "in", "Ljava/io/ObjectInput;", "readResolve", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Ser implements Externalizable {
    public static final int DATE_TAG = 2;
    public static final int DATE_TIME_TAG = 4;
    public static final int TIME_TAG = 3;
    public static final int UTC_OFFSET_TAG = 10;
    public static final int YEAR_MONTH_TAG = 11;
    private static final long serialVersionUID = 0;
    private int typeTag;
    private Object value;

    public Ser(int i, Object obj) {
        this.typeTag = i;
        this.value = obj;
    }

    public Ser() {
        this(0, null);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeByte(this.typeTag);
        Object obj = this.value;
        int i = this.typeTag;
        if (i == 2) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.datetime.LocalDate");
            out.writeLong(((LocalDate) obj).getValue().toEpochDay());
            return;
        }
        if (i == 3) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.datetime.LocalTime");
            out.writeLong(((LocalTime) obj).toNanosecondOfDay());
            return;
        }
        if (i == 4) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.datetime.LocalDateTime");
            LocalDateTime localDateTime = (LocalDateTime) obj;
            out.writeLong(localDateTime.getDate().getValue().toEpochDay());
            out.writeLong(localDateTime.getTime().toNanosecondOfDay());
            return;
        }
        if (i == 10) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.datetime.UtcOffset");
            out.writeInt(((UtcOffset) obj).getTotalSeconds());
        } else if (i == 11) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.datetime.YearMonth");
            out.writeLong(YearMonthJvmKt.toEpochMonths((YearMonth) obj));
        } else {
            throw new IllegalStateException("Unknown type tag: " + this.typeTag + " for value: " + obj);
        }
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException {
        LocalDate localDate;
        Intrinsics.checkNotNullParameter(in, "in");
        byte b = in.readByte();
        this.typeTag = b;
        if (b == 2) {
            j$.time.LocalDate localDateOfEpochDay = j$.time.LocalDate.ofEpochDay(in.readLong());
            Intrinsics.checkNotNullExpressionValue(localDateOfEpochDay, "ofEpochDay(...)");
            localDate = new LocalDate(localDateOfEpochDay);
        } else if (b == 3) {
            localDate = LocalTime.INSTANCE.fromNanosecondOfDay(in.readLong());
        } else if (b == 4) {
            j$.time.LocalDate localDateOfEpochDay2 = j$.time.LocalDate.ofEpochDay(in.readLong());
            Intrinsics.checkNotNullExpressionValue(localDateOfEpochDay2, "ofEpochDay(...)");
            localDate = new LocalDateTime(new LocalDate(localDateOfEpochDay2), LocalTime.INSTANCE.fromNanosecondOfDay(in.readLong()));
        } else if (b == 10) {
            localDate = UtcOffsetJvmKt.UtcOffset$default(null, null, Integer.valueOf(in.readInt()), 3, null);
        } else if (b == 11) {
            localDate = YearMonthJvmKt.fromEpochMonths(YearMonth.INSTANCE, in.readLong());
        } else {
            throw new IOException("Unknown type tag: " + this.typeTag);
        }
        this.value = localDate;
    }

    private final Object readResolve() {
        Object obj = this.value;
        Intrinsics.checkNotNull(obj);
        return obj;
    }
}
