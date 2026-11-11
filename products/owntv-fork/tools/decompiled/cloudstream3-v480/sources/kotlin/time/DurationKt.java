package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.datetime.internal.DateCalculationsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Duration.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b-\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0005\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0007\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\u008a\u0004¢\u0006\u0004\b\f\u0010\r\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\u008a\u0004¢\u0006\u0004\b\u000e\u0010\u000f\u001a)\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0016\u001a'\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0019\u001a/\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u001c\u001a\u0016\u0010\u001d\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u000e\u0010\u001f\u001a\u00020\u0014*\u00020\u0006H\u0083\u0088\u0004\u001a\u000e\u0010 \u001a\u00020\u0014*\u00020\u0006H\u0083\u0088\u0004\u001a\u001a\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0083\u0088\u0004\u001a&\u0010$\u001a\u00020\u0006*\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0080\u0004\u001a\u0016\u0010&\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0080\u0004\u001a!\u0010'\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010(\u001a\u00020\u0012H\u0083\u0088\u0004¢\u0006\u0002\u0010)\u001a'\u0010*\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u000e\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010,H\u0082\u0088\u0004¢\u0006\u0004\b-\u0010.\u001a\u0018\u0010/\u001a\u0004\u0018\u00010\u0004*\u00020\u00122\u0006\u00100\u001a\u00020\u0002H\u0082\u0080\u0004\u001a\u0018\u00101\u001a\u0004\u0018\u00010\u0004*\u00020\u00122\u0006\u00100\u001a\u00020\u0002H\u0082\u0080\u0004\u001a\u000e\u0010=\u001a\u00020\u0006*\u00020\u0006H\u0083\u0088\u0004\u001a\u000e\u0010=\u001a\u00020\u0002*\u00020\u0002H\u0083\u0088\u0004\u001a\u0012\u0010J\u001a\u00020\u00062\u0006\u0010K\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u0012\u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u0017\u0010N\u001a\u00020\u00012\u0006\u0010O\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u0017\u0010Q\u001a\u00020\u00012\u0006\u0010R\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u001f\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002H\u0082\u0080\u0004¢\u0006\u0002\u0010V\u001a\u0017\u0010W\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u0017\u0010X\u001a\u00020\u00012\u0006\u0010M\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\"\u001f\u00102\u001a\u00020\b*\u00020\u00048BX\u0082\u0084\b¢\u0006\f\u0012\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0019\u00107\u001a\u00020\u0006*\u00020\u00048BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b8\u00109\"\u0019\u0010:\u001a\u00020\u0002*\u00020\u00048BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b;\u0010<\"\u000f\u0010>\u001a\u00020\u0002X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010?\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010@\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010A\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010B\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010C\u001a\u00020\u0006X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010D\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010E\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010F\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010G\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010H\u001a\u00020\u0012X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010I\u001a\u00020\u0002X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"toDuration", "Lkotlin/time/Duration;", "", "unit", "Lkotlin/time/DurationUnit;", "(ILkotlin/time/DurationUnit;)J", "", "(JLkotlin/time/DurationUnit;)J", "", "(DLkotlin/time/DurationUnit;)J", "times", TypedValues.TransitionType.S_DURATION, "times-mvk6XK0", "(IJ)J", "times-kIfJnKk", "(DJ)J", "parseDuration", ES6Iterator.VALUE_PROPERTY, "", "strictIso", "", "throwException", "(Ljava/lang/String;ZZ)J", "parseIsoStringFormat", "startIndex", "(Ljava/lang/String;IZ)J", "parseDefaultStringFormat", "hasSign", "(Ljava/lang/String;IZZ)J", "addMillisWithoutOverflow", "other", "isInfiniteMillis", "isFiniteMillis", "sameSign", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "parseFractionFallback", "endIndex", "fractionDigitsToNanos", "handleError", ContentType.Message.TYPE, "(ZLjava/lang/String;)J", "onInvalid", "block", "Lkotlin/Function0;", "onInvalid-ge6A_vg", "(JLkotlin/jvm/functions/Function0;)Lkotlin/time/Duration;", "defaultDurationUnitByShortNameOrNull", "start", "isoDurationUnitByShortNameOrNull", "fractionMultiplier", "getFractionMultiplier$annotations", "(Lkotlin/time/DurationUnit;)V", "getFractionMultiplier", "(Lkotlin/time/DurationUnit;)D", "fallbackFractionMultiplier", "getFallbackFractionMultiplier", "(Lkotlin/time/DurationUnit;)J", "shortNameLength", "getShortNameLength", "(Lkotlin/time/DurationUnit;)I", "multiplyBy10", "NANOS_IN_MILLIS", "MICROS_IN_MILLIS", "NANOS_IN_MICROS", "MAX_NANOS", "MAX_MILLIS", "MAX_NANOS_IN_MILLIS", "MILLIS_IN_SECOND", "MILLIS_IN_MINUTE", "MILLIS_IN_HOUR", "MILLIS_IN_DAY", "INFINITY_STRING", "FRACTION_LIMIT", "nanosToMillis", "nanos", "millisToNanos", "millis", "durationOfNanos", "normalNanos", "(J)J", "durationOfMillis", "normalMillis", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfNanosNormalized", "durationOfMillisNormalized", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DurationKt {
    private static final int FRACTION_LIMIT = 15;
    private static final String INFINITY_STRING = "Infinity";
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final long MICROS_IN_MILLIS = 1000;
    public static final long MILLIS_IN_DAY = 86400000;
    public static final long MILLIS_IN_HOUR = 3600000;
    public static final long MILLIS_IN_MINUTE = 60000;
    public static final long MILLIS_IN_SECOND = 1000;
    public static final long NANOS_IN_MICROS = 1000;
    public static final int NANOS_IN_MILLIS = 1000000;

    /* JADX INFO: compiled from: Duration.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DurationUnit.values().length];
            try {
                iArr[DurationUnit.MICROSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DurationUnit.NANOSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DurationUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DurationUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DurationUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DurationUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DurationUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static /* synthetic */ void getFractionMultiplier$annotations(DurationUnit durationUnit) {
    }

    private static final boolean isFiniteMillis(long j) {
        return -4611686018427387903L < j && j < 4611686018427387903L;
    }

    private static final boolean isInfiniteMillis(long j) {
        return j == 4611686018427387903L || j == -4611686018427387903L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long millisToNanos(long j) {
        return j * 1000000;
    }

    private static final int multiplyBy10(int i) {
        return (i << 3) + (i << 1);
    }

    private static final long multiplyBy10(long j) {
        return (j << 3) + (j << 1);
    }

    private static final boolean sameSign(long j, long j2) {
        return (j ^ j2) >= 0;
    }

    public static final long toDuration(int i, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (unit.compareTo(DurationUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(i, unit, DurationUnit.NANOSECONDS));
        }
        return toDuration(i, unit);
    }

    public static final long toDuration(long j, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        long jConvertDurationUnitOverflow = DurationUnitKt.convertDurationUnitOverflow(MAX_NANOS, DurationUnit.NANOSECONDS, unit);
        if ((-jConvertDurationUnitOverflow) <= j && j <= jConvertDurationUnitOverflow) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(j, unit, DurationUnit.NANOSECONDS));
        }
        if (unit.compareTo(DurationUnit.MILLISECONDS) >= 0) {
            return durationOfMillis(((long) MathKt.getSign(j)) * DurationUnitKt.convertDurationUnitToMilliseconds(Math.abs(RangesKt.coerceAtLeast(j, C.TIME_UNSET)), unit));
        }
        return durationOfMillis(RangesKt.coerceIn(DurationUnitKt.convertDurationUnit(j, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, 4611686018427387903L));
    }

    public static final long toDuration(double d, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double dConvertDurationUnit = DurationUnitKt.convertDurationUnit(d, unit, DurationUnit.NANOSECONDS);
        if (Double.isNaN(dConvertDurationUnit)) {
            throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
        }
        long jRoundToLong = MathKt.roundToLong(dConvertDurationUnit);
        if (-4611686018426999999L <= jRoundToLong && jRoundToLong < 4611686018427000000L) {
            return durationOfNanos(jRoundToLong);
        }
        return durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt.convertDurationUnit(d, unit, DurationUnit.MILLISECONDS)));
    }

    /* JADX INFO: renamed from: times-mvk6XK0, reason: not valid java name */
    private static final long m10420timesmvk6XK0(int i, long j) {
        return Duration.m10349timesUwyO8pc(j, i);
    }

    /* JADX INFO: renamed from: times-kIfJnKk, reason: not valid java name */
    private static final long m10419timeskIfJnKk(double d, long j) {
        return Duration.m10348timesUwyO8pc(j, d);
    }

    static /* synthetic */ long parseDuration$default(String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        return parseDuration(str, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long parseDuration(String str, boolean z, boolean z2) {
        int i;
        int i2;
        long defaultStringFormat;
        if (str.length() == 0) {
            if (z2) {
                throw new IllegalArgumentException("The string is empty");
            }
            return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
        }
        char cCharAt = str.charAt(0);
        if (cCharAt != '+') {
            i = cCharAt != '-' ? 0 : 1;
            i2 = i;
        } else {
            i = 0;
            i2 = 1;
        }
        boolean z3 = i2 > 0;
        if (str.length() <= i2) {
            if (z2) {
                throw new IllegalArgumentException("No components");
            }
            return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
        }
        if (str.charAt(i2) == 'P') {
            defaultStringFormat = parseIsoStringFormat(str, i2 + 1, z2);
        } else {
            if (z) {
                if (z2) {
                    throw new IllegalArgumentException("");
                }
                return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
            }
            if (StringsKt.regionMatches(str, i2, INFINITY_STRING, 0, Math.max(str.length() - i2, 8), true)) {
                defaultStringFormat = Duration.INSTANCE.m10410getINFINITEUwyO8pc();
            } else {
                defaultStringFormat = parseDefaultStringFormat(str, i2, z3, z2);
            }
        }
        return (i == 0 || Duration.m10323equalsimpl0(defaultStringFormat, Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib())) ? defaultStringFormat : Duration.m10362unaryMinusUwyO8pc(defaultStringFormat);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x006b  */
    private static final long parseIsoStringFormat(String str, int i, boolean z) {
        int i2;
        int i3;
        char c;
        String str2;
        int i4;
        char cCharAt;
        char cCharAt2;
        int i5;
        char cCharAt3;
        String str3 = "";
        int i6 = i;
        if (i6 == str.length()) {
            if (z) {
                throw new IllegalArgumentException("");
            }
            return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
        }
        DurationUnit durationUnit = null;
        long jConvertDurationUnitToMilliseconds = 0;
        long jFractionDigitsToNanos = 0;
        boolean z2 = false;
        while (i6 < str.length()) {
            char cCharAt4 = str.charAt(i6);
            if (cCharAt4 == 'T') {
                if (z2 || (i6 = i6 + 1) == str.length()) {
                    if (z) {
                        throw new IllegalArgumentException(str3);
                    }
                    return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                }
                z2 = true;
            } else {
                LongParser iso = LongParser.INSTANCE.getIso();
                if (iso.allowSign) {
                    char cCharAt5 = str.charAt(i6);
                    if (cCharAt5 == '+') {
                        i2 = i6 + 1;
                        i3 = 1;
                    } else if (cCharAt5 != '-') {
                        i2 = i6;
                        i3 = 1;
                    } else {
                        i2 = i6 + 1;
                        i3 = -1;
                    }
                } else {
                    i2 = i6;
                    i3 = 1;
                }
                while (true) {
                    c = '0';
                    if (i2 >= str.length() || str.charAt(i2) != '0') {
                        break;
                    }
                    i2++;
                }
                long j = 0;
                while (true) {
                    if (i2 >= str.length() || c > (cCharAt2 = str.charAt(i2)) || cCharAt2 >= ':') {
                        str2 = str3;
                        i4 = i2;
                        if (i4 != str.length()) {
                            if (i4 == i6 + ((cCharAt4 == '+' || cCharAt4 == '-') ? 1 : 0)) {
                            }
                        }
                        if (z) {
                            throw new IllegalArgumentException(str2);
                        }
                        return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                    }
                    int i7 = cCharAt2 - '0';
                    if (j <= iso.overflowThreshold) {
                        if (j == iso.overflowThreshold) {
                            i5 = i2;
                            if (i7 > iso.lastDigitMax) {
                            }
                        } else {
                            i5 = i2;
                        }
                        j = (j << 3) + (j << 1) + ((long) i7);
                        i2 = i5 + 1;
                        str3 = str3;
                        c = '0';
                    } else {
                        i5 = i2;
                    }
                    String str4 = str3;
                    i4 = i5;
                    while (i4 < str.length() && '0' <= (cCharAt3 = str.charAt(i4)) && cCharAt3 < ':') {
                        i4++;
                    }
                    if (i4 != str.length()) {
                        if (i4 != i6 + ((cCharAt4 == '+' || cCharAt4 == '-') ? 1 : 0)) {
                            j = iso.overflowLimit;
                            str2 = str4;
                        }
                    }
                    if (z) {
                        throw new IllegalArgumentException(str4);
                    }
                    return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                    long j2 = j;
                    if (str.charAt(i4) == '.') {
                        int i8 = i4 + 1;
                        FractionalParser fractionalParser = FractionalParser.INSTANCE;
                        int iMin = Math.min(i4 + 7, str.length());
                        int i9 = i8;
                        int i10 = 0;
                        while (i9 < iMin) {
                            char cCharAt6 = str.charAt(i9);
                            if ('0' > cCharAt6 || cCharAt6 >= ':') {
                                break;
                            }
                            i10 = (i10 << 3) + (i10 << 1) + (cCharAt6 - '0');
                            i9++;
                        }
                        for (int i11 = 0; i11 < 6 - (i9 - i8); i11++) {
                            i10 = (i10 << 1) + (i10 << 3);
                        }
                        int iMin2 = Math.min(i9 + 9, str.length());
                        int i12 = i9;
                        int i13 = 0;
                        while (i12 < iMin2) {
                            char cCharAt7 = str.charAt(i12);
                            int i14 = iMin2;
                            if ('0' > cCharAt7 || cCharAt7 >= ':') {
                                break;
                            }
                            i13 = (i13 << 3) + (i13 << 1) + (cCharAt7 - '0');
                            i12++;
                            iMin2 = i14;
                        }
                        int i15 = 9 - (i12 - i9);
                        for (int i16 = 0; i16 < i15; i16++) {
                            i13 = (i13 << 1) + (i13 << 3);
                        }
                        i4 = i12;
                        while (i4 < str.length() && '0' <= (cCharAt = str.charAt(i4)) && cCharAt < ':') {
                            i4++;
                        }
                        if (i4 == i8 || i4 == str.length() || str.charAt(i4) != 'S') {
                            if (z) {
                                throw new IllegalArgumentException(str2);
                            }
                            return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                        }
                        jFractionDigitsToNanos = ((long) i3) * fractionDigitsToNanos((((long) i10) * C.NANOS_PER_SECOND) + ((long) i13), DurationUnit.SECONDS);
                    }
                    DurationUnit durationUnitIsoDurationUnitByShortNameOrNull = isoDurationUnitByShortNameOrNull(str, i4);
                    if (durationUnitIsoDurationUnitByShortNameOrNull == null) {
                        String str5 = "Unknown duration unit short name: " + str.charAt(i4);
                        if (z) {
                            throw new IllegalArgumentException(str5);
                        }
                        return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                    }
                    if (durationUnit != null && durationUnit.compareTo(durationUnitIsoDurationUnitByShortNameOrNull) <= 0) {
                        if (z) {
                            throw new IllegalArgumentException("Unexpected order of duration components");
                        }
                        return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                    }
                    if (durationUnitIsoDurationUnitByShortNameOrNull == DurationUnit.DAYS) {
                        if (z2) {
                            if (z) {
                                throw new IllegalArgumentException(str2);
                            }
                            return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                        }
                        jConvertDurationUnitToMilliseconds = ((long) i3) * DurationUnitKt.convertDurationUnitToMilliseconds(j2, durationUnitIsoDurationUnitByShortNameOrNull);
                    } else {
                        if (!z2) {
                            if (z) {
                                throw new IllegalArgumentException(str2);
                            }
                            return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                        }
                        long jAddMillisWithoutOverflow = addMillisWithoutOverflow(jConvertDurationUnitToMilliseconds, ((long) i3) * DurationUnitKt.convertDurationUnitToMilliseconds(j2, durationUnitIsoDurationUnitByShortNameOrNull));
                        if (jAddMillisWithoutOverflow == Duration.INVALID_RAW_VALUE) {
                            if (z) {
                                throw new IllegalArgumentException(str2);
                            }
                            return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                        }
                        jConvertDurationUnitToMilliseconds = jAddMillisWithoutOverflow;
                    }
                    i6 = i4 + 1;
                    str3 = str2;
                    durationUnit = durationUnitIsoDurationUnitByShortNameOrNull;
                }
            }
        }
        return Duration.m10347plusLRDsOJo(toDuration(jConvertDurationUnitToMilliseconds, DurationUnit.MILLISECONDS), toDuration(jFractionDigitsToNanos, DurationUnit.NANOSECONDS));
    }

    private static final long parseDefaultStringFormat(String str, int i, boolean z, boolean z2) {
        boolean z3;
        int shortNameLength;
        boolean z4;
        int i2;
        long j;
        long j2;
        long jFractionDigitsToNanos;
        int i3;
        char cCharAt;
        char cCharAt2;
        char cCharAt3;
        int length = str.length();
        boolean z5 = !z;
        if (z && str.charAt(i) == '(' && str.charAt(length - 1) == ')') {
            shortNameLength = i + 1;
            length--;
            if (shortNameLength == length) {
                if (z2) {
                    throw new IllegalArgumentException("No components");
                }
                return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
            }
            z3 = true;
        } else {
            z3 = z5;
            shortNameLength = i;
        }
        DurationUnit durationUnitDefaultDurationUnitByShortNameOrNull = null;
        long jAddMillisWithoutOverflow = 0;
        long j3 = 0;
        boolean z6 = true;
        while (shortNameLength < length) {
            if (!z6 && z3) {
                while (shortNameLength < str.length() && str.charAt(shortNameLength) == ' ') {
                    shortNameLength++;
                }
            }
            LongParser longParser = LongParser.INSTANCE.getDefault();
            int i4 = (longParser.allowSign && ((cCharAt3 = str.charAt(shortNameLength)) == '+' || cCharAt3 == '-')) ? shortNameLength + 1 : shortNameLength;
            while (i4 < str.length() && str.charAt(i4) == '0') {
                i4++;
            }
            long j4 = 0;
            while (true) {
                if (i4 < str.length()) {
                    char cCharAt4 = str.charAt(i4);
                    z3 = z3;
                    if ('0' <= cCharAt4 && cCharAt4 < ':') {
                        int i5 = cCharAt4 - '0';
                        if (j4 <= longParser.overflowThreshold) {
                            long j5 = j3;
                            if (j4 != longParser.overflowThreshold || i5 <= longParser.lastDigitMax) {
                                j4 = ((long) i5) + (j4 << 3) + (j4 << 1);
                                i4++;
                                z3 = z3;
                                j3 = j5;
                            }
                        }
                        while (i4 < str.length() && '0' <= (cCharAt2 = str.charAt(i4)) && cCharAt2 < ':') {
                            i4++;
                        }
                        if (z2) {
                            throw new IllegalArgumentException("");
                        }
                        return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                    }
                } else {
                    z3 = z3;
                }
                long j6 = j3;
                if (i4 == shortNameLength || i4 == length) {
                    if (z2) {
                        throw new IllegalArgumentException("");
                    }
                    return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                }
                boolean z7 = str.charAt(i4) == '.';
                if (z7) {
                    int i6 = i4 + 1;
                    FractionalParser fractionalParser = FractionalParser.INSTANCE;
                    int iMin = Math.min(i4 + 7, str.length());
                    int i7 = i6;
                    int i8 = 0;
                    while (true) {
                        if (i7 >= iMin) {
                            z4 = z7;
                            break;
                        }
                        char cCharAt5 = str.charAt(i7);
                        z4 = z7;
                        if ('0' > cCharAt5 || cCharAt5 >= ':') {
                            break;
                        }
                        i8 = (i8 << 3) + (i8 << 1) + (cCharAt5 - '0');
                        i7++;
                        z7 = z4;
                    }
                    for (int i9 = 0; i9 < 6 - (i7 - i6); i9++) {
                        i8 = (i8 << 1) + (i8 << 3);
                    }
                    int iMin2 = Math.min(i7 + 9, str.length());
                    int i10 = i7;
                    int i11 = 0;
                    while (true) {
                        if (i10 >= iMin2) {
                            i3 = i10;
                            break;
                        }
                        int i12 = iMin2;
                        char cCharAt6 = str.charAt(i10);
                        i3 = i10;
                        if ('0' > cCharAt6 || cCharAt6 >= ':') {
                            break;
                        }
                        i11 = (i11 << 3) + (i11 << 1) + (cCharAt6 - '0');
                        i10 = i3 + 1;
                        iMin2 = i12;
                    }
                    for (int i13 = 0; i13 < 9 - (i3 - i7); i13++) {
                        i11 = (i11 << 1) + (i11 << 3);
                    }
                    int i14 = i3;
                    while (i14 < str.length() && '0' <= (cCharAt = str.charAt(i14)) && cCharAt < ':') {
                        i14++;
                    }
                    if (i14 == i6 || i14 == length) {
                        if (z2) {
                            throw new IllegalArgumentException("");
                        }
                        return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                    }
                    j = (((long) i8) * C.NANOS_PER_SECOND) + ((long) i11);
                    i2 = i4;
                    i4 = i14;
                } else {
                    z4 = z7;
                    i2 = -1;
                    j = 0;
                }
                durationUnitDefaultDurationUnitByShortNameOrNull = defaultDurationUnitByShortNameOrNull(str, i4);
                if (durationUnitDefaultDurationUnitByShortNameOrNull == null) {
                    String str2 = "Unknown duration unit short name: " + str.charAt(i4);
                    if (z2) {
                        throw new IllegalArgumentException(str2);
                    }
                    return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                }
                if (durationUnitDefaultDurationUnitByShortNameOrNull != null && durationUnitDefaultDurationUnitByShortNameOrNull.compareTo(durationUnitDefaultDurationUnitByShortNameOrNull) <= 0) {
                    if (z2) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                    }
                    return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                }
                int i15 = WhenMappings.$EnumSwitchMapping$0[durationUnitDefaultDurationUnitByShortNameOrNull.ordinal()];
                if (i15 == 1) {
                    jAddMillisWithoutOverflow += j4 / 1000;
                    if (jAddMillisWithoutOverflow <= MAX_NANOS_IN_MILLIS) {
                        j2 = (j4 % 1000) * 1000;
                        j6 = j2;
                    }
                } else if (i15 == 2) {
                    jAddMillisWithoutOverflow += j4 / 1000000;
                    j2 = j6 + (j4 % 1000000);
                    j6 = j2;
                } else {
                    jAddMillisWithoutOverflow = addMillisWithoutOverflow(jAddMillisWithoutOverflow, DurationUnitKt.convertDurationUnitToMilliseconds(j4, durationUnitDefaultDurationUnitByShortNameOrNull));
                }
                shortNameLength = getShortNameLength(durationUnitDefaultDurationUnitByShortNameOrNull) + i4;
                if (!z4) {
                    j3 = j6;
                    z6 = false;
                } else {
                    if (shortNameLength < length) {
                        if (z2) {
                            throw new IllegalArgumentException("Fractional component must be last");
                        }
                        return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
                    }
                    if (durationUnitDefaultDurationUnitByShortNameOrNull.compareTo(DurationUnit.MINUTES) >= 0 && shortNameLength - i2 > 15) {
                        jFractionDigitsToNanos = parseFractionFallback(str, i2, shortNameLength - getShortNameLength(durationUnitDefaultDurationUnitByShortNameOrNull), durationUnitDefaultDurationUnitByShortNameOrNull);
                    } else {
                        jFractionDigitsToNanos = fractionDigitsToNanos(j, durationUnitDefaultDurationUnitByShortNameOrNull);
                    }
                    z6 = false;
                    j3 = j6 + jFractionDigitsToNanos;
                }
            }
        }
        return Duration.m10347plusLRDsOJo(toDuration(jAddMillisWithoutOverflow, DurationUnit.MILLISECONDS), toDuration(j3, DurationUnit.NANOSECONDS));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long addMillisWithoutOverflow(long j, long j2) {
        if (j == 4611686018427387903L || j == -4611686018427387903L) {
            return ((-4611686018427387903L >= j2 || j2 >= 4611686018427387903L) && (j2 ^ j) < 0) ? Duration.INVALID_RAW_VALUE : j;
        }
        return (j2 == 4611686018427387903L || j2 == -4611686018427387903L) ? j2 : RangesKt.coerceIn(j + j2, -4611686018427387903L, 4611686018427387903L);
    }

    private static final long parseFractionFallback(String str, int i, int i2, DurationUnit durationUnit) {
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return MathKt.roundToLong(Double.parseDouble(strSubstring) * getFallbackFractionMultiplier(durationUnit));
    }

    private static final long fractionDigitsToNanos(long j, DurationUnit durationUnit) {
        return MathKt.roundToLong(j * getFractionMultiplier(durationUnit));
    }

    static /* synthetic */ long handleError$default(boolean z, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        if (z) {
            throw new IllegalArgumentException(str);
        }
        return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
    }

    private static final long handleError(boolean z, String str) {
        if (z) {
            throw new IllegalArgumentException(str);
        }
        return Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib();
    }

    /* JADX INFO: renamed from: onInvalid-ge6A_vg, reason: not valid java name */
    private static final Duration m10418onInvalidge6A_vg(long j, Function0<Duration> function0) {
        return Duration.m10323equalsimpl0(j, Duration.INSTANCE.m10411getINVALIDUwyO8pc$kotlin_stdlib()) ? function0.invoke() : Duration.m10316boximpl(j);
    }

    private static final DurationUnit defaultDurationUnitByShortNameOrNull(String str, int i) {
        char cCharAt = str.charAt(i);
        char cCharAt2 = i < StringsKt.getLastIndex(str) ? str.charAt(i + 1) : (char) 0;
        if (cCharAt == 'd') {
            return DurationUnit.DAYS;
        }
        if (cCharAt == 'h') {
            return DurationUnit.HOURS;
        }
        if (cCharAt == 's') {
            return DurationUnit.SECONDS;
        }
        if (cCharAt == 'u') {
            if (cCharAt2 == 's') {
                return DurationUnit.MICROSECONDS;
            }
            return null;
        }
        if (cCharAt == 'm') {
            return cCharAt2 == 's' ? DurationUnit.MILLISECONDS : DurationUnit.MINUTES;
        }
        if (cCharAt == 'n' && cCharAt2 == 's') {
            return DurationUnit.NANOSECONDS;
        }
        return null;
    }

    private static final DurationUnit isoDurationUnitByShortNameOrNull(String str, int i) {
        char cCharAt = str.charAt(i);
        if (cCharAt == 'D') {
            return DurationUnit.DAYS;
        }
        if (cCharAt == 'H') {
            return DurationUnit.HOURS;
        }
        if (cCharAt == 'M') {
            return DurationUnit.MINUTES;
        }
        if (cCharAt != 'S') {
            return null;
        }
        return DurationUnit.SECONDS;
    }

    private static final double getFractionMultiplier(DurationUnit durationUnit) {
        switch (WhenMappings.$EnumSwitchMapping$0[durationUnit.ordinal()]) {
            case 1:
                return 1.0E-12d;
            case 2:
                return 1.0E-15d;
            case 3:
                return 1.0E-9d;
            case 4:
                return 1.0E-6d;
            case 5:
                return 6.0E-5d;
            case 6:
                return 0.0036d;
            case 7:
                return 0.0864d;
            default:
                throw new IllegalStateException(("Unknown unit: " + durationUnit).toString());
        }
    }

    private static final long getFallbackFractionMultiplier(DurationUnit durationUnit) {
        int i = WhenMappings.$EnumSwitchMapping$0[durationUnit.ordinal()];
        if (i == 5) {
            return DateCalculationsKt.NANOS_PER_MINUTE;
        }
        if (i == 6) {
            return DateCalculationsKt.NANOS_PER_HOUR;
        }
        if (i == 7) {
            return DateCalculationsKt.NANOS_PER_DAY;
        }
        throw new IllegalStateException(("Invalid unit: " + durationUnit + " for fallback fraction multiplier").toString());
    }

    private static final int getShortNameLength(DurationUnit durationUnit) {
        int i = WhenMappings.$EnumSwitchMapping$0[durationUnit.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? 2 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long nanosToMillis(long j) {
        return j / 1000000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanos(long j) {
        return Duration.INSTANCE.m10409fromRawValueUwyO8pc$kotlin_stdlib(j << 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillis(long j) {
        return Duration.INSTANCE.m10409fromRawValueUwyO8pc$kotlin_stdlib((j << 1) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOf(long j, int i) {
        return Duration.INSTANCE.m10409fromRawValueUwyO8pc$kotlin_stdlib((j << 1) + ((long) i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long j) {
        if (-4611686018426999999L <= j && j < 4611686018427000000L) {
            return durationOfNanos(j);
        }
        return durationOfMillis(nanosToMillis(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long j) {
        if (-4611686018426L <= j && j < 4611686018427L) {
            return durationOfNanos(millisToNanos(j));
        }
        return durationOfMillis(RangesKt.coerceIn(j, -4611686018427387903L, 4611686018427387903L));
    }
}
