package j$.time.format;

import androidx.collection.SieveCacheKt;
import androidx.media3.common.C;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import io.ktor.util.date.GMTDateParser;
import j$.nio.file.attribute.FileTime;
import j$.time.DateTimeException;
import j$.time.Duration;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.Year;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.time.chrono.Era;
import j$.time.chrono.IsoChronology;
import j$.time.temporal.ChronoField;
import j$.time.temporal.IsoFields;
import j$.time.temporal.JulianFields;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.ValueRange;
import j$.time.temporal.WeekFields;
import j$.time.zone.ZoneRulesProvider;
import j$.util.Base64;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.Consumer$CC;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import kotlinx.datetime.internal.DateCalculationsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.internal.connection.RealConnection;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes3.dex */
public final class DateTimeFormatterBuilder {
    private static final Map FIELD_MAP;
    static final Comparator LENGTH_SORT;
    private static final TemporalQuery QUERY_REGION_ONLY = new TemporalQuery() { // from class: j$.time.format.DateTimeFormatterBuilder.0
        @Override // j$.time.temporal.TemporalQuery
        public final Object queryFrom(TemporalAccessor temporalAccessor) {
            return DateTimeFormatterBuilder.lambda$static$0(temporalAccessor);
        }
    };
    private DateTimeFormatterBuilder active;
    private final boolean optional;
    private char padNextChar;
    private int padNextWidth;
    private final DateTimeFormatterBuilder parent;
    private final List printerParsers;
    private int valueParserIndex;

