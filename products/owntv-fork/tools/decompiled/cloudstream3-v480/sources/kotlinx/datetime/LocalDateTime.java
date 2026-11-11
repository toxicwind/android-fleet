package kotlinx.datetime;

import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.DateTimeException;
import j$.time.chrono.ChronoLocalDateTime;
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
import kotlinx.datetime.format.LocalDateTimeFormat;
import kotlinx.datetime.format.LocalDateTimeFormatKt;
import kotlinx.datetime.internal.UtilKt;
import kotlinx.datetime.serializers.LocalDateTimeSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: LocalDateTimeJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 K2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0002KLB\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006BE\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\u000fBE\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0010\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\u0011B\u0019\b\u0016\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0005\u0010\u0016BE\b\u0017\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\u0019J\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020$H\u0001¢\u0006\u0004\b%\u0010&J\u001a\u0010)\u001a\u00020(2\b\u0010'\u001a\u0004\u0018\u00010\u001fH\u0096\u0002¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0007H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/J\u0018\u00100\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b0\u00101R\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u00102\u001a\u0004\b3\u00104R\u001a\u00108\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b6\u00107\u001a\u0004\b5\u0010,R\u001a\u0010\u0018\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b:\u00107\u001a\u0004\b9\u0010,R\u0011\u0010\b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b;\u0010,R\u0011\u0010\t\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\"\u0010<R\u0011\u0010\n\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b=\u0010,R\u0011\u0010@\u001a\u00020>8F¢\u0006\u0006\u001a\u0004\b%\u0010?R\u0011\u0010B\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bA\u0010,R\u0011\u0010\u000b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bC\u0010,R\u0011\u0010\f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bD\u0010,R\u0011\u0010\r\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bE\u0010,R\u0011\u0010\u000e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bF\u0010,R\u0011\u0010\u0013\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0011\u0010\u0015\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\bI\u0010J¨\u0006M"}, d2 = {"Lkotlinx/datetime/LocalDateTime;", "", "Ljava/io/Serializable;", "j$/time/LocalDateTime", ES6Iterator.VALUE_PROPERTY, "<init>", "(Lj$/time/LocalDateTime;)V", "", "year", "month", "day", "hour", "minute", "second", "nanosecond", "(IIIIIII)V", "Lkotlinx/datetime/Month;", "(ILkotlinx/datetime/Month;IIIII)V", "Lkotlinx/datetime/LocalDate;", "date", "Lkotlinx/datetime/LocalTime;", "time", "(Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/LocalTime;)V", "j$/time/Month", "dayOfMonth", "(ILj$/time/Month;IIIII)V", "Ljava/io/ObjectInputStream;", "ois", "", "readObject", "(Ljava/io/ObjectInputStream;)V", "", "writeReplace", "()Ljava/lang/Object;", "getMonth", "()Lj$/time/Month;", "j$/time/DayOfWeek", "getDayOfWeek", "()Lj$/time/DayOfWeek;", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "compareTo", "(Lkotlinx/datetime/LocalDateTime;)I", "Lj$/time/LocalDateTime;", "getValue$kotlinx_datetime", "()Lj$/time/LocalDateTime;", "getMonthNumber", "getMonthNumber$annotations", "()V", "monthNumber", "getDayOfMonth", "getDayOfMonth$annotations", "getYear", "()Lkotlinx/datetime/Month;", "getDay", "Lkotlinx/datetime/DayOfWeek;", "()Lkotlinx/datetime/DayOfWeek;", "dayOfWeek", "getDayOfYear", "dayOfYear", "getHour", "getMinute", "getSecond", "getNanosecond", "getDate", "()Lkotlinx/datetime/LocalDate;", "getTime", "()Lkotlinx/datetime/LocalTime;", "Companion", "Formats", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@Serializable(with = LocalDateTimeSerializer.class)
public final class LocalDateTime implements Comparable<LocalDateTime>, java.io.Serializable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final LocalDateTime MAX;
    private static final LocalDateTime MIN;
    private static final long serialVersionUID = 0;
    private final j$.time.LocalDateTime value;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the 'day' property instead", replaceWith = @ReplaceWith(expression = "this.day", imports = {}))
    public static /* synthetic */ void getDayOfMonth$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the 'month' property instead", replaceWith = @ReplaceWith(expression = "this.month.number", imports = {}))
    public static /* synthetic */ void getMonthNumber$annotations() {
    }

    public LocalDateTime(j$.time.LocalDateTime value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    /* JADX INFO: renamed from: getValue$kotlinx_datetime, reason: from getter */
    public final j$.time.LocalDateTime getValue() {
        return this.value;
    }

    public /* synthetic */ LocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4, i5, (i8 & 32) != 0 ? 0 : i6, (i8 & 64) != 0 ? 0 : i7);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        try {
            j$.time.LocalDateTime localDateTimeOf = j$.time.LocalDateTime.of(i, i2, i3, i4, i5, i6, i7);
            Intrinsics.checkNotNull(localDateTimeOf);
            this(localDateTimeOf);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public /* synthetic */ LocalDateTime(int i, Month month, int i2, int i3, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, month, i2, i3, i4, (i7 & 32) != 0 ? 0 : i5, (i7 & 64) != 0 ? 0 : i6);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalDateTime(int i, Month month, int i2, int i3, int i4, int i5, int i6) {
        this(i, MonthKt.getNumber(month), i2, i3, i4, i5, i6);
        Intrinsics.checkNotNullParameter(month, "month");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LocalDateTime(LocalDate date, LocalTime time) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(time, "time");
        j$.time.LocalDateTime localDateTimeOf = j$.time.LocalDateTime.of(date.getValue(), time.getValue());
        Intrinsics.checkNotNullExpressionValue(localDateTimeOf, "of(...)");
        this(localDateTimeOf);
    }

    public /* synthetic */ LocalDateTime(int i, j$.time.Month month, int i2, int i3, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, month, i2, i3, i4, (i7 & 32) != 0 ? 0 : i5, (i7 & 64) != 0 ? 0 : i6);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Use kotlinx.datetime.Month", replaceWith = @ReplaceWith(expression = "LocalDateTime(year, month.toKotlinMonth(), dayOfMonth, hour, minute, second, nanosecond)", imports = {}))
    public LocalDateTime(int i, j$.time.Month month, int i2, int i3, int i4, int i5, int i6) {
        this(i, ConvertersKt.toKotlinMonth(month), i2, i3, i4, i5, i6);
        Intrinsics.checkNotNullParameter(month, "month");
    }

    public final int getYear() {
        return this.value.getYear();
    }

    public final int getMonthNumber() {
        return this.value.getMonthValue();
    }

    /* JADX INFO: renamed from: getMonth, reason: collision with other method in class */
    public final Month m10568getMonth() {
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
    public final DayOfWeek m10567getDayOfWeek() {
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

    public final LocalDate getDate() {
        j$.time.LocalDate localDate = this.value.toLocalDate();
        Intrinsics.checkNotNullExpressionValue(localDate, "toLocalDate(...)");
        return new LocalDate(localDate);
    }

    public final LocalTime getTime() {
        j$.time.LocalTime localTime = this.value.toLocalTime();
        Intrinsics.checkNotNullExpressionValue(localTime, "toLocalTime(...)");
        return new LocalTime(localTime);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LocalDateTime) && Intrinsics.areEqual(this.value, ((LocalDateTime) other).value);
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
    public int compareTo(LocalDateTime other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.value.compareTo((ChronoLocalDateTime<?>) other.value);
    }

    /* JADX INFO: compiled from: LocalDateTimeJvm.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u0007JD\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u0007J\u001e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J%\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001d¢\u0006\u0002\b J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050$R\u0014\u0010\u0016\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u000e\u0010!\u001a\u00020\"X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlinx/datetime/LocalDateTime$Companion;", "", "<init>", "()V", "orNull", "Lkotlinx/datetime/LocalDateTime;", "year", "", "month", "day", "hour", "minute", "second", "nanosecond", "Lkotlinx/datetime/Month;", "parse", TvContractCompat.PARAM_INPUT, "", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "isoString", "", "MIN", "getMIN$kotlinx_datetime", "()Lkotlinx/datetime/LocalDateTime;", "MAX", "getMAX$kotlinx_datetime", "Format", "builder", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDateTime;", "", "Lkotlin/ExtensionFunctionType;", "serialVersionUID", "", "serializer", "Lkotlinx/serialization/KSerializer;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<LocalDateTime> serializer() {
            return LocalDateTimeSerializer.INSTANCE;
        }

        public static /* synthetic */ LocalDateTime orNull$default(Companion companion, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Object obj) {
            if ((i8 & 32) != 0) {
                i6 = 0;
            }
            if ((i8 & 64) != 0) {
                i7 = 0;
            }
            return companion.orNull(i, i2, i3, i4, i5, i6, i7);
        }

        public final LocalDateTime orNull(int year, int month, int day, int hour, int minute, int second, int nanosecond) {
            try {
                return new LocalDateTime(j$.time.LocalDateTime.of(year, month, day, hour, minute, second, nanosecond));
            } catch (DateTimeException unused) {
                return null;
            }
        }

        public static /* synthetic */ LocalDateTime orNull$default(Companion companion, int i, Month month, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
            if ((i7 & 32) != 0) {
                i5 = 0;
            }
            if ((i7 & 64) != 0) {
                i6 = 0;
            }
            return companion.orNull(i, month, i2, i3, i4, i5, i6);
        }

        public final LocalDateTime orNull(int year, Month month, int day, int hour, int minute, int second, int nanosecond) {
            Intrinsics.checkNotNullParameter(month, "month");
            return orNull(year, MonthKt.getNumber(month), day, hour, minute, second, nanosecond);
        }

        public final LocalDateTime parse(CharSequence input, DateTimeFormat<LocalDateTime> format) {
            Intrinsics.checkNotNullParameter(input, "input");
            Intrinsics.checkNotNullParameter(format, "format");
            if (format == Formats.INSTANCE.getISO()) {
                try {
                    return new LocalDateTime(j$.time.LocalDateTime.parse(UtilKt.removeLeadingZerosFromLongYearFormLocalDateTime(input.toString())));
                } catch (DateTimeParseException e) {
                    throw new DateTimeFormatException(e);
                }
            }
            return format.parse(input);
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This overload is only kept for binary compatibility")
        public final /* synthetic */ LocalDateTime parse(String isoString) {
            Intrinsics.checkNotNullParameter(isoString, "isoString");
            return parse$default(this, isoString, null, 2, null);
        }

        public final LocalDateTime getMIN$kotlinx_datetime() {
            return LocalDateTime.MIN;
        }

        public final LocalDateTime getMAX$kotlinx_datetime() {
            return LocalDateTime.MAX;
        }

        public final DateTimeFormat<LocalDateTime> Format(Function1<? super DateTimeFormatBuilder.WithDateTime, Unit> builder) {
            Intrinsics.checkNotNullParameter(builder, "builder");
            return LocalDateTimeFormat.INSTANCE.build(builder);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LocalDateTime parse$default(Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
            if ((i & 2) != 0) {
                dateTimeFormat = LocalDateTimeKt.getIsoDateTimeFormat();
            }
            return companion.parse(charSequence, dateTimeFormat);
        }
    }

    static {
        j$.time.LocalDateTime MIN2 = j$.time.LocalDateTime.MIN;
        Intrinsics.checkNotNullExpressionValue(MIN2, "MIN");
        MIN = new LocalDateTime(MIN2);
        j$.time.LocalDateTime MAX2 = j$.time.LocalDateTime.MAX;
        Intrinsics.checkNotNullExpressionValue(MAX2, "MAX");
        MAX = new LocalDateTime(MAX2);
    }

    /* JADX INFO: compiled from: LocalDateTimeJvm.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/datetime/LocalDateTime$Formats;", "", "<init>", "()V", ExifInterface.TAG_RW2_ISO, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalDateTime;", "getISO", "()Lkotlinx/datetime/format/DateTimeFormat;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Formats {
        public static final Formats INSTANCE = new Formats();
        private static final DateTimeFormat<LocalDateTime> ISO = LocalDateTimeFormatKt.getISO_DATETIME();

        private Formats() {
        }

        public final DateTimeFormat<LocalDateTime> getISO() {
            return ISO;
        }
    }

    private final void readObject(ObjectInputStream ois) throws InvalidObjectException {
        throw new InvalidObjectException("kotlinx.datetime.LocalDateTime must be deserialized via kotlinx.datetime.Ser");
    }

    private final Object writeReplace() {
        return new Ser(4, this);
    }
}
