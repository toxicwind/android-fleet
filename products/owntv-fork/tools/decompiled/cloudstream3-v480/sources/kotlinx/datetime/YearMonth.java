package kotlinx.datetime;

import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.DateTimeException;
import j$.time.format.DateTimeParseException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.format.DateTimeFormatBuilder;
import kotlinx.datetime.format.YearMonthFormat;
import kotlinx.datetime.format.YearMonthFormatKt;
import kotlinx.datetime.internal.UtilKt;
import kotlinx.datetime.serializers.YearMonthSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: YearMonthJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 92\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u00029:B\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\nB\u0019\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u000b¢\u0006\u0004\b\u0005\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0019\u0010\u0018J\u0018\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010!\u001a\u00020 2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0012H\u0096\u0002¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u0007H\u0016¢\u0006\u0004\b#\u0010$R\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b&\u0010'R\u0011\u0010\b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b(\u0010$R\u0014\u0010*\u001a\u00020\u00078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b)\u0010$R\u0011\u0010\t\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0011\u00100\u001a\u00020-8F¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0011\u00102\u001a\u00020-8F¢\u0006\u0006\u001a\u0004\b1\u0010/R\u0011\u00104\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b3\u0010$R\u0011\u00108\u001a\u0002058F¢\u0006\u0006\u001a\u0004\b6\u00107¨\u0006;"}, d2 = {"Lkotlinx/datetime/YearMonth;", "", "Ljava/io/Serializable;", "j$/time/YearMonth", ES6Iterator.VALUE_PROPERTY, "<init>", "(Lj$/time/YearMonth;)V", "", "year", "month", "(II)V", "Lkotlinx/datetime/Month;", "(ILkotlinx/datetime/Month;)V", "Ljava/io/ObjectInputStream;", "ois", "", "readObject", "(Ljava/io/ObjectInputStream;)V", "", "writeReplace", "()Ljava/lang/Object;", "that", "Lkotlinx/datetime/YearMonthRange;", "rangeTo", "(Lkotlinx/datetime/YearMonth;)Lkotlinx/datetime/YearMonthRange;", "rangeUntil", "other", "compareTo", "(Lkotlinx/datetime/YearMonth;)I", "", "toString", "()Ljava/lang/String;", "", "equals", Signatures.MATH_TO_BOOLEAN, "hashCode", "()I", "Lj$/time/YearMonth;", "getValue$kotlinx_datetime", "()Lj$/time/YearMonth;", "getYear", "getMonthNumber$kotlinx_datetime", "monthNumber", "getMonth", "()Lkotlinx/datetime/Month;", "Lkotlinx/datetime/LocalDate;", "getFirstDay", "()Lkotlinx/datetime/LocalDate;", "firstDay", "getLastDay", "lastDay", "getNumberOfDays", "numberOfDays", "Lkotlinx/datetime/LocalDateRange;", "getDays", "()Lkotlinx/datetime/LocalDateRange;", "days", "Companion", "Formats", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@Serializable(with = YearMonthSerializer.class)
public final class YearMonth implements Comparable<YearMonth>, java.io.Serializable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long serialVersionUID = 0;
    private final j$.time.YearMonth value;

    public YearMonth(j$.time.YearMonth value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    /* JADX INFO: renamed from: getValue$kotlinx_datetime, reason: from getter */
    public final j$.time.YearMonth getValue() {
        return this.value;
    }

    public final int getYear() {
        return this.value.getYear();
    }

    public final int getMonthNumber$kotlinx_datetime() {
        return this.value.getMonthValue();
    }

    public final Month getMonth() {
        j$.time.Month month = this.value.getMonth();
        Intrinsics.checkNotNullExpressionValue(month, "getMonth(...)");
        return ConvertersKt.toKotlinMonth(month);
    }

    public final LocalDate getFirstDay() {
        j$.time.LocalDate localDateAtDay = this.value.atDay(1);
        Intrinsics.checkNotNullExpressionValue(localDateAtDay, "atDay(...)");
        return new LocalDate(localDateAtDay);
    }

    public final LocalDate getLastDay() {
        j$.time.LocalDate localDateAtEndOfMonth = this.value.atEndOfMonth();
        Intrinsics.checkNotNullExpressionValue(localDateAtEndOfMonth, "atEndOfMonth(...)");
        return new LocalDate(localDateAtEndOfMonth);
    }

    public final int getNumberOfDays() {
        return this.value.lengthOfMonth();
    }

    public final LocalDateRange getDays() {
        return getFirstDay().rangeTo(getLastDay());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public YearMonth(int i, int i2) {
        try {
            j$.time.YearMonth yearMonthOf = j$.time.YearMonth.of(i, i2);
            Intrinsics.checkNotNull(yearMonthOf);
            this(yearMonthOf);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public YearMonth(int i, Month month) {
        Intrinsics.checkNotNullParameter(month, "month");
        try {
            j$.time.YearMonth yearMonthOf = j$.time.YearMonth.of(i, ConvertersKt.toJavaMonth(month));
            Intrinsics.checkNotNull(yearMonthOf);
            this(yearMonthOf);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* JADX INFO: compiled from: YearMonthJvm.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eJ%\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0018R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lkotlinx/datetime/YearMonth$Companion;", "", "<init>", "()V", "orNull", "Lkotlinx/datetime/YearMonth;", "year", "", "month", "Lkotlinx/datetime/Month;", "parse", TvContractCompat.PARAM_INPUT, "", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "Format", "block", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "", "Lkotlin/ExtensionFunctionType;", "serialVersionUID", "", "serializer", "Lkotlinx/serialization/KSerializer;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<YearMonth> serializer() {
            return YearMonthSerializer.INSTANCE;
        }

        public final YearMonth orNull(int year, int month) {
            try {
                return new YearMonth(year, month);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        public final YearMonth orNull(int year, Month month) {
            Intrinsics.checkNotNullParameter(month, "month");
            try {
                return new YearMonth(year, month);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        public final YearMonth parse(CharSequence input, DateTimeFormat<YearMonth> format) {
            Intrinsics.checkNotNullParameter(input, "input");
            Intrinsics.checkNotNullParameter(format, "format");
            if (format == Formats.INSTANCE.getISO()) {
                try {
                    return new YearMonth(j$.time.YearMonth.parse(UtilKt.removeLeadingZerosFromLongYearFormYearMonth(input.toString())));
                } catch (DateTimeParseException e) {
                    throw new DateTimeFormatException(e);
                }
            }
            return format.parse(input);
        }

        public final DateTimeFormat<YearMonth> Format(Function1<? super DateTimeFormatBuilder.WithYearMonth, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return YearMonthFormat.INSTANCE.build(block);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ YearMonth parse$default(Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
            if ((i & 2) != 0) {
                dateTimeFormat = Formats.INSTANCE.getISO();
            }
            return companion.parse(charSequence, dateTimeFormat);
        }
    }

    /* JADX INFO: compiled from: YearMonthJvm.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/datetime/YearMonth$Formats;", "", "<init>", "()V", ExifInterface.TAG_RW2_ISO, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/YearMonth;", "getISO", "()Lkotlinx/datetime/format/DateTimeFormat;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Formats {
        public static final Formats INSTANCE = new Formats();

        private Formats() {
        }

        public final DateTimeFormat<YearMonth> getISO() {
            return YearMonthFormatKt.getISO_YEAR_MONTH();
        }
    }

    public final YearMonthRange rangeTo(YearMonth that) {
        Intrinsics.checkNotNullParameter(that, "that");
        return YearMonthRange.INSTANCE.fromRangeTo$kotlinx_datetime(this, that);
    }

    public final YearMonthRange rangeUntil(YearMonth that) {
        Intrinsics.checkNotNullParameter(that, "that");
        return YearMonthRange.INSTANCE.fromRangeUntil$kotlinx_datetime(this, that);
    }

    @Override // java.lang.Comparable
    public int compareTo(YearMonth other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.value.compareTo(other.value);
    }

    public String toString() {
        String str = YearMonthJvmKt.getIsoFormat().format(this.value);
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof YearMonth) && Intrinsics.areEqual(this.value, ((YearMonth) other).value);
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    private final void readObject(ObjectInputStream ois) throws InvalidObjectException {
        throw new InvalidObjectException("kotlinx.datetime.YearMonth must be deserialized via kotlinx.datetime.Ser");
    }

    private final Object writeReplace() {
        return new Ser(11, this);
    }
}