    /* JADX INFO: renamed from: j$.time.format.DateTimeFormatterBuilder$4, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass4 {
        public static /* synthetic */ void m(String str, int i, int i2) {
            throw new IllegalArgumentException(str + i + ((Object) " < ") + i2);
        }
    }

    /* JADX INFO: renamed from: j$.time.format.DateTimeFormatterBuilder$5, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass5 {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void m(String str, int i) {
            throw new IllegalArgumentException(str + ((char) i));
        }
    }

    interface DateTimePrinterParser {
        boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb);

        int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i);
    }

    static {
        HashMap map = new HashMap();
        FIELD_MAP = map;
        map.put('G', ChronoField.ERA);
        map.put('y', ChronoField.YEAR_OF_ERA);
        map.put(Character.valueOf(AbstractJsonLexerKt.UNICODE_ESC), ChronoField.YEAR);
        TemporalField temporalField = IsoFields.QUARTER_OF_YEAR;
        map.put('Q', temporalField);
        map.put('q', temporalField);
        Character chValueOf = Character.valueOf(GMTDateParser.MONTH);
        ChronoField chronoField = ChronoField.MONTH_OF_YEAR;
        map.put(chValueOf, chronoField);
        map.put('L', chronoField);
        map.put('D', ChronoField.DAY_OF_YEAR);
        map.put(Character.valueOf(GMTDateParser.DAY_OF_MONTH), ChronoField.DAY_OF_MONTH);
        map.put('F', ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        ChronoField chronoField2 = ChronoField.DAY_OF_WEEK;
        map.put('E', chronoField2);
        map.put('c', chronoField2);
        map.put('e', chronoField2);
        map.put('a', ChronoField.AMPM_OF_DAY);
        map.put('H', ChronoField.HOUR_OF_DAY);
        map.put('k', ChronoField.CLOCK_HOUR_OF_DAY);
        map.put('K', ChronoField.HOUR_OF_AMPM);
        map.put(Character.valueOf(GMTDateParser.HOURS), ChronoField.CLOCK_HOUR_OF_AMPM);
        map.put(Character.valueOf(GMTDateParser.MINUTES), ChronoField.MINUTE_OF_HOUR);
        map.put(Character.valueOf(GMTDateParser.SECONDS), ChronoField.SECOND_OF_MINUTE);
        ChronoField chronoField3 = ChronoField.NANO_OF_SECOND;
        map.put('S', chronoField3);
        map.put('A', ChronoField.MILLI_OF_DAY);
        map.put('n', chronoField3);
        map.put('N', ChronoField.NANO_OF_DAY);
        map.put('g', JulianFields.MODIFIED_JULIAN_DAY);
        LENGTH_SORT = new Comparator() { // from class: j$.time.format.DateTimeFormatterBuilder.2
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                return str.length() == str2.length() ? str.compareTo(str2) : str.length() - str2.length();
            }
        };
    }

    static /* synthetic */ ZoneId lambda$static$0(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zoneId());
        if (zoneId == null || (zoneId instanceof ZoneOffset)) {
            return null;
        }
        return zoneId;
    }

    public static String getLocalizedDateTimePattern(FormatStyle formatStyle, FormatStyle formatStyle2, Chronology chronology, Locale locale) {
        DateFormat dateTimeInstance;
        Objects.requireNonNull(locale, "locale");
        Objects.requireNonNull(chronology, "chrono");
        if (formatStyle == null && formatStyle2 == null) {
            ZoneId.AnonymousClass4.m("Either dateStyle or timeStyle must be non-null");
            return null;
        }
        if (formatStyle2 == null) {
            dateTimeInstance = DateFormat.getDateInstance(formatStyle.ordinal(), locale);
        } else if (formatStyle == null) {
            dateTimeInstance = DateFormat.getTimeInstance(formatStyle2.ordinal(), locale);
        } else {
            dateTimeInstance = DateFormat.getDateTimeInstance(formatStyle.ordinal(), formatStyle2.ordinal(), locale);
        }
        if (dateTimeInstance instanceof SimpleDateFormat) {
            return DateTimeFormatterBuilderHelper.transformAndroidJavaTextDateTimePattern(((SimpleDateFormat) dateTimeInstance).toPattern());
        }
        throw new UnsupportedOperationException("Can't determine pattern from " + dateTimeInstance);
    }

    public DateTimeFormatterBuilder() {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = null;
        this.optional = false;
    }

    private DateTimeFormatterBuilder(DateTimeFormatterBuilder dateTimeFormatterBuilder, boolean z) {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = dateTimeFormatterBuilder;
        this.optional = z;
    }

    public DateTimeFormatterBuilder parseCaseSensitive() {
        appendInternal(SettingsParser.SENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseCaseInsensitive() {
        appendInternal(SettingsParser.INSENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseStrict() {
        appendInternal(SettingsParser.STRICT);
        return this;
    }

    public DateTimeFormatterBuilder parseLenient() {
        appendInternal(SettingsParser.LENIENT);
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField) {
        Objects.requireNonNull(temporalField, "field");
        appendValue(new NumberPrinterParser(temporalField, 1, 19, SignStyle.NORMAL));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField, int i) {
        Objects.requireNonNull(temporalField, "field");
        if (i < 1 || i > 19) {
            Base64.Decoder.AnonymousClass0.m("The width must be from 1 to 19 inclusive but was ", i);
            return null;
        }
        appendValue(new NumberPrinterParser(temporalField, i, i, SignStyle.NOT_NEGATIVE));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
        if (i == i2 && signStyle == SignStyle.NOT_NEGATIVE) {
            return appendValue(temporalField, i2);
        }
        Objects.requireNonNull(temporalField, "field");
        Objects.requireNonNull(signStyle, "signStyle");
        if (i < 1 || i > 19) {
            Base64.Decoder.AnonymousClass0.m("The minimum width must be from 1 to 19 inclusive but was ", i);
            return null;
        }
        if (i2 < 1 || i2 > 19) {
            Base64.Decoder.AnonymousClass0.m("The maximum width must be from 1 to 19 inclusive but was ", i2);
            return null;
        }
        if (i2 < i) {
            AnonymousClass4.m("The maximum width must exceed or equal the minimum width but ", i2, i);
            return null;
        }
        appendValue(new NumberPrinterParser(temporalField, i, i2, signStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField temporalField, int i, int i2, ChronoLocalDate chronoLocalDate) {
        Objects.requireNonNull(temporalField, "field");
        Objects.requireNonNull(chronoLocalDate, "baseDate");
        appendValue(new ReducedPrinterParser(temporalField, i, i2, 0, chronoLocalDate));
        return this;
    }

    private DateTimeFormatterBuilder appendValue(NumberPrinterParser numberPrinterParser) {
        NumberPrinterParser numberPrinterParserWithFixedWidth;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.valueParserIndex;
        if (i >= 0) {
            NumberPrinterParser numberPrinterParser2 = (NumberPrinterParser) dateTimeFormatterBuilder.printerParsers.get(i);
            if (numberPrinterParser.minWidth == numberPrinterParser.maxWidth && numberPrinterParser.signStyle == SignStyle.NOT_NEGATIVE) {
                numberPrinterParserWithFixedWidth = numberPrinterParser2.withSubsequentWidth(numberPrinterParser.maxWidth);
                appendInternal(numberPrinterParser.withFixedWidth());
                this.active.valueParserIndex = i;
            } else {
                numberPrinterParserWithFixedWidth = numberPrinterParser2.withFixedWidth();
                this.active.valueParserIndex = appendInternal(numberPrinterParser);
            }
            this.active.printerParsers.set(i, numberPrinterParserWithFixedWidth);
            return this;
        }
        dateTimeFormatterBuilder.valueParserIndex = appendInternal(numberPrinterParser);
        return this;
    }

    public DateTimeFormatterBuilder appendFraction(TemporalField temporalField, int i, int i2, boolean z) {
        if (i == i2 && !z) {
            appendValue(new FractionPrinterParser(temporalField, i, i2, z));
            return this;
        }
        appendInternal(new FractionPrinterParser(temporalField, i, i2, z));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField, TextStyle textStyle) {
        Objects.requireNonNull(temporalField, "field");
        Objects.requireNonNull(textStyle, "textStyle");
        appendInternal(new TextPrinterParser(temporalField, textStyle, DateTimeTextProvider.getInstance()));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField, Map map) {
        Objects.requireNonNull(temporalField, "field");
        Objects.requireNonNull(map, "textLookup");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        TextStyle textStyle = TextStyle.FULL;
        final DateTimeTextProvider.LocaleStore localeStore = new DateTimeTextProvider.LocaleStore(Collections.singletonMap(textStyle, linkedHashMap));
        appendInternal(new TextPrinterParser(temporalField, textStyle, new DateTimeTextProvider() { // from class: j$.time.format.DateTimeFormatterBuilder.1
            @Override // j$.time.format.DateTimeTextProvider
            public String getText(Chronology chronology, TemporalField temporalField2, long j, TextStyle textStyle2, Locale locale) {
                return localeStore.getText(j, textStyle2);
            }

            @Override // j$.time.format.DateTimeTextProvider
            public String getText(TemporalField temporalField2, long j, TextStyle textStyle2, Locale locale) {
                return localeStore.getText(j, textStyle2);
            }

            @Override // j$.time.format.DateTimeTextProvider
            public Iterator getTextIterator(Chronology chronology, TemporalField temporalField2, TextStyle textStyle2, Locale locale) {
                return localeStore.getTextIterator(textStyle2);
            }

            @Override // j$.time.format.DateTimeTextProvider
            public Iterator getTextIterator(TemporalField temporalField2, TextStyle textStyle2, Locale locale) {
                return localeStore.getTextIterator(textStyle2);
            }
        }));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant() {
        appendInternal(new InstantPrinterParser(-2));
        return this;
    }

    public DateTimeFormatterBuilder appendOffsetId() {
        appendInternal(OffsetIdPrinterParser.INSTANCE_ID_Z);
        return this;
    }

    public DateTimeFormatterBuilder appendOffset(String str, String str2) {
        appendInternal(new OffsetIdPrinterParser(str, str2));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalizedOffset(TextStyle textStyle) {
        Objects.requireNonNull(textStyle, "style");
        if (textStyle != TextStyle.FULL && textStyle != TextStyle.SHORT) {
            ZoneId.AnonymousClass4.m("Style must be either full or short");
            return null;
        }
        appendInternal(new LocalizedOffsetIdPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zoneId(), "ZoneId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneRegionId() {
        appendInternal(new ZoneIdPrinterParser(QUERY_REGION_ONLY, "ZoneRegionId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle) {
        appendInternal(new ZoneTextPrinterParser(textStyle, null, false));
        return this;
    }

    public DateTimeFormatterBuilder appendGenericZoneText(TextStyle textStyle) {
        appendInternal(new ZoneTextPrinterParser(textStyle, null, true));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalized(FormatStyle formatStyle, FormatStyle formatStyle2) {
        if (formatStyle == null && formatStyle2 == null) {
            ZoneId.AnonymousClass4.m("Either the date or time style must be non-null");
            return null;
        }
        appendInternal(new LocalizedPrinterParser(formatStyle, formatStyle2));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        appendInternal(new CharLiteralPrinterParser(c));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        Objects.requireNonNull(str, "literal");
        if (!str.isEmpty()) {
            if (str.length() == 1) {
                appendInternal(new CharLiteralPrinterParser(str.charAt(0)));
                return this;
            }
            appendInternal(new StringLiteralPrinterParser(str));
        }
        return this;
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        appendInternal(dateTimeFormatter.toPrinterParser(false));
        return this;
    }

    public DateTimeFormatterBuilder appendPattern(String str) {
        Objects.requireNonNull(str, "pattern");
        parsePattern(str);
        return this;
    }

    private void parsePattern(String str) {
        int i;
        int i2 = 0;
        while (i2 < str.length()) {
            char cCharAt = str.charAt(i2);
            if ((cCharAt >= 'A' && cCharAt <= 'Z') || (cCharAt >= 'a' && cCharAt <= 'z')) {
                int i3 = i2 + 1;
                while (i3 < str.length() && str.charAt(i3) == cCharAt) {
                    i3++;
                }
                int i4 = i3 - i2;
                if (cCharAt == 'p') {
                    if (i3 >= str.length() || (((cCharAt = str.charAt(i3)) < 'A' || cCharAt > 'Z') && (cCharAt < 'a' || cCharAt > 'z'))) {
                        i = i4;
                        i4 = 0;
                    } else {
                        int i5 = i3 + 1;
                        while (i5 < str.length() && str.charAt(i5) == cCharAt) {
                            i5++;
                        }
                        i = i5 - i3;
                        i3 = i5;
                    }
                    if (i4 == 0) {
                        ZoneId.AnonymousClass4.m("Pad letter 'p' must be followed by valid pad pattern: ".concat(str));
                        return;
                    } else {
                        padNext(i4);
                        i4 = i;
                    }
                }
                TemporalField temporalField = (TemporalField) FIELD_MAP.get(Character.valueOf(cCharAt));
                if (temporalField != null) {
                    parseField(cCharAt, i4, temporalField);
                } else if (cCharAt == 'z') {
                    if (i4 > 4) {
                        AnonymousClass5.m("Too many pattern letters: ", cCharAt);
                        return;
                    } else if (i4 == 4) {
                        appendZoneText(TextStyle.FULL);
                    } else {
                        appendZoneText(TextStyle.SHORT);
                    }
                } else if (cCharAt == 'V') {
                    if (i4 != 2) {
                        AnonymousClass5.m("Pattern letter count must be 2: ", cCharAt);
                        return;
                    }
                    appendZoneId();
                } else if (cCharAt != 'v') {
                    String str2 = "+0000";
                    if (cCharAt == 'Z') {
                        if (i4 < 4) {
                            appendOffset("+HHMM", "+0000");
                        } else if (i4 == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else if (i4 == 5) {
                            appendOffset("+HH:MM:ss", "Z");
                        } else {
                            AnonymousClass5.m("Too many pattern letters: ", cCharAt);
                            return;
                        }
                    } else if (cCharAt == 'O') {
                        if (i4 == 1) {
                            appendLocalizedOffset(TextStyle.SHORT);
                        } else if (i4 == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else {
                            AnonymousClass5.m("Pattern letter count must be 1 or 4: ", cCharAt);
                            return;
                        }
                    } else if (cCharAt == 'X') {
                        if (i4 <= 5) {
                            appendOffset(OffsetIdPrinterParser.PATTERNS[i4 + (i4 == 1 ? 0 : 1)], "Z");
                        } else {
                            AnonymousClass5.m("Too many pattern letters: ", cCharAt);
                            return;
                        }
                    } else if (cCharAt == 'x') {
                        if (i4 > 5) {
                            AnonymousClass5.m("Too many pattern letters: ", cCharAt);
                            return;
                        }
                        if (i4 == 1) {
                            str2 = "+00";
                        } else if (i4 % 2 != 0) {
                            str2 = "+00:00";
                        }
                        appendOffset(OffsetIdPrinterParser.PATTERNS[i4 + (i4 == 1 ? 0 : 1)], str2);
                    } else if (cCharAt == 'W') {
                        if (i4 > 1) {
                            AnonymousClass5.m("Too many pattern letters: ", cCharAt);
                            return;
                        }
                        appendValue(new WeekBasedFieldPrinterParser(cCharAt, i4, i4, i4));
                    } else if (cCharAt == 'w') {
                        if (i4 > 2) {
                            AnonymousClass5.m("Too many pattern letters: ", cCharAt);
                            return;
                        }
                        appendValue(new WeekBasedFieldPrinterParser(cCharAt, i4, i4, 2));
                    } else if (cCharAt != 'Y') {
                        AnonymousClass5.m("Unknown pattern letter: ", cCharAt);
                        return;
                    } else if (i4 == 2) {
                        appendValue(new WeekBasedFieldPrinterParser(cCharAt, i4, i4, 2));
                    } else {
                        appendValue(new WeekBasedFieldPrinterParser(cCharAt, i4, i4, 19));
                    }
                } else if (i4 == 1) {
                    appendGenericZoneText(TextStyle.SHORT);
                } else if (i4 == 4) {
                    appendGenericZoneText(TextStyle.FULL);
                } else {
                    AnonymousClass5.m("Wrong number of  pattern letters: ", cCharAt);
                    return;
                }
                i2 = i3 - 1;
            } else if (cCharAt == '\'') {
                int i6 = i2 + 1;
                int i7 = i6;
                while (i7 < str.length()) {
                    if (str.charAt(i7) == '\'') {
                        int i8 = i7 + 1;
                        if (i8 >= str.length() || str.charAt(i8) != '\'') {
                            break;
                        } else {
                            i7 = i8;
                        }
                    }
                    i7++;
                }
                if (i7 >= str.length()) {
                    ZoneId.AnonymousClass4.m("Pattern ends with an incomplete string literal: ".concat(str));
                    return;
                }
                String strSubstring = str.substring(i6, i7);
                if (!strSubstring.isEmpty()) {
                    appendLiteral(strSubstring.replace("''", "'"));
                } else {
                    appendLiteral('\'');
                }
                i2 = i7;
            } else if (cCharAt == '[') {
                optionalStart();
            } else if (cCharAt == ']') {
                if (this.active.parent == null) {
                    ZoneId.AnonymousClass4.m("Pattern invalid as it contains ] without previous [");
                    return;
                }
                optionalEnd();
            } else {
                if (cCharAt == '{' || cCharAt == '}' || cCharAt == '#') {
                    throw new IllegalArgumentException("Pattern includes reserved character: '" + cCharAt + "'");
                }
                appendLiteral(cCharAt);
            }
            i2++;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:121:0x0117  */
    /* JADX WARN: Code duplicated, block: B:123:0x0120  */
    /* JADX WARN: Code duplicated, block: B:125:0x0124  */
    /* JADX WARN: Code duplicated, block: B:127:0x012a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:128:0x012c  */
    /* JADX WARN: Code duplicated, block: B:130:0x0130  */
    /* JADX WARN: Code duplicated, block: B:97:0x00e4 A[DONT_INVERT, FALL_THROUGH, PHI: r8
      0x00e4: PHI (r8v1 boolean) = (r8v0 boolean), (r8v2 boolean), (r8v0 boolean), (r8v0 boolean), (r8v3 boolean), (r8v0 boolean), (r8v4 boolean) binds: [B:5:0x0010, B:81:0x00c0, B:28:0x003e, B:29:0x0041, B:134:?, B:30:0x0044, B:135:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:98:0x00e6 A[ADDED_TO_REGION] */
    /* JADX WARN: Switch 'out' block B:97:0x00e4 for B:30:0x0044 already processed. Defaulting to fallback option. */
    private void parseField(char c, int i, TemporalField temporalField) {
        if (c != 'A') {
            boolean z = false;
            if (c != 'Q') {
                if (c == 'S') {
                    appendFraction(ChronoField.NANO_OF_SECOND, i, i, false);
                    return;
                }
                if (c == 'a') {
                    if (i == 1) {
                        appendText(temporalField, TextStyle.SHORT);
                        return;
                    } else {
                        AnonymousClass5.m("Too many pattern letters: ", c);
                        return;
                    }
                }
                if (c != 'k') {
                    if (c == 'q') {
                        z = true;
                    } else if (c != 's') {
                        if (c == 'u' || c == 'y') {
                            if (i == 2) {
                                appendValueReduced(temporalField, 2, 2, ReducedPrinterParser.BASE_DATE);
                                return;
                            } else if (i < 4) {
                                appendValue(temporalField, i, 19, SignStyle.NORMAL);
                                return;
                            } else {
                                appendValue(temporalField, i, 19, SignStyle.EXCEEDS_PAD);
                                return;
                            }
                        }
                        if (c == 'g') {
                            appendValue(temporalField, i, 19, SignStyle.NORMAL);
                            return;
                        }
                        if (c != 'h' && c != 'm') {
                            if (c != 'n') {
                                switch (c) {
                                    case 'D':
                                        if (i == 1) {
                                            appendValue(temporalField);
                                        } else if (i == 2 || i == 3) {
                                            appendValue(temporalField, i, 3, SignStyle.NOT_NEGATIVE);
                                        } else {
                                            AnonymousClass5.m("Too many pattern letters: ", c);
                                        }
                                        break;
                                    case 'E':
                                        break;
                                    case 'F':
                                        if (i == 1) {
                                            appendValue(temporalField);
                                        } else {
                                            AnonymousClass5.m("Too many pattern letters: ", c);
                                        }
                                        break;
                                    case 'G':
                                        if (i == 1 || i == 2 || i == 3) {
                                            appendText(temporalField, TextStyle.SHORT);
                                        } else if (i == 4) {
                                            appendText(temporalField, TextStyle.FULL);
                                        } else if (i == 5) {
                                            appendText(temporalField, TextStyle.NARROW);
                                        } else {
                                            AnonymousClass5.m("Too many pattern letters: ", c);
                                        }
                                        break;
                                    case 'H':
                                        break;
                                    default:
                                        switch (c) {
                                            case 'K':
                                                break;
                                            case 'L':
                                                z = true;
                                            case 'M':
                                                if (i == 1 && i != 2) {
                                                    if (i == 3) {
                                                        appendText(temporalField, z ? TextStyle.SHORT_STANDALONE : TextStyle.SHORT);
                                                    } else if (i == 4) {
                                                        appendText(temporalField, z ? TextStyle.FULL_STANDALONE : TextStyle.FULL);
                                                    } else if (i == 5) {
                                                        appendText(temporalField, z ? TextStyle.NARROW_STANDALONE : TextStyle.NARROW);
                                                    } else {
                                                        AnonymousClass5.m("Too many pattern letters: ", c);
                                                    }
                                                } else if (c == 'e') {
                                                    appendValue(new WeekBasedFieldPrinterParser(c, i, i, i));
                                                } else if (c == 'E') {
                                                    appendText(temporalField, TextStyle.SHORT);
                                                } else if (i == 1) {
                                                    appendValue(temporalField);
                                                } else {
                                                    appendValue(temporalField, 2);
                                                }
                                                break;
                                            case 'N':
                                                break;
                                            default:
                                                switch (c) {
                                                    case 'c':
                                                        if (i == 1) {
                                                            appendValue(new WeekBasedFieldPrinterParser(c, i, i, i));
                                                        } else if (i == 2) {
                                                            ZoneId.AnonymousClass4.m("Invalid pattern \"cc\"");
                                                        } else {
                                                            z = true;
                                                            if (i == 1) {
                                                            }
                                                            if (c == 'e') {
                                                                appendValue(new WeekBasedFieldPrinterParser(c, i, i, i));
                                                            } else if (c == 'E') {
                                                                appendText(temporalField, TextStyle.SHORT);
                                                            } else if (i == 1) {
                                                                appendValue(temporalField);
                                                            } else {
                                                                appendValue(temporalField, 2);
                                                            }
                                                        }
                                                        break;
                                                    case 'd':
                                                        break;
                                                    case 'e':
                                                        if (i == 1) {
                                                        }
                                                        if (c == 'e') {
                                                            appendValue(new WeekBasedFieldPrinterParser(c, i, i, i));
                                                        } else if (c == 'E') {
                                                            appendText(temporalField, TextStyle.SHORT);
                                                        } else if (i == 1) {
                                                            appendValue(temporalField);
                                                        } else {
                                                            appendValue(temporalField, 2);
                                                        }
                                                        break;
                                                    default:
                                                        if (i == 1) {
                                                            appendValue(temporalField);
                                                        } else {
                                                            appendValue(temporalField, i);
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                return;
                            }
                        }
                    }
                }
                if (i == 1) {
                    appendValue(temporalField);
                    return;
                } else if (i == 2) {
                    appendValue(temporalField, i);
                    return;
                } else {
                    AnonymousClass5.m("Too many pattern letters: ", c);
                    return;
                }
            }
            if (i == 1) {
            }
            if (c == 'e') {
                appendValue(new WeekBasedFieldPrinterParser(c, i, i, i));
                return;
            }
            if (c == 'E') {
                appendText(temporalField, TextStyle.SHORT);
                return;
            } else if (i == 1) {
                appendValue(temporalField);
                return;
            } else {
                appendValue(temporalField, 2);
                return;
            }
        }
        appendValue(temporalField, i, 19, SignStyle.NOT_NEGATIVE);
    }

    public DateTimeFormatterBuilder padNext(int i) {
        return padNext(i, ' ');
    }

    public DateTimeFormatterBuilder padNext(int i, char c) {
        if (i < 1) {
            Base64.Decoder.AnonymousClass0.m("The pad width must be at least one but was ", i);
            return null;
        }
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        dateTimeFormatterBuilder.padNextWidth = i;
        dateTimeFormatterBuilder.padNextChar = c;
        dateTimeFormatterBuilder.valueParserIndex = -1;
        return this;
    }

    public DateTimeFormatterBuilder optionalStart() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        dateTimeFormatterBuilder.valueParserIndex = -1;
        this.active = new DateTimeFormatterBuilder(dateTimeFormatterBuilder, true);
        return this;
    }

    public DateTimeFormatterBuilder optionalEnd() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        if (dateTimeFormatterBuilder.parent == null) {
            throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
        }
        int size = dateTimeFormatterBuilder.printerParsers.size();
        DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
        if (size > 0) {
            CompositePrinterParser compositePrinterParser = new CompositePrinterParser(dateTimeFormatterBuilder2.printerParsers, dateTimeFormatterBuilder2.optional);
            this.active = this.active.parent;
            appendInternal(compositePrinterParser);
            return this;
        }
        this.active = dateTimeFormatterBuilder2.parent;
        return this;
    }

    private int appendInternal(DateTimePrinterParser dateTimePrinterParser) {
        Objects.requireNonNull(dateTimePrinterParser, "pp");
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.padNextWidth;
        if (i > 0) {
            if (dateTimePrinterParser != null) {
                dateTimePrinterParser = new PadPrinterParserDecorator(dateTimePrinterParser, i, dateTimeFormatterBuilder.padNextChar);
            }
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            dateTimeFormatterBuilder2.padNextWidth = 0;
            dateTimeFormatterBuilder2.padNextChar = (char) 0;
        }
        this.active.printerParsers.add(dateTimePrinterParser);
        DateTimeFormatterBuilder dateTimeFormatterBuilder3 = this.active;
        dateTimeFormatterBuilder3.valueParserIndex = -1;
        return dateTimeFormatterBuilder3.printerParsers.size() - 1;
    }

    public DateTimeFormatter toFormatter() {
        return toFormatter(Locale.getDefault());
    }

    public DateTimeFormatter toFormatter(Locale locale) {
        return toFormatter(locale, ResolverStyle.SMART, null);
    }

    DateTimeFormatter toFormatter(ResolverStyle resolverStyle, Chronology chronology) {
        return toFormatter(Locale.getDefault(), resolverStyle, chronology);
    }

    private DateTimeFormatter toFormatter(Locale locale, ResolverStyle resolverStyle, Chronology chronology) {
        Objects.requireNonNull(locale, "locale");
        while (this.active.parent != null) {
            optionalEnd();
        }
        return new DateTimeFormatter(new CompositePrinterParser(this.printerParsers, false), locale, DecimalStyle.STANDARD, resolverStyle, null, chronology, null);
    }

    static final class CompositePrinterParser implements DateTimePrinterParser {
        private final boolean optional;
        private final DateTimePrinterParser[] printerParsers;

        CompositePrinterParser(List list, boolean z) {
            this((DateTimePrinterParser[]) list.toArray(new DateTimePrinterParser[list.size()]), z);
        }

        CompositePrinterParser(DateTimePrinterParser[] dateTimePrinterParserArr, boolean z) {
            this.printerParsers = dateTimePrinterParserArr;
            this.optional = z;
        }

        public CompositePrinterParser withOptional(boolean z) {
            return z == this.optional ? this : new CompositePrinterParser(this.printerParsers, z);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (this.optional) {
                dateTimePrintContext.startOptional();
            }
            try {
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    if (!dateTimePrinterParser.format(dateTimePrintContext, sb)) {
                        sb.setLength(length);
                        return true;
                    }
                }
                return true;
            } finally {
                if (this.optional) {
                    dateTimePrintContext.endOptional();
                }
            }
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (this.optional) {
                dateTimeParseContext.startOptional();
                int i2 = i;
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    i2 = dateTimePrinterParser.parse(dateTimeParseContext, charSequence, i2);
                    if (i2 < 0) {
                        dateTimeParseContext.endOptional(false);
                        return i;
                    }
                }
                dateTimeParseContext.endOptional(true);
                return i2;
            }
            for (DateTimePrinterParser dateTimePrinterParser2 : this.printerParsers) {
                i = dateTimePrinterParser2.parse(dateTimeParseContext, charSequence, i);
                if (i < 0) {
                    return i;
                }
            }
            return i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.printerParsers != null) {
                sb.append(this.optional ? "[" : "(");
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    sb.append(dateTimePrinterParser);
                }
                sb.append(this.optional ? "]" : ")");
            }
            return sb.toString();
        }
    }

    static final class PadPrinterParserDecorator implements DateTimePrinterParser {
        private final char padChar;
        private final int padWidth;
        private final DateTimePrinterParser printerParser;

        PadPrinterParserDecorator(DateTimePrinterParser dateTimePrinterParser, int i, char c) {
            this.printerParser = dateTimePrinterParser;
            this.padWidth = i;
            this.padChar = c;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (!this.printerParser.format(dateTimePrintContext, sb)) {
                return false;
            }
            int length2 = sb.length() - length;
            int i = this.padWidth;
            if (length2 > i) {
                throw new DateTimeException("Cannot print as output of " + length2 + " characters exceeds pad width of " + i);
            }
            for (int i2 = 0; i2 < this.padWidth - length2; i2++) {
                sb.insert(length, this.padChar);
            }
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            boolean zIsStrict = dateTimeParseContext.isStrict();
            if (i > charSequence.length()) {
                throw new IndexOutOfBoundsException();
            }
            if (i == charSequence.length()) {
                return ~i;
            }
            int length = this.padWidth + i;
            if (length > charSequence.length()) {
                if (zIsStrict) {
                    return ~i;
                }
                length = charSequence.length();
            }
            int i2 = i;
            while (i2 < length && dateTimeParseContext.charEquals(charSequence.charAt(i2), this.padChar)) {
                i2++;
            }
            int i3 = this.printerParser.parse(dateTimeParseContext, charSequence.subSequence(0, length), i2);
            return (i3 == length || !zIsStrict) ? i3 : ~(i + i2);
        }

        public String toString() {
            String str;
            DateTimePrinterParser dateTimePrinterParser = this.printerParser;
            int i = this.padWidth;
            char c = this.padChar;
            if (c == ' ') {
                str = ")";
            } else {
                str = ",'" + c + "')";
            }
            return "Pad(" + dateTimePrinterParser + "," + i + str;
        }
    }

    enum SettingsParser implements DateTimePrinterParser {
        SENSITIVE,
        INSENSITIVE,
        STRICT,
        LENIENT;

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int iOrdinal = ordinal();
            if (iOrdinal == 0) {
                dateTimeParseContext.setCaseSensitive(true);
                return i;
            }
            if (iOrdinal == 1) {
                dateTimeParseContext.setCaseSensitive(false);
                return i;
            }
            if (iOrdinal == 2) {
                dateTimeParseContext.setStrict(true);
                return i;
            }
            if (iOrdinal != 3) {
                return i;
            }
            dateTimeParseContext.setStrict(false);
            return i;
        }

        @Override // java.lang.Enum
        public String toString() {
            int iOrdinal = ordinal();
            if (iOrdinal == 0) {
                return "ParseCaseSensitive(true)";
            }
            if (iOrdinal == 1) {
                return "ParseCaseSensitive(false)";
            }
            if (iOrdinal == 2) {
                return "ParseStrict(true)";
            }
            if (iOrdinal == 3) {
                return "ParseStrict(false)";
            }
            throw new IllegalStateException("Unreachable");
        }
    }

    static final class CharLiteralPrinterParser implements DateTimePrinterParser {
        private final char literal;

        CharLiteralPrinterParser(char c) {
            this.literal = c;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i == charSequence.length()) {
                return ~i;
            }
            char cCharAt = charSequence.charAt(i);
            return (cCharAt == this.literal || (!dateTimeParseContext.isCaseSensitive() && (Character.toUpperCase(cCharAt) == Character.toUpperCase(this.literal) || Character.toLowerCase(cCharAt) == Character.toLowerCase(this.literal)))) ? i + 1 : ~i;
        }

        public String toString() {
            char c = this.literal;
            if (c == '\'') {
                return "''";
            }
            return "'" + c + "'";
        }
    }

    static final class StringLiteralPrinterParser implements DateTimePrinterParser {
        private final String literal;

        StringLiteralPrinterParser(String str) {
            this.literal = str;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i > charSequence.length() || i < 0) {
                throw new IndexOutOfBoundsException();
            }
            String str = this.literal;
            return !dateTimeParseContext.subSequenceEquals(charSequence, i, str, 0, str.length()) ? ~i : i + this.literal.length();
        }

        public String toString() {
            return "'" + this.literal.replace("'", "''") + "'";
        }
    }

    static class NumberPrinterParser implements DateTimePrinterParser {
        static final long[] EXCEED_POINTS = {0, 10, 100, 1000, 10000, SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US, 1000000, 10000000, 100000000, C.NANOS_PER_SECOND, RealConnection.IDLE_CONNECTION_HEALTHY_NS};
        final TemporalField field;
        final int maxWidth;
        final int minWidth;
        private final SignStyle signStyle;
        final int subsequentWidth;

        long getValue(DateTimePrintContext dateTimePrintContext, long j) {
            return j;
        }

        NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle;
            this.subsequentWidth = 0;
        }

        protected NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle, int i3) {
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle;
            this.subsequentWidth = i3;
        }

        NumberPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, -1);
        }

        NumberPrinterParser withSubsequentWidth(int i) {
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, this.subsequentWidth + i);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            long value2 = getValue(dateTimePrintContext, value.longValue());
            DecimalStyle decimalStyle = dateTimePrintContext.getDecimalStyle();
            String string = value2 == Long.MIN_VALUE ? "9223372036854775808" : Long.toString(Math.abs(value2));
            int length = string.length();
            int i = this.maxWidth;
            if (length > i) {
                throw new DateTimeException("Field " + this.field + " cannot be printed as the value " + value2 + " exceeds the maximum print width of " + i);
            }
            String strConvertNumberToI18N = decimalStyle.convertNumberToI18N(string);
            if (value2 >= 0) {
                int i2 = AnonymousClass3.$SwitchMap$java$time$format$SignStyle[this.signStyle.ordinal()];
                if (i2 == 1) {
                    int i3 = this.minWidth;
                    if (i3 < 19 && value2 >= EXCEED_POINTS[i3]) {
                        sb.append(decimalStyle.getPositiveSign());
                    }
                } else if (i2 == 2) {
                    sb.append(decimalStyle.getPositiveSign());
                }
            } else {
                int i4 = AnonymousClass3.$SwitchMap$java$time$format$SignStyle[this.signStyle.ordinal()];
                if (i4 == 1 || i4 == 2 || i4 == 3) {
                    sb.append(decimalStyle.getNegativeSign());
                } else if (i4 == 4) {
                    throw new DateTimeException("Field " + this.field + " cannot be printed as the value " + value2 + " cannot be negative according to the SignStyle");
                }
            }
            for (int i5 = 0; i5 < this.minWidth - strConvertNumberToI18N.length(); i5++) {
                sb.append(decimalStyle.getZeroDigit());
            }
            sb.append(strConvertNumberToI18N);
            return true;
        }

        boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            int i = this.subsequentWidth;
            if (i != -1) {
                return i > 0 && this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE;
            }
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            boolean z;
            boolean z2;
            BigInteger bigIntegerDivide;
            long j;
            int i3;
            long j2;
            boolean z3;
            int length = charSequence.length();
            if (i == length) {
                return ~i;
            }
            char cCharAt = charSequence.charAt(i);
            int i4 = 0;
            boolean z4 = true;
            if (cCharAt == dateTimeParseContext.getDecimalStyle().getPositiveSign()) {
                if (!this.signStyle.parse(true, dateTimeParseContext.isStrict(), this.minWidth == this.maxWidth)) {
                    return ~i;
                }
                i2 = i + 1;
                z = false;
                z2 = true;
            } else {
                char negativeSign = dateTimeParseContext.getDecimalStyle().getNegativeSign();
                SignStyle signStyle = this.signStyle;
                if (cCharAt == negativeSign) {
                    if (!signStyle.parse(false, dateTimeParseContext.isStrict(), this.minWidth == this.maxWidth)) {
                        return ~i;
                    }
                    i2 = i + 1;
                    z2 = false;
                    z = true;
                } else {
                    if (signStyle == SignStyle.ALWAYS && dateTimeParseContext.isStrict()) {
                        return ~i;
                    }
                    i2 = i;
                    z = false;
                    z2 = false;
                }
            }
            int i5 = (dateTimeParseContext.isStrict() || isFixedWidth(dateTimeParseContext)) ? this.minWidth : 1;
            int i6 = i2 + i5;
            if (i6 > length) {
                return ~i2;
            }
            int iMax = ((dateTimeParseContext.isStrict() || isFixedWidth(dateTimeParseContext)) ? this.maxWidth : 9) + Math.max(this.subsequentWidth, 0);
            while (true) {
                bigIntegerDivide = null;
                if (i4 >= 2) {
                    j = 0;
                    i3 = i2;
                    j2 = 0;
                    break;
                }
                int iMin = Math.min(iMax + i2, length);
                int i7 = i2;
                j2 = 0;
                while (true) {
                    if (i7 >= iMin) {
                        z3 = z4;
                        j = 0;
                        break;
                    }
                    int i8 = i7 + 1;
                    z3 = z4;
                    j = 0;
                    int iConvertToDigit = dateTimeParseContext.getDecimalStyle().convertToDigit(charSequence.charAt(i7));
                    if (iConvertToDigit < 0) {
                        if (i7 >= i6) {
                            break;
                        }
                        return ~i2;
                    }
                    if (i8 - i2 > 18) {
                        if (bigIntegerDivide == null) {
                            bigIntegerDivide = BigInteger.valueOf(j2);
                        }
                        bigIntegerDivide = bigIntegerDivide.multiply(BigInteger.TEN).add(BigInteger.valueOf(iConvertToDigit));
                    } else {
                        j2 = (j2 * 10) + ((long) iConvertToDigit);
                    }
                    i7 = i8;
                    z4 = z3;
                }
                int i9 = this.subsequentWidth;
                if (i9 <= 0 || i4 != 0) {
                    i3 = i7;
                    break;
                }
                iMax = Math.max(i5, (i7 - i2) - i9);
                i4++;
                z4 = z3;
            }
            if (z) {
                if (bigIntegerDivide != null) {
                    if (bigIntegerDivide.equals(BigInteger.ZERO) && dateTimeParseContext.isStrict()) {
                        return ~(i2 - 1);
                    }
                    bigIntegerDivide = bigIntegerDivide.negate();
                } else {
                    if (j2 == j && dateTimeParseContext.isStrict()) {
                        return ~(i2 - 1);
                    }
                    j2 = -j2;
                }
            } else if (this.signStyle == SignStyle.EXCEEDS_PAD && dateTimeParseContext.isStrict()) {
                int i10 = i3 - i2;
                int i11 = this.minWidth;
                if (z2) {
                    if (i10 <= i11) {
                        return ~(i2 - 1);
                    }
                } else if (i10 > i11) {
                    return ~i2;
                }
            }
            long j3 = j2;
            if (bigIntegerDivide != null) {
                if (bigIntegerDivide.bitLength() > 63) {
                    bigIntegerDivide = bigIntegerDivide.divide(BigInteger.TEN);
                    i3--;
                }
                return setValue(dateTimeParseContext, bigIntegerDivide.longValue(), i2, i3);
            }
            return setValue(dateTimeParseContext, j3, i2, i3);
        }

        int setValue(DateTimeParseContext dateTimeParseContext, long j, int i, int i2) {
            return dateTimeParseContext.setParsedField(this.field, j, i, i2);
        }

        public String toString() {
            int i = this.minWidth;
            if (i == 1 && this.maxWidth == 19 && this.signStyle == SignStyle.NORMAL) {
                return "Value(" + this.field + ")";
            }
            int i2 = this.maxWidth;
            if (i == i2 && this.signStyle == SignStyle.NOT_NEGATIVE) {
                return "Value(" + this.field + "," + i + ")";
            }
            return "Value(" + this.field + "," + i + "," + i2 + "," + this.signStyle + ")";
        }
    }

    /* JADX INFO: renamed from: j$.time.format.DateTimeFormatterBuilder$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$java$time$format$SignStyle;

        static {
            int[] iArr = new int[SignStyle.values().length];
            $SwitchMap$java$time$format$SignStyle = iArr;
            try {
                iArr[SignStyle.EXCEEDS_PAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.NOT_NEGATIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static final class ReducedPrinterParser extends NumberPrinterParser {
        static final LocalDate BASE_DATE = LocalDate.of(2000, 1, 1);
        private final ChronoLocalDate baseDate;
        private final int baseValue;

        ReducedPrinterParser(TemporalField temporalField, int i, int i2, int i3, ChronoLocalDate chronoLocalDate) {
            this(temporalField, i, i2, i3, chronoLocalDate, 0);
            if (i < 1 || i > 10) {
                Base64.Decoder.AnonymousClass0.m("The minWidth must be from 1 to 10 inclusive but was ", i);
                throw null;
            }
            if (i2 < 1 || i2 > 10) {
                Base64.Decoder.AnonymousClass0.m("The maxWidth must be from 1 to 10 inclusive but was ", i);
                throw null;
            }
            if (i2 < i) {
                AnonymousClass4.m("Maximum width must exceed or equal the minimum width but ", i2, i);
                throw null;
            }
            if (chronoLocalDate == null) {
                ValueRange valueRangeRange = temporalField.range();
                long j = i3;
                if (!valueRangeRange.isValidValue(j)) {
                    ZoneId.AnonymousClass4.m("The base value must be within the range of the field");
                    throw null;
                }
                if (j + NumberPrinterParser.EXCEED_POINTS[i2] <= SieveCacheKt.NodeLinkMask) {
                    return;
                }
                Year.AnonymousClass3.m("Unable to add printer-parser as the range exceeds the capacity of an int");
                throw null;
            }
        }

        private ReducedPrinterParser(TemporalField temporalField, int i, int i2, int i3, ChronoLocalDate chronoLocalDate, int i4) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE, i4);
            this.baseValue = i3;
            this.baseDate = chronoLocalDate;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        long getValue(DateTimePrintContext dateTimePrintContext, long j) {
            long jAbs = Math.abs(j);
            int i = this.baseValue;
            if (this.baseDate != null) {
                i = Chronology.CC.from(dateTimePrintContext.getTemporal()).date(this.baseDate).get(this.field);
            }
            long j2 = i;
            if (j >= j2) {
                long j3 = NumberPrinterParser.EXCEED_POINTS[this.minWidth];
                if (j < j2 + j3) {
                    return jAbs % j3;
                }
            }
            return jAbs % NumberPrinterParser.EXCEED_POINTS[this.maxWidth];
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        int setValue(final DateTimeParseContext dateTimeParseContext, long j, final int i, final int i2) {
            ReducedPrinterParser reducedPrinterParser;
            DateTimeParseContext dateTimeParseContext2;
            final long j2;
            long j3;
            int i3 = this.baseValue;
            if (this.baseDate != null) {
                i3 = dateTimeParseContext.getEffectiveChronology().date(this.baseDate).get(this.field);
                reducedPrinterParser = this;
                j2 = j;
                dateTimeParseContext2 = dateTimeParseContext;
                dateTimeParseContext2.addChronoChangedListener(new Consumer() { // from class: j$.time.format.DateTimeFormatterBuilder.ReducedPrinterParser.0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ReducedPrinterParser.this.m8890xdf3a601e(dateTimeParseContext, j2, i, i2, (Chronology) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }
                });
            } else {
                reducedPrinterParser = this;
                dateTimeParseContext2 = dateTimeParseContext;
                j2 = j;
            }
            int i4 = i2 - i;
            int i5 = reducedPrinterParser.minWidth;
            if (i4 != i5 || j2 < 0) {
                j3 = j2;
            } else {
                long j4 = NumberPrinterParser.EXCEED_POINTS[i5];
                long j5 = i3;
                long j6 = j5 - (j5 % j4);
                long j7 = i3 > 0 ? j6 + j2 : j6 - j2;
                j3 = j7 < j5 ? j4 + j7 : j7;
            }
            return dateTimeParseContext2.setParsedField(reducedPrinterParser.field, j3, i, i2);
        }

        /* JADX INFO: renamed from: lambda$setValue$0$java-time-format-DateTimeFormatterBuilder$ReducedPrinterParser, reason: not valid java name */
        /* synthetic */ void m8890xdf3a601e(DateTimeParseContext dateTimeParseContext, long j, int i, int i2, Chronology chronology) {
            setValue(dateTimeParseContext, j, i, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public ReducedPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, -1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public ReducedPrinterParser withSubsequentWidth(int i) {
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, this.subsequentWidth + i);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            if (dateTimeParseContext.isStrict()) {
                return super.isFixedWidth(dateTimeParseContext);
            }
            return false;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public String toString() {
            return "ReducedValue(" + this.field + "," + this.minWidth + "," + this.maxWidth + "," + Objects.requireNonNullElse(this.baseDate, Integer.valueOf(this.baseValue)) + ")";
        }
    }

    static final class FractionPrinterParser extends NumberPrinterParser {
        private final boolean decimalPoint;

        /* JADX INFO: renamed from: j$.time.format.DateTimeFormatterBuilder$FractionPrinterParser$0, reason: invalid class name */
        public abstract /* synthetic */ class AnonymousClass0 {
            public static /* synthetic */ BigDecimal m(BigDecimal bigDecimal) {
                return bigDecimal.signum() == 0 ? new BigDecimal(BigInteger.ZERO, 0) : bigDecimal.stripTrailingZeros();
            }
        }

        FractionPrinterParser(TemporalField temporalField, int i, int i2, boolean z) {
            this(temporalField, i, i2, z, 0);
            Objects.requireNonNull(temporalField, "field");
            if (!temporalField.range().isFixed()) {
                ZoneId.AnonymousClass2.AnonymousClass0.m("Field must have a fixed set of values: ", temporalField);
                throw null;
            }
            if (i < 0 || i > 9) {
                Base64.Decoder.AnonymousClass0.m("Minimum width must be from 0 to 9 inclusive but was ", i);
                throw null;
            }
            if (i2 < 1 || i2 > 9) {
                Base64.Decoder.AnonymousClass0.m("Maximum width must be from 1 to 9 inclusive but was ", i2);
                throw null;
            }
            if (i2 >= i) {
                return;
            }
            AnonymousClass4.m("Maximum width must exceed or equal the minimum width but ", i2, i);
            throw null;
        }

        FractionPrinterParser(TemporalField temporalField, int i, int i2, boolean z, int i3) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE, i3);
            this.decimalPoint = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public FractionPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new FractionPrinterParser(this.field, this.minWidth, this.maxWidth, this.decimalPoint, -1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public FractionPrinterParser withSubsequentWidth(int i) {
            return new FractionPrinterParser(this.field, this.minWidth, this.maxWidth, this.decimalPoint, this.subsequentWidth + i);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            return dateTimeParseContext.isStrict() && this.minWidth == this.maxWidth && !this.decimalPoint;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser, j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            DecimalStyle decimalStyle = dateTimePrintContext.getDecimalStyle();
            BigDecimal bigDecimalConvertToFraction = convertToFraction(value.longValue());
            if (bigDecimalConvertToFraction.scale() == 0) {
                if (this.minWidth <= 0) {
                    return true;
                }
                if (this.decimalPoint) {
                    sb.append(decimalStyle.getDecimalSeparator());
                }
                for (int i = 0; i < this.minWidth; i++) {
                    sb.append(decimalStyle.getZeroDigit());
                }
                return true;
            }
            String strConvertNumberToI18N = decimalStyle.convertNumberToI18N(bigDecimalConvertToFraction.setScale(Math.min(Math.max(bigDecimalConvertToFraction.scale(), this.minWidth), this.maxWidth), RoundingMode.FLOOR).toPlainString().substring(2));
            if (this.decimalPoint) {
                sb.append(decimalStyle.getDecimalSeparator());
            }
            sb.append(strConvertNumberToI18N);
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser, j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2 = 0;
            int i3 = (dateTimeParseContext.isStrict() || isFixedWidth(dateTimeParseContext)) ? this.minWidth : 0;
            int i4 = (dateTimeParseContext.isStrict() || isFixedWidth(dateTimeParseContext)) ? this.maxWidth : 9;
            int length = charSequence.length();
            if (i != length) {
                if (this.decimalPoint) {
                    if (charSequence.charAt(i) == dateTimeParseContext.getDecimalStyle().getDecimalSeparator()) {
                        i++;
                    } else if (i3 > 0) {
                        return ~i;
                    }
                }
                int i5 = i;
                int i6 = i3 + i5;
                if (i6 > length) {
                    return ~i5;
                }
                int iMin = Math.min(i4 + i5, length);
                int i7 = i5;
                while (i7 < iMin) {
                    int i8 = i7 + 1;
                    int iConvertToDigit = dateTimeParseContext.getDecimalStyle().convertToDigit(charSequence.charAt(i7));
                    if (iConvertToDigit < 0) {
                        if (i8 >= i6) {
                            break;
                        }
                        return ~i5;
                    }
                    i2 = (i2 * 10) + iConvertToDigit;
                    i7 = i8;
                }
                return dateTimeParseContext.setParsedField(this.field, convertFromFraction(new BigDecimal(i2).movePointLeft(i7 - i5)), i5, i7);
            }
            if (i3 > 0) {
                return ~i;
            }
            return i;
        }

        private BigDecimal convertToFraction(long j) {
            ValueRange valueRangeRange = this.field.range();
            valueRangeRange.checkValidValue(j, this.field);
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(valueRangeRange.getMinimum());
            BigDecimal bigDecimalDivide = BigDecimal.valueOf(j).subtract(bigDecimalValueOf).divide(BigDecimal.valueOf(valueRangeRange.getMaximum()).subtract(bigDecimalValueOf).add(BigDecimal.ONE), 9, RoundingMode.FLOOR);
            BigDecimal bigDecimal = BigDecimal.ZERO;
            return bigDecimalDivide.compareTo(bigDecimal) == 0 ? bigDecimal : AnonymousClass0.m(bigDecimalDivide);
        }

        private long convertFromFraction(BigDecimal bigDecimal) {
            ValueRange valueRangeRange = this.field.range();
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(valueRangeRange.getMinimum());
            return bigDecimal.multiply(BigDecimal.valueOf(valueRangeRange.getMaximum()).subtract(bigDecimalValueOf).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(bigDecimalValueOf).longValueExact();
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public String toString() {
            String str = this.decimalPoint ? ",DecimalPoint" : "";
            return "Fraction(" + this.field + "," + this.minWidth + "," + this.maxWidth + str + ")";
        }
    }

    static final class TextPrinterParser implements DateTimePrinterParser {
        private final TemporalField field;
        private volatile NumberPrinterParser numberPrinterParser;
        private final DateTimeTextProvider provider;
        private final TextStyle textStyle;

        TextPrinterParser(TemporalField temporalField, TextStyle textStyle, DateTimeTextProvider dateTimeTextProvider) {
            this.field = temporalField;
            this.textStyle = textStyle;
            this.provider = dateTimeTextProvider;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            String text;
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            Chronology chronology = (Chronology) dateTimePrintContext.getTemporal().query(TemporalQueries.chronology());
            if (chronology == null || chronology == IsoChronology.INSTANCE) {
                text = this.provider.getText(this.field, value.longValue(), this.textStyle, dateTimePrintContext.getLocale());
            } else {
                text = this.provider.getText(chronology, this.field, value.longValue(), this.textStyle, dateTimePrintContext.getLocale());
            }
            if (text == null) {
                return numberPrinterParser().format(dateTimePrintContext, sb);
            }
            sb.append(text);
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            Iterator textIterator;
            DateTimeParseContext dateTimeParseContext2;
            CharSequence charSequence2;
            int i2;
            int length = charSequence.length();
            if (i < 0 || i > length) {
                throw new IndexOutOfBoundsException();
            }
            TextStyle textStyle = dateTimeParseContext.isStrict() ? this.textStyle : null;
            Chronology effectiveChronology = dateTimeParseContext.getEffectiveChronology();
            if (effectiveChronology == null || effectiveChronology == IsoChronology.INSTANCE) {
                textIterator = this.provider.getTextIterator(this.field, textStyle, dateTimeParseContext.getLocale());
            } else {
                textIterator = this.provider.getTextIterator(effectiveChronology, this.field, textStyle, dateTimeParseContext.getLocale());
            }
            if (textIterator != null) {
                while (textIterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) textIterator.next();
                    String str = (String) entry.getKey();
                    DateTimeParseContext dateTimeParseContext3 = dateTimeParseContext;
                    CharSequence charSequence3 = charSequence;
                    int i3 = i;
                    if (dateTimeParseContext3.subSequenceEquals(str, 0, charSequence3, i3, str.length())) {
                        return dateTimeParseContext3.setParsedField(this.field, ((Long) entry.getValue()).longValue(), i3, i3 + str.length());
                    }
                    dateTimeParseContext = dateTimeParseContext3;
                    charSequence = charSequence3;
                    i = i3;
                }
                dateTimeParseContext2 = dateTimeParseContext;
                charSequence2 = charSequence;
                i2 = i;
                if (this.field == ChronoField.ERA && !dateTimeParseContext2.isStrict()) {
                    for (Era era : effectiveChronology.eras()) {
                        CharSequence charSequence4 = charSequence2;
                        String string = era.toString();
                        boolean zSubSequenceEquals = dateTimeParseContext2.subSequenceEquals(string, 0, charSequence4, i2, string.length());
                        charSequence2 = charSequence4;
                        if (zSubSequenceEquals) {
                            return dateTimeParseContext2.setParsedField(this.field, era.getValue(), i2, i2 + string.length());
                        }
                    }
                }
                if (dateTimeParseContext2.isStrict()) {
                    return ~i2;
                }
            } else {
                dateTimeParseContext2 = dateTimeParseContext;
                charSequence2 = charSequence;
                i2 = i;
            }
            return numberPrinterParser().parse(dateTimeParseContext2, charSequence2, i2);
        }

        private NumberPrinterParser numberPrinterParser() {
            if (this.numberPrinterParser == null) {
                this.numberPrinterParser = new NumberPrinterParser(this.field, 1, 19, SignStyle.NORMAL);
            }
            return this.numberPrinterParser;
        }

        public String toString() {
            TextStyle textStyle = this.textStyle;
            TextStyle textStyle2 = TextStyle.FULL;
            TemporalField temporalField = this.field;
            if (textStyle == textStyle2) {
                return "Text(" + temporalField + ")";
            }
            return "Text(" + temporalField + "," + textStyle + ")";
        }
    }

    static final class InstantPrinterParser implements DateTimePrinterParser {
        private final int fractionalDigits;

        InstantPrinterParser(int i) {
            this.fractionalDigits = i;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.INSTANT_SECONDS);
            TemporalAccessor temporal = dateTimePrintContext.getTemporal();
            ChronoField chronoField = ChronoField.NANO_OF_SECOND;
            Long lValueOf = temporal.isSupported(chronoField) ? Long.valueOf(dateTimePrintContext.getTemporal().getLong(chronoField)) : null;
            int i = 0;
            if (value == null) {
                return false;
            }
            long jLongValue = value.longValue();
            int iCheckValidIntValue = chronoField.checkValidIntValue(lValueOf != null ? lValueOf.longValue() : 0L);
            if (jLongValue >= -62167219200L) {
                long j = jLongValue - 253402300800L;
                long jM = FileTime.AnonymousClass0.m(j, DateCalculationsKt.SECONDS_PER_10000_YEARS) + 1;
                LocalDateTime localDateTimeOfEpochSecond = LocalDateTime.ofEpochSecond(FileTime.AnonymousClass2.m(j, DateCalculationsKt.SECONDS_PER_10000_YEARS) - 62167219200L, 0, ZoneOffset.UTC);
                if (jM > 0) {
                    sb.append('+');
                    sb.append(jM);
                }
                sb.append(localDateTimeOfEpochSecond);
                if (localDateTimeOfEpochSecond.getSecond() == 0) {
                    sb.append(":00");
                }
            } else {
                long j2 = jLongValue + 62167219200L;
                long j3 = j2 / DateCalculationsKt.SECONDS_PER_10000_YEARS;
                long j4 = j2 % DateCalculationsKt.SECONDS_PER_10000_YEARS;
                LocalDateTime localDateTimeOfEpochSecond2 = LocalDateTime.ofEpochSecond(j4 - 62167219200L, 0, ZoneOffset.UTC);
                int length = sb.length();
                sb.append(localDateTimeOfEpochSecond2);
                if (localDateTimeOfEpochSecond2.getSecond() == 0) {
                    sb.append(":00");
                }
                if (j3 < 0) {
                    if (localDateTimeOfEpochSecond2.getYear() == -10000) {
                        sb.replace(length, length + 2, Long.toString(j3 - 1));
                    } else if (j4 == 0) {
                        sb.insert(length, j3);
                    } else {
                        sb.insert(length + 1, Math.abs(j3));
                    }
                }
            }
            int i2 = this.fractionalDigits;
            if ((i2 < 0 && iCheckValidIntValue > 0) || i2 > 0) {
                sb.append('.');
                int i3 = 100000000;
                while (true) {
                    int i4 = this.fractionalDigits;
                    if ((i4 != -1 || iCheckValidIntValue <= 0) && ((i4 != -2 || (iCheckValidIntValue <= 0 && i % 3 == 0)) && i >= i4)) {
                        break;
                    }
                    int i5 = iCheckValidIntValue / i3;
                    sb.append((char) (i5 + 48));
                    iCheckValidIntValue -= i5 * i3;
                    i3 /= 10;
                    i++;
                }
            }
            sb.append('Z');
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int i3;
            int i4 = this.fractionalDigits;
            int i5 = 0;
            int i6 = i4 < 0 ? 0 : i4;
            if (i4 < 0) {
                i4 = 9;
            }
            DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral('T');
            ChronoField chronoField = ChronoField.HOUR_OF_DAY;
            DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral2 = dateTimeFormatterBuilderAppendLiteral.appendValue(chronoField, 2).appendLiteral(AbstractJsonLexerKt.COLON);
            ChronoField chronoField2 = ChronoField.MINUTE_OF_HOUR;
            DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral3 = dateTimeFormatterBuilderAppendLiteral2.appendValue(chronoField2, 2).appendLiteral(AbstractJsonLexerKt.COLON);
            ChronoField chronoField3 = ChronoField.SECOND_OF_MINUTE;
            DateTimeFormatterBuilder dateTimeFormatterBuilderAppendValue = dateTimeFormatterBuilderAppendLiteral3.appendValue(chronoField3, 2);
            ChronoField chronoField4 = ChronoField.NANO_OF_SECOND;
            CompositePrinterParser printerParser = dateTimeFormatterBuilderAppendValue.appendFraction(chronoField4, i6, i4, true).appendLiteral('Z').toFormatter().toPrinterParser(false);
            DateTimeParseContext dateTimeParseContextCopy = dateTimeParseContext.copy();
            int i7 = printerParser.parse(dateTimeParseContextCopy, charSequence, i);
            if (i7 < 0) {
                return i7;
            }
            long jLongValue = dateTimeParseContextCopy.getParsed(ChronoField.YEAR).longValue();
            int iIntValue = dateTimeParseContextCopy.getParsed(ChronoField.MONTH_OF_YEAR).intValue();
            int iIntValue2 = dateTimeParseContextCopy.getParsed(ChronoField.DAY_OF_MONTH).intValue();
            int iIntValue3 = dateTimeParseContextCopy.getParsed(chronoField).intValue();
            int iIntValue4 = dateTimeParseContextCopy.getParsed(chronoField2).intValue();
            Long parsed = dateTimeParseContextCopy.getParsed(chronoField3);
            Long parsed2 = dateTimeParseContextCopy.getParsed(chronoField4);
            int iIntValue5 = parsed != null ? parsed.intValue() : 0;
            int iIntValue6 = parsed2 != null ? parsed2.intValue() : 0;
            if (iIntValue3 == 24 && iIntValue4 == 0 && iIntValue5 == 0 && iIntValue6 == 0) {
                i2 = 0;
                i3 = iIntValue5;
                i5 = 1;
            } else if (iIntValue3 == 23 && iIntValue4 == 59 && iIntValue5 == 60) {
                dateTimeParseContext.setParsedLeapSecond();
                i3 = 59;
                i2 = iIntValue3;
            } else {
                i2 = iIntValue3;
                i3 = iIntValue5;
            }
            try {
                return dateTimeParseContext.setParsedField(chronoField4, iIntValue6, i, dateTimeParseContext.setParsedField(ChronoField.INSTANT_SECONDS, LocalDateTime.of(((int) jLongValue) % 10000, iIntValue, iIntValue2, i2, iIntValue4, i3, 0).plusDays(i5).toEpochSecond(ZoneOffset.UTC) + Duration.AnonymousClass2.m(jLongValue / 10000, DateCalculationsKt.SECONDS_PER_10000_YEARS), i, i7));
            } catch (RuntimeException unused) {
                return ~i;
            }
        }

        public String toString() {
            return "Instant()";
        }
    }

    static final class OffsetIdPrinterParser implements DateTimePrinterParser {
        private final String noOffsetText;
        private final int style;
        private final int type;
        static final String[] PATTERNS = {"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS", "+HHmmss", "+HH:mm:ss", "+H", "+Hmm", "+H:mm", "+HMM", "+H:MM", "+HMMss", "+H:MM:ss", "+HMMSS", "+H:MM:SS", "+Hmmss", "+H:mm:ss"};
        static final OffsetIdPrinterParser INSTANCE_ID_Z = new OffsetIdPrinterParser("+HH:MM:ss", "Z");
        static final OffsetIdPrinterParser INSTANCE_ID_ZERO = new OffsetIdPrinterParser("+HH:MM:ss", "0");

        OffsetIdPrinterParser(String str, String str2) {
            Objects.requireNonNull(str, "pattern");
            Objects.requireNonNull(str2, "noOffsetText");
            int iCheckPattern = checkPattern(str);
            this.type = iCheckPattern;
            this.style = iCheckPattern % 11;
            this.noOffsetText = str2;
        }

        private int checkPattern(String str) {
            int i = 0;
            while (true) {
                String[] strArr = PATTERNS;
                if (i < strArr.length) {
                    if (strArr[i].equals(str)) {
                        return i;
                    }
                    i++;
                } else {
                    ZoneId.AnonymousClass2.AnonymousClass0.m("Invalid zone offset pattern: ", str);
                    return 0;
                }
            }
        }

        private boolean isPaddedHour() {
            return this.type < 11;
        }

        private boolean isColon() {
            int i = this.style;
            return i > 0 && i % 2 == 0;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.OFFSET_SECONDS);
            if (value == null) {
                return false;
            }
            int iM = LocalDate.AnonymousClass0.m(value.longValue());
            if (iM == 0) {
                sb.append(this.noOffsetText);
            } else {
                int iAbs = Math.abs((iM / DateCalculationsKt.SECONDS_PER_HOUR) % 100);
                int iAbs2 = Math.abs((iM / 60) % 60);
                int iAbs3 = Math.abs(iM % 60);
                int length = sb.length();
                sb.append(iM < 0 ? "-" : Marker.ANY_NON_NULL_MARKER);
                if (isPaddedHour() || iAbs >= 10) {
                    formatZeroPad(false, iAbs, sb);
                } else {
                    sb.append((char) (iAbs + 48));
                }
                int i = this.style;
                if ((i >= 3 && i <= 8) || ((i >= 9 && iAbs3 > 0) || (i >= 1 && iAbs2 > 0))) {
                    formatZeroPad(isColon(), iAbs2, sb);
                    iAbs += iAbs2;
                    int i2 = this.style;
                    if (i2 == 7 || i2 == 8 || (i2 >= 5 && iAbs3 > 0)) {
                        formatZeroPad(isColon(), iAbs3, sb);
                        iAbs += iAbs3;
                    }
                }
                if (iAbs == 0) {
                    sb.setLength(length);
                    sb.append(this.noOffsetText);
                }
            }
            return true;
        }

        private void formatZeroPad(boolean z, int i, StringBuilder sb) {
            sb.append(z ? ":" : "");
            sb.append((char) ((i / 10) + 48));
            sb.append((char) ((i % 10) + 48));
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            DateTimeParseContext dateTimeParseContext2;
            int i2;
            CharSequence charSequence2;
            int i3;
            int i4;
            int i5;
            int i6;
            int length = charSequence.length();
            int length2 = this.noOffsetText.length();
            if (length2 != 0) {
                dateTimeParseContext2 = dateTimeParseContext;
                i2 = i;
                if (i2 == length) {
                    return ~i2;
                }
                charSequence2 = charSequence;
                if (dateTimeParseContext2.subSequenceEquals(charSequence2, i2, this.noOffsetText, 0, length2)) {
                    return dateTimeParseContext2.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i2, i2 + length2);
                }
            } else {
                if (i == length) {
                    return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i, i);
                }
                dateTimeParseContext2 = dateTimeParseContext;
                charSequence2 = charSequence;
                i2 = i;
            }
            char cCharAt = charSequence2.charAt(i2);
            if (cCharAt == '+' || cCharAt == '-') {
                int i7 = cCharAt == '-' ? -1 : 1;
                boolean zIsColon = isColon();
                boolean zIsPaddedHour = isPaddedHour();
                int[] iArr = new int[4];
                iArr[0] = i2 + 1;
                int i8 = this.type;
                if (!dateTimeParseContext2.isStrict()) {
                    if (zIsPaddedHour) {
                        if (zIsColon || (i8 == 0 && length > (i6 = i2 + 3) && charSequence2.charAt(i6) == ':')) {
                            i8 = 10;
                            zIsColon = true;
                        } else {
                            i8 = 9;
                        }
                    } else if (zIsColon || (i8 == 11 && length > (i5 = i2 + 3) && (charSequence2.charAt(i2 + 2) == ':' || charSequence2.charAt(i5) == ':'))) {
                        i8 = 21;
                        zIsColon = true;
                    } else {
                        i8 = 20;
                    }
                }
                switch (i8) {
                    case 0:
                    case 11:
                        parseHour(charSequence2, zIsPaddedHour, iArr);
                        break;
                    case 1:
                    case 2:
                    case 13:
                        parseHour(charSequence2, zIsPaddedHour, iArr);
                        parseMinute(charSequence2, zIsColon, false, iArr);
                        break;
                    case 3:
                    case 4:
                    case 15:
                        parseHour(charSequence2, zIsPaddedHour, iArr);
                        parseMinute(charSequence2, zIsColon, true, iArr);
                        break;
                    case 5:
                    case 6:
                    case 17:
                        parseHour(charSequence2, zIsPaddedHour, iArr);
                        parseMinute(charSequence2, zIsColon, true, iArr);
                        parseSecond(charSequence2, zIsColon, false, iArr);
                        break;
                    case 7:
                    case 8:
                    case 19:
                        parseHour(charSequence2, zIsPaddedHour, iArr);
                        parseMinute(charSequence2, zIsColon, true, iArr);
                        parseSecond(charSequence2, zIsColon, true, iArr);
                        break;
                    case 9:
                    case 10:
                    case 21:
                        parseHour(charSequence2, zIsPaddedHour, iArr);
                        parseOptionalMinuteSecond(charSequence2, zIsColon, iArr);
                        break;
                    case 12:
                        parseVariableWidthDigits(charSequence2, 1, 4, iArr);
                        break;
                    case 14:
                        parseVariableWidthDigits(charSequence2, 3, 4, iArr);
                        break;
                    case 16:
                        parseVariableWidthDigits(charSequence2, 3, 6, iArr);
                        break;
                    case 18:
                        parseVariableWidthDigits(charSequence2, 5, 6, iArr);
                        break;
                    case 20:
                        parseVariableWidthDigits(charSequence2, 1, 6, iArr);
                        break;
                }
                int i9 = iArr[0];
                if (i9 > 0) {
                    int i10 = iArr[1];
                    if (i10 > 23 || (i3 = iArr[2]) > 59 || (i4 = iArr[3]) > 59) {
                        Year.AnonymousClass3.m("Value out of range: Hour[0-23], Minute[0-59], Second[0-59]");
                        return 0;
                    }
                    return dateTimeParseContext2.setParsedField(ChronoField.OFFSET_SECONDS, ((long) i7) * ((((long) i10) * 3600) + (((long) i3) * 60) + ((long) i4)), i2, i9);
                }
            }
            return length2 == 0 ? dateTimeParseContext2.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i2, i2) : ~i2;
        }

        private void parseHour(CharSequence charSequence, boolean z, int[] iArr) {
            if (z) {
                if (parseDigits(charSequence, false, 1, iArr)) {
                    return;
                }
                iArr[0] = ~iArr[0];
                return;
            }
            parseVariableWidthDigits(charSequence, 1, 2, iArr);
        }

        private void parseMinute(CharSequence charSequence, boolean z, boolean z2, int[] iArr) {
            if (parseDigits(charSequence, z, 2, iArr) || !z2) {
                return;
            }
            iArr[0] = ~iArr[0];
        }

        private void parseSecond(CharSequence charSequence, boolean z, boolean z2, int[] iArr) {
            if (parseDigits(charSequence, z, 3, iArr) || !z2) {
                return;
            }
            iArr[0] = ~iArr[0];
        }

        private void parseOptionalMinuteSecond(CharSequence charSequence, boolean z, int[] iArr) {
            if (parseDigits(charSequence, z, 2, iArr)) {
                parseDigits(charSequence, z, 3, iArr);
            }
        }

        private boolean parseDigits(CharSequence charSequence, boolean z, int i, int[] iArr) {
            int i2;
            int i3 = iArr[0];
            if (i3 < 0) {
                return true;
            }
            if (z && i != 1) {
                int i4 = i3 + 1;
                if (i4 > charSequence.length() || charSequence.charAt(i3) != ':') {
                    return false;
                }
                i3 = i4;
            }
            if (i3 + 2 > charSequence.length()) {
                return false;
            }
            int i5 = i3 + 1;
            char cCharAt = charSequence.charAt(i3);
            int i6 = i3 + 2;
            char cCharAt2 = charSequence.charAt(i5);
            if (cCharAt < '0' || cCharAt > '9' || cCharAt2 < '0' || cCharAt2 > '9' || (i2 = ((cCharAt - '0') * 10) + (cCharAt2 - '0')) < 0 || i2 > 59) {
                return false;
            }
            iArr[i] = i2;
            iArr[0] = i6;
            return true;
        }

        private void parseVariableWidthDigits(CharSequence charSequence, int i, int i2, int[] iArr) {
            int i3;
            char cCharAt;
            int i4 = iArr[0];
            char[] cArr = new char[i2];
            int i5 = 0;
            int i6 = 0;
            while (i5 < i2 && (i3 = i4 + 1) <= charSequence.length() && (cCharAt = charSequence.charAt(i4)) >= '0' && cCharAt <= '9') {
                cArr[i5] = cCharAt;
                i6++;
                i5++;
                i4 = i3;
            }
            if (i6 < i) {
                iArr[0] = ~iArr[0];
                return;
            }
            switch (i6) {
                case 1:
                    iArr[1] = cArr[0] - '0';
                    break;
                case 2:
                    iArr[1] = ((cArr[0] - '0') * 10) + (cArr[1] - '0');
                    break;
                case 3:
                    iArr[1] = cArr[0] - '0';
                    iArr[2] = ((cArr[1] - '0') * 10) + (cArr[2] - '0');
                    break;
                case 4:
                    iArr[1] = ((cArr[0] - '0') * 10) + (cArr[1] - '0');
                    iArr[2] = ((cArr[2] - '0') * 10) + (cArr[3] - '0');
                    break;
                case 5:
                    iArr[1] = cArr[0] - '0';
                    iArr[2] = ((cArr[1] - '0') * 10) + (cArr[2] - '0');
                    iArr[3] = ((cArr[3] - '0') * 10) + (cArr[4] - '0');
                    break;
                case 6:
                    iArr[1] = ((cArr[0] - '0') * 10) + (cArr[1] - '0');
                    iArr[2] = ((cArr[2] - '0') * 10) + (cArr[3] - '0');
                    iArr[3] = ((cArr[4] - '0') * 10) + (cArr[5] - '0');
                    break;
            }
            iArr[0] = i4;
        }

        public String toString() {
            String strReplace = this.noOffsetText.replace("'", "''");
            return "Offset(" + PATTERNS[this.type] + ",'" + strReplace + "')";
        }
    }

    static final class LocalizedOffsetIdPrinterParser implements DateTimePrinterParser {
        private final TextStyle style;

        LocalizedOffsetIdPrinterParser(TextStyle textStyle) {
            this.style = textStyle;
        }

        private static StringBuilder appendHMS(StringBuilder sb, int i) {
            sb.append((char) ((i / 10) + 48));
            sb.append((char) ((i % 10) + 48));
            return sb;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.OFFSET_SECONDS);
            if (value == null) {
                return false;
            }
            sb.append("GMT");
            int iM = LocalDate.AnonymousClass0.m(value.longValue());
            if (iM == 0) {
                return true;
            }
            int iAbs = Math.abs((iM / DateCalculationsKt.SECONDS_PER_HOUR) % 100);
            int iAbs2 = Math.abs((iM / 60) % 60);
            int iAbs3 = Math.abs(iM % 60);
            sb.append(iM < 0 ? "-" : Marker.ANY_NON_NULL_MARKER);
            if (this.style == TextStyle.FULL) {
                appendHMS(sb, iAbs);
                sb.append(AbstractJsonLexerKt.COLON);
                appendHMS(sb, iAbs2);
                if (iAbs3 == 0) {
                    return true;
                }
                sb.append(AbstractJsonLexerKt.COLON);
                appendHMS(sb, iAbs3);
                return true;
            }
            if (iAbs >= 10) {
                sb.append((char) ((iAbs / 10) + 48));
            }
            sb.append((char) ((iAbs % 10) + 48));
            if (iAbs2 == 0 && iAbs3 == 0) {
                return true;
            }
            sb.append(AbstractJsonLexerKt.COLON);
            appendHMS(sb, iAbs2);
            if (iAbs3 == 0) {
                return true;
            }
            sb.append(AbstractJsonLexerKt.COLON);
            appendHMS(sb, iAbs3);
            return true;
        }

        int getDigit(CharSequence charSequence, int i) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return -1;
            }
            return cCharAt - '0';
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int digit;
            int i3;
            int i4;
            int i5;
            int i6;
            int length = charSequence.length();
            if (!dateTimeParseContext.subSequenceEquals(charSequence, i, "GMT", 0, 3)) {
                return ~i;
            }
            int i7 = i + 3;
            if (i7 == length) {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i, i7);
            }
            char cCharAt = charSequence.charAt(i7);
            if (cCharAt == '+') {
                i2 = 1;
            } else {
                if (cCharAt != '-') {
                    return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i, i7);
                }
                i2 = -1;
            }
            int i8 = i + 4;
            int i9 = 0;
            if (this.style == TextStyle.FULL) {
                int i10 = i + 5;
                int digit2 = getDigit(charSequence, i8);
                int i11 = i + 6;
                int digit3 = getDigit(charSequence, i10);
                if (digit2 >= 0 && digit3 >= 0) {
                    int i12 = i + 7;
                    if (charSequence.charAt(i11) == ':') {
                        digit = (digit2 * 10) + digit3;
                        int digit4 = getDigit(charSequence, i12);
                        i6 = i + 9;
                        int digit5 = getDigit(charSequence, i + 8);
                        if (digit4 < 0 || digit5 < 0) {
                            return ~i;
                        }
                        i5 = (digit4 * 10) + digit5;
                        int i13 = i + 11;
                        if (i13 < length && charSequence.charAt(i6) == ':') {
                            int digit6 = getDigit(charSequence, i + 10);
                            int digit7 = getDigit(charSequence, i13);
                            if (digit6 >= 0 && digit7 >= 0) {
                                i9 = (digit6 * 10) + digit7;
                                i6 = i + 12;
                            }
                        }
                    }
                }
                return ~i;
            }
            int i14 = i + 5;
            digit = getDigit(charSequence, i8);
            if (digit < 0) {
                return ~i;
            }
            if (i14 < length) {
                int digit8 = getDigit(charSequence, i14);
                if (digit8 >= 0) {
                    digit = (digit * 10) + digit8;
                    i14 = i + 6;
                }
                int i15 = i14 + 2;
                if (i15 < length && charSequence.charAt(i14) == ':' && i15 < length && charSequence.charAt(i14) == ':') {
                    int digit9 = getDigit(charSequence, i14 + 1);
                    int digit10 = getDigit(charSequence, i15);
                    if (digit9 >= 0 && digit10 >= 0) {
                        i5 = (digit9 * 10) + digit10;
                        int i16 = i14 + 3;
                        int i17 = i14 + 5;
                        if (i17 < length && charSequence.charAt(i16) == ':') {
                            int digit11 = getDigit(charSequence, i14 + 4);
                            int digit12 = getDigit(charSequence, i17);
                            if (digit11 >= 0 && digit12 >= 0) {
                                i9 = (digit11 * 10) + digit12;
                                i6 = i14 + 6;
                            }
                            i9 = i5;
                        }
                        i4 = i16;
                        i3 = 0;
                        i9 = i5;
                    }
                    return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, ((long) i2) * ((((long) digit) * 3600) + (((long) i9) * 60) + ((long) i3)), i, i4);
                }
            }
            i3 = 0;
            i4 = i14;
            return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, ((long) i2) * ((((long) digit) * 3600) + (((long) i9) * 60) + ((long) i3)), i, i4);
            i3 = i9;
            i4 = i6;
            i9 = i5;
            return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, ((long) i2) * ((((long) digit) * 3600) + (((long) i9) * 60) + ((long) i3)), i, i4);
        }

        public String toString() {
            return "LocalizedOffset(" + this.style + ")";
        }
    }

    static final class ZoneTextPrinterParser extends ZoneIdPrinterParser {
        private static final Map cache = new ConcurrentHashMap();
        private final Map cachedTree;
        private final Map cachedTreeCI;
        private final boolean isGeneric;
        private Set preferredZones;
        private final TextStyle textStyle;

        ZoneTextPrinterParser(TextStyle textStyle, Set set, boolean z) {
            super(TemporalQueries.zone(), "ZoneText(" + textStyle + ")");
            this.cachedTree = new HashMap();
            this.cachedTreeCI = new HashMap();
            this.textStyle = (TextStyle) Objects.requireNonNull(textStyle, "textStyle");
            this.isGeneric = z;
            if (set == null || set.size() == 0) {
                return;
            }
            this.preferredZones = new HashSet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                this.preferredZones.add(((ZoneId) it.next()).getId());
            }
        }

        private String getDisplayName(String str, int i, Locale locale) {
            String[] strArr;
            Map concurrentHashMap = null;
            if (this.textStyle == TextStyle.NARROW) {
                return null;
            }
            Map map = cache;
            SoftReference softReference = (SoftReference) map.get(str);
            if (softReference == null || (concurrentHashMap = (Map) softReference.get()) == null || (strArr = (String[]) concurrentHashMap.get(locale)) == null) {
                TimeZone timeZone = TimeZone.getTimeZone(str);
                String[] strArr2 = {str, timeZone.getDisplayName(false, 1, locale), timeZone.getDisplayName(false, 0, locale), timeZone.getDisplayName(true, 1, locale), timeZone.getDisplayName(true, 0, locale), str, str};
                if (concurrentHashMap == null) {
                    concurrentHashMap = new ConcurrentHashMap();
                }
                concurrentHashMap.put(locale, strArr2);
                map.put(str, new SoftReference(concurrentHashMap));
                strArr = strArr2;
            }
            if (i == 0) {
                return strArr[this.textStyle.zoneNameStyleIndex() + 1];
            }
            TextStyle textStyle = this.textStyle;
            if (i == 1) {
                return strArr[textStyle.zoneNameStyleIndex() + 3];
            }
            return strArr[textStyle.zoneNameStyleIndex() + 5];
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0072  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v11 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v4, types: [int] */
        /* JADX WARN: Type inference failed for: r7v0, types: [j$.time.format.DateTimeFormatterBuilder$ZoneTextPrinterParser] */
        @Override // j$.time.format.DateTimeFormatterBuilder.ZoneIdPrinterParser, j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ?? IsDaylightSavings;
            ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue(TemporalQueries.zoneId());
            if (zoneId == null) {
                return false;
            }
            String id = zoneId.getId();
            if (!(zoneId instanceof ZoneOffset)) {
                TemporalAccessor temporal = dateTimePrintContext.getTemporal();
                if (this.isGeneric) {
                    IsDaylightSavings = 2;
                } else if (temporal.isSupported(ChronoField.INSTANT_SECONDS)) {
                    IsDaylightSavings = zoneId.getRules().isDaylightSavings(Instant.from(temporal));
                } else {
                    ChronoField chronoField = ChronoField.EPOCH_DAY;
                    if (temporal.isSupported(chronoField)) {
                        ChronoField chronoField2 = ChronoField.NANO_OF_DAY;
                        if (temporal.isSupported(chronoField2)) {
                            LocalDateTime localDateTimeAtTime = LocalDate.ofEpochDay(temporal.getLong(chronoField)).atTime(LocalTime.ofNanoOfDay(temporal.getLong(chronoField2)));
                            if (zoneId.getRules().getTransition(localDateTimeAtTime) == null) {
                                IsDaylightSavings = zoneId.getRules().isDaylightSavings(localDateTimeAtTime.atZone(zoneId).toInstant());
                            } else {
                                IsDaylightSavings = 2;
                            }
                        } else {
                            IsDaylightSavings = 2;
                        }
                    } else {
                        IsDaylightSavings = 2;
                    }
                }
                String displayName = getDisplayName(id, IsDaylightSavings, dateTimePrintContext.getLocale());
                if (displayName != null) {
                    id = displayName;
                }
            }
            sb.append(id);
            return true;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.ZoneIdPrinterParser
        protected PrefixTree getTree(DateTimeParseContext dateTimeParseContext) {
            PrefixTree prefixTree;
            if (this.textStyle == TextStyle.NARROW) {
                return super.getTree(dateTimeParseContext);
            }
            Locale locale = dateTimeParseContext.getLocale();
            boolean zIsCaseSensitive = dateTimeParseContext.isCaseSensitive();
            Set availableZoneIds = ZoneRulesProvider.getAvailableZoneIds();
            int size = availableZoneIds.size();
            Map map = zIsCaseSensitive ? this.cachedTree : this.cachedTreeCI;
            Map.Entry entry = (Map.Entry) map.get(locale);
            if (entry != null && ((Integer) entry.getKey()).intValue() == size && (prefixTree = (PrefixTree) ((SoftReference) entry.getValue()).get()) != null) {
                return prefixTree;
            }
            PrefixTree prefixTreeNewTree = PrefixTree.newTree(dateTimeParseContext);
            String[][] zoneStrings = DateFormatSymbols.getInstance(locale).getZoneStrings();
            int length = zoneStrings.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String[] strArr = zoneStrings[i];
                String str = strArr[0];
                if (availableZoneIds.contains(str)) {
                    prefixTreeNewTree.add(str, str);
                    String zid = ZoneName.toZid(str, locale);
                    for (int i2 = this.textStyle == TextStyle.FULL ? 1 : 2; i2 < strArr.length; i2 += 2) {
                        prefixTreeNewTree.add(strArr[i2], zid);
                    }
                }
                i++;
            }
            if (this.preferredZones != null) {
                for (String[] strArr2 : zoneStrings) {
                    String str2 = strArr2[0];
                    if (this.preferredZones.contains(str2) && availableZoneIds.contains(str2)) {
                        for (int i3 = this.textStyle == TextStyle.FULL ? 1 : 2; i3 < strArr2.length; i3 += 2) {
                            prefixTreeNewTree.add(strArr2[i3], str2);
                        }
                    }
                }
            }
            map.put(locale, new AbstractMap.SimpleImmutableEntry(Integer.valueOf(size), new SoftReference(prefixTreeNewTree)));
            return prefixTreeNewTree;
        }
    }

    static class ZoneIdPrinterParser implements DateTimePrinterParser {
        private static volatile Map.Entry cachedPrefixTree;
        private static volatile Map.Entry cachedPrefixTreeCI;
        private final String description;
        private final TemporalQuery query;

        ZoneIdPrinterParser(TemporalQuery temporalQuery, String str) {
            this.query = temporalQuery;
            this.description = str;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue(this.query);
            if (zoneId == null) {
                return false;
            }
            sb.append(zoneId.getId());
            return true;
        }

        protected PrefixTree getTree(DateTimeParseContext dateTimeParseContext) {
            Set availableZoneIds = ZoneRulesProvider.getAvailableZoneIds();
            int size = availableZoneIds.size();
            Map.Entry simpleImmutableEntry = dateTimeParseContext.isCaseSensitive() ? cachedPrefixTree : cachedPrefixTreeCI;
            if (simpleImmutableEntry == null || ((Integer) simpleImmutableEntry.getKey()).intValue() != size) {
                synchronized (this) {
                    try {
                        simpleImmutableEntry = dateTimeParseContext.isCaseSensitive() ? cachedPrefixTree : cachedPrefixTreeCI;
                        if (simpleImmutableEntry == null || ((Integer) simpleImmutableEntry.getKey()).intValue() != size) {
                            simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry(Integer.valueOf(size), PrefixTree.newTree(availableZoneIds, dateTimeParseContext));
                            if (dateTimeParseContext.isCaseSensitive()) {
                                cachedPrefixTree = simpleImmutableEntry;
                            } else {
                                cachedPrefixTreeCI = simpleImmutableEntry;
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return (PrefixTree) simpleImmutableEntry.getValue();
        }

        /* JADX WARN: Code duplicated, block: B:42:0x009e  */
        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            char c;
            int length = charSequence.length();
            if (i > length) {
                throw new IndexOutOfBoundsException();
            }
            if (i == length) {
                return ~i;
            }
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '+' || cCharAt == '-') {
                return parseOffsetBased(dateTimeParseContext, charSequence, i, i, OffsetIdPrinterParser.INSTANCE_ID_Z);
            }
            int i2 = i + 2;
            if (length >= i2) {
                char cCharAt2 = charSequence.charAt(i + 1);
                if (dateTimeParseContext.charEquals(cCharAt, 'U') && dateTimeParseContext.charEquals(cCharAt2, 'T')) {
                    int i3 = i + 3;
                    if (length >= i3 && dateTimeParseContext.charEquals(charSequence.charAt(i2), 'C')) {
                        return parseOffsetBased(dateTimeParseContext, charSequence, i, i3, OffsetIdPrinterParser.INSTANCE_ID_ZERO);
                    }
                    return parseOffsetBased(dateTimeParseContext, charSequence, i, i2, OffsetIdPrinterParser.INSTANCE_ID_ZERO);
                }
                if (dateTimeParseContext.charEquals(cCharAt, 'G')) {
                    c = cCharAt;
                    int i4 = i + 3;
                    if (length >= i4 && dateTimeParseContext.charEquals(cCharAt2, GMTDateParser.MONTH) && dateTimeParseContext.charEquals(charSequence.charAt(i2), 'T')) {
                        int i5 = i + 4;
                        if (length >= i5 && dateTimeParseContext.charEquals(charSequence.charAt(i4), '0')) {
                            dateTimeParseContext.setParsed(ZoneId.of("GMT0"));
                            return i5;
                        }
                        return parseOffsetBased(dateTimeParseContext, charSequence, i, i4, OffsetIdPrinterParser.INSTANCE_ID_ZERO);
                    }
                } else {
                    c = cCharAt;
                }
            } else {
                c = cCharAt;
            }
            PrefixTree tree = getTree(dateTimeParseContext);
            ParsePosition parsePosition = new ParsePosition(i);
            String strMatch = tree.match(charSequence, parsePosition);
            if (strMatch == null) {
                if (!dateTimeParseContext.charEquals(c, 'Z')) {
                    return ~i;
                }
                dateTimeParseContext.setParsed(ZoneOffset.UTC);
                return i + 1;
            }
            dateTimeParseContext.setParsed(ZoneId.of(strMatch));
            return parsePosition.getIndex();
        }

        private int parseOffsetBased(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i, int i2, OffsetIdPrinterParser offsetIdPrinterParser) {
            String upperCase = charSequence.subSequence(i, i2).toString().toUpperCase();
            if (i2 >= charSequence.length()) {
                dateTimeParseContext.setParsed(ZoneId.of(upperCase));
                return i2;
            }
            if (charSequence.charAt(i2) == '0' || dateTimeParseContext.charEquals(charSequence.charAt(i2), 'Z')) {
                dateTimeParseContext.setParsed(ZoneId.of(upperCase));
                return i2;
            }
            DateTimeParseContext dateTimeParseContextCopy = dateTimeParseContext.copy();
            int i3 = offsetIdPrinterParser.parse(dateTimeParseContextCopy, charSequence, i2);
            try {
                if (i3 < 0) {
                    if (offsetIdPrinterParser == OffsetIdPrinterParser.INSTANCE_ID_Z) {
                        return ~i;
                    }
                    dateTimeParseContext.setParsed(ZoneId.of(upperCase));
                    return i2;
                }
                dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.ofTotalSeconds((int) dateTimeParseContextCopy.getParsed(ChronoField.OFFSET_SECONDS).longValue())));
                return i3;
            } catch (DateTimeException unused) {
                return ~i;
            }
        }

        public String toString() {
            return this.description;
        }
    }

    static class PrefixTree {
        protected char c0;
        protected PrefixTree child;
        protected String key;
        protected PrefixTree sibling;
        protected String value;

        protected boolean isEqual(char c, char c2) {
            return c == c2;
        }

        protected String toKey(String str) {
            return str;
        }

        private PrefixTree(String str, String str2, PrefixTree prefixTree) {
            this.key = str;
            this.value = str2;
            this.child = prefixTree;
            if (str.isEmpty()) {
                this.c0 = (char) 65535;
            } else {
                this.c0 = this.key.charAt(0);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static PrefixTree newTree(DateTimeParseContext dateTimeParseContext) {
            String str = "";
            String str2 = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            if (dateTimeParseContext.isCaseSensitive()) {
                return new PrefixTree("", null, null);
            }
            return new CI(str, str2, objArr2 == true ? 1 : 0);
        }

        public static PrefixTree newTree(Set set, DateTimeParseContext dateTimeParseContext) {
            PrefixTree prefixTreeNewTree = newTree(dateTimeParseContext);
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                prefixTreeNewTree.add0(str, str);
            }
            return prefixTreeNewTree;
        }

        public boolean add(String str, String str2) {
            return add0(str, str2);
        }

        private boolean add0(String str, String str2) {
            String key = toKey(str);
            int iPrefixLength = prefixLength(key);
            if (iPrefixLength == this.key.length()) {
                if (iPrefixLength < key.length()) {
                    String strSubstring = key.substring(iPrefixLength);
                    for (PrefixTree prefixTree = this.child; prefixTree != null; prefixTree = prefixTree.sibling) {
                        if (isEqual(prefixTree.c0, strSubstring.charAt(0))) {
                            return prefixTree.add0(strSubstring, str2);
                        }
                    }
                    PrefixTree prefixTreeNewNode = newNode(strSubstring, str2, null);
                    prefixTreeNewNode.sibling = this.child;
                    this.child = prefixTreeNewNode;
                    return true;
                }
                this.value = str2;
                return true;
            }
            PrefixTree prefixTreeNewNode2 = newNode(this.key.substring(iPrefixLength), this.value, this.child);
            this.key = key.substring(0, iPrefixLength);
            this.child = prefixTreeNewNode2;
            if (iPrefixLength < key.length()) {
                this.child.sibling = newNode(key.substring(iPrefixLength), str2, null);
                this.value = null;
            } else {
                this.value = str2;
            }
            return true;
        }

        public String match(CharSequence charSequence, ParsePosition parsePosition) {
            int index = parsePosition.getIndex();
            int length = charSequence.length();
            if (!prefixOf(charSequence, index, length)) {
                return null;
            }
            int length2 = index + this.key.length();
            PrefixTree prefixTree = this.child;
            if (prefixTree != null && length2 != length) {
                while (!isEqual(prefixTree.c0, charSequence.charAt(length2))) {
                    prefixTree = prefixTree.sibling;
                    if (prefixTree == null) {
                    }
                }
                parsePosition.setIndex(length2);
                String strMatch = prefixTree.match(charSequence, parsePosition);
                if (strMatch != null) {
                    return strMatch;
                }
            }
            parsePosition.setIndex(length2);
            return this.value;
        }

        protected PrefixTree newNode(String str, String str2, PrefixTree prefixTree) {
            return new PrefixTree(str, str2, prefixTree);
        }

        protected boolean prefixOf(CharSequence charSequence, int i, int i2) {
            boolean z = charSequence instanceof String;
            String str = this.key;
            if (z) {
                return ((String) charSequence).startsWith(str, i);
            }
            int length = str.length();
            if (length > i2 - i) {
                return false;
            }
            int i3 = 0;
            while (true) {
                int i4 = length - 1;
                if (length <= 0) {
                    return true;
                }
                int i5 = i3 + 1;
                int i6 = i + 1;
                if (!isEqual(this.key.charAt(i3), charSequence.charAt(i))) {
                    return false;
                }
                i = i6;
                length = i4;
                i3 = i5;
            }
        }

        private int prefixLength(String str) {
            int i = 0;
            while (i < str.length() && i < this.key.length() && isEqual(str.charAt(i), this.key.charAt(i))) {
                i++;
            }
            return i;
        }

        private static class CI extends PrefixTree {
            private CI(String str, String str2, PrefixTree prefixTree) {
                super(str, str2, prefixTree);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // j$.time.format.DateTimeFormatterBuilder.PrefixTree
            public CI newNode(String str, String str2, PrefixTree prefixTree) {
                return new CI(str, str2, prefixTree);
            }

            @Override // j$.time.format.DateTimeFormatterBuilder.PrefixTree
            protected boolean isEqual(char c, char c2) {
                return DateTimeParseContext.charEqualsIgnoreCase(c, c2);
            }

            @Override // j$.time.format.DateTimeFormatterBuilder.PrefixTree
            protected boolean prefixOf(CharSequence charSequence, int i, int i2) {
                int length = this.key.length();
                if (length > i2 - i) {
                    return false;
                }
                int i3 = 0;
                while (true) {
                    int i4 = length - 1;
                    if (length <= 0) {
                        return true;
                    }
                    int i5 = i3 + 1;
                    int i6 = i + 1;
                    if (!isEqual(this.key.charAt(i3), charSequence.charAt(i))) {
                        return false;
                    }
                    i = i6;
                    length = i4;
                    i3 = i5;
                }
            }
        }
    }

    static final class LocalizedPrinterParser implements DateTimePrinterParser {
        private static final ConcurrentMap FORMATTER_CACHE = new ConcurrentHashMap(16, 0.75f, 2);
        private final FormatStyle dateStyle;
        private final FormatStyle timeStyle;

        LocalizedPrinterParser(FormatStyle formatStyle, FormatStyle formatStyle2) {
            this.dateStyle = formatStyle;
            this.timeStyle = formatStyle2;
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return formatter(dateTimePrintContext.getLocale(), Chronology.CC.from(dateTimePrintContext.getTemporal())).toPrinterParser(false).format(dateTimePrintContext, sb);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            return formatter(dateTimeParseContext.getLocale(), dateTimeParseContext.getEffectiveChronology()).toPrinterParser(false).parse(dateTimeParseContext, charSequence, i);
        }

        private DateTimeFormatter formatter(Locale locale, Chronology chronology) {
            String str = chronology.getId() + "|" + locale.toString() + "|" + this.dateStyle + this.timeStyle;
            ConcurrentMap concurrentMap = FORMATTER_CACHE;
            DateTimeFormatter dateTimeFormatter = (DateTimeFormatter) concurrentMap.get(str);
            if (dateTimeFormatter != null) {
                return dateTimeFormatter;
            }
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(DateTimeFormatterBuilder.getLocalizedDateTimePattern(this.dateStyle, this.timeStyle, chronology, locale)).toFormatter(locale);
            DateTimeFormatter dateTimeFormatter2 = (DateTimeFormatter) concurrentMap.putIfAbsent(str, formatter);
            return dateTimeFormatter2 != null ? dateTimeFormatter2 : formatter;
        }

        public String toString() {
            Object obj = this.dateStyle;
            if (obj == null) {
                obj = "";
            }
            FormatStyle formatStyle = this.timeStyle;
            return "Localized(" + obj + "," + (formatStyle != null ? formatStyle : "") + ")";
        }
    }

    static final class WeekBasedFieldPrinterParser extends NumberPrinterParser {
        private char chr;
        private int count;

        WeekBasedFieldPrinterParser(char c, int i, int i2, int i3) {
            this(c, i, i2, i3, 0);
        }

        WeekBasedFieldPrinterParser(char c, int i, int i2, int i3, int i4) {
            super(null, i2, i3, SignStyle.NOT_NEGATIVE, i4);
            this.chr = c;
            this.count = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public WeekBasedFieldPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new WeekBasedFieldPrinterParser(this.chr, this.count, this.minWidth, this.maxWidth, -1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public WeekBasedFieldPrinterParser withSubsequentWidth(int i) {
            return new WeekBasedFieldPrinterParser(this.chr, this.count, this.minWidth, this.maxWidth, this.subsequentWidth + i);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser, j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return printerParser(dateTimePrintContext.getLocale()).format(dateTimePrintContext, sb);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser, j$.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            return printerParser(dateTimeParseContext.getLocale()).parse(dateTimeParseContext, charSequence, i);
        }

        private DateTimePrinterParser printerParser(Locale locale) {
            TemporalField temporalFieldWeekOfMonth;
            WeekFields weekFieldsOf = WeekFields.of(locale);
            char c = this.chr;
            if (c == 'W') {
                temporalFieldWeekOfMonth = weekFieldsOf.weekOfMonth();
            } else {
                if (c == 'Y') {
                    TemporalField temporalFieldWeekBasedYear = weekFieldsOf.weekBasedYear();
                    int i = this.count;
                    if (i == 2) {
                        return new ReducedPrinterParser(temporalFieldWeekBasedYear, 2, 2, 0, ReducedPrinterParser.BASE_DATE, this.subsequentWidth);
                    }
                    return new NumberPrinterParser(temporalFieldWeekBasedYear, i, 19, i < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD, this.subsequentWidth);
                }
                if (c == 'c' || c == 'e') {
                    temporalFieldWeekOfMonth = weekFieldsOf.dayOfWeek();
                } else if (c == 'w') {
                    temporalFieldWeekOfMonth = weekFieldsOf.weekOfWeekBasedYear();
                } else {
                    throw new IllegalStateException("unreachable");
                }
            }
            return new NumberPrinterParser(temporalFieldWeekOfMonth, this.minWidth, this.maxWidth, SignStyle.NOT_NEGATIVE, this.subsequentWidth);
        }

        @Override // j$.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public String toString() {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Localized(");
            char c = this.chr;
            if (c == 'Y') {
                int i = this.count;
                if (i == 1) {
                    sb.append("WeekBasedYear");
                } else if (i == 2) {
                    sb.append("ReducedValue(WeekBasedYear,2,2,2000-01-01)");
                } else {
                    sb.append("WeekBasedYear,");
                    sb.append(this.count);
                    sb.append(",19,");
                    sb.append(this.count < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD);
                }
            } else {
                if (c == 'W') {
                    sb.append("WeekOfMonth");
                } else if (c == 'c' || c == 'e') {
                    sb.append("DayOfWeek");
                } else if (c == 'w') {
                    sb.append("WeekOfWeekBasedYear");
                }
                sb.append(",");
                sb.append(this.count);
            }
            sb.append(")");
            return sb.toString();
        }
    }
}
