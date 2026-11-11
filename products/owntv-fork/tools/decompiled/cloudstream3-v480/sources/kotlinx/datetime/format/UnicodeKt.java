package kotlinx.datetime.format;

import androidx.core.app.NotificationCompat;
import io.ktor.util.date.GMTDateParser;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: Unicode.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u001c\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\f\u0010\u0011\u001a\u00020\u000e*\u00020\u0012H\u0002\u001a\u0018\u0010\u0013\u001a\u00020\u000e*\u00020\u00122\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u000e*\u00020\u00122\u0006\u0010\u0015\u001a\u00020\nH\u0002\"\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"byUnicodePattern", "", "Lkotlinx/datetime/format/DateTimeFormatBuilder;", "pattern", "", "unicodeDirective", "Lkotlinx/datetime/format/UnicodeFormat;", "char", "", "formatLength", "", "nonPlainCharacters", "", "unsupportedDirective", "", "fieldName", NotificationCompat.CATEGORY_RECOMMENDATION, "unknownLength", "Lkotlinx/datetime/format/UnicodeFormat$Directive;", "localizedDirective", "unsupportedPadding", "digits", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class UnicodeKt {
    private static final List<Character> nonPlainCharacters = CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', GMTDateParser.ZONE), (Iterable) new CharRange('A', 'Z')), (Iterable) CollectionsKt.listOf((Object[]) new Character[]{Character.valueOf(AbstractJsonLexerKt.BEGIN_LIST), Character.valueOf(AbstractJsonLexerKt.END_LIST), '\''}));

    public static final void byUnicodePattern(DateTimeFormatBuilder dateTimeFormatBuilder, String pattern) {
        Intrinsics.checkNotNullParameter(dateTimeFormatBuilder, "<this>");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        byUnicodePattern$rec(dateTimeFormatBuilder, UnicodeFormat.INSTANCE.parse(pattern));
    }

    private static final void byUnicodePattern$rec(DateTimeFormatBuilder dateTimeFormatBuilder, final UnicodeFormat unicodeFormat) {
        if (unicodeFormat instanceof UnicodeFormat.StringLiteral) {
            dateTimeFormatBuilder.chars(((UnicodeFormat.StringLiteral) unicodeFormat).getLiteral());
            return;
        }
        if (!(unicodeFormat instanceof UnicodeFormat.Sequence)) {
            if (unicodeFormat instanceof UnicodeFormat.OptionalGroup) {
                DateTimeFormatBuilderKt.alternativeParsing(dateTimeFormatBuilder, new Function1[]{new Function1() { // from class: kotlinx.datetime.format.UnicodeKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return UnicodeKt.byUnicodePattern$rec$lambda$1((DateTimeFormatBuilder) obj);
                    }
                }}, new Function1() { // from class: kotlinx.datetime.format.UnicodeKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return UnicodeKt.byUnicodePattern$rec$lambda$2(unicodeFormat, (DateTimeFormatBuilder) obj);
                    }
                });
                return;
            }
            if (!(unicodeFormat instanceof UnicodeFormat.Directive)) {
                throw new NoWhenBranchMatchedException();
            }
            UnicodeFormat.Directive directive = (UnicodeFormat.Directive) unicodeFormat;
            if (directive instanceof UnicodeFormat.Directive.TimeBased) {
                if (!(dateTimeFormatBuilder instanceof DateTimeFormatBuilder.WithTime)) {
                    throw new IllegalArgumentException(("A time-based directive " + unicodeFormat + " was used in a format builder that doesn't support time components").toString());
                }
                ((UnicodeFormat.Directive.TimeBased) unicodeFormat).mo10573addToFormat((DateTimeFormatBuilder.WithTime) dateTimeFormatBuilder);
                return;
            }
            if (directive instanceof UnicodeFormat.Directive.YearMonthBased) {
                if (!(dateTimeFormatBuilder instanceof DateTimeFormatBuilder.WithYearMonth)) {
                    throw new IllegalArgumentException(("A year-month-based directive " + unicodeFormat + " was used in a format builder that doesn't support year-month components").toString());
                }
                ((UnicodeFormat.Directive.YearMonthBased) unicodeFormat).mo10574addToFormat((DateTimeFormatBuilder.WithYearMonth) dateTimeFormatBuilder);
                return;
            }
            if (directive instanceof UnicodeFormat.Directive.DateBased) {
                if (!(dateTimeFormatBuilder instanceof DateTimeFormatBuilder.WithDate)) {
                    throw new IllegalArgumentException(("A date-based directive " + unicodeFormat + " was used in a format builder that doesn't support date components").toString());
                }
                ((UnicodeFormat.Directive.DateBased) unicodeFormat).mo10571addToFormat((DateTimeFormatBuilder.WithDate) dateTimeFormatBuilder);
                return;
            }
            if (directive instanceof UnicodeFormat.Directive.ZoneBased) {
                if (!(dateTimeFormatBuilder instanceof DateTimeFormatBuilder.WithDateTimeComponents)) {
                    throw new IllegalArgumentException(("A time-zone-based directive " + unicodeFormat + " was used in a format builder that doesn't support time-zone components").toString());
                }
                ((UnicodeFormat.Directive.ZoneBased) unicodeFormat).mo10575addToFormat((DateTimeFormatBuilder.WithDateTimeComponents) dateTimeFormatBuilder);
                return;
            }
            if (directive instanceof UnicodeFormat.Directive.OffsetBased) {
                if (!(dateTimeFormatBuilder instanceof DateTimeFormatBuilder.WithUtcOffset)) {
                    throw new IllegalArgumentException(("A UTC-offset-based directive " + unicodeFormat + " was used in a format builder that doesn't support UTC offset components").toString());
                }
                ((UnicodeFormat.Directive.OffsetBased) unicodeFormat).mo10572addToFormat((DateTimeFormatBuilder.WithUtcOffset) dateTimeFormatBuilder);
                return;
            }
            if (!(directive instanceof UnknownUnicodeDirective)) {
                throw new NoWhenBranchMatchedException();
            }
            throw new IllegalArgumentException("The meaning of the directive '" + unicodeFormat + "' is unknown");
        }
        Iterator<T> it = ((UnicodeFormat.Sequence) unicodeFormat).getFormats().iterator();
        while (it.hasNext()) {
            byUnicodePattern$rec(dateTimeFormatBuilder, (UnicodeFormat) it.next());
        }
    }

    static final Unit byUnicodePattern$rec$lambda$1(DateTimeFormatBuilder alternativeParsing) {
        Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
        return Unit.INSTANCE;
    }

    static final Unit byUnicodePattern$rec$lambda$2(UnicodeFormat unicodeFormat, DateTimeFormatBuilder alternativeParsing) {
        Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
        byUnicodePattern$rec(alternativeParsing, ((UnicodeFormat.OptionalGroup) unicodeFormat).getFormat());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UnicodeFormat unicodeDirective(char c, int i) {
        switch (c) {
            case 'A':
                return new UnicodeFormat.Directive.TimeBased.WithSubsecondPrecision.MilliOfDay(i);
            case 'B':
            case 'C':
            case 'I':
            case 'J':
            case 'K':
            case 'P':
            case 'R':
            case 'T':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'b':
            case 'f':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'o':
            case 'p':
            case 't':
            default:
                return new UnknownUnicodeDirective(c, i);
            case 'D':
                return new UnicodeFormat.Directive.DateBased.DayOfYear(i);
            case 'E':
                return new UnicodeFormat.Directive.DateBased.DayOfWeek(i);
            case 'F':
                return new UnicodeFormat.Directive.DateBased.DayOfWeekInMonth(i);
            case 'G':
                return new UnicodeFormat.Directive.YearMonthBased.Era(i);
            case 'H':
                return new UnicodeFormat.Directive.TimeBased.HourOfDay(i);
            case 'L':
                return new UnicodeFormat.Directive.YearMonthBased.StandaloneMonthOfYear(i);
            case 'M':
                return new UnicodeFormat.Directive.YearMonthBased.MonthOfYear(i);
            case 'N':
                return new UnicodeFormat.Directive.TimeBased.WithSubsecondPrecision.NanoOfDay(i);
            case 'O':
                return new UnicodeFormat.Directive.OffsetBased.LocalizedZoneOffset(i);
            case 'Q':
                return new UnicodeFormat.Directive.YearMonthBased.QuarterOfYear(i);
            case 'S':
                return new UnicodeFormat.Directive.TimeBased.WithSubsecondPrecision.FractionOfSecond(i);
            case 'U':
                return new UnicodeFormat.Directive.YearMonthBased.CyclicYearName(i);
            case 'V':
                return new UnicodeFormat.Directive.ZoneBased.TimeZoneId(i);
            case 'W':
                return new UnicodeFormat.Directive.DateBased.WeekOfMonth(i);
            case 'X':
                return new UnicodeFormat.Directive.OffsetBased.ZoneOffset1(i);
            case 'Y':
                return new UnicodeFormat.Directive.DateBased.WeekBasedYear(i);
            case 'Z':
                return new UnicodeFormat.Directive.OffsetBased.ZoneOffset3(i);
            case 'a':
                return new UnicodeFormat.Directive.TimeBased.AmPmMarker(i);
            case 'c':
                return new UnicodeFormat.Directive.DateBased.StandaloneLocalizedDayOfWeek(i);
            case 'd':
                return new UnicodeFormat.Directive.DateBased.DayOfMonth(i);
            case 'e':
                return new UnicodeFormat.Directive.DateBased.LocalizedDayOfWeek(i);
            case 'g':
                return new UnicodeFormat.Directive.DateBased.ModifiedJulianDay(i);
            case 'h':
                return new UnicodeFormat.Directive.TimeBased.AmPmHourOfDay(i);
            case 'm':
                return new UnicodeFormat.Directive.TimeBased.MinuteOfHour(i);
            case 'n':
                return new UnicodeFormat.Directive.TimeBased.WithSubsecondPrecision.NanoOfSecond(i);
            case 'q':
                return new UnicodeFormat.Directive.YearMonthBased.StandaloneQuarterOfYear(i);
            case 'r':
                return new UnicodeFormat.Directive.YearMonthBased.RelatedGregorianYear(i);
            case 's':
                return new UnicodeFormat.Directive.TimeBased.WithSecondPrecision.SecondOfMinute(i);
            case 'u':
                return new UnicodeFormat.Directive.YearMonthBased.Year(i);
            case 'v':
                return new UnicodeFormat.Directive.ZoneBased.GenericTimeZoneName(i);
            case 'w':
                return new UnicodeFormat.Directive.DateBased.WeekOfWeekBasedYear(i);
            case 'x':
                return new UnicodeFormat.Directive.OffsetBased.ZoneOffset2(i);
            case 'y':
                return new UnicodeFormat.Directive.YearMonthBased.YearOfEra(i);
            case 'z':
                return new UnicodeFormat.Directive.ZoneBased.TimeZoneName(i);
        }
    }

    static /* synthetic */ Void unsupportedDirective$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return unsupportedDirective(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void unsupportedDirective(String str, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder("kotlinx.datetime formatting does not support the ");
        sb.append(str);
        sb.append(" field. ");
        if (str2 != null) {
            str3 = str2 + ' ';
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append("Please report your use case to https://github.com/Kotlin/kotlinx-datetime/issues");
        throw new UnsupportedOperationException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void unknownLength(UnicodeFormat.Directive directive) {
        throw new IllegalArgumentException("Unknown length " + directive.getFormatLength() + " for the " + directive.getFormatLetter() + " directive");
    }

    static /* synthetic */ Void localizedDirective$default(UnicodeFormat.Directive directive, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return localizedDirective(directive, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void localizedDirective(UnicodeFormat.Directive directive, String str) {
        String str2;
        StringBuilder sb = new StringBuilder("The directive '");
        sb.append(directive);
        sb.append("' is locale-dependent, but locales are not supported in Kotlin");
        if (str != null) {
            str2 = ". " + str;
        } else {
            str2 = "";
        }
        sb.append(str2);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void unsupportedPadding(UnicodeFormat.Directive directive, int i) {
        throw new UnsupportedOperationException("Padding do " + i + " digits is not supported for the " + directive.getFormatLetter() + " directive");
    }
}
