package kotlinx.datetime.format;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import kotlin.time.Instant;
import kotlinx.datetime.DateTimeFormatException;
import kotlinx.datetime.DayOfWeek;
import kotlinx.datetime.DayOfWeekKt;
import kotlinx.datetime.LocalDate;
import kotlinx.datetime.LocalDateKt;
import kotlinx.datetime.LocalDateTime;
import kotlinx.datetime.LocalTime;
import kotlinx.datetime.Month;
import kotlinx.datetime.MonthKt;
import kotlinx.datetime.OverloadMarker;
import kotlinx.datetime.TimeZoneKt;
import kotlinx.datetime.UtcOffset;
import kotlinx.datetime.YearMonth;
import kotlinx.datetime.internal.DateCalculationsKt;
import kotlinx.datetime.internal.MathJvmKt;
import kotlinx.datetime.internal.format.AppendableFormatStructure;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: DateTimeComponents.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u0081\u00012\u00020\u0001:\u0004\u0081\u0001\u0082\u0001B\u0013\b\u0000\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010y\u001a\u00020\u0017J\u0006\u0010z\u001a\u00020\u000eJ\u0006\u0010{\u001a\u00020\u0011J\u0006\u0010|\u001a\u00020\u000bJ\u0006\u0010}\u001a\u00020\u0014J\u0011\u0010~\u001a\u00020\u001a2\t\b\u0002\u0010\u007f\u001a\u00030\u0080\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R/\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#*\u0004\b\u001e\u0010\u001fR/\u0010$\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R(\u0010+\u001a\u0004\u0018\u00010*2\b\u0010)\u001a\u0004\u0018\u00010*8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R/\u00100\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b3\u0010(\u001a\u0004\b1\u0010!\"\u0004\b2\u0010#R5\u00104\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b9\u0010(\u0012\u0004\b5\u00106\u001a\u0004\b7\u0010!\"\u0004\b8\u0010#R(\u0010;\u001a\u0004\u0018\u00010:2\b\u0010)\u001a\u0004\u0018\u00010:8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R/\u0010@\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bA\u0010!\"\u0004\bB\u0010#R/\u0010E\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010(\u001a\u0004\bF\u0010!\"\u0004\bG\u0010#R/\u0010I\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010(\u001a\u0004\bJ\u0010!\"\u0004\bK\u0010#R/\u0010N\u001a\u0004\u0018\u00010M2\b\u0010\u001b\u001a\u0004\u0018\u00010M8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010S*\u0004\bO\u0010\u001fR/\u0010T\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010(\u001a\u0004\bU\u0010!\"\u0004\bV\u0010#R/\u0010X\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b[\u0010(\u001a\u0004\bY\u0010!\"\u0004\bZ\u0010#R(\u0010\\\u001a\u0004\u0018\u00010\u001c2\b\u0010)\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b]\u0010!\"\u0004\b^\u0010#R/\u0010`\u001a\u0004\u0018\u00010_2\b\u0010\u001b\u001a\u0004\u0018\u00010_8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bb\u0010c\"\u0004\bd\u0010e*\u0004\ba\u0010\u001fR/\u0010f\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bi\u0010(\u001a\u0004\bg\u0010!\"\u0004\bh\u0010#R/\u0010j\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bm\u0010(\u001a\u0004\bk\u0010!\"\u0004\bl\u0010#R/\u0010n\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bq\u0010(\u001a\u0004\bo\u0010!\"\u0004\bp\u0010#R/\u0010s\u001a\u0004\u0018\u00010r2\b\u0010\u001b\u001a\u0004\u0018\u00010r8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bu\u0010v\"\u0004\bw\u0010x*\u0004\bt\u0010\u001f¨\u0006\u0083\u0001"}, d2 = {"Lkotlinx/datetime/format/DateTimeComponents;", "", "contents", "Lkotlinx/datetime/format/DateTimeComponentsContents;", "<init>", "(Lkotlinx/datetime/format/DateTimeComponentsContents;)V", "getContents$kotlinx_datetime", "()Lkotlinx/datetime/format/DateTimeComponentsContents;", "setTime", "", "localTime", "Lkotlinx/datetime/LocalTime;", "setYearMonth", "yearMonth", "Lkotlinx/datetime/YearMonth;", "setDate", "localDate", "Lkotlinx/datetime/LocalDate;", "setDateTime", "localDateTime", "Lkotlinx/datetime/LocalDateTime;", "setOffset", "utcOffset", "Lkotlinx/datetime/UtcOffset;", "setDateTimeOffset", "instant", "Lkotlin/time/Instant;", "<set-?>", "", "year", "getYear$delegate", "(Lkotlinx/datetime/format/DateTimeComponents;)Ljava/lang/Object;", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "monthNumber", "getMonthNumber", "setMonthNumber", "monthNumber$delegate", "Lkotlinx/datetime/format/TwoDigitNumber;", ES6Iterator.VALUE_PROPERTY, "Lkotlinx/datetime/Month;", "month", "getMonth", "()Lkotlinx/datetime/Month;", "setMonth", "(Lkotlinx/datetime/Month;)V", "day", "getDay", "setDay", "day$delegate", "dayOfMonth", "getDayOfMonth$annotations", "()V", "getDayOfMonth", "setDayOfMonth", "dayOfMonth$delegate", "Lkotlinx/datetime/DayOfWeek;", "dayOfWeek", "getDayOfWeek", "()Lkotlinx/datetime/DayOfWeek;", "setDayOfWeek", "(Lkotlinx/datetime/DayOfWeek;)V", "dayOfYear", "getDayOfYear", "setDayOfYear", "dayOfYear$delegate", "Lkotlinx/datetime/format/ThreeDigitNumber;", "hour", "getHour", "setHour", "hour$delegate", "hourOfAmPm", "getHourOfAmPm", "setHourOfAmPm", "hourOfAmPm$delegate", "Lkotlinx/datetime/format/AmPmMarker;", "amPm", "getAmPm$delegate", "getAmPm", "()Lkotlinx/datetime/format/AmPmMarker;", "setAmPm", "(Lkotlinx/datetime/format/AmPmMarker;)V", "minute", "getMinute", "setMinute", "minute$delegate", "second", "getSecond", "setSecond", "second$delegate", "nanosecond", "getNanosecond", "setNanosecond", "", "offsetIsNegative", "getOffsetIsNegative$delegate", "getOffsetIsNegative", "()Ljava/lang/Boolean;", "setOffsetIsNegative", "(Ljava/lang/Boolean;)V", "offsetHours", "getOffsetHours", "setOffsetHours", "offsetHours$delegate", "offsetMinutesOfHour", "getOffsetMinutesOfHour", "setOffsetMinutesOfHour", "offsetMinutesOfHour$delegate", "offsetSecondsOfMinute", "getOffsetSecondsOfMinute", "setOffsetSecondsOfMinute", "offsetSecondsOfMinute$delegate", "", "timeZoneId", "getTimeZoneId$delegate", "getTimeZoneId", "()Ljava/lang/String;", "setTimeZoneId", "(Ljava/lang/String;)V", "toUtcOffset", "toYearMonth", "toLocalDate", "toLocalTime", "toLocalDateTime", "toInstantUsingOffset", "youShallNotPass", "Lkotlinx/datetime/OverloadMarker;", "Companion", "Formats", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DateTimeComponents {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "monthNumber", "getMonthNumber()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "day", "getDay()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "dayOfMonth", "getDayOfMonth()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "dayOfYear", "getDayOfYear()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "hour", "getHour()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "hourOfAmPm", "getHourOfAmPm()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "minute", "getMinute()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "second", "getSecond()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "offsetHours", "getOffsetHours()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "offsetMinutesOfHour", "getOffsetMinutesOfHour()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DateTimeComponents.class, "offsetSecondsOfMinute", "getOffsetSecondsOfMinute()Ljava/lang/Integer;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DateTimeComponentsContents contents;

    /* JADX INFO: renamed from: day$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber day;

    /* JADX INFO: renamed from: dayOfMonth$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber dayOfMonth;

    /* JADX INFO: renamed from: dayOfYear$delegate, reason: from kotlin metadata */
    private final ThreeDigitNumber dayOfYear;

    /* JADX INFO: renamed from: hour$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber hour;

    /* JADX INFO: renamed from: hourOfAmPm$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber hourOfAmPm;

    /* JADX INFO: renamed from: minute$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber minute;

    /* JADX INFO: renamed from: monthNumber$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber monthNumber;

    /* JADX INFO: renamed from: offsetHours$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber offsetHours;

    /* JADX INFO: renamed from: offsetMinutesOfHour$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber offsetMinutesOfHour;

    /* JADX INFO: renamed from: offsetSecondsOfMinute$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber offsetSecondsOfMinute;

    /* JADX INFO: renamed from: second$delegate, reason: from kotlin metadata */
    private final TwoDigitNumber second;

    /* JADX WARN: Multi-variable type inference failed */
    public DateTimeComponents() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Deprecated(message = "Use 'day' instead", replaceWith = @ReplaceWith(expression = "day", imports = {}))
    public static /* synthetic */ void getDayOfMonth$annotations() {
    }

    /* JADX INFO: compiled from: DateTimeComponents.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Lkotlinx/datetime/format/DateTimeComponents$Companion;", "", "<init>", "()V", "Format", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/format/DateTimeComponents;", "block", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDateTimeComponents;", "", "Lkotlin/ExtensionFunctionType;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DateTimeFormat<DateTimeComponents> Format(Function1<? super DateTimeFormatBuilder.WithDateTimeComponents, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            DateTimeComponentsFormat.Builder builder = new DateTimeComponentsFormat.Builder(new AppendableFormatStructure());
            block.invoke(builder);
            return new DateTimeComponentsFormat(builder.build());
        }
    }

    public DateTimeComponents(DateTimeComponentsContents contents) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        this.contents = contents;
        contents.getDate();
        final IncompleteLocalDate date = contents.getDate();
        this.monthNumber = new TwoDigitNumber(new MutablePropertyReference0Impl(date) { // from class: kotlinx.datetime.format.DateTimeComponents$monthNumber$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteLocalDate) this.receiver).getMonthNumber();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteLocalDate) this.receiver).setMonthNumber((Integer) obj);
            }
        });
        final IncompleteLocalDate date2 = contents.getDate();
        this.day = new TwoDigitNumber(new MutablePropertyReference0Impl(date2) { // from class: kotlinx.datetime.format.DateTimeComponents$day$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteLocalDate) this.receiver).getDay();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteLocalDate) this.receiver).setDay((Integer) obj);
            }
        });
        final IncompleteLocalDate date3 = contents.getDate();
        this.dayOfMonth = new TwoDigitNumber(new MutablePropertyReference0Impl(date3) { // from class: kotlinx.datetime.format.DateTimeComponents$dayOfMonth$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteLocalDate) this.receiver).getDay();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteLocalDate) this.receiver).setDay((Integer) obj);
            }
        });
        final IncompleteLocalDate date4 = contents.getDate();
        this.dayOfYear = new ThreeDigitNumber(new MutablePropertyReference0Impl(date4) { // from class: kotlinx.datetime.format.DateTimeComponents$dayOfYear$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteLocalDate) this.receiver).getDayOfYear();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteLocalDate) this.receiver).setDayOfYear((Integer) obj);
            }
        });
        final IncompleteLocalTime time = contents.getTime();
        this.hour = new TwoDigitNumber(new MutablePropertyReference0Impl(time) { // from class: kotlinx.datetime.format.DateTimeComponents$hour$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteLocalTime) this.receiver).getHour();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteLocalTime) this.receiver).setHour((Integer) obj);
            }
        });
        final IncompleteLocalTime time2 = contents.getTime();
        this.hourOfAmPm = new TwoDigitNumber(new MutablePropertyReference0Impl(time2) { // from class: kotlinx.datetime.format.DateTimeComponents$hourOfAmPm$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteLocalTime) this.receiver).getHourOfAmPm();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteLocalTime) this.receiver).setHourOfAmPm((Integer) obj);
            }
        });
        contents.getTime();
        final IncompleteLocalTime time3 = contents.getTime();
        this.minute = new TwoDigitNumber(new MutablePropertyReference0Impl(time3) { // from class: kotlinx.datetime.format.DateTimeComponents$minute$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteLocalTime) this.receiver).getMinute();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteLocalTime) this.receiver).setMinute((Integer) obj);
            }
        });
        final IncompleteLocalTime time4 = contents.getTime();
        this.second = new TwoDigitNumber(new MutablePropertyReference0Impl(time4) { // from class: kotlinx.datetime.format.DateTimeComponents$second$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteLocalTime) this.receiver).getSecond();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteLocalTime) this.receiver).setSecond((Integer) obj);
            }
        });
        contents.getOffset();
        final IncompleteUtcOffset offset = contents.getOffset();
        this.offsetHours = new TwoDigitNumber(new MutablePropertyReference0Impl(offset) { // from class: kotlinx.datetime.format.DateTimeComponents$offsetHours$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteUtcOffset) this.receiver).getOffsetHours();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteUtcOffset) this.receiver).setOffsetHours((Integer) obj);
            }
        });
        final IncompleteUtcOffset offset2 = contents.getOffset();
        this.offsetMinutesOfHour = new TwoDigitNumber(new MutablePropertyReference0Impl(offset2) { // from class: kotlinx.datetime.format.DateTimeComponents$offsetMinutesOfHour$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteUtcOffset) this.receiver).getOffsetMinutesOfHour();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteUtcOffset) this.receiver).setOffsetMinutesOfHour((Integer) obj);
            }
        });
        final IncompleteUtcOffset offset3 = contents.getOffset();
        this.offsetSecondsOfMinute = new TwoDigitNumber(new MutablePropertyReference0Impl(offset3) { // from class: kotlinx.datetime.format.DateTimeComponents$offsetSecondsOfMinute$2
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((IncompleteUtcOffset) this.receiver).getOffsetSecondsOfMinute();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((IncompleteUtcOffset) this.receiver).setOffsetSecondsOfMinute((Integer) obj);
            }
        });
    }

    public /* synthetic */ DateTimeComponents(DateTimeComponentsContents dateTimeComponentsContents, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            dateTimeComponentsContents = new DateTimeComponentsContents(null, null, null, null, 15, null);
        }
        this(dateTimeComponentsContents);
    }

    /* JADX INFO: renamed from: getContents$kotlinx_datetime, reason: from getter */
    public final DateTimeComponentsContents getContents() {
        return this.contents;
    }

    /* JADX INFO: compiled from: DateTimeComponents.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lkotlinx/datetime/format/DateTimeComponents$Formats;", "", "<init>", "()V", "ISO_DATE_TIME_OFFSET", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/format/DateTimeComponents;", "getISO_DATE_TIME_OFFSET", "()Lkotlinx/datetime/format/DateTimeFormat;", "RFC_1123", "getRFC_1123", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Formats {
        public static final Formats INSTANCE = new Formats();
        private static final DateTimeFormat<DateTimeComponents> ISO_DATE_TIME_OFFSET = DateTimeComponents.INSTANCE.Format(new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET$lambda$5((DateTimeFormatBuilder.WithDateTimeComponents) obj);
            }
        });
        private static final DateTimeFormat<DateTimeComponents> RFC_1123 = DateTimeComponents.INSTANCE.Format(new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DateTimeComponents.Formats.RFC_1123$lambda$14((DateTimeFormatBuilder.WithDateTimeComponents) obj);
            }
        });

        private Formats() {
        }

        public final DateTimeFormat<DateTimeComponents> getISO_DATE_TIME_OFFSET() {
            return ISO_DATE_TIME_OFFSET;
        }

        static final Unit ISO_DATE_TIME_OFFSET$lambda$5(DateTimeFormatBuilder.WithDateTimeComponents Format) {
            Intrinsics.checkNotNullParameter(Format, "$this$Format");
            Format.date(LocalDateFormatKt.getISO_DATE());
            DateTimeFormatBuilder.WithDateTimeComponents withDateTimeComponents = Format;
            DateTimeFormatBuilderKt.alternativeParsing(withDateTimeComponents, new Function1[]{new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET$lambda$5$lambda$0((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            }}, new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET$lambda$5$lambda$1((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            });
            DateTimeFormatBuilder.WithDateTimeComponents withDateTimeComponents2 = Format;
            DateTimeFormatBuilder.WithTime.CC.hour$default(withDateTimeComponents2, null, 1, null);
            DateTimeFormatBuilderKt.m10570char(withDateTimeComponents, AbstractJsonLexerKt.COLON);
            DateTimeFormatBuilder.WithTime.CC.minute$default(withDateTimeComponents2, null, 1, null);
            DateTimeFormatBuilderKt.m10570char(withDateTimeComponents, AbstractJsonLexerKt.COLON);
            DateTimeFormatBuilder.WithTime.CC.second$default(withDateTimeComponents2, null, 1, null);
            DateTimeFormatBuilderKt.optional$default(withDateTimeComponents, null, new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET$lambda$5$lambda$2((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            }, 1, null);
            DateTimeFormatBuilderKt.alternativeParsing(withDateTimeComponents, new Function1[]{new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET$lambda$5$lambda$3((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            }}, new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET$lambda$5$lambda$4((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit ISO_DATE_TIME_OFFSET$lambda$5$lambda$0(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            DateTimeFormatBuilderKt.m10570char(alternativeParsing, 't');
            return Unit.INSTANCE;
        }

        static final Unit ISO_DATE_TIME_OFFSET$lambda$5$lambda$1(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            DateTimeFormatBuilderKt.m10570char(alternativeParsing, 'T');
            return Unit.INSTANCE;
        }

        static final Unit ISO_DATE_TIME_OFFSET$lambda$5$lambda$2(DateTimeFormatBuilder.WithDateTimeComponents optional) {
            Intrinsics.checkNotNullParameter(optional, "$this$optional");
            DateTimeFormatBuilderKt.m10570char(optional, '.');
            optional.secondFraction(1, 9);
            return Unit.INSTANCE;
        }

        static final Unit ISO_DATE_TIME_OFFSET$lambda$5$lambda$3(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            DateTimeFormatBuilder.WithUtcOffset.CC.offsetHours$default(alternativeParsing, null, 1, null);
            return Unit.INSTANCE;
        }

        static final Unit ISO_DATE_TIME_OFFSET$lambda$5$lambda$4(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            alternativeParsing.offset(UtcOffset.Formats.INSTANCE.getISO());
            return Unit.INSTANCE;
        }

        public final DateTimeFormat<DateTimeComponents> getRFC_1123() {
            return RFC_1123;
        }

        static final Unit RFC_1123$lambda$14(DateTimeFormatBuilder.WithDateTimeComponents Format) {
            Intrinsics.checkNotNullParameter(Format, "$this$Format");
            DateTimeFormatBuilder.WithDateTimeComponents withDateTimeComponents = Format;
            DateTimeFormatBuilderKt.alternativeParsing(withDateTimeComponents, new Function1[]{new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.RFC_1123$lambda$14$lambda$6((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            }}, new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.RFC_1123$lambda$14$lambda$7((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            });
            Format.day(Padding.NONE);
            DateTimeFormatBuilderKt.m10570char(withDateTimeComponents, ' ');
            Format.monthName(MonthNames.INSTANCE.getENGLISH_ABBREVIATED());
            DateTimeFormatBuilderKt.m10570char(withDateTimeComponents, ' ');
            DateTimeFormatBuilder.WithYearMonth.CC.year$default(Format, null, 1, null);
            DateTimeFormatBuilderKt.m10570char(withDateTimeComponents, ' ');
            DateTimeFormatBuilder.WithDateTimeComponents withDateTimeComponents2 = Format;
            DateTimeFormatBuilder.WithTime.CC.hour$default(withDateTimeComponents2, null, 1, null);
            DateTimeFormatBuilderKt.m10570char(withDateTimeComponents, AbstractJsonLexerKt.COLON);
            DateTimeFormatBuilder.WithTime.CC.minute$default(withDateTimeComponents2, null, 1, null);
            DateTimeFormatBuilderKt.alternativeParsing(withDateTimeComponents, new Function1[]{new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.RFC_1123$lambda$14$lambda$8((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            }}, new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.RFC_1123$lambda$14$lambda$9((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            });
            Format.chars(Stream.ID_UNKNOWN);
            DateTimeFormatBuilderKt.alternativeParsing(withDateTimeComponents, new Function1[]{new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.RFC_1123$lambda$14$lambda$10((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            }, new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.RFC_1123$lambda$14$lambda$11((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            }}, new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.RFC_1123$lambda$14$lambda$13((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit RFC_1123$lambda$14$lambda$6(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            return Unit.INSTANCE;
        }

        static final Unit RFC_1123$lambda$14$lambda$7(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            alternativeParsing.dayOfWeek(DayOfWeekNames.INSTANCE.getENGLISH_ABBREVIATED());
            alternativeParsing.chars(", ");
            return Unit.INSTANCE;
        }

        static final Unit RFC_1123$lambda$14$lambda$8(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            return Unit.INSTANCE;
        }

        static final Unit RFC_1123$lambda$14$lambda$9(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            DateTimeFormatBuilderKt.m10570char(alternativeParsing, AbstractJsonLexerKt.COLON);
            DateTimeFormatBuilder.WithTime.CC.second$default(alternativeParsing, null, 1, null);
            return Unit.INSTANCE;
        }

        static final Unit RFC_1123$lambda$14$lambda$10(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            alternativeParsing.chars("UT");
            return Unit.INSTANCE;
        }

        static final Unit RFC_1123$lambda$14$lambda$11(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            alternativeParsing.chars("Z");
            return Unit.INSTANCE;
        }

        static final Unit RFC_1123$lambda$14$lambda$13(DateTimeFormatBuilder.WithDateTimeComponents alternativeParsing) {
            Intrinsics.checkNotNullParameter(alternativeParsing, "$this$alternativeParsing");
            DateTimeFormatBuilderKt.optional(alternativeParsing, "GMT", new Function1() { // from class: kotlinx.datetime.format.DateTimeComponents$Formats$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateTimeComponents.Formats.RFC_1123$lambda$14$lambda$13$lambda$12((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit RFC_1123$lambda$14$lambda$13$lambda$12(DateTimeFormatBuilder.WithDateTimeComponents optional) {
            Intrinsics.checkNotNullParameter(optional, "$this$optional");
            optional.offset(UtcOffset.Formats.INSTANCE.getFOUR_DIGITS());
            return Unit.INSTANCE;
        }
    }

    public final void setTime(LocalTime localTime) {
        Intrinsics.checkNotNullParameter(localTime, "localTime");
        this.contents.getTime().populateFrom(localTime);
    }

    public final void setYearMonth(YearMonth yearMonth) {
        Intrinsics.checkNotNullParameter(yearMonth, "yearMonth");
        this.contents.getDate().getYearMonth().populateFrom(yearMonth);
    }

    public final void setDate(LocalDate localDate) {
        Intrinsics.checkNotNullParameter(localDate, "localDate");
        this.contents.getDate().populateFrom(localDate);
    }

    public final void setDateTime(LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "localDateTime");
        this.contents.getDate().populateFrom(localDateTime.getDate());
        this.contents.getTime().populateFrom(localDateTime.getTime());
    }

    public final void setOffset(UtcOffset utcOffset) {
        Intrinsics.checkNotNullParameter(utcOffset, "utcOffset");
        this.contents.getOffset().populateFrom(utcOffset);
    }

    public final void setDateTimeOffset(Instant instant, UtcOffset utcOffset) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        Intrinsics.checkNotNullParameter(utcOffset, "utcOffset");
        setDateTime(TimeZoneKt.toLocalDateTime(Instant.INSTANCE.fromEpochSeconds(instant.getEpochSeconds() % DateCalculationsKt.SECONDS_PER_10000_YEARS, instant.getNanosecondsOfSecond()), utcOffset));
        setOffset(utcOffset);
        Integer year = getYear();
        Intrinsics.checkNotNull(year);
        setYear(Integer.valueOf(year.intValue() + ((int) ((instant.getEpochSeconds() / DateCalculationsKt.SECONDS_PER_10000_YEARS) * 10000))));
    }

    public final void setDateTimeOffset(LocalDateTime localDateTime, UtcOffset utcOffset) {
        Intrinsics.checkNotNullParameter(localDateTime, "localDateTime");
        Intrinsics.checkNotNullParameter(utcOffset, "utcOffset");
        setDateTime(localDateTime);
        setOffset(utcOffset);
    }

    public final Integer getYear() {
        return this.contents.getDate().getYear();
    }

    public final void setYear(Integer num) {
        this.contents.getDate().setYear(num);
    }

    public final Integer getMonthNumber() {
        return this.monthNumber.getValue(this, $$delegatedProperties[0]);
    }

    public final void setMonthNumber(Integer num) {
        this.monthNumber.setValue(this, $$delegatedProperties[0], num);
    }

    public final Month getMonth() {
        Integer monthNumber = getMonthNumber();
        if (monthNumber != null) {
            return MonthKt.m10569Month(monthNumber.intValue());
        }
        return null;
    }

    public final void setMonth(Month month) {
        setMonthNumber(month != null ? Integer.valueOf(MonthKt.getNumber(month)) : null);
    }

    public final Integer getDay() {
        return this.day.getValue(this, $$delegatedProperties[1]);
    }

    public final void setDay(Integer num) {
        this.day.setValue(this, $$delegatedProperties[1], num);
    }

    public final Integer getDayOfMonth() {
        return this.dayOfMonth.getValue(this, $$delegatedProperties[2]);
    }

    public final void setDayOfMonth(Integer num) {
        this.dayOfMonth.setValue(this, $$delegatedProperties[2], num);
    }

    public final DayOfWeek getDayOfWeek() {
        Integer dayOfWeek = this.contents.getDate().getDayOfWeek();
        if (dayOfWeek != null) {
            return DayOfWeekKt.m10558DayOfWeek(dayOfWeek.intValue());
        }
        return null;
    }

    public final void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.contents.getDate().setDayOfWeek(dayOfWeek != null ? Integer.valueOf(DayOfWeekKt.getIsoDayNumber(dayOfWeek)) : null);
    }

    public final Integer getDayOfYear() {
        return this.dayOfYear.getValue(this, $$delegatedProperties[3]);
    }

    public final void setDayOfYear(Integer num) {
        this.dayOfYear.setValue(this, $$delegatedProperties[3], num);
    }

    public final Integer getHour() {
        return this.hour.getValue(this, $$delegatedProperties[4]);
    }

    public final void setHour(Integer num) {
        this.hour.setValue(this, $$delegatedProperties[4], num);
    }

    public final Integer getHourOfAmPm() {
        return this.hourOfAmPm.getValue(this, $$delegatedProperties[5]);
    }

    public final void setHourOfAmPm(Integer num) {
        this.hourOfAmPm.setValue(this, $$delegatedProperties[5], num);
    }

    public final AmPmMarker getAmPm() {
        return this.contents.getTime().getAmPm();
    }

    public final void setAmPm(AmPmMarker amPmMarker) {
        this.contents.getTime().setAmPm(amPmMarker);
    }

    public final Integer getMinute() {
        return this.minute.getValue(this, $$delegatedProperties[6]);
    }

    public final void setMinute(Integer num) {
        this.minute.setValue(this, $$delegatedProperties[6], num);
    }

    public final Integer getSecond() {
        return this.second.getValue(this, $$delegatedProperties[7]);
    }

    public final void setSecond(Integer num) {
        this.second.setValue(this, $$delegatedProperties[7], num);
    }

    public final Integer getNanosecond() {
        return this.contents.getTime().getNanosecond();
    }

    public final void setNanosecond(Integer num) {
        if (num != null && !new IntRange(0, LocalDateKt.YEAR_MAX).contains(num.intValue())) {
            throw new IllegalArgumentException("Nanosecond must be in the range [0, 999_999_999].".toString());
        }
        this.contents.getTime().setNanosecond(num);
    }

    public final Boolean getOffsetIsNegative() {
        return this.contents.getOffset().getOffsetIsNegative();
    }

    public final void setOffsetIsNegative(Boolean bool) {
        this.contents.getOffset().setOffsetIsNegative(bool);
    }

    public final Integer getOffsetHours() {
        return this.offsetHours.getValue(this, $$delegatedProperties[8]);
    }

    public final void setOffsetHours(Integer num) {
        this.offsetHours.setValue(this, $$delegatedProperties[8], num);
    }

    public final Integer getOffsetMinutesOfHour() {
        return this.offsetMinutesOfHour.getValue(this, $$delegatedProperties[9]);
    }

    public final void setOffsetMinutesOfHour(Integer num) {
        this.offsetMinutesOfHour.setValue(this, $$delegatedProperties[9], num);
    }

    public final Integer getOffsetSecondsOfMinute() {
        return this.offsetSecondsOfMinute.getValue(this, $$delegatedProperties[10]);
    }

    public final void setOffsetSecondsOfMinute(Integer num) {
        this.offsetSecondsOfMinute.setValue(this, $$delegatedProperties[10], num);
    }

    public final String getTimeZoneId() {
        return this.contents.getTimeZoneId();
    }

    public final void setTimeZoneId(String str) {
        this.contents.setTimeZoneId(str);
    }

    public final UtcOffset toUtcOffset() {
        return this.contents.getOffset().toUtcOffset();
    }

    public final YearMonth toYearMonth() {
        return this.contents.getDate().getYearMonth().toYearMonth();
    }

    public final LocalDate toLocalDate() {
        return this.contents.getDate().toLocalDate();
    }

    public final LocalTime toLocalTime() {
        return this.contents.getTime().toLocalTime();
    }

    public final LocalDateTime toLocalDateTime() {
        return LocalDateKt.atTime(toLocalDate(), toLocalTime());
    }

    public static /* synthetic */ Instant toInstantUsingOffset$default(DateTimeComponents dateTimeComponents, OverloadMarker overloadMarker, int i, Object obj) {
        if ((i & 1) != 0) {
            overloadMarker = OverloadMarker.INSTANCE.getINSTANCE$kotlinx_datetime();
        }
        return dateTimeComponents.toInstantUsingOffset(overloadMarker);
    }

    public final Instant toInstantUsingOffset(OverloadMarker youShallNotPass) {
        Intrinsics.checkNotNullParameter(youShallNotPass, "youShallNotPass");
        UtcOffset utcOffset = toUtcOffset();
        LocalTime localTime = toLocalTime();
        IncompleteLocalDate incompleteLocalDateCopy2 = this.contents.getDate().copy();
        incompleteLocalDateCopy2.setYear(Integer.valueOf(((Number) YearMonthFormatKt.requireParsedField(incompleteLocalDateCopy2.getYear(), "year")).intValue() % 10000));
        try {
            Integer year = getYear();
            Intrinsics.checkNotNull(year);
            long jSafeAdd = MathJvmKt.safeAdd(MathJvmKt.safeMultiply(year.intValue() / 10000, DateCalculationsKt.SECONDS_PER_10000_YEARS), ((incompleteLocalDateCopy2.toLocalDate().m10565toEpochDays() * 86400) + ((long) localTime.toSecondOfDay())) - ((long) utcOffset.getTotalSeconds()));
            Instant.Companion companion = Instant.INSTANCE;
            Integer nanosecond = getNanosecond();
            Instant instantFromEpochSeconds = companion.fromEpochSeconds(jSafeAdd, nanosecond != null ? nanosecond.intValue() : 0);
            if (instantFromEpochSeconds.getEpochSeconds() == jSafeAdd) {
                return instantFromEpochSeconds;
            }
            throw new DateTimeFormatException("The parsed date is outside the range representable by Instant");
        } catch (ArithmeticException e) {
            throw new DateTimeFormatException("The parsed date is outside the range representable by Instant", e);
        }
    }
}
