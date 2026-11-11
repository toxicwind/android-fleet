package kotlinx.datetime;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentType;
import j$.util.Objects;
import java.io.IOException;
import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.UtilKt;

/* JADX INFO: compiled from: LocalIsoWeekDate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001 B)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\u000bB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\fJ\u001d\u0010\u0012\u001a\u00020\b2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014H\u0082\bJ\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096\u0002J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\u0011\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0000H\u0096\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lkotlinx/datetime/LocalIsoWeekDate;", "", "isoWeekYear", "", "isoWeekNumber", "dayOfWeek", "Lkotlinx/datetime/DayOfWeek;", "unit", "", "<init>", "(IILkotlinx/datetime/DayOfWeek;Lkotlin/Unit;)V", "(IILkotlinx/datetime/DayOfWeek;)V", "(III)V", "getIsoWeekYear", "()I", "getIsoWeekNumber", "getDayOfWeek", "()Lkotlinx/datetime/DayOfWeek;", "checkValidity", "fail", "Lkotlin/Function1;", "", "", "toLocalDate", "Lkotlinx/datetime/LocalDate;", "toString", "equals", "", "other", "", "hashCode", "compareTo", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LocalIsoWeekDate implements Comparable<LocalIsoWeekDate> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DayOfWeek dayOfWeek;
    private final int isoWeekNumber;
    private final int isoWeekYear;

    public /* synthetic */ LocalIsoWeekDate(int i, int i2, DayOfWeek dayOfWeek, Unit unit, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, dayOfWeek, unit);
    }

    private LocalIsoWeekDate(int i, int i2, DayOfWeek dayOfWeek, Unit unit) {
        this.isoWeekYear = i;
        this.isoWeekNumber = i2;
        this.dayOfWeek = dayOfWeek;
    }

    public final int getIsoWeekYear() {
        return this.isoWeekYear;
    }

    public final int getIsoWeekNumber() {
        return this.isoWeekNumber;
    }

    public final DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    private final void checkValidity(Function1 fail) {
        int isoWeekYear = getIsoWeekYear();
        if (-999999999 <= isoWeekYear && isoWeekYear < 999999999) {
            int isoWeekNumber = getIsoWeekNumber();
            if (1 > isoWeekNumber || isoWeekNumber >= 54) {
                fail.invoke("isoWeekNumber must be in the range 1..53, got: " + getIsoWeekNumber());
                throw new KotlinNothingValueException();
            }
            if (getIsoWeekNumber() != 53 || LocalIsoWeekDateKt.isIsoWeekLeapYear(getIsoWeekYear())) {
                return;
            }
            fail.invoke("There are only 52 weeks in ISO week year " + getIsoWeekYear() + ", but `isoWeekNumber` is 53");
            throw new KotlinNothingValueException();
        }
        if (isoWeekYear == 999999999) {
            int isoWeekNumber2 = getIsoWeekNumber();
            if (1 > isoWeekNumber2 || isoWeekNumber2 >= 53) {
                fail.invoke("isoWeekNumber for year 999999999 must be in the range 1..52, got: " + getIsoWeekNumber());
                throw new KotlinNothingValueException();
            }
            if (getIsoWeekNumber() != 52 || getDayOfWeek().compareTo(DayOfWeek.FRIDAY) <= 0) {
                return;
            }
            fail.invoke("In the year 999999999, the 52nd week of the year is only representable up to Friday for compatibility with the LocalDate range, but got " + getDayOfWeek());
            throw new KotlinNothingValueException();
        }
        fail.invoke("The ISO week-year " + getIsoWeekYear() + " is out of range");
        throw new KotlinNothingValueException();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalIsoWeekDate(int i, int i2, DayOfWeek dayOfWeek) {
        this(i, i2, dayOfWeek, Unit.INSTANCE);
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        int isoWeekYear = getIsoWeekYear();
        if (-999999999 <= isoWeekYear && isoWeekYear < 999999999) {
            int isoWeekNumber = getIsoWeekNumber();
            if (1 > isoWeekNumber || isoWeekNumber >= 54) {
                throw new IllegalArgumentException("isoWeekNumber must be in the range 1..53, got: " + getIsoWeekNumber());
            }
            if (getIsoWeekNumber() != 53 || LocalIsoWeekDateKt.isIsoWeekLeapYear(getIsoWeekYear())) {
                return;
            }
            throw new IllegalArgumentException("There are only 52 weeks in ISO week year " + getIsoWeekYear() + ", but `isoWeekNumber` is 53");
        }
        if (isoWeekYear != 999999999) {
            throw new IllegalArgumentException("The ISO week-year " + getIsoWeekYear() + " is out of range");
        }
        int isoWeekNumber2 = getIsoWeekNumber();
        if (1 > isoWeekNumber2 || isoWeekNumber2 >= 53) {
            throw new IllegalArgumentException("isoWeekNumber for year 999999999 must be in the range 1..52, got: " + getIsoWeekNumber());
        }
        if (getIsoWeekNumber() != 52 || getDayOfWeek().compareTo(DayOfWeek.FRIDAY) <= 0) {
            return;
        }
        throw new IllegalArgumentException("In the year 999999999, the 52nd week of the year is only representable up to Friday for compatibility with the LocalDate range, but got " + getDayOfWeek());
    }

    public LocalIsoWeekDate(int i, int i2, int i3) {
        this(i, i2, DayOfWeekKt.m10558DayOfWeek(i3));
    }

    /* JADX INFO: compiled from: LocalIsoWeekDate.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ \u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\rJ¡\u0001\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001a\u00020\r26\u0010\u0012\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u00132K\u0010\u0019\u001aG\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u0002H\u00100\u001aH\u0082\b¢\u0006\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"Lkotlinx/datetime/LocalIsoWeekDate$Companion;", "", "<init>", "()V", "orNull", "Lkotlinx/datetime/LocalIsoWeekDate;", "isoWeekYear", "", "isoWeekNumber", "dayOfWeek", "Lkotlinx/datetime/DayOfWeek;", "parse", "isoString", "", "parseOrNull", "parseImpl", ExifInterface.GPS_DIRECTION_TRUE, "text", "parseException", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", ContentType.Message.TYPE, "position", "", "construct", "Lkotlin/Function3;", "year", "weekNumber", "isoDayOfWeekNumber", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LocalIsoWeekDate orNull(int isoWeekYear, int isoWeekNumber, DayOfWeek dayOfWeek) {
            Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
            LocalIsoWeekDate localIsoWeekDate = new LocalIsoWeekDate(isoWeekYear, isoWeekNumber, dayOfWeek, Unit.INSTANCE, null);
            int isoWeekYear2 = localIsoWeekDate.getIsoWeekYear();
            if (-999999999 <= isoWeekYear2 && isoWeekYear2 < 999999999) {
                int isoWeekNumber2 = localIsoWeekDate.getIsoWeekNumber();
                if (1 > isoWeekNumber2 || isoWeekNumber2 >= 54) {
                    localIsoWeekDate.getIsoWeekNumber();
                    return null;
                }
                if (localIsoWeekDate.getIsoWeekNumber() == 53 && !LocalIsoWeekDateKt.isIsoWeekLeapYear(localIsoWeekDate.getIsoWeekYear())) {
                    localIsoWeekDate.getIsoWeekYear();
                    return null;
                }
            } else if (isoWeekYear2 == 999999999) {
                int isoWeekNumber3 = localIsoWeekDate.getIsoWeekNumber();
                if (1 > isoWeekNumber3 || isoWeekNumber3 >= 53) {
                    localIsoWeekDate.getIsoWeekNumber();
                    return null;
                }
                if (localIsoWeekDate.getIsoWeekNumber() == 52 && localIsoWeekDate.getDayOfWeek().compareTo(DayOfWeek.FRIDAY) > 0) {
                    Objects.toString(localIsoWeekDate.getDayOfWeek());
                    return null;
                }
            } else {
                localIsoWeekDate.getIsoWeekYear();
                return null;
            }
            return localIsoWeekDate;
        }

        public final LocalIsoWeekDate orNull(int isoWeekYear, int isoWeekNumber, int dayOfWeek) {
            if (1 > dayOfWeek || dayOfWeek >= 8) {
                return null;
            }
            return orNull(isoWeekYear, isoWeekNumber, DayOfWeek.getEntries().get(dayOfWeek - 1));
        }

        private final <T> T parseImpl(String text, Function2 parseException, Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> construct) {
            int i;
            char c;
            char cCharAt;
            String strRemoveLeadingZerosFromLongYearFormIsoWeekDate = UtilKt.removeLeadingZerosFromLongYearFormIsoWeekDate(text);
            int length = text.length() - strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length();
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() == 0) {
                parseException.invoke("An empty string is not a valid LocalIsoWeekDate", 0);
                throw new KotlinNothingValueException();
            }
            char cCharAt2 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(0);
            if (cCharAt2 == '+' || cCharAt2 == '-') {
                i = 1;
            } else {
                i = 0;
                cCharAt2 = ' ';
            }
            int iCharAt = 0;
            int i2 = i;
            while (i2 < strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() && '0' <= (cCharAt = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2)) && cCharAt < ':') {
                iCharAt = (iCharAt * 10) + (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2) - '0');
                i2++;
            }
            int i3 = i2 - i;
            if (i3 > 10) {
                parseException.invoke("Expected at most 10 digits for the year number, got " + i3 + " digits", Integer.valueOf(i));
                throw new KotlinNothingValueException();
            }
            if (i3 == 10) {
                c = 1;
                if (Intrinsics.compare((int) strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i), 50) >= 0) {
                    parseException.invoke("Expected at most 9 digits for the year number or year 1000000000, got " + i3 + " digits", Integer.valueOf(i));
                    throw new KotlinNothingValueException();
                }
            } else {
                c = 1;
            }
            if (i3 < 4) {
                parseException.invoke("The year number must be padded to 4 digits, got " + i3 + " digits", Integer.valueOf(i));
                throw new KotlinNothingValueException();
            }
            if (cCharAt2 == '+' && i3 == 4) {
                parseException.invoke("The '+' sign at the start is only valid for year numbers longer than 4 digits", Integer.valueOf(i));
                throw new KotlinNothingValueException();
            }
            if (cCharAt2 == ' ' && i3 != 4) {
                parseException.invoke("A '+' or '-' sign is required for year numbers longer than 4 digits", Integer.valueOf(i));
                throw new KotlinNothingValueException();
            }
            if (cCharAt2 == '-') {
                iCharAt = -iCharAt;
            }
            int i4 = i2 + 6;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() < i4) {
                parseException.invoke("The input string is too short", Integer.valueOf(strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() + length));
                throw new KotlinNothingValueException();
            }
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2) != '-') {
                parseException.invoke("Expected '-', but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2) + '\'', Integer.valueOf(i2 + length));
                throw new KotlinNothingValueException();
            }
            int i5 = i2 + 1;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5) != 'W' && strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5) != 'w') {
                parseException.invoke("Expected 'W', but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5) + '\'', Integer.valueOf(i5 + length));
                throw new KotlinNothingValueException();
            }
            int i6 = i2 + 4;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i6) != '-') {
                parseException.invoke("Expected '-', but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i6) + '\'', Integer.valueOf(i6 + length));
                throw new KotlinNothingValueException();
            }
            Integer[] numArr = new Integer[3];
            numArr[0] = 2;
            numArr[c] = 3;
            numArr[2] = 5;
            Iterator it = CollectionsKt.listOf((Object[]) numArr).iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue() + i2;
                char cCharAt3 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(iIntValue);
                if ('0' > cCharAt3 || cCharAt3 >= ':') {
                    parseException.invoke("Expected an ASCII digit, but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(iIntValue) + '\'', Integer.valueOf(iIntValue + length));
                    throw new KotlinNothingValueException();
                }
            }
            int i7 = i2 + 5;
            char cCharAt4 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7);
            if ('1' > cCharAt4 || cCharAt4 >= '8') {
                parseException.invoke("Expected '1'..'7', but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7) + '\'', Integer.valueOf(i7 + length));
                throw new KotlinNothingValueException();
            }
            int iCharAt2 = ((strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2 + 2) - '0') * 10) + (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2 + 3) - '0');
            int iCharAt3 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7) - '0';
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() > i4) {
                parseException.invoke("Trailing characters", Integer.valueOf(i4 + length));
                throw new KotlinNothingValueException();
            }
            return construct.invoke(Integer.valueOf(iCharAt), Integer.valueOf(iCharAt2), Integer.valueOf(iCharAt3));
        }

        public final LocalIsoWeekDate parse(String isoString) {
            int i;
            char cCharAt;
            Intrinsics.checkNotNullParameter(isoString, "isoString");
            String strRemoveLeadingZerosFromLongYearFormIsoWeekDate = UtilKt.removeLeadingZerosFromLongYearFormIsoWeekDate(isoString);
            int length = isoString.length() - strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length();
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() == 0) {
                throw new DateTimeFormatException("An empty string is not a valid LocalIsoWeekDate at position 0 when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            char cCharAt2 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(0);
            if (cCharAt2 == '+' || cCharAt2 == '-') {
                i = 1;
            } else {
                i = 0;
                cCharAt2 = ' ';
            }
            int iCharAt = 0;
            int i2 = i;
            while (i2 < strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() && '0' <= (cCharAt = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2)) && cCharAt < ':') {
                iCharAt = (iCharAt * 10) + (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2) - '0');
                i2++;
            }
            int i3 = i2 - i;
            if (i3 > 10) {
                throw new DateTimeFormatException(("Expected at most 10 digits for the year number, got " + i3 + " digits") + " at position " + i + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            if (i3 == 10 && Intrinsics.compare((int) strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i), 50) >= 0) {
                throw new DateTimeFormatException(("Expected at most 9 digits for the year number or year 1000000000, got " + i3 + " digits") + " at position " + i + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            if (i3 < 4) {
                throw new DateTimeFormatException(("The year number must be padded to 4 digits, got " + i3 + " digits") + " at position " + i + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            if (cCharAt2 == '+' && i3 == 4) {
                throw new DateTimeFormatException("The '+' sign at the start is only valid for year numbers longer than 4 digits at position " + i + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            if (cCharAt2 == ' ' && i3 != 4) {
                throw new DateTimeFormatException("A '+' or '-' sign is required for year numbers longer than 4 digits at position " + i + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            if (cCharAt2 == '-') {
                iCharAt = -iCharAt;
            }
            int i4 = i2 + 6;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() < i4) {
                throw new DateTimeFormatException("The input string is too short at position " + (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() + length) + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2) != '-') {
                throw new DateTimeFormatException(("Expected '-', but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2) + '\'') + " at position " + (i2 + length) + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            int i5 = i2 + 1;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5) != 'W' && strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5) != 'w') {
                throw new DateTimeFormatException(("Expected 'W', but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5) + '\'') + " at position " + (i5 + length) + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            int i6 = i2 + 4;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i6) != '-') {
                throw new DateTimeFormatException(("Expected '-', but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i6) + '\'') + " at position " + (i6 + length) + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            Iterator it = CollectionsKt.listOf((Object[]) new Integer[]{2, 3, 5}).iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue() + i2;
                char cCharAt3 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(iIntValue);
                if ('0' > cCharAt3 || cCharAt3 >= ':') {
                    throw new DateTimeFormatException(("Expected an ASCII digit, but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(iIntValue) + '\'') + " at position " + (iIntValue + length) + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
                }
            }
            int i7 = i2 + 5;
            char cCharAt4 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7);
            if ('1' > cCharAt4 || cCharAt4 >= '8') {
                throw new DateTimeFormatException(("Expected '1'..'7', but got '" + strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7) + '\'') + " at position " + (i7 + length) + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
            }
            int iCharAt2 = ((strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2 + 2) - '0') * 10) + (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2 + 3) - '0');
            int iCharAt3 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7) - '0';
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() <= i4) {
                try {
                    return new LocalIsoWeekDate(iCharAt, iCharAt2, DayOfWeekKt.m10558DayOfWeek(iCharAt3));
                } catch (IllegalArgumentException e) {
                    throw new DateTimeFormatException("Invalid ISO week date: " + isoString, e);
                }
            }
            throw new DateTimeFormatException("Trailing characters at position " + (i4 + length) + " when parsing a LocalIsoWeekDate from \"" + isoString + '\"');
        }

        public final LocalIsoWeekDate parseOrNull(String isoString) {
            int i;
            char cCharAt;
            Intrinsics.checkNotNullParameter(isoString, "isoString");
            String strRemoveLeadingZerosFromLongYearFormIsoWeekDate = UtilKt.removeLeadingZerosFromLongYearFormIsoWeekDate(isoString);
            isoString.length();
            strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length();
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() == 0) {
                return null;
            }
            char cCharAt2 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(0);
            if (cCharAt2 == '+' || cCharAt2 == '-') {
                i = 1;
            } else {
                i = 0;
                cCharAt2 = ' ';
            }
            int iCharAt = 0;
            int i2 = i;
            while (i2 < strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() && '0' <= (cCharAt = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2)) && cCharAt < ':') {
                iCharAt = (iCharAt * 10) + (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2) - '0');
                i2++;
            }
            int i3 = i2 - i;
            if (i3 > 10) {
                return null;
            }
            if ((i3 == 10 && Intrinsics.compare((int) strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i), 50) >= 0) || i3 < 4) {
                return null;
            }
            if (cCharAt2 == '+' && i3 == 4) {
                return null;
            }
            if (cCharAt2 == ' ' && i3 != 4) {
                return null;
            }
            if (cCharAt2 == '-') {
                iCharAt = -iCharAt;
            }
            int i4 = i2 + 6;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() < i4) {
                strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length();
                return null;
            }
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2) != '-') {
                strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2);
                return null;
            }
            int i5 = i2 + 1;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5) != 'W' && strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5) != 'w') {
                strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i5);
                return null;
            }
            int i6 = i2 + 4;
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i6) != '-') {
                strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i6);
                return null;
            }
            Iterator it = CollectionsKt.listOf((Object[]) new Integer[]{2, 3, 5}).iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue() + i2;
                char cCharAt3 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(iIntValue);
                if ('0' > cCharAt3 || cCharAt3 >= ':') {
                    strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(iIntValue);
                    return null;
                }
            }
            int i7 = i2 + 5;
            char cCharAt4 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7);
            if ('1' > cCharAt4 || cCharAt4 >= '8') {
                strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7);
                return null;
            }
            int iCharAt2 = ((strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2 + 2) - '0') * 10) + (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i2 + 3) - '0');
            int iCharAt3 = strRemoveLeadingZerosFromLongYearFormIsoWeekDate.charAt(i7) - '0';
            if (strRemoveLeadingZerosFromLongYearFormIsoWeekDate.length() > i4) {
                return null;
            }
            return LocalIsoWeekDate.INSTANCE.orNull(iCharAt, iCharAt2, DayOfWeekKt.m10558DayOfWeek(iCharAt3));
        }
    }

    public final LocalDate toLocalDate() {
        return LocalDateKt.plus(LocalDateKt.plus(LocalIsoWeekDateKt.startOfIsoWeekYear(this.isoWeekYear), this.isoWeekNumber - 1, DateTimeUnit.INSTANCE.getWEEK()), DayOfWeekKt.getIsoDayNumber(this.dayOfWeek) - 1, DateTimeUnit.INSTANCE.getDAY());
    }

    public String toString() throws IOException {
        StringBuilder sb = new StringBuilder();
        int i = this.isoWeekYear;
        if (Math.abs(i) < 1000) {
            StringBuilder sb2 = new StringBuilder();
            if (i >= 0) {
                sb2.append(i + 10000);
                Intrinsics.checkNotNullExpressionValue(sb2, "append(...)");
                Intrinsics.checkNotNullExpressionValue(sb2.deleteCharAt(0), "deleteCharAt(...)");
            } else {
                sb2.append(i - 10000);
                Intrinsics.checkNotNullExpressionValue(sb2, "append(...)");
                Intrinsics.checkNotNullExpressionValue(sb2.deleteCharAt(1), "deleteCharAt(...)");
            }
            sb.append((CharSequence) sb2);
        } else {
            if (i >= 10000) {
                sb.append('+');
            }
            sb.append(i);
        }
        sb.append("-W");
        toString$lambda$2$appendTwoDigits(sb, sb, this.isoWeekNumber);
        sb.append('-');
        sb.append(DayOfWeekKt.getIsoDayNumber(this.dayOfWeek));
        return sb.toString();
    }

    private static final void toString$lambda$2$appendTwoDigits(Appendable appendable, StringBuilder sb, int i) throws IOException {
        if (i < 10) {
            appendable.append('0');
        }
        sb.append(i);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocalIsoWeekDate)) {
            return false;
        }
        LocalIsoWeekDate localIsoWeekDate = (LocalIsoWeekDate) other;
        return this.isoWeekYear == localIsoWeekDate.isoWeekYear && this.isoWeekNumber == localIsoWeekDate.isoWeekNumber && this.dayOfWeek == localIsoWeekDate.dayOfWeek;
    }

    public int hashCode() {
        return this.dayOfWeek.hashCode() ^ (this.isoWeekYear ^ this.isoWeekNumber);
    }

    static final Comparable compareTo$lambda$3(LocalIsoWeekDate it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Integer.valueOf(it.isoWeekYear);
    }

    static final Comparable compareTo$lambda$4(LocalIsoWeekDate it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Integer.valueOf(it.isoWeekNumber);
    }

    static final Comparable compareTo$lambda$5(LocalIsoWeekDate it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.dayOfWeek;
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalIsoWeekDate other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return ComparisonsKt.compareValuesBy(this, other, (Function1<? super LocalIsoWeekDate, ? extends Comparable<?>>[]) new Function1[]{new Function1() { // from class: kotlinx.datetime.LocalIsoWeekDate$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LocalIsoWeekDate.compareTo$lambda$3((LocalIsoWeekDate) obj);
            }
        }, new Function1() { // from class: kotlinx.datetime.LocalIsoWeekDate$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LocalIsoWeekDate.compareTo$lambda$4((LocalIsoWeekDate) obj);
            }
        }, new Function1() { // from class: kotlinx.datetime.LocalIsoWeekDate$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LocalIsoWeekDate.compareTo$lambda$5((LocalIsoWeekDate) obj);
            }
        }});
    }
}
