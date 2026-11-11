package kotlinx.datetime;

import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.DateTimeException;
import j$.time.format.DateTimeParseException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.format.DateTimeFormatBuilder;
import kotlinx.datetime.format.LocalTimeFormat;
import kotlinx.datetime.format.LocalTimeFormatKt;
import kotlinx.datetime.serializers.LocalTimeSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: LocalTimeJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0007\u0018\u0000 ,2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0002,-B\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B-\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0007¢\u0006\u0004\b\u0017\u0010\u0016J\r\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012H\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001f\u0010\u0016J\u000f\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\u0018\u0010#\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b#\u0010$R\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b&\u0010'R\u0011\u0010\b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b(\u0010\u0016R\u0011\u0010\t\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b)\u0010\u0016R\u0011\u0010\n\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b*\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b+\u0010\u0016¨\u0006."}, d2 = {"Lkotlinx/datetime/LocalTime;", "", "Ljava/io/Serializable;", "j$/time/LocalTime", ES6Iterator.VALUE_PROPERTY, "<init>", "(Lj$/time/LocalTime;)V", "", "hour", "minute", "second", "nanosecond", "(IIII)V", "Ljava/io/ObjectInputStream;", "ois", "", "readObject", "(Ljava/io/ObjectInputStream;)V", "", "writeReplace", "()Ljava/lang/Object;", "toSecondOfDay", "()I", "toMillisecondOfDay", "", "toNanosecondOfDay", "()J", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "", "toString", "()Ljava/lang/String;", "compareTo", "(Lkotlinx/datetime/LocalTime;)I", "Lj$/time/LocalTime;", "getValue$kotlinx_datetime", "()Lj$/time/LocalTime;", "getHour", "getMinute", "getSecond", "getNanosecond", "Companion", "Formats", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@Serializable(with = LocalTimeSerializer.class)
public final class LocalTime implements Comparable<LocalTime>, java.io.Serializable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final LocalTime MAX;
    private static final LocalTime MIN;
    private static final long serialVersionUID = 0;
    private final j$.time.LocalTime value;

    public LocalTime(j$.time.LocalTime value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    /* JADX INFO: renamed from: getValue$kotlinx_datetime, reason: from getter */
    public final j$.time.LocalTime getValue() {
        return this.value;
    }

    public /* synthetic */ LocalTime(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LocalTime(int i, int i2, int i3, int i4) {
        try {
            j$.time.LocalTime localTimeOf = j$.time.LocalTime.of(i, i2, i3, i4);
            Intrinsics.checkNotNull(localTimeOf);
            this(localTimeOf);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final int getHour() {
        return this.value.getHour();
    }

    public final int getMinute() {
        return this.value.getMinute();
    }

    public final int getSecond() {
        return this.value.getSecond();
    }

    public final int getNanosecond() {
        return this.value.getNano();
    }

    public final int toSecondOfDay() {
        return this.value.toSecondOfDay();
    }

    public final int toMillisecondOfDay() {
        return (int) (this.value.toNanoOfDay() / 1000000);
    }

    public final long toNanosecondOfDay() {
        return this.value.toNanoOfDay();
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LocalTime) && Intrinsics.areEqual(this.value, ((LocalTime) other).value);
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        String string = this.value.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalTime other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.value.compareTo(other.value);
    }

    /* JADX INFO: compiled from: LocalTimeJvm.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007J\u001e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fJ\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0007J\u000e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018J%\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0017\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0 ¢\u0006\u0002\b#J\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050&R\u0014\u0010\u0019\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u000e\u0010$\u001a\u00020\u0018X\u0082T¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lkotlinx/datetime/LocalTime$Companion;", "", "<init>", "()V", "orNull", "Lkotlinx/datetime/LocalTime;", "hour", "", "minute", "second", "nanosecond", "parse", TvContractCompat.PARAM_INPUT, "", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "isoString", "", "fromSecondOfDay", "secondOfDay", "fromMillisecondOfDay", "millisecondOfDay", "fromNanosecondOfDay", "nanosecondOfDay", "", "MIN", "getMIN$kotlinx_datetime", "()Lkotlinx/datetime/LocalTime;", "MAX", "getMAX$kotlinx_datetime", "Format", "builder", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "", "Lkotlin/ExtensionFunctionType;", "serialVersionUID", "serializer", "Lkotlinx/serialization/KSerializer;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<LocalTime> serializer() {
            return LocalTimeSerializer.INSTANCE;
        }

        public static /* synthetic */ LocalTime orNull$default(Companion companion, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 4) != 0) {
                i3 = 0;
            }
            if ((i5 & 8) != 0) {
                i4 = 0;
            }
            return companion.orNull(i, i2, i3, i4);
        }

        public final LocalTime orNull(int hour, int minute, int second, int nanosecond) {
            try {
                return new LocalTime(j$.time.LocalTime.of(hour, minute, second, nanosecond));
            } catch (DateTimeException unused) {
                return null;
            }
        }

        public final LocalTime parse(CharSequence input, DateTimeFormat<LocalTime> format) {
            Intrinsics.checkNotNullParameter(input, "input");
            Intrinsics.checkNotNullParameter(format, "format");
            if (format == Formats.INSTANCE.getISO()) {
                try {
                    return new LocalTime(j$.time.LocalTime.parse(input));
                } catch (DateTimeParseException e) {
                    throw new DateTimeFormatException(e);
                }
            }
            return format.parse(input);
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This overload is only kept for binary compatibility")
        public final /* synthetic */ LocalTime parse(String isoString) {
            Intrinsics.checkNotNullParameter(isoString, "isoString");
            return parse$default(this, isoString, null, 2, null);
        }

        public final LocalTime fromSecondOfDay(int secondOfDay) {
            try {
                return new LocalTime(j$.time.LocalTime.ofSecondOfDay(secondOfDay));
            } catch (DateTimeException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public final LocalTime fromMillisecondOfDay(int millisecondOfDay) {
            try {
                return new LocalTime(j$.time.LocalTime.ofNanoOfDay(((long) millisecondOfDay) * 1000000));
            } catch (Throwable th) {
                throw new IllegalArgumentException(th);
            }
        }

        public final LocalTime fromNanosecondOfDay(long nanosecondOfDay) {
            try {
                return new LocalTime(j$.time.LocalTime.ofNanoOfDay(nanosecondOfDay));
            } catch (DateTimeException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public final LocalTime getMIN$kotlinx_datetime() {
            return LocalTime.MIN;
        }

        public final LocalTime getMAX$kotlinx_datetime() {
            return LocalTime.MAX;
        }

        public final DateTimeFormat<LocalTime> Format(Function1<? super DateTimeFormatBuilder.WithTime, Unit> builder) {
            Intrinsics.checkNotNullParameter(builder, "builder");
            return LocalTimeFormat.INSTANCE.build(builder);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LocalTime parse$default(Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
            if ((i & 2) != 0) {
                dateTimeFormat = LocalTimeKt.getIsoTimeFormat();
            }
            return companion.parse(charSequence, dateTimeFormat);
        }
    }

    static {
        j$.time.LocalTime MIN2 = j$.time.LocalTime.MIN;
        Intrinsics.checkNotNullExpressionValue(MIN2, "MIN");
        MIN = new LocalTime(MIN2);
        j$.time.LocalTime MAX2 = j$.time.LocalTime.MAX;
        Intrinsics.checkNotNullExpressionValue(MAX2, "MAX");
        MAX = new LocalTime(MAX2);
    }

    /* JADX INFO: compiled from: LocalTimeJvm.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/datetime/LocalTime$Formats;", "", "<init>", "()V", ExifInterface.TAG_RW2_ISO, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalTime;", "getISO", "()Lkotlinx/datetime/format/DateTimeFormat;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Formats {
        public static final Formats INSTANCE = new Formats();

        private Formats() {
        }

        public final DateTimeFormat<LocalTime> getISO() {
            return LocalTimeFormatKt.getISO_TIME();
        }
    }

    private final void readObject(ObjectInputStream ois) throws InvalidObjectException {
        throw new InvalidObjectException("kotlinx.datetime.LocalTime must be deserialized via kotlinx.datetime.Ser");
    }

    private final Object writeReplace() {
        return new Ser(3, this);
    }
}
