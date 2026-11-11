package kotlinx.datetime;

import androidx.collection.SieveCacheKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.C;
import io.ktor.http.ContentType;
import io.ktor.util.date.GMTDateParser;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.datetime.internal.DateCalculationsKt;
import kotlinx.datetime.internal.MathJvmKt;
import kotlinx.datetime.serializers.DateTimePeriodSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: DateTimePeriod.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 !2\u00020\u0001:\u0001!B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0013\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010 \u001a\u00020\tH\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X \u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\u0005X \u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0014\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bR\u0014\u0010\u0016\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000bR\u0014\u0010\u0018\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000b\u0082\u0001\u0002\"#¨\u0006$"}, d2 = {"Lkotlinx/datetime/DateTimePeriod;", "", "<init>", "()V", "totalMonths", "", "getTotalMonths$kotlinx_datetime", "()J", "days", "", "getDays", "()I", "totalNanoseconds", "getTotalNanoseconds$kotlinx_datetime", "years", "getYears", "months", "getMonths", "hours", "getHours", "minutes", "getMinutes", "seconds", "getSeconds", "nanoseconds", "getNanoseconds", "allNonpositive", "", "toString", "", "equals", "other", "hashCode", "Companion", "Lkotlinx/datetime/DatePeriod;", "Lkotlinx/datetime/DateTimePeriodImpl;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@Serializable(with = DateTimePeriodSerializer.class)
public abstract class DateTimePeriod {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ DateTimePeriod(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract int getDays();

    public abstract long getTotalMonths$kotlinx_datetime();

    public abstract long getTotalNanoseconds$kotlinx_datetime();

    private DateTimePeriod() {
    }

    public final int getYears() {
        return (int) (getTotalMonths$kotlinx_datetime() / 12);
    }

    public final int getMonths() {
        return (int) (getTotalMonths$kotlinx_datetime() % 12);
    }

    public int getHours() {
        return (int) (getTotalNanoseconds$kotlinx_datetime() / DateCalculationsKt.NANOS_PER_HOUR);
    }

    public int getMinutes() {
        return (int) ((getTotalNanoseconds$kotlinx_datetime() % DateCalculationsKt.NANOS_PER_HOUR) / DateCalculationsKt.NANOS_PER_MINUTE);
    }

    public int getSeconds() {
        return (int) ((getTotalNanoseconds$kotlinx_datetime() % DateCalculationsKt.NANOS_PER_MINUTE) / C.NANOS_PER_SECOND);
    }

    public int getNanoseconds() {
        return (int) (getTotalNanoseconds$kotlinx_datetime() % C.NANOS_PER_SECOND);
    }

    private final boolean allNonpositive() {
        if (getTotalMonths$kotlinx_datetime() > 0 || getDays() > 0 || getTotalNanoseconds$kotlinx_datetime() > 0) {
            return false;
        }
        return ((getTotalMonths$kotlinx_datetime() | getTotalNanoseconds$kotlinx_datetime()) == 0 && getDays() == 0) ? false : true;
    }

    public String toString() {
        int i;
        Object objValueOf;
        StringBuilder sb = new StringBuilder();
        if (allNonpositive()) {
            sb.append('-');
            i = -1;
        } else {
            i = 1;
        }
        sb.append('P');
        if (getYears() != 0) {
            sb.append(getYears() * i);
            sb.append(GMTDateParser.YEAR);
        }
        if (getMonths() != 0) {
            sb.append(getMonths() * i);
            sb.append(GMTDateParser.MONTH);
        }
        if (getDays() != 0) {
            sb.append(getDays() * i);
            sb.append('D');
        }
        int hours = getHours();
        String str = "";
        String str2 = ExifInterface.GPS_DIRECTION_TRUE;
        if (hours != 0) {
            sb.append(ExifInterface.GPS_DIRECTION_TRUE);
            sb.append(getHours() * i);
            sb.append('H');
            str2 = "";
        }
        if (getMinutes() != 0) {
            sb.append(str2);
            sb.append(getMinutes() * i);
            sb.append(GMTDateParser.MONTH);
        } else {
            str = str2;
        }
        if ((getSeconds() | getNanoseconds()) != 0) {
            sb.append(str);
            if (getSeconds() != 0) {
                objValueOf = Integer.valueOf(getSeconds() * i);
            } else {
                objValueOf = getNanoseconds() * i < 0 ? "-0" : "0";
            }
            sb.append(objValueOf);
            if (getNanoseconds() != 0) {
                sb.append('.');
                sb.append(StringsKt.padStart(String.valueOf(Math.abs(getNanoseconds())), 9, '0'));
            }
            sb.append('S');
        }
        if (sb.length() == 1) {
            sb.append("0D");
        }
        return sb.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DateTimePeriod)) {
            return false;
        }
        DateTimePeriod dateTimePeriod = (DateTimePeriod) other;
        return getTotalMonths$kotlinx_datetime() == dateTimePeriod.getTotalMonths$kotlinx_datetime() && getDays() == dateTimePeriod.getDays() && getTotalNanoseconds$kotlinx_datetime() == dateTimePeriod.getTotalNanoseconds$kotlinx_datetime();
    }

    public int hashCode() {
        return (((UByte$$ExternalSyntheticBackport0.m(getTotalMonths$kotlinx_datetime()) * 31) + getDays()) * 31) + UByte$$ExternalSyntheticBackport0.m(getTotalNanoseconds$kotlinx_datetime());
    }

    /* JADX INFO: compiled from: DateTimePeriod.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J÷\u0001\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u0006\u001a\u00020\u000726\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\f2 \u0001\u0010\u0013\u001a\u009b\u0001\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\n0\u0014H\u0082\b¢\u0006\u0002\u0010\u001dJU\u0010\u001e\u001a\u00020\u0010*\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001026\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\fH\u0082\bJ\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050#¨\u0006$"}, d2 = {"Lkotlinx/datetime/DateTimePeriod$Companion;", "", "<init>", "()V", "parse", "Lkotlinx/datetime/DateTimePeriod;", "text", "", "parseOrNull", "parseImpl", ExifInterface.GPS_DIRECTION_TRUE, "parseException", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", ContentType.Message.TYPE, "", "position", "", "construct", "Lkotlin/Function7;", "years", "months", "days", "hours", "minutes", "seconds", "", "nanoseconds", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function7;)Ljava/lang/Object;", "toIntThrowing", "component", "", "iStart", "serializer", "Lkotlinx/serialization/KSerializer;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DateTimePeriod> serializer() {
            return DateTimePeriodSerializer.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:202:0x03fc  */
        /* JADX WARN: Code duplicated, block: B:204:0x0404  */
        /* JADX WARN: Code duplicated, block: B:207:0x040b  */
        /* JADX WARN: Code duplicated, block: B:214:0x0420  */
        /* JADX WARN: Code duplicated, block: B:216:0x0457  */
        /* JADX WARN: Code duplicated, block: B:218:0x045b  */
        /* JADX WARN: Code duplicated, block: B:253:0x04de A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:278:0x04bf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:279:0x04b0 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:280:0x04a1 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:281:0x0494 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:294:0x01dc A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:84:0x0199  */
        /* JADX WARN: Code duplicated, block: B:96:0x01ed  */
        private final <T> T parseImpl(String text, Function2 parseException, Function7<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Long, ? extends T> construct) {
            int i;
            int i2;
            int i3;
            char c;
            Integer num;
            char upperCase;
            int i4;
            int i5;
            int i6;
            char cCharAt;
            char c2;
            char cCharAt2;
            int i7;
            int i8;
            int i9 = 0;
            Integer num2 = 0;
            char c3 = 0;
            int i10 = 0;
            int i11 = 0;
            boolean z = false;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 1;
            while (true) {
                int i19 = i11;
                if (i9 >= text.length()) {
                    if (c3 == 0) {
                        parseException.invoke("Unexpected end of input; 'P' designator is required", Integer.valueOf(i9));
                        throw new KotlinNothingValueException();
                    }
                    if (c3 == 6) {
                        parseException.invoke("Unexpected end of input; at least one time component is required after 'T'", Integer.valueOf(i9));
                        throw new KotlinNothingValueException();
                    }
                    long j = ((long) i10) + ((long) (i19 * 7));
                    if (SieveCacheKt.NodeMetaAndPreviousMask > j || j > SieveCacheKt.NodeLinkMask) {
                        parseException.invoke("The total number of days under 'D' and 'W' designators should fit into an Int", num2);
                        throw new KotlinNothingValueException();
                    }
                    int i20 = (int) j;
                    if (!z) {
                        parseException.invoke("At least one component is required, but none were found", num2);
                        throw new KotlinNothingValueException();
                    }
                    return construct.invoke(Integer.valueOf(i12), Integer.valueOf(i13), Integer.valueOf(i20), Integer.valueOf(i14), Integer.valueOf(i15), Integer.valueOf(i16), Long.valueOf(i17));
                }
                if (c3 == 0) {
                    int i21 = i9 + 1;
                    if (i21 >= text.length() && (text.charAt(i9) == '+' || text.charAt(i9) == '-')) {
                        parseException.invoke("Unexpected end of string; 'P' designator is required", Integer.valueOf(i9));
                        throw new KotlinNothingValueException();
                    }
                    char cCharAt3 = text.charAt(i9);
                    if (cCharAt3 == '+' || cCharAt3 == '-') {
                        if (text.charAt(i9) == '-') {
                            i18 = -1;
                        }
                        if (text.charAt(i21) != 'P') {
                            parseException.invoke("Expected 'P', got '" + text.charAt(i21) + '\'', Integer.valueOf(i21));
                            throw new KotlinNothingValueException();
                        }
                        i9 += 2;
                        Unit unit = Unit.INSTANCE;
                    } else if (cCharAt3 == 'P') {
                        Unit unit2 = Unit.INSTANCE;
                        i9 = i21;
                    } else {
                        parseException.invoke("Expected '+', '-', 'P', got '" + text.charAt(i9) + '\'', Integer.valueOf(i9));
                        throw new KotlinNothingValueException();
                    }
                    c3 = 1;
                    i11 = i19;
                } else {
                    char cCharAt4 = text.charAt(i9);
                    if (cCharAt4 == '+' || cCharAt4 == '-') {
                        i = text.charAt(i9) == '-' ? i18 * (-1) : i18;
                        i2 = i9 + 1;
                        if (i2 < text.length()) {
                            char cCharAt5 = text.charAt(i2);
                            i3 = i9;
                            if ('0' <= cCharAt5) {
                                c = AbstractJsonLexerKt.COLON;
                                if (cCharAt5 >= ':') {
                                }
                                long jSafeAdd = 0;
                                while (true) {
                                    if (i2 < text.length()) {
                                        num = num2;
                                        break;
                                    }
                                    cCharAt2 = text.charAt(i2);
                                    num = num2;
                                    if ('0' <= cCharAt2 || cCharAt2 >= c) {
                                        break;
                                    }
                                    i7 = i;
                                    i8 = i12;
                                    try {
                                        jSafeAdd = MathJvmKt.safeAdd(MathJvmKt.safeMultiply(jSafeAdd, 10L), text.charAt(i2) - '0');
                                        i2++;
                                        i = i7;
                                        num2 = num;
                                        i12 = i8;
                                        c = AbstractJsonLexerKt.COLON;
                                    } catch (ArithmeticException unused) {
                                        parseException.invoke("The number is too large", Integer.valueOf(i3));
                                        throw new KotlinNothingValueException();
                                    }
                                }
                                long j2 = ((long) i) * jSafeAdd;
                                if (i2 != text.length()) {
                                    parseException.invoke("Expected a designator after the numerical value", Integer.valueOf(i2));
                                    throw new KotlinNothingValueException();
                                }
                                upperCase = Character.toUpperCase(text.charAt(i2));
                                i4 = i;
                                int i22 = i10;
                                int i23 = i13;
                                int i24 = i14;
                                if (upperCase != ',' || upperCase == '.') {
                                    i5 = i2 + 1;
                                    if (i5 >= text.length()) {
                                        parseException.invoke("Expected designator 'S' after " + text.charAt(i2), Integer.valueOf(i5));
                                        throw new KotlinNothingValueException();
                                    }
                                    i2 = i5;
                                    while (i2 < text.length() && '0' <= (cCharAt = text.charAt(i2)) && cCharAt < ':') {
                                        i2++;
                                    }
                                    i6 = i2 - i5;
                                    if (i6 > 9) {
                                        parseException.invoke("Only the nanosecond fractions of a second are supported", Integer.valueOf(i5));
                                        throw new KotlinNothingValueException();
                                    }
                                    StringBuilder sb = new StringBuilder();
                                    String strSubstring = text.substring(i5, i2);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                                    sb.append(strSubstring);
                                    sb.append(StringsKt.repeat("0", 9 - i6));
                                    int i25 = Integer.parseInt(sb.toString(), CharsKt.checkRadix(10)) * i4;
                                    if (text.charAt(i2) != 'S') {
                                        parseException.invoke("Expected the 'S' designator after a fraction", Integer.valueOf(i2));
                                        throw new KotlinNothingValueException();
                                    }
                                    if (c3 < '\t' || c3 < 6) {
                                        parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                                        throw new KotlinNothingValueException();
                                    }
                                    if (j2 >= SieveCacheKt.NodeMetaAndPreviousMask && j2 <= SieveCacheKt.NodeLinkMask) {
                                        int i26 = (int) j2;
                                        Unit unit3 = Unit.INSTANCE;
                                        i16 = i26;
                                        i17 = i25;
                                        c3 = '\t';
                                    } else {
                                        parseException.invoke("Value " + j2 + " does not fit into an Int, which is required for component 'S'", Integer.valueOf(i3));
                                        throw new KotlinNothingValueException();
                                    }
                                } else {
                                    if (upperCase != 'D') {
                                        if (upperCase != 'H') {
                                            if (upperCase == 'M') {
                                                if (c3 >= 6) {
                                                    c2 = '\b';
                                                    if (c3 >= '\b') {
                                                        parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                    if (j2 < SieveCacheKt.NodeMetaAndPreviousMask || j2 > SieveCacheKt.NodeLinkMask) {
                                                        parseException.invoke("Value " + j2 + " does not fit into an Int, which is required for component 'M'", Integer.valueOf(i3));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                    i15 = (int) j2;
                                                    i13 = i23;
                                                } else {
                                                    c2 = 3;
                                                    if (c3 >= 3) {
                                                        parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                    if (j2 < SieveCacheKt.NodeMetaAndPreviousMask || j2 > SieveCacheKt.NodeLinkMask) {
                                                        parseException.invoke("Value " + j2 + " does not fit into an Int, which is required for component 'M'", Integer.valueOf(i3));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                    i13 = (int) j2;
                                                }
                                                Unit unit4 = Unit.INSTANCE;
                                                c3 = c2;
                                                i10 = i22;
                                                i12 = i12;
                                                i14 = i24;
                                            } else if (upperCase != 'S') {
                                                if (upperCase != 'W') {
                                                    if (upperCase != 'Y') {
                                                        parseException.invoke("Expected a designator after the numerical value", Integer.valueOf(i2));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                    if (c3 >= 2) {
                                                        parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                    if (j2 >= SieveCacheKt.NodeMetaAndPreviousMask && j2 <= SieveCacheKt.NodeLinkMask) {
                                                        Unit unit5 = Unit.INSTANCE;
                                                        c3 = 2;
                                                        i12 = (int) j2;
                                                        i10 = i22;
                                                    } else {
                                                        parseException.invoke("Value " + j2 + " does not fit into an Int, which is required for component 'Y'", Integer.valueOf(i3));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                } else {
                                                    if (c3 >= 4) {
                                                        parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                    if (j2 >= SieveCacheKt.NodeMetaAndPreviousMask && j2 <= SieveCacheKt.NodeLinkMask) {
                                                        Unit unit6 = Unit.INSTANCE;
                                                        c3 = 4;
                                                        i19 = (int) j2;
                                                    } else {
                                                        parseException.invoke("Value " + j2 + " does not fit into an Int, which is required for component 'W'", Integer.valueOf(i3));
                                                        throw new KotlinNothingValueException();
                                                    }
                                                }
                                                i13 = i23;
                                                i14 = i24;
                                            } else {
                                                if (c3 >= '\t' || c3 < 6) {
                                                    parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                                                    throw new KotlinNothingValueException();
                                                }
                                                if (j2 >= SieveCacheKt.NodeMetaAndPreviousMask && j2 <= SieveCacheKt.NodeLinkMask) {
                                                    int i27 = (int) j2;
                                                    Unit unit7 = Unit.INSTANCE;
                                                    i16 = i27;
                                                    i10 = i22;
                                                    i12 = i12;
                                                    i13 = i23;
                                                    i14 = i24;
                                                    c3 = '\t';
                                                } else {
                                                    parseException.invoke("Value " + j2 + " does not fit into an Int, which is required for component 'S'", Integer.valueOf(i3));
                                                    throw new KotlinNothingValueException();
                                                }
                                            }
                                        } else {
                                            if (c3 >= 7 || c3 < 6) {
                                                parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                                                throw new KotlinNothingValueException();
                                            }
                                            if (j2 >= SieveCacheKt.NodeMetaAndPreviousMask && j2 <= SieveCacheKt.NodeLinkMask) {
                                                Unit unit8 = Unit.INSTANCE;
                                                c3 = 7;
                                                i14 = (int) j2;
                                                i10 = i22;
                                                i12 = i12;
                                                i13 = i23;
                                            } else {
                                                parseException.invoke("Value " + j2 + " does not fit into an Int, which is required for component 'H'", Integer.valueOf(i3));
                                                throw new KotlinNothingValueException();
                                            }
                                        }
                                    } else {
                                        if (c3 >= 5) {
                                            parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                                            throw new KotlinNothingValueException();
                                        }
                                        if (j2 >= SieveCacheKt.NodeMetaAndPreviousMask && j2 <= SieveCacheKt.NodeLinkMask) {
                                            Unit unit9 = Unit.INSTANCE;
                                            c3 = 5;
                                            i10 = (int) j2;
                                            i12 = i12;
                                            i13 = i23;
                                            i14 = i24;
                                        } else {
                                            parseException.invoke("Value " + j2 + " does not fit into an Int, which is required for component 'D'", Integer.valueOf(i3));
                                            throw new KotlinNothingValueException();
                                        }
                                    }
                                    i9 = i2 + 1;
                                    z = true;
                                    i11 = i19;
                                    num2 = num;
                                }
                                i10 = i22;
                                i12 = i12;
                                i13 = i23;
                                i14 = i24;
                                i9 = i2 + 1;
                                z = true;
                                i11 = i19;
                                num2 = num;
                            }
                        }
                        parseException.invoke("A number expected after '" + text.charAt(i2) + '\'', Integer.valueOf(i2));
                        throw new KotlinNothingValueException();
                    }
                    if (('0' <= cCharAt4 && cCharAt4 < ':') || cCharAt4 != 'T') {
                        i2 = i9;
                        i3 = i2;
                        i = i18;
                        c = AbstractJsonLexerKt.COLON;
                        long jSafeAdd2 = 0;
                        while (true) {
                            if (i2 < text.length()) {
                                num = num2;
                                break;
                            }
                            cCharAt2 = text.charAt(i2);
                            num = num2;
                            if ('0' <= cCharAt2) {
                            }
                            break;
                            break;
                            i2++;
                            i = i7;
                            num2 = num;
                            i12 = i8;
                            c = AbstractJsonLexerKt.COLON;
                        }
                        long j3 = ((long) i) * jSafeAdd2;
                        if (i2 != text.length()) {
                            parseException.invoke("Expected a designator after the numerical value", Integer.valueOf(i2));
                            throw new KotlinNothingValueException();
                        }
                        upperCase = Character.toUpperCase(text.charAt(i2));
                        i4 = i;
                        int i28 = i10;
                        int i29 = i13;
                        int i210 = i14;
                        if (upperCase != ',') {
                            i5 = i2 + 1;
                            if (i5 >= text.length()) {
                                parseException.invoke("Expected designator 'S' after " + text.charAt(i2), Integer.valueOf(i5));
                                throw new KotlinNothingValueException();
                            }
                            i2 = i5;
                            while (i2 < text.length()) {
                                i2++;
                            }
                            i6 = i2 - i5;
                            if (i6 > 9) {
                                parseException.invoke("Only the nanosecond fractions of a second are supported", Integer.valueOf(i5));
                                throw new KotlinNothingValueException();
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String strSubstring2 = text.substring(i5, i2);
                            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                            sb2.append(strSubstring2);
                            sb2.append(StringsKt.repeat("0", 9 - i6));
                            int i211 = Integer.parseInt(sb2.toString(), CharsKt.checkRadix(10)) * i4;
                            if (text.charAt(i2) != 'S') {
                                parseException.invoke("Expected the 'S' designator after a fraction", Integer.valueOf(i2));
                                throw new KotlinNothingValueException();
                            }
                            if (c3 < '\t') {
                            }
                            parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                            throw new KotlinNothingValueException();
                        }
                        i5 = i2 + 1;
                        if (i5 >= text.length()) {
                            parseException.invoke("Expected designator 'S' after " + text.charAt(i2), Integer.valueOf(i5));
                            throw new KotlinNothingValueException();
                        }
                        i2 = i5;
                        while (i2 < text.length()) {
                            i2++;
                        }
                        i6 = i2 - i5;
                        if (i6 > 9) {
                            parseException.invoke("Only the nanosecond fractions of a second are supported", Integer.valueOf(i5));
                            throw new KotlinNothingValueException();
                        }
                        StringBuilder sb3 = new StringBuilder();
                        String strSubstring3 = text.substring(i5, i2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                        sb3.append(strSubstring3);
                        sb3.append(StringsKt.repeat("0", 9 - i6));
                        int i212 = Integer.parseInt(sb3.toString(), CharsKt.checkRadix(10)) * i4;
                        if (text.charAt(i2) != 'S') {
                            parseException.invoke("Expected the 'S' designator after a fraction", Integer.valueOf(i2));
                            throw new KotlinNothingValueException();
                        }
                        if (c3 < '\t') {
                        }
                        parseException.invoke("Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'", Integer.valueOf(i2));
                        throw new KotlinNothingValueException();
                        i9 = i2 + 1;
                        z = true;
                        i11 = i19;
                        num2 = num;
                    } else {
                        if (c3 >= 6) {
                            parseException.invoke("Only one 'T' designator is allowed", Integer.valueOf(i9));
                            throw new KotlinNothingValueException();
                        }
                        i9++;
                        i11 = i19;
                        c3 = 6;
                    }
                }
            }
        }

        private final int toIntThrowing(long j, char c, int i, Function2 function2) {
            if (j >= SieveCacheKt.NodeMetaAndPreviousMask && j <= SieveCacheKt.NodeLinkMask) {
                return (int) j;
            }
            function2.invoke("Value " + j + " does not fit into an Int, which is required for component '" + c + '\'', Integer.valueOf(i));
            throw new KotlinNothingValueException();
        }

        /* JADX WARN: Code duplicated, block: B:204:0x04c0  */
        /* JADX WARN: Code duplicated, block: B:206:0x04c8  */
        /* JADX WARN: Code duplicated, block: B:209:0x04cf  */
        /* JADX WARN: Code duplicated, block: B:216:0x04e4  */
        /* JADX WARN: Code duplicated, block: B:218:0x051b  */
        /* JADX WARN: Code duplicated, block: B:220:0x051f  */
        /* JADX WARN: Code duplicated, block: B:255:0x05d2 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:280:0x05a8 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:281:0x0591 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:282:0x057a A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:283:0x0565 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:296:0x01fb A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:86:0x01b4  */
        /* JADX WARN: Code duplicated, block: B:98:0x020d  */
        public final DateTimePeriod parse(String text) {
            int i;
            int i2;
            int i3;
            char upperCase;
            int i4;
            int i5;
            int i6;
            char cCharAt;
            char c;
            char cCharAt2;
            int i7;
            int i8;
            Intrinsics.checkNotNullParameter(text, "text");
            int i9 = 0;
            char c2 = 0;
            int i10 = 0;
            int i11 = 0;
            boolean z = false;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 1;
            while (i9 < text.length()) {
                if (c2 == 0) {
                    int i19 = i9 + 1;
                    if (i19 >= text.length() && (text.charAt(i9) == '+' || text.charAt(i9) == '-')) {
                        throw new DateTimeFormatException("Parse error at char " + i9 + ": Unexpected end of string; 'P' designator is required");
                    }
                    char cCharAt3 = text.charAt(i9);
                    if (cCharAt3 == '+' || cCharAt3 == '-') {
                        if (text.charAt(i9) == '-') {
                            i18 = -1;
                        }
                        if (text.charAt(i19) != 'P') {
                            throw new DateTimeFormatException("Parse error at char " + i19 + ": " + ("Expected 'P', got '" + text.charAt(i19) + '\''));
                        }
                        i9 += 2;
                        Unit unit = Unit.INSTANCE;
                    } else {
                        if (cCharAt3 != 'P') {
                            throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Expected '+', '-', 'P', got '" + text.charAt(i9) + '\''));
                        }
                        Unit unit2 = Unit.INSTANCE;
                        i9 = i19;
                    }
                    c2 = 1;
                    i10 = i10;
                } else {
                    int i20 = i10;
                    char cCharAt4 = text.charAt(i9);
                    if (cCharAt4 == '+' || cCharAt4 == '-') {
                        int i21 = text.charAt(i9) == '-' ? i18 * (-1) : i18;
                        int i22 = i9 + 1;
                        if (i22 < text.length()) {
                            char cCharAt5 = text.charAt(i22);
                            int i23 = i21;
                            if ('0' <= cCharAt5 && cCharAt5 < ':') {
                                i = i22;
                                i2 = i23;
                                long jSafeAdd = 0;
                                while (true) {
                                    if (i < text.length()) {
                                        i3 = i11;
                                        break;
                                    }
                                    cCharAt2 = text.charAt(i);
                                    i3 = i11;
                                    if ('0' > cCharAt2 || cCharAt2 >= ':') {
                                        break;
                                    }
                                    i7 = i12;
                                    i8 = i13;
                                    try {
                                        jSafeAdd = MathJvmKt.safeAdd(MathJvmKt.safeMultiply(jSafeAdd, 10L), text.charAt(i) - '0');
                                        i++;
                                        i11 = i3;
                                        i12 = i7;
                                        i13 = i8;
                                    } catch (ArithmeticException unused) {
                                        throw new DateTimeFormatException("Parse error at char " + i9 + ": The number is too large");
                                    }
                                }
                                int i24 = i12;
                                i13 = i13;
                                long j = ((long) i2) * jSafeAdd;
                                if (i != text.length()) {
                                    throw new DateTimeFormatException("Parse error at char " + i + ": Expected a designator after the numerical value");
                                }
                                upperCase = Character.toUpperCase(text.charAt(i));
                                i4 = i2;
                                int i25 = i14;
                                int i26 = i15;
                                int i27 = i16;
                                if (upperCase != ',' || upperCase == '.') {
                                    i5 = i + 1;
                                    if (i5 >= text.length()) {
                                        throw new DateTimeFormatException("Parse error at char " + i5 + ": " + ("Expected designator 'S' after " + text.charAt(i)));
                                    }
                                    i = i5;
                                    while (i < text.length() && '0' <= (cCharAt = text.charAt(i)) && cCharAt < ':') {
                                        i++;
                                    }
                                    i6 = i - i5;
                                    if (i6 > 9) {
                                        throw new DateTimeFormatException("Parse error at char " + i5 + ": Only the nanosecond fractions of a second are supported");
                                    }
                                    StringBuilder sb = new StringBuilder();
                                    String strSubstring = text.substring(i5, i);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                                    sb.append(strSubstring);
                                    sb.append(StringsKt.repeat("0", 9 - i6));
                                    int i28 = Integer.parseInt(sb.toString(), CharsKt.checkRadix(10)) * i4;
                                    if (text.charAt(i) != 'S') {
                                        throw new DateTimeFormatException("Parse error at char " + i + ": Expected the 'S' designator after a fraction");
                                    }
                                    if (c2 < '\t' || c2 < 6) {
                                        throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                                    }
                                    if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                        throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Value " + j + " does not fit into an Int, which is required for component 'S'"));
                                    }
                                    Unit unit3 = Unit.INSTANCE;
                                    i17 = (int) j;
                                    i14 = i28;
                                    c2 = '\t';
                                    i11 = i3;
                                    i12 = i24;
                                    i10 = i20;
                                } else {
                                    if (upperCase != 'D') {
                                        if (upperCase != 'H') {
                                            if (upperCase == 'M') {
                                                if (c2 >= 6) {
                                                    c = '\b';
                                                    if (c2 >= '\b') {
                                                        throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                                                    }
                                                    if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                        throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Value " + j + " does not fit into an Int, which is required for component 'M'"));
                                                    }
                                                    i16 = (int) j;
                                                    i13 = i13;
                                                } else {
                                                    c = 3;
                                                    if (c2 >= 3) {
                                                        throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                                                    }
                                                    if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                        throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Value " + j + " does not fit into an Int, which is required for component 'M'"));
                                                    }
                                                    i13 = (int) j;
                                                    i16 = i27;
                                                }
                                                Unit unit4 = Unit.INSTANCE;
                                                c2 = c;
                                                i11 = i3;
                                                i12 = i24;
                                                i10 = i20;
                                                i14 = i25;
                                                i15 = i26;
                                            } else if (upperCase != 'S') {
                                                if (upperCase != 'W') {
                                                    if (upperCase != 'Y') {
                                                        throw new DateTimeFormatException("Parse error at char " + i + ": Expected a designator after the numerical value");
                                                    }
                                                    if (c2 >= 2) {
                                                        throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                                                    }
                                                    if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                        throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Value " + j + " does not fit into an Int, which is required for component 'Y'"));
                                                    }
                                                    Unit unit5 = Unit.INSTANCE;
                                                    i12 = (int) j;
                                                    c2 = 2;
                                                    i11 = i3;
                                                } else {
                                                    if (c2 >= 4) {
                                                        throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                                                    }
                                                    if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                        throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Value " + j + " does not fit into an Int, which is required for component 'W'"));
                                                    }
                                                    Unit unit6 = Unit.INSTANCE;
                                                    i11 = (int) j;
                                                    c2 = 4;
                                                    i12 = i24;
                                                }
                                                i10 = i20;
                                            } else {
                                                if (c2 >= '\t' || c2 < 6) {
                                                    throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                                                }
                                                if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                    throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Value " + j + " does not fit into an Int, which is required for component 'S'"));
                                                }
                                                Unit unit7 = Unit.INSTANCE;
                                                i17 = (int) j;
                                                i11 = i3;
                                                i12 = i24;
                                                i10 = i20;
                                                i14 = i25;
                                                i13 = i13;
                                                i15 = i26;
                                                i16 = i27;
                                                c2 = '\t';
                                            }
                                        } else {
                                            if (c2 >= 7 || c2 < 6) {
                                                throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                                            }
                                            if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Value " + j + " does not fit into an Int, which is required for component 'H'"));
                                            }
                                            Unit unit8 = Unit.INSTANCE;
                                            i15 = (int) j;
                                            c2 = 7;
                                            i11 = i3;
                                            i12 = i24;
                                            i10 = i20;
                                            i14 = i25;
                                            i16 = i27;
                                        }
                                        i9 = i + 1;
                                        z = true;
                                    } else {
                                        if (c2 >= 5) {
                                            throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                                        }
                                        if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                            throw new DateTimeFormatException("Parse error at char " + i9 + ": " + ("Value " + j + " does not fit into an Int, which is required for component 'D'"));
                                        }
                                        Unit unit9 = Unit.INSTANCE;
                                        i10 = (int) j;
                                        c2 = 5;
                                        i11 = i3;
                                        i12 = i24;
                                    }
                                    i14 = i25;
                                }
                                i15 = i26;
                                i16 = i27;
                                i9 = i + 1;
                                z = true;
                            }
                        }
                        throw new DateTimeFormatException("Parse error at char " + i22 + ": " + ("A number expected after '" + text.charAt(i22) + '\''));
                    }
                    if (('0' <= cCharAt4 && cCharAt4 < ':') || cCharAt4 != 'T') {
                        i = i9;
                        i2 = i18;
                        long jSafeAdd2 = 0;
                        while (true) {
                            if (i < text.length()) {
                                i3 = i11;
                                break;
                            }
                            cCharAt2 = text.charAt(i);
                            i3 = i11;
                            if ('0' > cCharAt2) {
                                break;
                            }
                            break;
                            break;
                            i++;
                            i11 = i3;
                            i12 = i7;
                            i13 = i8;
                        }
                        int i29 = i12;
                        i13 = i13;
                        long j2 = ((long) i2) * jSafeAdd2;
                        if (i != text.length()) {
                            throw new DateTimeFormatException("Parse error at char " + i + ": Expected a designator after the numerical value");
                        }
                        upperCase = Character.toUpperCase(text.charAt(i));
                        i4 = i2;
                        int i210 = i14;
                        int i211 = i15;
                        int i212 = i16;
                        if (upperCase != ',') {
                            i5 = i + 1;
                            if (i5 >= text.length()) {
                                throw new DateTimeFormatException("Parse error at char " + i5 + ": " + ("Expected designator 'S' after " + text.charAt(i)));
                            }
                            i = i5;
                            while (i < text.length()) {
                                i++;
                            }
                            i6 = i - i5;
                            if (i6 > 9) {
                                throw new DateTimeFormatException("Parse error at char " + i5 + ": Only the nanosecond fractions of a second are supported");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String strSubstring2 = text.substring(i5, i);
                            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                            sb2.append(strSubstring2);
                            sb2.append(StringsKt.repeat("0", 9 - i6));
                            int i213 = Integer.parseInt(sb2.toString(), CharsKt.checkRadix(10)) * i4;
                            if (text.charAt(i) != 'S') {
                                throw new DateTimeFormatException("Parse error at char " + i + ": Expected the 'S' designator after a fraction");
                            }
                            if (c2 < '\t') {
                            }
                            throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                        }
                        i5 = i + 1;
                        if (i5 >= text.length()) {
                            throw new DateTimeFormatException("Parse error at char " + i5 + ": " + ("Expected designator 'S' after " + text.charAt(i)));
                        }
                        i = i5;
                        while (i < text.length()) {
                            i++;
                        }
                        i6 = i - i5;
                        if (i6 > 9) {
                            throw new DateTimeFormatException("Parse error at char " + i5 + ": Only the nanosecond fractions of a second are supported");
                        }
                        StringBuilder sb3 = new StringBuilder();
                        String strSubstring3 = text.substring(i5, i);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                        sb3.append(strSubstring3);
                        sb3.append(StringsKt.repeat("0", 9 - i6));
                        int i214 = Integer.parseInt(sb3.toString(), CharsKt.checkRadix(10)) * i4;
                        if (text.charAt(i) != 'S') {
                            throw new DateTimeFormatException("Parse error at char " + i + ": Expected the 'S' designator after a fraction");
                        }
                        if (c2 < '\t') {
                        }
                        throw new DateTimeFormatException("Parse error at char " + i + ": Wrong component order: should be 'Y', 'M', 'W', 'D', then designator 'T', then 'H', 'M', 'S'");
                        i9 = i + 1;
                        z = true;
                    } else {
                        if (c2 >= 6) {
                            throw new DateTimeFormatException("Parse error at char " + i9 + ": Only one 'T' designator is allowed");
                        }
                        i9++;
                        i10 = i20;
                        c2 = 6;
                    }
                }
            }
            if (c2 == 0) {
                throw new DateTimeFormatException("Parse error at char " + i9 + ": Unexpected end of input; 'P' designator is required");
            }
            if (c2 == 6) {
                throw new DateTimeFormatException("Parse error at char " + i9 + ": Unexpected end of input; at least one time component is required after 'T'");
            }
            long j3 = ((long) i10) + ((long) (i11 * 7));
            if (SieveCacheKt.NodeMetaAndPreviousMask > j3 || j3 > SieveCacheKt.NodeLinkMask) {
                throw new DateTimeFormatException("Parse error at char 0: The total number of days under 'D' and 'W' designators should fit into an Int");
            }
            int i30 = (int) j3;
            if (z) {
                return DateTimePeriodKt.DateTimePeriod(i12, i13, i30, i15, i16, i17, i14);
            }
            throw new DateTimeFormatException("Parse error at char 0: At least one component is required, but none were found");
        }

        /* JADX WARN: Code duplicated, block: B:178:0x01ce  */
        /* JADX WARN: Code duplicated, block: B:182:0x01da  */
        /* JADX WARN: Code duplicated, block: B:185:0x01e1  */
        /* JADX WARN: Code duplicated, block: B:193:0x01f7  */
        /* JADX WARN: Code duplicated, block: B:196:0x022f A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:226:0x0110 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:251:0x01d6 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:252:0x01f6 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:253:0x022e A[ADDED_TO_REGION, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:83:0x0111  */
        public final DateTimePeriod parseOrNull(String text) {
            int i;
            char cCharAt;
            char c;
            long jSafeAdd;
            char c2;
            long j;
            char upperCase;
            int i2;
            int i3;
            int i4;
            int i5;
            char cCharAt2;
            char c3;
            char cCharAt3;
            Intrinsics.checkNotNullParameter(text, "text");
            int i6 = 0;
            char c4 = 0;
            int i7 = 0;
            int i8 = 0;
            boolean z = false;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 1;
            while (i6 < text.length()) {
                if (c4 == 0) {
                    int i16 = i6 + 1;
                    if (i16 >= text.length() && (text.charAt(i6) == '+' || text.charAt(i6) == '-')) {
                        return null;
                    }
                    char cCharAt4 = text.charAt(i6);
                    int i17 = i7;
                    if (cCharAt4 == '+' || cCharAt4 == '-') {
                        if (text.charAt(i6) == '-') {
                            i15 = -1;
                        }
                        if (text.charAt(i16) != 'P') {
                            text.charAt(i16);
                            return null;
                        }
                        i6 += 2;
                        Unit unit = Unit.INSTANCE;
                    } else if (cCharAt4 == 'P') {
                        Unit unit2 = Unit.INSTANCE;
                        i6 = i16;
                    } else {
                        text.charAt(i6);
                        return null;
                    }
                    c4 = 1;
                    i7 = i17;
                } else {
                    int i18 = i7;
                    char cCharAt5 = text.charAt(i6);
                    char c5 = '0';
                    if (cCharAt5 == '+' || cCharAt5 == '-') {
                        i = text.charAt(i6) == '-' ? i15 * (-1) : i15;
                        i6++;
                        if (i6 < text.length() && '0' <= (cCharAt = text.charAt(i6))) {
                            c = AbstractJsonLexerKt.COLON;
                            if (cCharAt >= ':') {
                            }
                            jSafeAdd = 0;
                            while (i6 < text.length() && c5 <= (cCharAt3 = text.charAt(i6)) && cCharAt3 < c) {
                                char c6 = c5;
                                char c7 = c4;
                                try {
                                    jSafeAdd = MathJvmKt.safeAdd(MathJvmKt.safeMultiply(jSafeAdd, 10L), text.charAt(i6) - '0');
                                    i6++;
                                    c4 = c7;
                                    c5 = c6;
                                    c = AbstractJsonLexerKt.COLON;
                                } catch (ArithmeticException unused) {
                                    return null;
                                }
                            }
                            c2 = c5;
                            char c8 = c4;
                            j = jSafeAdd * ((long) i);
                            if (i6 == text.length()) {
                                return null;
                            }
                            upperCase = Character.toUpperCase(text.charAt(i6));
                            i2 = i;
                            char c9 = '\t';
                            if (upperCase != ',' || upperCase == '.') {
                                i3 = i6 + 1;
                                if (i3 >= text.length()) {
                                    text.charAt(i6);
                                    return null;
                                }
                                i6 = i3;
                                while (i6 < text.length() && (c3 = c2) <= (cCharAt2 = text.charAt(i6)) && cCharAt2 < ':') {
                                    i6++;
                                    c2 = c3;
                                }
                                i4 = i6 - i3;
                                if (i4 > 9) {
                                    return null;
                                }
                                StringBuilder sb = new StringBuilder();
                                String strSubstring = text.substring(i3, i6);
                                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                                sb.append(strSubstring);
                                sb.append(StringsKt.repeat("0", 9 - i4));
                                i11 = Integer.parseInt(sb.toString(), CharsKt.checkRadix(10)) * i2;
                                if (text.charAt(i6) == 'S' || c8 >= '\t' || c8 < 6 || j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                    return null;
                                }
                                i5 = (int) j;
                                Unit unit3 = Unit.INSTANCE;
                            } else {
                                if (upperCase != 'D') {
                                    if (upperCase == 'H') {
                                        c9 = 7;
                                        if (c8 >= 7 || c8 < 6 || j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                            return null;
                                        }
                                        Unit unit4 = Unit.INSTANCE;
                                        i12 = (int) j;
                                    } else if (upperCase == 'M') {
                                        if (c8 >= 6) {
                                            c9 = '\b';
                                            if (c8 >= '\b' || j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                return null;
                                            }
                                            i13 = (int) j;
                                        } else {
                                            c9 = 3;
                                            if (c8 >= 3 || j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                return null;
                                            }
                                            i10 = (int) j;
                                        }
                                        Unit unit5 = Unit.INSTANCE;
                                    } else if (upperCase != 'S') {
                                        if (upperCase == 'W') {
                                            c9 = 4;
                                            if (c8 >= 4 || j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                return null;
                                            }
                                            Unit unit6 = Unit.INSTANCE;
                                            i8 = (int) j;
                                        } else {
                                            if (upperCase != 'Y') {
                                                return null;
                                            }
                                            c9 = 2;
                                            if (c8 >= 2 || j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                                return null;
                                            }
                                            Unit unit7 = Unit.INSTANCE;
                                            i9 = (int) j;
                                        }
                                    } else {
                                        if (c8 >= '\t' || c8 < 6 || j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                            return null;
                                        }
                                        i5 = (int) j;
                                        Unit unit8 = Unit.INSTANCE;
                                    }
                                    c4 = c9;
                                    i7 = i18;
                                } else {
                                    if (c8 >= 5 || j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                        return null;
                                    }
                                    Unit unit9 = Unit.INSTANCE;
                                    c4 = 5;
                                    i7 = (int) j;
                                }
                                i6++;
                                z = true;
                            }
                            i14 = i5;
                            c4 = c9;
                            i7 = i18;
                            i6++;
                            z = true;
                        }
                        text.charAt(i6);
                        return null;
                    }
                    if (('0' <= cCharAt5 && cCharAt5 < ':') || cCharAt5 != 'T') {
                        i = i15;
                        c = AbstractJsonLexerKt.COLON;
                        jSafeAdd = 0;
                        while (i6 < text.length()) {
                            char c10 = c5;
                            char c11 = c4;
                            jSafeAdd = MathJvmKt.safeAdd(MathJvmKt.safeMultiply(jSafeAdd, 10L), text.charAt(i6) - '0');
                            i6++;
                            c4 = c11;
                            c5 = c10;
                            c = AbstractJsonLexerKt.COLON;
                        }
                        c2 = c5;
                        char c12 = c4;
                        j = jSafeAdd * ((long) i);
                        if (i6 == text.length()) {
                            return null;
                        }
                        upperCase = Character.toUpperCase(text.charAt(i6));
                        i2 = i;
                        char c13 = '\t';
                        if (upperCase != ',') {
                            i3 = i6 + 1;
                            if (i3 >= text.length()) {
                                text.charAt(i6);
                                return null;
                            }
                            i6 = i3;
                            while (i6 < text.length()) {
                                i6++;
                                c2 = c3;
                            }
                            i4 = i6 - i3;
                            if (i4 > 9) {
                                return null;
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String strSubstring2 = text.substring(i3, i6);
                            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                            sb2.append(strSubstring2);
                            sb2.append(StringsKt.repeat("0", 9 - i4));
                            i11 = Integer.parseInt(sb2.toString(), CharsKt.checkRadix(10)) * i2;
                            if (text.charAt(i6) == 'S') {
                                return null;
                            }
                            i5 = (int) j;
                            Unit unit10 = Unit.INSTANCE;
                            i14 = i5;
                            c4 = c13;
                            i7 = i18;
                        } else {
                            i3 = i6 + 1;
                            if (i3 >= text.length()) {
                                text.charAt(i6);
                                return null;
                            }
                            i6 = i3;
                            while (i6 < text.length()) {
                                i6++;
                                c2 = c3;
                            }
                            i4 = i6 - i3;
                            if (i4 > 9) {
                                return null;
                            }
                            StringBuilder sb3 = new StringBuilder();
                            String strSubstring3 = text.substring(i3, i6);
                            Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                            sb3.append(strSubstring3);
                            sb3.append(StringsKt.repeat("0", 9 - i4));
                            i11 = Integer.parseInt(sb3.toString(), CharsKt.checkRadix(10)) * i2;
                            if (text.charAt(i6) == 'S') {
                                return null;
                            }
                            i5 = (int) j;
                            Unit unit11 = Unit.INSTANCE;
                            i14 = i5;
                            c4 = c13;
                            i7 = i18;
                        }
                        i6++;
                        z = true;
                    } else {
                        if (c4 >= 6) {
                            return null;
                        }
                        i6++;
                        i7 = i18;
                        c4 = 6;
                    }
                }
            }
            if (c4 == 0 || c4 == 6) {
                return null;
            }
            long j2 = ((long) i7) + ((long) (i8 * 7));
            if (SieveCacheKt.NodeMetaAndPreviousMask <= j2 && j2 <= SieveCacheKt.NodeLinkMask) {
                int i19 = (int) j2;
                if (!z) {
                    return null;
                }
                try {
                    return DateTimePeriodKt.DateTimePeriod(i9, i10, i19, i12, i13, i14, i11);
                } catch (IllegalArgumentException unused2) {
                }
            }
            return null;
        }
    }
}
