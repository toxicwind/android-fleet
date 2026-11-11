package kotlinx.datetime;

import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.DateTimeException;
import j$.time.chrono.ChronoLocalDate;
import j$.time.format.DateTimeParseException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.format.DateTimeFormatBuilder;
import kotlinx.datetime.format.LocalDateFormat;
import kotlinx.datetime.format.LocalDateFormatKt;
import kotlinx.datetime.internal.MathKt;
import kotlinx.datetime.internal.UtilKt;
import kotlinx.datetime.serializers.LocalDateSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: LocalDate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 C2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0002CDB\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B!\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\u000bB!\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\f\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\rB!\b\u0017\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\u0010J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b \u0010!J\u0018\u0010\"\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\"\u0010!J\u001a\u0010%\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\u0016H\u0096\u0002¢\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u0007H\u0016¢\u0006\u0004\b'\u0010(J\u000f\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+J\u0018\u0010,\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b,\u0010-J\r\u0010/\u001a\u00020.¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\u0007H\u0001¢\u0006\u0004\b/\u0010(R\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u00102\u001a\u0004\b3\u00104R\u001a\u00108\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b6\u00107\u001a\u0004\b5\u0010(R\u001a\u0010\u000f\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b:\u00107\u001a\u0004\b9\u0010(R\u0011\u0010\b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b;\u0010(R\u0011\u0010\t\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010<R\u0011\u0010\n\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b=\u0010(R\u0011\u0010@\u001a\u00020>8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010?R\u0011\u0010B\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bA\u0010(¨\u0006E"}, d2 = {"Lkotlinx/datetime/LocalDate;", "", "Ljava/io/Serializable;", "j$/time/LocalDate", ES6Iterator.VALUE_PROPERTY, "<init>", "(Lj$/time/LocalDate;)V", "", "year", "month", "day", "(III)V", "Lkotlinx/datetime/Month;", "(ILkotlinx/datetime/Month;I)V", "j$/time/Month", "dayOfMonth", "(ILj$/time/Month;I)V", "Ljava/io/ObjectInputStream;", "ois", "", "readObject", "(Ljava/io/ObjectInputStream;)V", "", "writeReplace", "()Ljava/lang/Object;", "getMonth", "()Lj$/time/Month;", "j$/time/DayOfWeek", "getDayOfWeek", "()Lj$/time/DayOfWeek;", "that", "Lkotlinx/datetime/LocalDateRange;", "rangeTo", "(Lkotlinx/datetime/LocalDate;)Lkotlinx/datetime/LocalDateRange;", "rangeUntil", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "compareTo", "(Lkotlinx/datetime/LocalDate;)I", "", "toEpochDays", "()J", "toEpochDaysJvm", "Lj$/time/LocalDate;", "getValue$kotlinx_datetime", "()Lj$/time/LocalDate;", "getMonthNumber", "getMonthNumber$annotations", "()V", "monthNumber", "getDayOfMonth", "getDayOfMonth$annotations", "getYear", "()Lkotlinx/datetime/Month;", "getDay", "Lkotlinx/datetime/DayOfWeek;", "()Lkotlinx/datetime/DayOfWeek;", "dayOfWeek", "getDayOfYear", "dayOfYear", "Companion", "Formats", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@Serializable(with = LocalDateSerializer.class)
public final class LocalDate implements Comparable<LocalDate>, java.io.Serializable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final LocalDate MAX;
    private static final LocalDate MIN;
    private static final long serialVersionUID = 0;
    private final j$.time.LocalDate value;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the 'day' property instead", replaceWith = @ReplaceWith(expression = "this.day", imports = {}))
    public static /* synthetic */ void getDayOfMonth$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the 'month' property instead", replaceWith = @ReplaceWith(expression = "this.month.number", imports = {}))
    public static /* synthetic */ void getMonthNumber$annotations() {
    }

    public LocalDate(j$.time.LocalDate value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    /* JADX INFO: renamed from: getValue$kotlinx_datetime, reason: from getter */
    public final j$.time.LocalDate getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: LocalDate.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J \u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0007J\u001e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fJ\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u000e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0007J%\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001c¢\u0006\u0002\b\u001fJ\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\"R\u0014\u0010\u0012\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u000e\u0010 \u001a\u00020\u0019X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lkotlinx/datetime/LocalDate$Companion;", "", "<init>", "()V", "orNull", "Lkotlinx/datetime/LocalDate;", "year", "", "month", "day", "Lkotlinx/datetime/Month;", "parse", TvContractCompat.PARAM_INPUT, "", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "isoString", "", "MIN", "getMIN$kotlinx_datetime", "()Lkotlinx/datetime/LocalDate;", "MAX", "getMAX$kotlinx_datetime", "fromEpochDays", "epochDays", "", "Format", "block", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "", "Lkotlin/ExtensionFunctionType;", "serialVersionUID", "serializer", "Lkotlinx/serialization/KSerializer;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<LocalDate> serializer() {
            return LocalDateSerializer.INSTANCE;
        }

        public final LocalDate orNull(int year, int month, int day) {
            try {
                return new LocalDate(year, month, day);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        public final LocalDate orNull(int year, Month month, int day) {
            Intrinsics.checkNotNullParameter(month, "month");
            try {
                return new LocalDate(year, month, day);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        public final LocalDate parse(CharSequence input, DateTimeFormat<LocalDate> format) {
            Intrinsics.checkNotNullParameter(input, "input");
            Intrinsics.checkNotNullParameter(format, "format");
            if (format == Formats.INSTANCE.getISO()) {
                try {
                    return new LocalDate(j$.time.LocalDate.parse(UtilKt.removeLeadingZerosFromLongYearFormLocalDate(input.toString())));
                } catch (DateTimeParseException e) {
                    throw new DateTimeFormatException(e);
                }
            }
            return format.parse(input);
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This overload is only kept for binary compatibility")
        public final /* synthetic */ LocalDate parse(String isoString) {
            Intrinsics.checkNotNullParameter(isoString, "isoString");
            return parse$default(this, isoString, null, 2, null);
        }

        public final LocalDate getMIN$kotlinx_datetime() {
            return LocalDate.MIN;
        }

        public final LocalDate getMAX$kotlinx_datetime() {
            return LocalDate.MAX;
        }

        public final LocalDate fromEpochDays(long epochDays) {
            try {
                j$.time.LocalDate localDateOfEpochDay = j$.time.LocalDate.ofEpochDay(epochDays);
                Intrinsics.checkNotNullExpressionValue(localDateOfEpochDay, "ofEpochDay(...)");
                return new LocalDate(localDateOfEpochDay);
            } catch (DateTimeException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public final LocalDate fromEpochDays(int epochDays) {
            return fromEpochDays(epochDays);
        }

        public final DateTimeFormat<LocalDate> Format(Function1<? super DateTimeFormatBuilder.WithDate, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return LocalDateFormat.INSTANCE.build(block);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LocalDate parse$default(Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
            if ((i & 2) != 0) {
                dateTimeFormat = LocalDateKt.getIsoDateFormat();
            }
            return companion.parse(charSequence, dateTimeFormat);
        }
    }

    static {
        j$.time.LocalDate MIN2 = j$.time.LocalDate.MIN;
        Intrinsics.checkNotNullExpressionValue(MIN2, "MIN");
        MIN = new LocalDate(MIN2);
        j$.time.LocalDate MAX2 = j$.time.LocalDate.MAX;
        Intrinsics.checkNotNullExpressionValue(MAX2, "MAX");
        MAX = new LocalDate(MAX2);
    }

    /* JADX INFO: compiled from: LocalDate.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lkotlinx/datetime/LocalDate$Formats;", "", "<init>", "()V", ExifInterface.TAG_RW2_ISO, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalDate;", "getISO", "()Lkotlinx/datetime/format/DateTimeFormat;", "ISO_BASIC", "getISO_BASIC", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Formats {
        public static final Formats INSTANCE = new Formats();
        private static final DateTimeFormat<LocalDate> ISO_BASIC = LocalDateFormatKt.getISO_DATE_BASIC();

        private Formats() {
        }

        public final DateTimeFormat<LocalDate> getISO() {
            return LocalDateFormatKt.getISO_DATE();
        }

        public final DateTimeFormat<LocalDate> getISO_BASIC() {
            return ISO_BASIC;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LocalDate(int i, int i2, int i3) {
        try {
            j$.time.LocalDate localDateOf = j$.time.LocalDate.of(i, i2, i3);
            Intrinsics.checkNotNull(localDateOf);
            this(localDateOf);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalDate(int i, Month month, int i2) {
        this(i, MonthKt.getNumber(month), i2);
        Intrinsics.checkNotNullParameter(month, "month");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Use kotlinx.datetime.Month", replaceWith = @ReplaceWith(expression = "LocalDate(year, month.toKotlinMonth(), dayOfMonth)", imports = {}))
    public LocalDate(int i, j$.time.Month month, int i2) {
        this(i, ConvertersKt.toKotlinMonth(month), i2);
        Intrinsics.checkNotNullParameter(month, "month");
    }

    public final int getYear() {
        return this.value.getYear();
    }

    public final int getMonthNumber() {
        return this.value.getMonthValue();
    }

    /* JADX INFO: renamed from: getMonth, reason: collision with other method in class */
    public final Month m10564getMonth() {
        j$.time.Month month = this.value.getMonth();
        Intrinsics.checkNotNullExpressionValue(month, "getMonth(...)");
        return ConvertersKt.toKotlinMonth(month);
    }

    public final j$.time.Month getMonth() {
        j$.time.Month month = this.value.getMonth();
        Intrinsics.checkNotNullExpressionValue(month, "getMonth(...)");
        return month;
    }

    public final int getDayOfMonth() {
        return this.value.getDayOfMonth();
    }

    public final int getDay() {
        return this.value.getDayOfMonth();
    }

    /* JADX INFO: renamed from: getDayOfWeek, reason: collision with other method in class */
    public final DayOfWeek m10563getDayOfWeek() {
        j$.time.DayOfWeek dayOfWeek = this.value.getDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dayOfWeek, "getDayOfWeek(...)");
        return ConvertersKt.toKotlinDayOfWeek(dayOfWeek);
    }

    public final j$.time.DayOfWeek getDayOfWeek() {
        j$.time.DayOfWeek dayOfWeek = this.value.getDayOfWeek();
        Intrinsics.checkNotNullExpressionValue(dayOfWeek, "getDayOfWeek(...)");
        return dayOfWeek;
    }

    public final int getDayOfYear() {
        return this.value.getDayOfYear();
    }

    public final LocalDateRange rangeTo(LocalDate that) {
        Intrinsics.checkNotNullParameter(that, "that");
        return LocalDateRange.INSTANCE.fromRangeTo$kotlinx_datetime(this, that);
    }

    public final LocalDateRange rangeUntil(LocalDate that) {
        Intrinsics.checkNotNullParameter(that, "that");
        return LocalDateRange.INSTANCE.fromRangeUntil$kotlinx_datetime(this, that);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LocalDate) && Intrinsics.areEqual(this.value, ((LocalDate) other).value);
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
    public int compareTo(LocalDate other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.value.compareTo((ChronoLocalDate) other.value);
    }

    /* JADX INFO: renamed from: toEpochDays, reason: collision with other method in class */
    public final long m10565toEpochDays() {
        return this.value.toEpochDay();
    }

    public final int toEpochDays() {
        return MathKt.clampToInt(this.value.toEpochDay());
    }

    private final void readObject(ObjectInputStream ois) throws InvalidObjectException {
        throw new InvalidObjectException("kotlinx.datetime.LocalDate must be deserialized via kotlinx.datetime.Ser");
    }

    private final Object writeReplace() {
        return new Ser(2, this);
    }
}
